package com.mirror2.csd.dao;

import com.mirror2.common.model.Badge;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CsdDaoImpl implements CsdDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

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
