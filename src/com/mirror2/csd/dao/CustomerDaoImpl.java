package com.mirror2.csd.dao;

import com.mirror2.csd.bean.CustomerSearchBean;
import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.MID;
import com.mirror2.csd.model.Offer;
import com.mirror2.csd.model.Voucher;
import com.mirror2.util.TheDates;
import org.apache.commons.math3.util.Precision;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public class CustomerDaoImpl implements CustomerDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Customer customer) {
        try {
            hibernateTemplate.persist(customer);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Customer customer) {
        hibernateTemplate.merge(customer);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Customer> findAllCustomer() {
        return hibernateTemplate.find("from Customer order by CID");
        //return null;
    }

    @Override
    public Customer getCustomer(Long id) {
        Customer customer = hibernateTemplate.get(Customer.class, id);
        return setTransientProperty(customer);
    }

    @Override
    public Customer getCustomer(String cid) {
        Object[] paramArr = new Object[1];
        paramArr[0] = new String(cid);
        try {
            //getSalesDataSalesPersonWise(null, null);
            Customer customer = (Customer) hibernateTemplate.find("from Customer where CID=?", paramArr).get(0);
            return setTransientProperty(customer);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getSalesDataSalesPersonWise(String type, Date from, Date to) {
        Object[] paramArr = new Object[]{from, to, type, from, to};
        try {
            return hibernateTemplate.find("select cust.referenceBy.name, count(cust), " +
                    "(select sum(mr.amount) from MoneyReceipt mr where cust.referenceBy.id = mr.customer.referenceBy.id and mr.status = 'Passed'), " +
                    "(select sum(mr.amount) from MoneyReceipt mr where cust.referenceBy.id = mr.customer.referenceBy.id and mr.cashDate between ? and ? and mr.status = 'Passed') " +
                    " from Customer cust where cust.status = 'Approved' and  cust.referenceBy.type = ?  and cust.bookingDate between ? and ? group by cust.referenceBy.name order by count(cust) desc ", paramArr);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getSalesDataDetailsSalesPersonWise(String type, Date from, Date to) {
        Object[] paramArr = new Object[]{from, to, type, from, to};
        try {
            return hibernateTemplate.find("select cust.referenceBy, count(cust), " +
                    "(select sum(mr.amount) from MoneyReceipt mr where cust.referenceBy.id = mr.customer.referenceBy.id and mr.status = 'Passed'), " +
                    "(select sum(mr.amount) from MoneyReceipt mr where cust.referenceBy.id = mr.customer.referenceBy.id and mr.cashDate between ? and ? and mr.status = 'Passed') " +
                    " from Customer cust where cust.status = 'Approved' and  cust.referenceBy.type = ?  and cust.bookingDate between ? and ? group by cust.referenceBy.name order by count(cust) desc ", paramArr);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Customer setTransientProperty(Customer customer) {
        List<Object[]> list;
        Query queryInstTotal = null;
        Integer totInst = 0;
        Double totInstAmt = 0.0;
        Integer recoverableInst = 0;
        Double paidInst = 0.0;
        Double totDp = 0.0;
        Double recoverableDp = 0.0;
        Double totSp = 0.0;
        Double recoverableSp = 0.0;
        Double totCp = 0.0;
        Double recoverableCp = 0.0;


        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        try {         //count(select inst2 from  Installment inst2 where inst2.deadLine < :today)
            queryInstTotal = session.createQuery("select count(inst.id), sum(inst.amount) from Installment inst  where customer = :customer");
            queryInstTotal.setParameter("customer", customer);

            list = queryInstTotal.list();
            totInst = ((Long) (list.get(0))[0]).intValue();
            totInstAmt = ((Double) (list.get(0))[1]);


            queryInstTotal = session.createQuery("select count(inst.id) from Installment inst  where deadLine < :today and customer = :customer");
            queryInstTotal.setParameter("customer", customer);
            queryInstTotal.setParameter("today", new Date());
            List<Long> list2 = queryInstTotal.list();
            recoverableInst = (list2.get(0)).intValue();

            Double instAmt = totInstAmt / totInst;
            paidInst = customer.getMoneyDisburse().getInstallment() / instAmt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        customer.setTotalInst(totInst);
        customer.setTotalInstAmt(totInstAmt);
        customer.setRecoverableInst(recoverableInst);
        customer.setPaidInst(Precision.round(paidInst, 2));
        customer.setDueInst(Precision.round(recoverableInst - paidInst, 2));

        return customer;
    }


    @Override
    public List<Customer> getAllCustomerByName(String name) {
        Object[] paramArr = new Object[1];
        paramArr[0] = "%" + name + "%";
        try {
            return hibernateTemplate.find("from Customer where name like ?", paramArr);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public Integer getTotalCustomerByOffer(Offer offer) {
        Object[] paramArr = new Object[2];
        paramArr[0] = offer;
        paramArr[1] = new String("Refunded");
        return hibernateTemplate.find("from Customer where offer=? and status!=? ", paramArr).size();
    }

    @Override
    public Integer getTotalCustomerByMID(MID mid) {
        Object[] paramArr = new Object[2];
        paramArr[0] = mid;
        paramArr[1] = new String("Refunded");
        return hibernateTemplate.find("from Customer where referenceBy=? and status!=? ", paramArr).size();
    }

    @Override
    public List<Customer> getCustomerListByStatus(String status) {
        Object[] paramArr = new Object[1];
        paramArr[0] = status;
        return hibernateTemplate.find("from Customer where status=?", paramArr);
    }

    @Override
    public List<Customer> getCustomerListByBookingDate(String status, Date from, Date to) {
        Object[] paramArr = new Object[3];
        paramArr[0] = status;
        paramArr[1] = from;
        paramArr[2] = to;
        return hibernateTemplate.find("from Customer where status=? and (bookingDate between ? and ?)", paramArr);
    }

    @Override
    public List<Customer> getCustomerListByBookingDate(Offer offer, Date from, Date to) {
        Object[] paramArr = new Object[3];
        paramArr[0] = offer;
        paramArr[1] = from;
        paramArr[2] = to;
        return hibernateTemplate.find("from Customer where offer=? and (bookingDate between ? and ?)", paramArr);
    }

    @Override
    public List<Customer> searchCustomer(CustomerSearchBean searchBean) {
        Object[] paramArr = new Object[2];
        paramArr[0] = searchBean.getReferenceName();
        paramArr[1] = searchBean.getReferenceName();
        try {
            return hibernateTemplate.find("from Customer cust where cust.referenceBy.name like ? or  cust.referenceBy.mid like ?", paramArr);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }


    @Override
    public List getCollectionReport(String from, String to) {
        Object[] paramArr = new Object[2];
        paramArr[0] = from;
        paramArr[1] = to;
        //return (Double) hibernateTemplate.find("SUM(amount) FROM csd_money_receipt r WHERE customer_id=136 AND STATUS='Passed' AND cash_date BETWEEN ? AND ?;)", paramArr);
/*
        String hql = "SELECT c.CID, c.name," +
                " (SELECT SUM(r.amount) FROM MoneyReceipt" +
                " WHERE r.customer = c AND r.status='Passed'" +
                " AND r.cashDate BETWEEN '2010-09-04' AND '2015-11-04')" +
                " FROM Customer c" +
                " INNER JOIN MoneyReceipt r";*/

        // String hql = "select c.name from Customer c";
        //String hql = "select c.name, sum(mr.amount) from MoneyReceipt mr right outer join mr.customer c";
        String sql = "SELECT c.customer_id, c.name, c.id, " +
                "( SELECT SUM(r.amount) FROM csd_money_receipt r " +
                "WHERE r.customer_id = c.id " +
                "AND r.status='Passed'" +
                "AND r.cash_date BETWEEN '" + from + "' AND '" + to + "' " +
                "), " +
                "( SELECT CASE WHEN v.type='adjustment' " +
                "THEN SUM(v.amount) END FROM csd_voucher v " +
                "WHERE v.customer_id = c.id AND v.voucher_date " +
                "BETWEEN '" + from + "' AND '" + to + "' " +
                "), " +
                "(SELECT CASE WHEN v.type='withdraw' " +
                "THEN SUM(v.amount) END FROM csd_voucher v " +
                "WHERE v.customer_id = c.id AND v.voucher_date " +
                "BETWEEN '" + from + "' AND '" + to + "' " +
                ")" +
                "FROM csd_customer c " +
                //"WHERE c.status != 'Refunded'" +
                "ORDER BY c.customer_id";

        // select sum(VR.amount) from Voucher VR right outer join VR.customer
        //Query query = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(sql)
        //.addEntity(Customer.class);
        SQLQuery query = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(sql);
        //query.setParameter("mr_status","Passed");
        //query.setParameter("date_from", new TheDates().getFirstDateByMMYY("0115"));
        //query.setParameter("date_to",new TheDates().getLastDateByMMYY("1215"));
        List list = query.list();
        return list;
    }

    /*
    Working code -
    String hql = "select C.CID, C.name," +
                " sum(MR.amount)" +
                " from MoneyReceipt MR right outer join MR.customer C" +
                " where MR.status= :mr_status" +
                " and MR.cashDate between :date_from and :date_to" +
                " group by C.CID";*/

    /*
    * select C.id as id, C.firstName as firstName, C.lastName as lastName,
  C.address as address, C.city as city, count(O.id) as totalOrders
from Order O right outer join O.customer C
group by C.id*/
}

/*
try{
        List<Customer> customers = hibernateTemplate.find(" from Customer  as aut where  aut.CID="+ cid +"'");
            if(customers.size()> 0){
                return customers.get(0);
            }
            else {
                return null;
            }
        }
        catch (Exception ex){
            return null;
        }

 */
