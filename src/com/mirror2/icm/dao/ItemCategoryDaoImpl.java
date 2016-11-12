package com.mirror2.icm.dao;

import com.mirror2.icm.model.ItemCategory;
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
public class ItemCategoryDaoImpl implements ItemCategoryDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(ItemCategory itemCategory) {
        try {
            hibernateTemplate.persist(itemCategory);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(ItemCategory itemCategory) {
        hibernateTemplate.merge(itemCategory);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(ItemCategory itemCategory) {
        hibernateTemplate.delete(itemCategory);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<ItemCategory> findAllItemCategory() {
        return hibernateTemplate.find("from ItemCategory");
    }

    @Override
    public ItemCategory getItemCategory(Long id) {
        return hibernateTemplate.get(ItemCategory.class, id);
    }
}
