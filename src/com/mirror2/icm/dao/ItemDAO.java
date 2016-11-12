package com.mirror2.icm.dao;

import com.mirror2.icm.model.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDAO {

    public boolean save(Item item);

    public boolean update(Item item);

    public boolean delete(Item item);

    public List<Item> findAllItem();

    public Item getItem(Long id);

    public Item getItem(String itemName);

}
