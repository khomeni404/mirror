package com.mirror2.icm.service;

import com.mirror2.icm.model.*;
import com.mirror2.security.model.User;

import java.util.List;
import java.util.Map;

public interface IcmService {
    //Reporting methods
    public Double getTotalItemIn(Item item);
    public Double getTotalItemOut(Item item);
    public Double getTotalReceived(Item item, User user);
    public Double getTotalRequisition(Item item, String requisitionStatus);
    public Double getTotalRequisition(Item item, User user,  String requisitionStatus);
    public Double getAvailable(Item item);
    public List getAllRequisitionSummary();
    public List<Map<String, Object>> getItemSummary(User user);
    public List<Map<String, Comparable>> getDisburseWaitingList();
    public List<List<Map<String, Comparable>>> getWaitingItems();
    public Map<String, Object> getItemDetails(User user, Item item);
    public Double useddTotal(User user, Item item);
    public Double lostTotal(User user, Item item);
    public Double backTotal(User user, Item item);

    public Double getTotalItemIn(Item item, Store store);
    public Double getTotalItemOut(Item item, Store store);

    //Item
    public boolean save(Item item);

    public boolean update(Item item);

    public boolean delete(Item item);

    public List<Map<String, Comparable>> findAllItemComparable();

    public List<Map<String, Comparable>> findAllItemStatus();

    public List<Item> findAllItem();

    public Item getItem(Long id);

    public Item getItem(String itemName);

    //Item In
    public boolean save(ItemIn itemIn);

    public boolean update(ItemIn itemIn);

    public boolean delete(ItemIn itemIn);

    public List<ItemIn> findAllItemIn();

    public ItemIn getItemIn(Long id);

    //Item Out
    public boolean save(ItemOut itemOut);

    public boolean update(ItemOut itemOut);

    public boolean delete(ItemOut itemOut);

    public List<ItemOut> findAllItemOut();

    public ItemOut getItemOut(Long id);

    //Category
    public boolean save(ItemCategory itemCategory);

    public boolean update(ItemCategory itemCategory);

    public boolean delete(ItemCategory itemCategory);

    public List<ItemCategory> findAllItemCategory();

    public ItemCategory getItemCategory(Long id);

    //Store
    public boolean save(Store store);

    public boolean update(Store store);

    public boolean delete(Store store);

    public List<Store> findAllStore();

    public Store getStore(Long id);

    public Store getStore(String storeName);

    //Unit
    public boolean save(Unit unit);

    public boolean update(Unit unit);

    public boolean delete(Unit unit);

    public List<Unit> findAllUnit();

    public Unit getUnit(Long id);

    //Item Usage History
    public boolean save(ItemUsageHistory  itemUsageHistory);

    public boolean update(ItemUsageHistory  itemUsageHistory);

    public boolean delete(ItemUsageHistory  itemUsageHistory);

    public List<ItemUsageHistory> findAllItemUsageHistory();

    public ItemUsageHistory getItemUsageHistory(Long id);

    //Internal Requisition
    public boolean save(InternalRequisition internalRequisition);

    public boolean update(InternalRequisition internalRequisition);

    public boolean delete(InternalRequisition internalRequisition);

    public List<InternalRequisition> findAllInternalRequisition();

    public InternalRequisition getInternalRequisition(Long id);


}
