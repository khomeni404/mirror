package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.ItemCategory;
import com.mirror2.icm.model.ItemIn;
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
public class ItemInDaoImpl implements ItemInDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(ItemIn itemIn) {
        try {
            hibernateTemplate.persist(itemIn);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(ItemIn itemIn) {
        hibernateTemplate.merge(itemIn);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(ItemIn itemIn) {
        hibernateTemplate.delete(itemIn);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<ItemIn> findAllItemIn() {
        return hibernateTemplate.find("from ItemIn");
    }

    @Override
    public ItemIn getItemIn(Long id) {
        return hibernateTemplate.get(ItemIn.class, id);
    }
}
