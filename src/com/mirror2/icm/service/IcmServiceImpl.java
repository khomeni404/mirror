package com.mirror2.icm.service;


import com.mirror2.icm.dao.*;
import com.mirror2.icm.model.*;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IcmServiceImpl implements IcmService {
    @Autowired
    UnitDAO unitDAO;
    @Autowired
    ItemCategoryDAO itemCategoryDAO;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    ItemInDAO itemInDAO;
    @Autowired
    ItemOutDAO itemOutDAO;
    @Autowired
    ItemUsageHistoryDAO itemUsageHistoryDAO;
    @Autowired
    StoreDAO storeDAO;
    @Autowired
    InternalRequisitionDAO internalRequisitionDAO;

    @Autowired
    UserDetailsService userDetailsService;

    //Reporting Methods
    @Override
    public List<List<Map<String, Comparable>>> getWaitingItems(){
        List<List<Map<String, Comparable>>> listA = new ArrayList<List<Map<String, Comparable>>>();
        List<Map<String, Comparable>> listB;
        Map<String, Comparable> mapA;

        List<User> users = userDetailsService.getAllUsers();
        List<Item> items;
        for(User user : users){
            items = itemDAO.findAllItem();
            listB = new ArrayList<Map<String, Comparable>>();
            for(Item item : items){
                mapA = new HashMap<String, Comparable>();
                mapA.put("userName", user.getToken().getName());
                mapA.put("userId", user.getAuthorizeId());
                mapA.put("itemName", item.getName());
                mapA.put("itemId", item.getId());
                Double accepted =  getTotalRequisition(item, user, "Accepted");
                mapA.put("accepted", accepted);
                Double received = getTotalReceived(item, user);
                mapA.put("received", received);
                mapA.put("unitName", item.getUnit().getName());
                if(accepted-received > 0)
                    listB.add(mapA);
            }
            listA.add(listB);
        }
        return listA;
    }

    @Override
    public Double getTotalItemIn(Item item) {
        Double total = 0.0;
        List<ItemIn> itemIns = item.getItemIns();
        if (itemIns != null) {
            for (ItemIn itemIn : itemIns) {
                total += itemIn.getQty();
            }
        }
        return total;
    }

    @Override
    public Double getTotalItemOut(Item item) {
        Double total = 0.0;
        List<ItemOut> itemOuts = item.getItemOuts();
        if (itemOuts != null) {
            for (ItemOut itemOut : itemOuts) {
                total += itemOut.getQty();
            }
        }
        return total;
    }

    @Override
    public Double getTotalReceived(Item item, User user) {
        Double total = 0.0;
        List<ItemOut> itemOuts = item.getItemOuts();
        if (itemOuts != null) {
            for (ItemOut itemOut : itemOuts) {
                if(itemOut.getStatus().toUpperCase().equals("RECEIVED")
                        && itemOut.getOutFor().getAuthorizeId() == user.getAuthorizeId())
                    total += itemOut.getQty();
            }
        }
        return total;
    }

    @Override
    public Double getTotalRequisition(Item item, String requisitionStatus){
        Double total = 0.0;
        List<InternalRequisition> irs = item.getInternalRequisitions();
        if(irs != null){
            for(InternalRequisition ir : irs){
                if(ir.getStatus().toUpperCase().equals(requisitionStatus.toUpperCase()))
                    total += ir.getQty();
            }
        }

        return total;
    }

    @Override
    public Double getTotalRequisition(Item item, User user,  String requisitionStatus){
        Double total = 0.0;
        List<InternalRequisition> irs = item.getInternalRequisitions();
        if(irs != null){
            for(InternalRequisition ir : irs){
                if(ir.getStatus().toUpperCase().equals(requisitionStatus.toUpperCase())
                        &&ir.getRequestBy().getAuthorizeId() == user.getAuthorizeId())
                    total += ir.getQty();
            }
        }

        return total;
    }

    @Override
    public Double getAvailable(Item item) {
        return getTotalItemIn(item) - getTotalItemOut(item);
    }

    @Override
    public List getAllRequisitionSummary() {
        List list = new ArrayList();
        Map<String, Object> map;
        List<Item> items = itemDAO.findAllItem();
        for (Item item : items) {
            map = new HashMap<String, Object>();
            map.put("itemName", item.getName());
            map.put("itemDetails", item.getDescription());
            map.put("unitName", item.getUnit().getName());
            map.put("totalIn", getTotalItemIn(item));
            map.put("totalOut", getTotalItemOut(item));
            map.put("available", getAvailable(item));
            map.put("requested", getTotalRequisition(item, "Requested"));
            map.put("accepted", getTotalRequisition(item, "Accepted"));
            map.put("rejected", getTotalRequisition(item, "Rejected"));
            list.add(map);
        }

        return list;
    }



    @Override
    public List<Map<String, Object>> getItemSummary(User user) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        Double received;
        Double used;
        Double lost;
        Double back;
        Double notVerified;
        Integer mapId = 1;
        List<Item> items = itemDAO.findAllItem();
        for (Item item : items) {
            received = 0.0;
            used = 0.0;
            lost = 0.0;
            back = 0.0;
            notVerified = 0.0;
            map = new HashMap<String, Object>();
            map.put("itemId", item.getId());
            map.put("itemName", item.getName());

            List<ItemOut> itemOuts = item.getItemOuts();
            for (ItemOut itemOut : itemOuts) {
                if (itemOut.getStatus().toUpperCase().equals("RECEIVED")
                        && itemOut.getOutFor().getAuthorizeId().equals(user.getAuthorizeId())) {
                    received = received + itemOut.getQty();
                } else {
                    //TODO...
                }
            }
            map.put("received", received);
            List<ItemUsageHistory> itemUsageHistories = item.getItemUsageHistories();

            for (ItemUsageHistory itemUsageHistory : itemUsageHistories) {
                if (itemUsageHistory.getOwner().getAuthorizeId().equals(user.getAuthorizeId())
                        && itemUsageHistory.getVerified().toUpperCase().equals("YES")) {
                    if (itemUsageHistory.getStatus().toUpperCase().equals("USED")) {
                        used += itemUsageHistory.getQuantity();
                    } else if (itemUsageHistory.getStatus().toUpperCase().equals("LOST")) {
                        lost += itemUsageHistory.getQuantity();
                    } else if (itemUsageHistory.getStatus().toUpperCase().equals("BACK")) {
                        back += itemUsageHistory.getQuantity();
                    } else {
                        //TODO...
                    }
                } else if (itemUsageHistory.getOwner().getAuthorizeId().equals(user.getAuthorizeId())
                        && itemUsageHistory.getVerified().toUpperCase().equals("NO")) {
                    notVerified += itemUsageHistory.getQuantity();
                }
            }
            map.put("used", used);
            map.put("lost", lost);
            map.put("back", back);
            map.put("rest", received - used - lost - back);
            map.put("notVerified", notVerified);
            map.put("unitName", item.getUnit().getName());
            map.put("approxPrice", item.getApproximatePrice());
            map.put("mapId", mapId);
            list.add(map);
            mapId++;
        }

        return list;
    }

    @Override
    public List<Map<String, Comparable>> getDisburseWaitingList() {
        List<Map<String, Comparable>> list = null;
        List<User> users = userDetailsService.getAllUsers();
        List<Item> items = itemDAO.findAllItem();
        for(User user : users){
             for(Item item : items){
                 getItemDetails(user, item);
             }
        }


        return list;
    }

    @Override
    public Map<String, Object> getItemDetails(User user, Item item) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<ItemOut> itemOuts = item.getItemOuts();
        Double received = 0.0;
        for (ItemOut itemOut : itemOuts) {
            if (itemOut.getStatus().toUpperCase().equals("RECEIVED")
                    && itemOut.getOutFor().getAuthorizeId().equals(user.getAuthorizeId())) {
                received = received + itemOut.getQty();
            } else {
                //TODO...
            }
        }
        map.put("received", received);

        List<InternalRequisition> internalRequisitions = item.getInternalRequisitions();
        Double requested = 0.0;
        Double accepted = 0.0;
        Double rejected = 0.0;
        for (InternalRequisition ir : internalRequisitions) {
            if (ir.getRequestBy().getAuthorizeId().equals(user.getAuthorizeId())) {
                if (ir.getStatus().toUpperCase().equals("REQUESTED")) {
                    requested += ir.getQty();
                } else if (ir.getStatus().toUpperCase().equals("ACCEPTED")) {
                    accepted += ir.getQty();
                } else {
                    rejected += ir.getQty();
                }
            }
        }
        map.put("requested", requested);
        map.put("accepted", accepted);
        map.put("rejected", rejected);

        List<ItemUsageHistory> itemUsageHistories = item.getItemUsageHistories();
        Double used = 0.0;
        Double lost = 0.0;
        Double back = 0.0;
        Double notVerified = 0.0;
        for (ItemUsageHistory itemUsageHistory : itemUsageHistories) {
            if (itemUsageHistory.getOwner().getAuthorizeId().equals(user.getAuthorizeId())
                    && itemUsageHistory.getVerified().toUpperCase().equals("YES")) {
                if (itemUsageHistory.getStatus().toUpperCase().equals("USED")) {
                    used += itemUsageHistory.getQuantity();
                } else if (itemUsageHistory.getStatus().toUpperCase().equals("LOST")) {
                    lost += itemUsageHistory.getQuantity();
                } else if (itemUsageHistory.getStatus().toUpperCase().equals("BACK")) {
                    back += itemUsageHistory.getQuantity();
                } else {
                    //TODO...
                }
            } else if (itemUsageHistory.getOwner().getAuthorizeId().equals(user.getAuthorizeId())
                    && itemUsageHistory.getVerified().toUpperCase().equals("NO")) {
                notVerified += itemUsageHistory.getQuantity();
            }
        }
        map.put("used", used);
        map.put("lost", lost);
        map.put("back", back);
        map.put("rest", received - used - lost - back);
        map.put("notVerified", notVerified);
        map.put("unitName", item.getUnit().getName());
        map.put("approxPrice", item.getApproximatePrice());

        return map;
    }

    @Override
    public Double useddTotal(User user, Item item) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double lostTotal(User user, Item item) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double backTotal(User user, Item item) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double getTotalItemIn(Item item, Store store) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Double getTotalItemOut(Item item, Store store) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //Item
    @Override
    public boolean save(Item item) {
        return itemDAO.save(item);
    }

    @Override
    public boolean update(Item item) {
        return itemDAO.update(item);
    }

    @Override
    public boolean delete(Item item) {
        return itemDAO.delete(item);
    }

    @Override
    public List<Item> findAllItem() {
        return itemDAO.findAllItem();
    }

    @Override
    public List<Map<String, Comparable>> findAllItemComparable() {
        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        List<Item> items = findAllItem();
        Map<String, Comparable> map;
        for(Item item : items){
            map = new HashMap<String, Comparable>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            map.put("itemCategory", item.getItemCategory().getName());
            map.put("parentCategory", item.getItemCategory().getParentItemCategory().getName());
            map.put("description", item.getDescription());
            map.put("approximatePrice", item.getApproximatePrice());
            map.put("unit", item.getUnit().getName());
            list.add(map);
        }

        Collections.sort(list, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("name").compareTo(o2.get("name"));
            }
        });

        return list;
    }

    @Override
    public List<Map<String, Comparable>> findAllItemStatus() {
        List<Map<String, Comparable>> list = new ArrayList<Map<String, Comparable>>();
        List<Item> items = findAllItem();
        Map<String, Comparable> map;
        for(Item item : items){
            map = new HashMap<String, Comparable>();
            map.put("id", item.getId());
            map.put("name", item.getName());
            map.put("itemCategory", item.getItemCategory().getName());
            map.put("parentCategory", item.getItemCategory().getParentItemCategory().getName());
            map.put("description", item.getDescription());
            map.put("approximatePrice", item.getApproximatePrice());
            map.put("unit", item.getUnit().getName());
            map.put("inTotal", getTotalItemIn(item));
            map.put("outTotal", getTotalItemOut(item));
            list.add(map);
        }

        Collections.sort(list, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("name").compareTo(o2.get("name"));
            }
        });

        return list;
    }

    @Override
    public Item getItem(Long id) {
        return itemDAO.getItem(id);
    }

    @Override
    public Item getItem(String itemName) {
        return itemDAO.getItem(itemName);
    }

    ///Item In

    @Override
    public boolean save(ItemIn itemIn) {
        return itemInDAO.save(itemIn);
    }

    @Override
    public boolean update(ItemIn itemIn) {
        return itemInDAO.update(itemIn);
    }

    @Override
    public boolean delete(ItemIn itemIn) {
        return itemInDAO.delete(itemIn);
    }

    @Override
    public List<ItemIn> findAllItemIn() {
        return itemInDAO.findAllItemIn();
    }

    @Override
    public ItemIn getItemIn(Long id) {
        return itemInDAO.getItemIn(id);
    }


    //Item Out   =========================================================================

    @Override
    public boolean save(ItemOut itemOut) {
        return itemOutDAO.save(itemOut);
    }

    @Override
    public boolean update(ItemOut itemOut) {
        return itemOutDAO.update(itemOut);
    }

    @Override
    public boolean delete(ItemOut itemOut) {
        return itemOutDAO.delete(itemOut);
    }

    @Override
    public List<ItemOut> findAllItemOut() {
        return itemOutDAO.findAllItemOut();
    }

    @Override
    public ItemOut getItemOut(Long id) {
        return itemOutDAO.getItemOut(id);
    }

    //Item Category
    @Override
    public boolean save(ItemCategory itemCategory) {
        return itemCategoryDAO.save(itemCategory);
    }

    @Override
    public boolean update(ItemCategory itemCategory) {
        return itemCategoryDAO.update(itemCategory);
    }

    @Override
    public boolean delete(ItemCategory itemCategory) {
        return itemCategoryDAO.delete(itemCategory);
    }

    @Override
    public List<ItemCategory> findAllItemCategory() {
        return itemCategoryDAO.findAllItemCategory();
    }

    @Override
    public ItemCategory getItemCategory(Long id) {
        return itemCategoryDAO.getItemCategory(id);
    }

    //Item Usage History   =========================================================================

    @Override
    public boolean save(ItemUsageHistory itemUsageHistory) {
        return itemUsageHistoryDAO.save(itemUsageHistory);
    }

    @Override
    public boolean update(ItemUsageHistory itemUsageHistory) {
        return itemUsageHistoryDAO.update(itemUsageHistory);
    }

    @Override
    public boolean delete(ItemUsageHistory itemUsageHistory) {
        return itemUsageHistoryDAO.delete(itemUsageHistory);
    }

    @Override
    public List<ItemUsageHistory> findAllItemUsageHistory() {
        return itemUsageHistoryDAO.findAllItemUsageHistory();
    }

    @Override
    public ItemUsageHistory getItemUsageHistory(Long id) {
        return itemUsageHistoryDAO.getItemUsageHistory(id);
    }


    ///Units

    @Override
    public boolean save(Unit unit) {
        return unitDAO.save(unit);
    }

    @Override
    public boolean update(Unit unit) {
        return unitDAO.update(unit);
    }

    @Override
    public boolean delete(Unit unit) {
        return unitDAO.delete(unit);
    }

    @Override
    public List<Unit> findAllUnit() {
        return unitDAO.findAllUnit();
    }

    @Override
    public Unit getUnit(Long id) {
        return unitDAO.getUnit(id);
    }

    //Store
    @Override
    public boolean save(Store store) {
        return storeDAO.save(store);
    }

    @Override
    public boolean update(Store store) {
        return storeDAO.update(store);
    }

    @Override
    public boolean delete(Store store) {
        return storeDAO.save(store);
    }

    @Override
    public List<Store> findAllStore() {
        return storeDAO.findAllStore();
    }

    @Override
    public Store getStore(Long id) {
        return storeDAO.getStore(id);
    }

    @Override
    public Store getStore(String storeName) {
        return storeDAO.getStore(storeName);
    }

    //Internal Requisition
    @Override
    public boolean save(InternalRequisition internalRequisition) {
        return internalRequisitionDAO.save(internalRequisition);
    }

    @Override
    public boolean update(InternalRequisition internalRequisition) {
        return internalRequisitionDAO.update(internalRequisition);
    }

    @Override
    public boolean delete(InternalRequisition internalRequisition) {
        return internalRequisitionDAO.save(internalRequisition);
    }

    @Override
    public List<InternalRequisition> findAllInternalRequisition() {
        return internalRequisitionDAO.findAllInternalRequisition();
    }

    @Override
    public InternalRequisition getInternalRequisition(Long id) {
        return internalRequisitionDAO.getInternalRequisition(id);
    }
}
