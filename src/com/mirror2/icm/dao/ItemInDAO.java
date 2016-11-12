package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;
import com.mirror2.icm.model.ItemIn;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemInDAO {
    public boolean save(ItemIn itemIn);

    public boolean update(ItemIn itemIn);

    public boolean delete(ItemIn itemIn);

    public List<ItemIn> findAllItemIn();

    public ItemIn getItemIn(Long id);

}
