package com.mirror2.report.dao;

import com.mirror2.csd.model.MID;
import com.mirror2.util.DateUtil;
import com.mirror2.util.MirrorConstants;
import com.mirror2.util.MirrorUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 03/03/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 03/03/16
 * Version : 1.0
 */

@Repository
//@Transactional
public class ReportDAOImpl implements ReportDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public List<Map<String, Object>> getCollectionDataAllYear() {
        int thisYear = Integer.parseInt(DateUtil.getCurrentYear().substring(2, 4));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 11; i <= thisYear; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("year", "20" + i);
            Date start = DateUtil.getFirstDateByMMYY("01" + i);
            Date end = DateUtil.getFirstDateByMMYY("12" + i);
            Object[] params = new Object[]{start, end};

            Long otBooking = (Long) hibernateTemplate.find("select count(c) from Customer c " +
                    "where status != 'Refunded' and paymentType = '" + MirrorConstants.PAYMENT_TYPE_OT + "' and bookingDate between ? and ?", params).get(0);
            Double otTotalDP = (Double) hibernateTemplate.find("select sum(amount) from OtherPayment " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_OT + "' and customer.bookingDate between ? and ?", params).get(0);
            Double otPayableInst = 0.0;
            Double otPayableDP = (Double) hibernateTemplate.find("select sum(amount) from OtherPayment inst " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_OT + "' and deadLine between ? and ?", params).get(0);
            Double otCollected = (Double) hibernateTemplate.find("select sum(amount) from MoneyReceipt " +
                    "where customer.status != 'Refunded' and (receiptDate between ? and ?) and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_OT + "'", params).get(0);
            map.put("otBooking", otBooking == null ? 0.0 : otBooking);
            map.put("otSalesValue", otTotalDP);
            map.put("otPayableInst", otPayableInst);
            map.put("otPayableDP", otPayableDP == null ? 0.0 : otPayableDP);
            map.put("otCollected", otCollected == null ? 0.0 : otCollected);


            Long instBooking = (Long) hibernateTemplate.find("select count(c) from Customer c " +
                    "where c.status != 'Refunded' and paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "' and bookingDate between ? and ?", params).get(0);
            Double instTotalInstAmt = (Double) hibernateTemplate.find("select sum(amount) from Installment " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "' and customer.bookingDate between ? and ?", params).get(0);
            Double instTotalDPAmt = (Double) hibernateTemplate.find("select sum(amount) from OtherPayment " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "' and customer.bookingDate between ? and ?", params).get(0);
            Double instPayableInstAmt = (Double) hibernateTemplate.find("select sum(amount) from Installment inst " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "' and deadLine between ? and ?", params).get(0);

            Double instPayableDPAmt = (Double) hibernateTemplate.find("select sum(amount) from OtherPayment " +
                    "where customer.status != 'Refunded' and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "' and deadLine between ? and ?", params).get(0);

            Double instCollected = (Double) hibernateTemplate.find("select sum(amount) from MoneyReceipt " +
                    "where customer.status != 'Refunded' and (receiptDate between ? and ?) and customer.paymentType = '" + MirrorConstants.PAYMENT_TYPE_INST + "'", params).get(0);
            map.put("instBooking", instBooking == null ? 0.0 : instBooking);
            map.put("instSalesValue", instTotalInstAmt + instTotalDPAmt);
            map.put("instPayableDP", instPayableDPAmt == null ? 0.0 : instPayableDPAmt);
            map.put("instPayableInst", instPayableInstAmt == null ? 0.0 : instPayableInstAmt);
            map.put("instCollected", instCollected == null ? 0.0 : instCollected);
            list.add(map);

        }
        return list;


    }

    @Override
    @SuppressWarnings("unchecked")
    public List getSalesByYear(Date from, Date to) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT YEAR(booking_date), COUNT(*) " +
                            //", GROUP_CONCAT(id SEPARATOR ',') " +
                            "FROM csd_customer csdc WHERE STATUS != 'Refunded' " +
                            //"AND booking_date BETWEEN '2011-01-01' AND '2015-12-31' " +
                            "GROUP BY YEAR(booking_date) ORDER BY YEAR(booking_date) ASC"
            );
            List<Object[]> results = query.list();

