package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.ItemUsageHistory;
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
public class ItemUsageHistoryDaoImpl implements ItemUsageHistoryDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean save(ItemUsageHistory itemUsageHistory) {
        try {
            hibernateTemplate.persist(itemUsageHistory);
            hibernateTemplate.flush();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(ItemUsageHistory itemUsageHistory) {
        hibernateTemplate.merge(itemUsageHistory);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean delete(ItemUsageHistory itemUsageHistory) {
        hibernateTemplate.delete(itemUsageHistory);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public List<ItemUsageHistory> findAllItemUsageHistory() {
        return hibernateTemplate.find("from ItemUsageHistory");
    }

    @Override
    public ItemUsageHistory getItemUsageHistory(Long id) {
        return hibernateTemplate.get(ItemUsageHistory.class, id);
    }
}
