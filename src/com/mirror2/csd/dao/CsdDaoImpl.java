package com.mirror2.csd.dao;

import com.mirror2.common.model.Badge;
import com.mirror2.csd.model.*;
import com.mirror2.util.DateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CsdDaoImpl implements CsdDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<DelayCharge> calculateDelayCharge(Customer c, LocalDate from, LocalDate to) {

        Session session = sessionFactory.getCurrentSession();

        try {
            Criteria voucherC = session.createCriteria(Voucher.class)
                    .setProjection(Projections.projectionList()
                                    .add(Projections.groupProperty("voucherDate").as("VD"))
                                    .add(Projections.sum("amount").as("AMT"))
                    )
                    .add(Restrictions.eq("customer", c))
                            //.add(Restrictions.between("voucherDate", startDate.toDate(), endDate.toDate()))
                    .add(Restrictions.eq("status", "Approved"))
                    .addOrder(Order.asc("voucherDate"));
            List<Object[]> vrList = voucherC /*.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)*/.list();
            Map<Date, Double> vrMap = new HashMap<Date, Double>();
            for (Object[] oo : vrList) {
                vrMap.put((Date) oo[0], (Double) oo[1]);
            }


            Criteria mrC = session.createCriteria(MoneyReceipt.class)
                    .setProjection(Projections.projectionList()
                                    .add(Projections.groupProperty("cashDate").as("CD"))
                                    .add(Projections.sum("amount").as("AMT"))
                    )
                    .add(Restrictions.eq("customer", c))
                            //.add(Restrictions.between("cashDate", startDate.toDate(), endDate.toDate()))
                    .add(Restrictions.eq("status", "Passed"))
                    .addOrder(Order.asc("cashDate"));
            List<Object[]> mrList = mrC /*.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)*/.list();
            Map<Date, Double> mrMap = new HashMap<Date, Double>();
            for (Object[] oo : mrList) {
                mrMap.put((Date) oo[0], (Double) oo[1]);
            }


            // ===================================================
            Criteria instC = session.createCriteria(Installment.class)
                    .setProjection(Projections.projectionList()
                                    .add(Projections.groupProperty("deadLine"))
                                    .add(Projections.sum("amount"))
                    )
                    .add(Restrictions.eq("customer", c))
                            //.add(Restrictions.between("voucherDate", startDate.toDate(), endDate.toDate()))
                    .addOrder(Order.asc("deadLine"));
            List<Object[]> instList = instC /*.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)*/.list();
            Map<Date, Double> instMap = new HashMap<Date, Double>();
            for (Object[] oo : instList) {
                instMap.put((Date) oo[0], (Double) oo[1]);
            }
            Criteria opC = session.createCriteria(OtherPayment.class)
                    .setProjection(Projections.projectionList()
                                    .add(Projections.groupProperty("deadLine"))
                                    .add(Projections.sum("amount"))
                    )
                    .add(Restrictions.eq("customer", c))
                            //.add(Restrictions.between("voucherDate", startDate.toDate(), endDate.toDate()))
                    .addOrder(Order.asc("deadLine"));
            List<Object[]> opList = opC /*.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)*/.list();
            Map<Date, Double> opMap = new HashMap<Date, Double>();
            for (Object[] oo : opList) {
                opMap.put((Date) oo[0], (Double) oo[1]);
            }


            //            Map<LocalDate, Map<String, Object>> mapMap = new HashMap<LocalDate, Map<String, Object>>();
            Map<String, Object> map;
            //SortedMap<LocalDate, Map<String, Object>> treemapMap = new TreeMap<LocalDate, Map<String, Object>>();
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            Double balance = 0.0;
            Double payable = 0.0;
            Double delayCharge = 0.0;
            Double monthlyCharge = 0.0;

            DateTimeFormatter MMyyy = DateTimeFormat.forPattern("MMyy");
            DateTimeFormatter MMMMyyyy = DateTimeFormat.forPattern("MMMM, yyyy");
            // DateTime primerDiaDelMes = new DateTime().dayOfMonth().withMinimumValue();
            List<DelayCharge> dcList = new ArrayList<DelayCharge>();
            int totalDays = 0;
            for (LocalDate localDate = from; localDate.isBefore(to); localDate = localDate.plusDays(1)) {
                if (localDate.toString().startsWith("-04-30")) {
                    System.out.println("");
                    //DateUtil.getTotalDaysOfMonth()
                }
                Date date = localDate.toDate();
                if (totalDays == 0) {
                    totalDays = DateUtil.getTotalDaysOfMonth(new LocalDate(date).toString(MMyyy));
                    monthlyCharge = 0.0;
                }

                Double mrAmt = mrMap.get(date);
                mrAmt = mrAmt == null ? 0.0 : mrAmt;
                Double vrAmt = vrMap.get(date);
                vrAmt = vrAmt == null ? 0.0 : vrAmt;
                map = new HashMap<String, Object>();
                map.put("DATE", date);
                Double dayBalance = mrAmt + vrAmt;
                balance += dayBalance;
                map.put("BALANCE", balance);

                Double payableOpAmt = opMap.get(date);
                payableOpAmt = payableOpAmt == null ? 0.0 : payableOpAmt;
                Double payableInstAmt = instMap.get(date);
                payableInstAmt = payableInstAmt == null ? 0.0 : payableInstAmt;
                Double dayPayable = payableOpAmt + payableInstAmt;
                payable += dayPayable;
                map.put("PAYABLE", payable);

                Double overdue = payable - balance;
                Double dayCharge = 0.0;
                if (overdue > 0) {
                    dayCharge = overdue * 0.001;
                    delayCharge += dayCharge;
                    monthlyCharge += dayCharge;
                }
                map.put("D_CHARGE", dayCharge);
                map.put("D_CHARGE_BAL", dayCharge);
                if (dayCharge > 0) {
                    dataList.add(map);
                }

                totalDays--;
                if (totalDays == 0) { // last day of month
                    if (monthlyCharge > 0) {
                        DelayCharge dc = new DelayCharge();
                        dc.setCustomer(c);
                        dc.setAmount(monthlyCharge);
                        dc.setChargeOf(new LocalDate(date).toString(MMMMyyyy));
                        dc.setDeadLine(localDate.plusDays(10).toDate());
                        dcList.add(dc);
                        //session.save(dc);
                    }
                }
            }
            System.out.println(dataList.size());
            System.out.println(dcList.size());
            return dcList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("session.toString() = " + session.toString());
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> getAssignedBadgeIdList(Long customerId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Badge.class)
                .createAlias("customerList", "Cust")
                .setProjection(Projections.property("id"))
                .add(Restrictions.eq("Cust.id", customerId));
        return (List<Long>) hibernateTemplate.findByCriteria(criteria);
    }

}