            Map<String, Object> resultMap;
            for (Object[] objects : results) {
                resultMap = new HashMap<String, Object>();
                Integer year = (Integer) objects[0];
                resultMap.put("Year", objects[0]);
                resultMap.put("Sales", ((BigInteger) objects[1]).intValue());
                query = session.createSQLQuery(
                        "SELECT id, moneyDisburse_id " +
                                "FROM csd_customer csdc WHERE STATUS != 'Refunded' " +
                                "AND booking_date " +
                                "BETWEEN '" + year + "-01-01' AND '" + year + "-12-31' "
                );
                //query.addEntity(Employee.class);
                List<Object[]> results2 = query.list();
                List<String> idList = new ArrayList<String>(results2.size());
                List<String> disburseIdList = new ArrayList<String>(results2.size());
                for (Object[] objects1 : results2) {
                    idList.add((objects1[0]).toString());
                    disburseIdList.add((objects1[1]).toString());
                }
                String ids = StringUtils.join(idList, ", ");
                String disburseIds = StringUtils.join(disburseIdList, ", ");
                resultMap.put("ids", ids);
                resultMap.put("disburseIds", disburseIds);
                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_other_payments WHERE customer_id " +
                                "IN (" + ids + ")"
                );
                Double OP = ((List<Double>) query.list()).get(0);
                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_installment WHERE customer_id " +
                                "IN (" + ids + ")"
                );
                Double inst = ((List<Double>) query.list()).get(0);
                resultMap.put("SalesValue", OP + inst);

                //SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '2015-01-01' AND '2015-12-31';
                //SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '2015-01-01' AND '2015-12-31';
                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '" + year + "-01-01' AND '" + year + "-12-31' AND status = 'Passed'"
                );
                Double mrAmt = ((List<Double>) query.list()).get(0);
                mrAmt = mrAmt == null ? 0.0 : mrAmt;
                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN  '" + year + "-01-01' AND '" + year + "-12-31' AND  status = 'Approved'"
                );
                Double vrAmt = ((List<Double>) query.list()).get(0);
                vrAmt = vrAmt == null ? 0.0 : vrAmt;

                resultMap.put("Collected", mrAmt + vrAmt);

                //SELECT SUM(amount) FROM csd_installment WHERE customer_id IN ();
                returnResult.add(resultMap);
            }
            return returnResult;

        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public List getSalesByMonth(Date from, Date to) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT YEAR(booking_date) YEAR, MONTH(booking_date) MONTH, COUNT(*) sales  FROM csd_customer\n" +
                            "GROUP BY YEAR(booking_date), MONTH(booking_date)"
            );
            //query.addEntity(Employee.class);
            List<Object[]> results = query.list();

            Map<String, Object> resultMap;
            for (Object[] objects : results) {
                resultMap = new HashMap<String, Object>();
                Integer year = (Integer) objects[0];
                Integer monthSl = (Integer) objects[1];
                String monthName = DateUtil.getNameOfMonth(monthSl);
                Integer sales = ((BigInteger) objects[2]).intValue();
                resultMap.put("Year", year);
                resultMap.put("Month", monthName);
                resultMap.put("Sales", sales);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date firstDate = DateUtil.getFirstDateByMMYY(String.format("%02d", monthSl) + year.toString().substring(2, 4));
                Date lastDate = DateUtil.getLastDateByMMYY(String.format("%02d", monthSl) + year.toString().substring(2, 4));
                String firstDateString = sdf.format(firstDate);
                String lastDateString = sdf.format(lastDate);

                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '" + firstDateString + "' AND '" + lastDateString + "' AND status = 'Passed'"
                );
                Double mrAmt = ((List<Double>) query.list()).get(0);
                mrAmt = mrAmt == null ? 0.0 : mrAmt;

                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '" + firstDateString + "' AND '" + lastDateString + "' AND  status = 'Approved'"
                );
                Double vrAmt = ((List<Double>) query.list()).get(0);
                vrAmt = vrAmt == null ? 0.0 : vrAmt;

                resultMap.put("Collected", mrAmt + vrAmt);

                returnResult.add(resultMap);
            }
            return returnResult;

        } catch (Exception e) {
            return list;
        }
    }


    @Override
    public List getSalesBySalesPersonType(Date from, Date to) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT DISTINCT TYPE FROM csd_marketing_identity"
            );
            List<String> results = query.list();

            Map<String, Object> resultMap;
            for (String type : results) {
                resultMap = new HashMap<String, Object>();
                resultMap.put("Type", type);
                query = session.createSQLQuery("SELECT id FROM csd_marketing_identity csd_mi WHERE csd_mi.type = '" + type + "'");
                List<BigInteger> results2 = query.list();
                List<String> midList = new ArrayList<String>(results2.size());
                for (BigInteger objects1 : results2) {
                    midList.add((objects1).toString());
                }
                String mids = StringUtils.join(midList, ", ");
                resultMap.put("mids", mids);

                query = session.createSQLQuery("SELECT id FROM csd_customer WHERE referenceBy_id IN (" + mids + ") AND status != 'Refunded'");
                List<BigInteger> results3 = query.list();
                resultMap.put("Sales", results3.size());
                List<String> custidList = new ArrayList<String>(results3.size());
                for (BigInteger id : results3) {
                    custidList.add((id).toString());
                }
                String custids = StringUtils.join(custidList, ", ");
                resultMap.put("custids", custids);


                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_other_payments WHERE customer_id " +
                                "IN (" + custids + ")"
                );
                Double OP = ((List<Double>) query.list()).get(0);
                query = session.createSQLQuery(
                        "SELECT SUM(amount) FROM csd_installment WHERE customer_id " +
                                "IN (" + custids + ")"
                );
                Double inst = ((List<Double>) query.list()).get(0);
                resultMap.put("SalesValue", OP + inst);

                //SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '2015-01-01' AND '2015-12-31';
                //SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '2015-01-01' AND '2015-12-31';
                query = session.createSQLQuery("SELECT SUM(amount) FROM csd_money_receipt WHERE customer_id IN (" + custids + ") AND status = 'Passed'");
                Double mrAmt = ((List<Double>) query.list()).get(0);
                mrAmt = mrAmt == null ? 0.0 : mrAmt;
                query = session.createSQLQuery("SELECT SUM(amount) FROM csd_voucher WHERE customer_id IN (" + custids + ") AND  status = 'Approved'");
                Double vrAmt = ((List<Double>) query.list()).get(0);
                vrAmt = vrAmt == null ? 0.0 : vrAmt;

                resultMap.put("Collected", mrAmt + vrAmt);

                //SELECT SUM(amount) FROM csd_installment WHERE customer_id IN ();
                returnResult.add(resultMap);
            }
            return returnResult;

        } catch (Exception e) {
            return list;
        }
    }

    @Override
    public List getSalesBySalesPerson(Date from, Date to, String type, int limit) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query;
            List<MID> midList;
            if (type.equals("") || type.equals("All") || type.toUpperCase().equals("ALL")) {
                midList = hibernateTemplate.find("from MID  ORDER BY mid");
            } else {
                Object[] param = new Object[1];
                param[0] = type;
                midList = hibernateTemplate.find("from MID m where m.type = ?  ORDER BY m.mid", param);
            }

            Map<String, Object> resultMap;
            for (MID identity : midList) {
                resultMap = new HashMap<String, Object>();
                resultMap.put("Identity", identity);
                List<String> custidList = identity.getReferedCutomerIdList("Refunded");
                resultMap.put("Sales", custidList.size());
                String custids = StringUtils.join(custidList, ", ");
                resultMap.put("custids", custids);

                Double collected = 0.0;
                Double salesValue = 0.0;
                if (custidList.size() > 0) {
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_other_payments WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double otherPayAmt = ((List<Double>) query.list()).get(0);
                    otherPayAmt = otherPayAmt == null ? 0.0 : otherPayAmt;
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_installment WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double instAmt = ((List<Double>) query.list()).get(0);
                    instAmt = instAmt == null ? 0.0 : instAmt;
                    salesValue = otherPayAmt + instAmt;

                    //SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '2015-01-01' AND '2015-12-31';
                    //SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '2015-01-01' AND '2015-12-31';
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_money_receipt WHERE customer_id IN (" + custids + ") AND status = 'Passed'");
                    Double mrAmt = ((List<Double>) query.list()).get(0);
                    mrAmt = mrAmt == null ? 0.0 : mrAmt;
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_voucher WHERE customer_id IN (" + custids + ") AND  status = 'Approved'");
                    Double vrAmt = ((List<Double>) query.list()).get(0);
                    vrAmt = vrAmt == null ? 0.0 : vrAmt;
                    collected = mrAmt + vrAmt;
                }
                resultMap.put("SalesValue", salesValue);
                resultMap.put("Collected", collected);
                //SELECT SUM(amount) FROM csd_installment WHERE customer_id IN ();
                returnResult.add(resultMap);
            }
            return returnResult;

        } catch (Exception e) {
            return list;
        }
    }


    @Override
    public List getSalesByBOD(Date from, Date to) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query;
            List<MID> identityList = hibernateTemplate.find("from MID  where designation = 'BOD'");

            Map<String, Object> resultMap;
            for (MID identity : identityList) {
                resultMap = new HashMap<String, Object>();
                resultMap.put("Identity", identity);
                List<String> custidList = identity.getReferedCutomerIdListBetweenDate("Refunded", from, to);
                resultMap.put("Sales", custidList.size());
                String custids = StringUtils.join(custidList, ", ");
                resultMap.put("custids", custids);

                Double collected = 0.0;
                Double salesValue = 0.0;
                if (custidList.size() > 0) {
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_other_payments WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double otherPayAmt = ((List<Double>) query.list()).get(0);
                    otherPayAmt = otherPayAmt == null ? 0.0 : otherPayAmt;
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_installment WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double instAmt = ((List<Double>) query.list()).get(0);
                    instAmt = instAmt == null ? 0.0 : instAmt;
                    salesValue = otherPayAmt + instAmt;

                    //SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '2015-01-01' AND '2015-12-31';
                    //SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '2015-01-01' AND '2015-12-31';
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_money_receipt WHERE customer_id IN (" + custids + ") AND status = 'Passed'");
                    Double mrAmt = ((List<Double>) query.list()).get(0);
                    mrAmt = mrAmt == null ? 0.0 : mrAmt;
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_voucher WHERE customer_id IN (" + custids + ") AND  status = 'Approved'");
                    Double vrAmt = ((List<Double>) query.list()).get(0);
                    vrAmt = vrAmt == null ? 0.0 : vrAmt;
                    collected = mrAmt + vrAmt;
                }
                resultMap.put("SalesValue", salesValue);
                resultMap.put("Collected", collected);
                //SELECT SUM(amount) FROM csd_installment WHERE customer_id IN ();
                returnResult.add(resultMap);
            }
            return returnResult;

        } catch (Exception e) {
            return list;
        }
    }


    @Override
    public void getSalesBySalesPersonExcel(Date from, Date to, String type, int limit) {
        List<Map<String, Object>> returnResult = new ArrayList<Map<String, Object>>();
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {
            SQLQuery query;
            List<MID> midList;
            if (type.equals("") || type.equals("All") || type.toUpperCase().equals("ALL")) {
                midList = hibernateTemplate.find("from MID  ORDER BY mid");
            } else {
                Object[] param = new Object[1];
                param[0] = type;
                midList = hibernateTemplate.find("from MID m where m.type = ?  ORDER BY m.mid", param);
            }

            Map<String, Object> resultMap;
            for (MID identity : midList) {
                resultMap = new HashMap<String, Object>();
                resultMap.put("Identity", identity);
                List<String> custidList = identity.getReferedCutomerIdList("Refunded");
                resultMap.put("Sales", custidList.size());
                String custids = StringUtils.join(custidList, ", ");
                resultMap.put("custids", custids);

                Double collected = 0.0;
                Double salesValue = 0.0;
                if (custidList.size() > 0) {
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_other_payments WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double otherPayAmt = ((List<Double>) query.list()).get(0);
                    otherPayAmt = otherPayAmt == null ? 0.0 : otherPayAmt;
                    query = session.createSQLQuery(
                            "SELECT SUM(amount) FROM csd_installment WHERE customer_id " +
                                    "IN (" + custids + ")"
                    );
                    Double instAmt = ((List<Double>) query.list()).get(0);
                    instAmt = instAmt == null ? 0.0 : instAmt;
                    salesValue = otherPayAmt + instAmt;

                    //SELECT SUM(amount) FROM csd_money_receipt WHERE cash_date BETWEEN '2015-01-01' AND '2015-12-31';
                    //SELECT SUM(amount) FROM csd_voucher WHERE voucher_date BETWEEN '2015-01-01' AND '2015-12-31';
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_money_receipt WHERE customer_id IN (" + custids + ") AND status = 'Passed'");
                    Double mrAmt = ((List<Double>) query.list()).get(0);
                    mrAmt = mrAmt == null ? 0.0 : mrAmt;
                    query = session.createSQLQuery("SELECT SUM(amount) FROM csd_voucher WHERE customer_id IN (" + custids + ") AND  status = 'Approved'");
                    Double vrAmt = ((List<Double>) query.list()).get(0);
                    vrAmt = vrAmt == null ? 0.0 : vrAmt;
                    collected = mrAmt + vrAmt;
                }
                resultMap.put("SalesValue", salesValue);
                resultMap.put("Collected", collected);
                //SELECT SUM(amount) FROM csd_installment WHERE customer_id IN ();
                returnResult.add(resultMap);
            }

            try {
                WritableWorkbook workbook = Workbook.createWorkbook(new File("F:\\sales_by_sales_person.xls"));
                WritableSheet sheet = workbook.createSheet("First Sheet", 0);

                sheet.addCell(new Label(0, 0, "SL"));
                sheet.addCell(new Label(1, 0, "MID"));
                sheet.addCell(new Label(2, 0, "Name"));
                sheet.addCell(new Label(3, 0, "Sales"));
                sheet.addCell(new Label(4, 0, "Sales Value"));
                sheet.addCell(new Label(5, 0, "Net Collected (Crore)"));
                int row = 1;
                for (Map<String, Object> map : returnResult) {
                    Label sl = new Label(0, row, row + "");
                    sheet.addCell(sl);
                    MID identity = (MID) map.get("Identity");
                    Label name = new Label(1, row, (identity.getName()));
                    sheet.addCell(name);
                    Label mid = new Label(2, row, (identity.getMid()));
                    sheet.addCell(mid);
                    Label sales = new Label(3, row, (map.get("Sales")).toString());
                    sheet.addCell(sales);
                    Label salesValue = new Label(4, row, MirrorUtil.toJasperAmt(((Double) map.get("SalesValue"))));
                    sheet.addCell(salesValue);
                    Label netCollection = new Label(5, row, MirrorUtil.toJasperAmt(((Double) map.get("Collected"))));
                    sheet.addCell(netCollection);
                    row++;
                }

                workbook.write();
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

        }
    }

}
