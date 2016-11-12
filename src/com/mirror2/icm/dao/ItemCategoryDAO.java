package com.mirror2.icm.dao;

import com.mirror2.icm.model.ItemCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemCategoryDAO {
    public boolean save(ItemCategory itemCategory);

    public boolean update(ItemCategory itemCategory);

    public boolean delete(ItemCategory itemCategory);

    public List<ItemCategory> findAllItemCategory();

    public ItemCategory getItemCategory(Long id);

}
