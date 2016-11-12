package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.Store;
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
public class StoreDaoImpl implements StoreDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(Store store) {
        try {
            hibernateTemplate.persist(store);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(Store store) {
        hibernateTemplate.merge(store);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(Store store) {
        hibernateTemplate.delete(store);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<Store> findAllStore() {
        return hibernateTemplate.find("from Store");
    }

    @Override
    public Store getStore(Long id) {
        return hibernateTemplate.get(Store.class, id);
    }

    @Override
    public Store getStore(String storeName) {
        Object[] paramArr = new Object[1];
        paramArr[0] = new String(storeName);
        return (Store) hibernateTemplate.find("from Store where name=?", paramArr).get(0);
    }
}
