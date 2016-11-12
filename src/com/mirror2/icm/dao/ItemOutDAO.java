package com.mirror2.icm.dao;

import com.mirror2.icm.model.ItemIn;
import com.mirror2.icm.model.ItemOut;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemOutDAO {
    public boolean save(ItemOut itemOut);

    public boolean update(ItemOut itemOut);

    public boolean delete(ItemOut itemOut);

    public List<ItemOut> findAllItemOut();

    public ItemOut getItemOut(Long id);

}
