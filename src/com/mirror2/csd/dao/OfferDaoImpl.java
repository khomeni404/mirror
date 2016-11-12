package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class OfferDaoImpl implements OfferDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Offer offer) {
        try {
            hibernateTemplate.persist(offer);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Offer offer) {
        hibernateTemplate.merge(offer);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Offer offer) {
        hibernateTemplate.delete(offer);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Offer> findAllOffer() {
        return hibernateTemplate.find("from Offer");
    }

    @Override
    public Offer getOffer(Long id) {
        return hibernateTemplate.get(Offer.class, id);
    }
}
