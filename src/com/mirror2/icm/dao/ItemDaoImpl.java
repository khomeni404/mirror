package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: M Ayatullah
 * Date: 8/23/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemDaoImpl implements ItemDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Item item) {
        try {
            hibernateTemplate.persist(item);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Item item) {
        hibernateTemplate.merge(item);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Item item) {
        hibernateTemplate.delete(item);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Item> findAllItem() {
        return hibernateTemplate.find("from Item");
    }

    @Override
    public Item getItem(Long id) {
        return hibernateTemplate.get(Item.class, id);
    }

    @Override
    public Item getItem(String itemName) {
        Object[] paramArr = new Object[1];
        paramArr[0] = new String(itemName);
        return (Item) hibernateTemplate.find("from Item where name=?", paramArr).get(0);
    }
}
