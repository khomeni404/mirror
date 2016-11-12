package com.mirror2.csd.dao;

import com.mirror2.csd.model.Building;
import com.mirror2.csd.model.Offer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OfferDAO {
    public boolean save(Offer offer);

    public boolean update(Offer offer);

    public boolean delete(Offer offer);

    public List<Offer> findAllOffer();

    public Offer getOffer(Long id);
}
