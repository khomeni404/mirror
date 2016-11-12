package com.mirror2.icm.dao;

import com.mirror2.icm.model.ItemOut;
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
public class ItemOutDaoImpl implements ItemOutDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(ItemOut itemOut) {
        try {
            hibernateTemplate.persist(itemOut);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(ItemOut itemOut) {
        hibernateTemplate.merge(itemOut);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(ItemOut itemOut) {
        hibernateTemplate.delete(itemOut);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<ItemOut> findAllItemOut() {
        return hibernateTemplate.find("from ItemOut");
    }

    @Override
    public ItemOut getItemOut(Long id) {
        return hibernateTemplate.get(ItemOut.class, id);
    }
}
