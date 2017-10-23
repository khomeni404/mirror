package com.mirror2.mis.dao;

import com.mirror2.csd.model.Customer;
import com.mirror2.csd.model.Installment;
import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.mis.bean.SearchBean;
import com.mirror2.util.DateUtil;
import com.mirror2.util.MirrorDataList;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Khomeni on 23-Feb-17.
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MisDaoImpl implements MisDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getCustomerData(Long offerId) {
        if (offerId == null) {
            Object[] param = new Object[]{MirrorDataList.CUST_STATUS_CANCELLED, MirrorDataList.CUST_STATUS_REFUNDED};
            return hibernateTemplate.find("select CID, name, bookingDate, floorSize, building.nameAlias, " +
                    "AID, referenceBy.mid,  c.offer.offerName  from Customer c where c.status not in(?, ?) order by c.CID", param);
        } else {
            Object[] param = new Object[]{offerId, MirrorDataList.CUST_STATUS_CANCELLED, MirrorDataList.CUST_STATUS_REFUNDED};
            return hibernateTemplate.find("select CID, name, bookingDate, floorSize, building.nameAlias, " +
                    "AID, referenceBy.mid,  c.offer.offerName  from Customer c where c.offer.id = ? " +
                    " and  c.status not in(?, ?) " +
                    "order by c.CID", param);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getCustomerDataListMap(SearchBean searchBean) {
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .createAlias("building", "b")
                .setProjection(Projections.projectionList()
                                .add(Projections.property("id"), "ID")
                                .add(Projections.property("CID").as("CID"))
                                .add(Projections.property("name").as("NAME"))
                                .add(Projections.property("AID").as("AID"))
                                .add(Projections.property("handoverDate").as("HOD"))
                                .add(Projections.property("b.nameAlias").as("B_AL"))
                                .add(Projections.property("floorSize").as("SIZE"))
                );
        if (!GenericValidator.isBlankOrNull(searchBean.getNotStatus())) {
            dc.add(Restrictions.ne("status", searchBean.getNotStatus()));
        }
        String handoverYear = searchBean.getHandoverYear();
        if (!GenericValidator.isBlankOrNull(handoverYear)) {
            dc.add(Restrictions.eq("b.handOver", handoverYear));
        }
        dc.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return hibernateTemplate.findByCriteria(dc);
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Double> getCustomersPayableInstAmtMap(SearchBean searchBean) {
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class).createAlias("installments", "i")
                .createAlias("building", "b");
        dc.setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("id"), "ID")
                        .add(Projections.sum("i.amount"), "AMT")
        );
        dc.add(Restrictions.le("i.deadLine", new Date()));
        if (!GenericValidator.isBlankOrNull(searchBean.getNotStatus())) {
            dc.add(Restrictions.ne("status", searchBean.getNotStatus()));
        }
        String handoverYear = searchBean.getHandoverYear();
        if (!GenericValidator.isBlankOrNull(handoverYear)) {
            dc.add(Restrictions.eq("b.handOver", handoverYear));
        }
        dc.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> data = hibernateTemplate.findByCriteria(dc);
        Map<Long, Double> map = new HashMap<Long, Double>();
        for (Map<String, Object> m : data) {
            map.put((Long) m.get("ID"), (Double) m.get("AMT"));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Double> getCustomersPayableOPAmtMap(SearchBean searchBean) {
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class).createAlias("otherPayments", "op")
                .createAlias("building", "b");
        dc.setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("id"), "ID")
                        .add(Projections.sum("op.amount"), "AMT")
        );
        dc.add(Restrictions.le("op.deadLine", new Date()));
        if (!GenericValidator.isBlankOrNull(searchBean.getNotStatus())) {
            dc.add(Restrictions.ne("status", searchBean.getNotStatus()));
        }
        String handoverYear = searchBean.getHandoverYear();
        if (!GenericValidator.isBlankOrNull(handoverYear)) {
            dc.add(Restrictions.eq("b.handOver", handoverYear));
        }
        dc.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> data = hibernateTemplate.findByCriteria(dc);
        Map<Long, Double> map = new HashMap<Long, Double>();
        for (Map<String, Object> m : data) {
            map.put((Long) m.get("ID"), (Double) m.get("AMT"));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Double> getCustomersPaidInstAmtMap(SearchBean searchBean) {
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class).createAlias("moneyReceipts", "r")
                .createAlias("building", "b");
        dc.setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("id"), "ID")
                        .add(Projections.sum("r.amount"), "AMT")
        );
        //dc.add(Restrictions.le("i.deadLine", new Date()));
        if (!GenericValidator.isBlankOrNull(searchBean.getNotStatus())) {
            dc.add(Restrictions.ne("status", searchBean.getNotStatus()));
        }
        String handoverYear = searchBean.getHandoverYear();
        if (!GenericValidator.isBlankOrNull(handoverYear)) {
            dc.add(Restrictions.eq("b.handOver", handoverYear));
        }
        dc.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> data = hibernateTemplate.findByCriteria(dc);
        Map<Long, Double> map = new HashMap<Long, Double>();
        for (Map<String, Object> m : data) {
            map.put((Long) m.get("ID"), (Double) m.get("AMT"));
        }
        return map;

    }

}



