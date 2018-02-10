package com.mirror2.common.dao;

import com.google.gson.internal.Primitives;
import com.mirror2.csd.model.*;
import com.mirror2.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 2016-10-20 for qrf
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Version : 1.0
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CommonDAOImpl implements CommonDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Object model) {
        hibernateTemplate.persist(model);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean saveAll(List modelList) {
        /*modelList.forEach(model ->
                        CommonDAOImpl::save
        );*/
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Object model) {
        hibernateTemplate.merge(model);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean updateAll(List modelList) {
       /* modelList.stream().forEach(entity ->
                        hibernateTemplate.merge(entity)
        );*/
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Object model) {
        hibernateTemplate.delete(model);
        return true;
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id) {
        Object model = hibernateTemplate.get(clazz, id);
        return Primitives.wrap(clazz).cast(model);
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName, propertyValue));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, String propertyName1, Object propertyValue1, String propertyName2, Object propertyValue2) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName1, propertyValue1))
                .add(Restrictions.eq(propertyName2, propertyValue2));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> MODEL get(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .createAlias(aliasModel, "model2")
                .add(Restrictions.eq("model2." + propertyName, propertyValue));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public <MODEL> MODEL get(Class<MODEL> clazz, Long id, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("active", active));
        try {
            Object model = hibernateTemplate.findByCriteria(criteria).get(0);
            return Primitives.wrap(clazz).cast(model);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String[] idArray) {
        List<String> integerList = Arrays.asList(idArray);
        List<Long> longList = Arrays.asList(12L, 13L);// TODO integerList.stream().map(NumberUtils::toLong).collect(Collectors.toList());
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.in("id", longList));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Criteria executableCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().openSession());

        executableCriteria.setFirstResult(start);
        executableCriteria.setMaxResults(limit);
        return (List<MODEL>) executableCriteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, int start, int limit, List<String> projections) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        Criteria executableCriteria = criteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().openSession());

        if (!CollectionUtils.isEmpty(projections)) {
            ProjectionList projectionList = Projections.projectionList();
            for (String property : projections) {
                projectionList.add(Projections.property(property), property);
            }
            executableCriteria.setProjection(projectionList);
        }

        executableCriteria.setResultTransformer(Transformers.aliasToBean(clazz));
        executableCriteria.setFirstResult(start);
        executableCriteria.setMaxResults(limit);
        return (List<MODEL>) executableCriteria.list();

    }


    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("active", active));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq(propertyName, propertyValue));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, String aliasModel, String propertyName, Object propertyValue) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .createAlias(aliasModel, "model2")
                .add(Restrictions.eq("model2." + propertyName, propertyValue));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> findAll(Class<MODEL> clazz, Map<String, Object> propertyValueMap) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        for (String property : propertyValueMap.keySet()) {
            criteria.add(Restrictions.eq(property, propertyValueMap.get(property)));
        }
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    public Object findAll(DetachedCriteria dc) {
        return hibernateTemplate.findByCriteria(dc);
    }

    @Override
    public Object findAll(String sql, Object[] param) {
        if (ArrayUtils.isEmpty(param)) {
            return hibernateTemplate.find(sql);
        } else {
            return hibernateTemplate.find(sql, param);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> searchBy_name(Class<MODEL> clazz, String text) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.like("name", text, MatchMode.ANYWHERE));
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <MODEL> List<MODEL> searchBy_name_nameBN(Class<MODEL> clazz, String text) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.disjunction()
                                .add(
                                        Restrictions.or(
                                                Restrictions.like("name", text, MatchMode.ANYWHERE),
                                                Restrictions.like("nameBN", text, MatchMode.ANYWHERE)
                                        )
                                )
                );
        return (List<MODEL>) hibernateTemplate.findByCriteria(criteria);
    }


    @Override
    public Integer count(Class clazz) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .setProjection(Projections.rowCount());
        Object o = hibernateTemplate.findByCriteria(criteria).get(0);
        if (o == null) {
            return 0;
        } else {
            return Integer.valueOf(o.toString());
        }
    }

    @Override
    public Integer count(Class clazz, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("active", active))
                .setProjection(Projections.rowCount());
        Object o = hibernateTemplate.findByCriteria(criteria).get(0);
        if (o == null) {
            return 0;
        } else {
            return Integer.valueOf(o.toString());
        }
    }

    @Override
    public Object test() {
        Session session = sessionFactory.getCurrentSession();

        try {
            Customer c =  get(Customer.class, "CID", "DPL 000004");
            //c.setId(290L);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            LocalDate startDate = new LocalDate("2010-01-01");
            LocalDate endDate = new LocalDate("2018-12-31");
            //Date startDate = sdf.parse("01/01/2017");
            //Date endDate = sdf.parse("31/12/2017");


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
            for (LocalDate localDate = startDate; localDate.isBefore(endDate); localDate = localDate.plusDays(1)) {
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
                        save(dc);
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

    public static Date getEndOfDay(Date date) {
        if (date == null) return null;
        DateTime dt = new DateTime(date);
        /*
        // java 1.8
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);*/

        // Using Zoda-time
        dt = dt.millisOfDay().withMaximumValue();
        return dt.toDate();
    }

    public static Date getStartOfDay(Date date) {
        if (date == null) return null;
        /*
        // java 1.8
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
        */

        // Using Zoda-time
        DateTime dt = new DateTime(date);
        dt = dt.millisOfDay().withMinimumValue();
        return dt.toDate();
    }
}
