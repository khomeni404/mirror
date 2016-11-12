package com.mirror2.icm.dao;

import com.mirror2.icm.model.Store;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 8/23/14
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreDAO {
    public boolean save(Store store);

    public boolean update(Store store);

    public boolean delete(Store store);

    public List<Store> findAllStore();

    public Store getStore(Long id);

    public Store getStore(String storeName);

}
