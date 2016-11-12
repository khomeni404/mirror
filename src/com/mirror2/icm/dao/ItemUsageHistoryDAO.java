package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.ItemUsageHistory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemUsageHistoryDAO {
    public boolean save(ItemUsageHistory  itemUsageHistory);

    public boolean update(ItemUsageHistory  itemUsageHistory);

    public boolean delete(ItemUsageHistory  itemUsageHistory);

    public List<ItemUsageHistory> findAllItemUsageHistory();

    public ItemUsageHistory getItemUsageHistory(Long id);

}
