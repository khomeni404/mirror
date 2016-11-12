package com.mirror2.icm;


import com.mirror2.csd.util.CurrentUserCredentials;
import com.mirror2.icm.model.*;
import com.mirror2.icm.service.IcmService;
import com.mirror2.security.model.User;
import com.mirror2.security.service.UserDetailsService;
import com.mirror2.util.MirrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/icm/")
public class IcmController {
    @Autowired
    IcmService icmService;
    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.GET, value = "/icmConfigurationHome.erp")
    public ModelAndView icmConfigurationHome() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Internal Content Management Config");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("icm/home/configuration_home", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reportHome.erp")
    public ModelAndView reportHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "Report Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/icm/report/report_home", payHomeMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myIcmHome.erp")
    public ModelAndView myIcmHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "My Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/icm/my/my_home", payHomeMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/icmHome.erp")
    public ModelAndView icmHome() {
        Map<String, Object> payHomeMap = new HashMap<String, Object>();
        payHomeMap.put("PageTitle", "ICM Home");
        payHomeMap.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        return new ModelAndView("/icm/home/icm_home", payHomeMap);
    }

    //Item In
    @RequestMapping(method = RequestMethod.GET, value = "/createItem.erp")
    public ModelAndView createItem() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Item");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("msg", "");
        List<ItemCategory> allItemCategory = icmService.findAllItemCategory();
        map.put("categories", allItemCategory);

        List<Unit> units = icmService.findAllUnit();
        map.put("units", units);
        return new ModelAndView("/icm/item_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItem.erp")
    public ModelAndView saveItem(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("catId") Long catId,
                                 @RequestParam("approximatePrice") Double approximatePrice,
                                 @RequestParam("unitId") Long unitId) {

        Map<String, Object> map = new HashMap<String, Object>();
        Item itemDuplicate = null;
        try {
            itemDuplicate = icmService.getItem(name);
        } catch (Exception e) {
            //TODO...
        }

        if (itemDuplicate != null) {
            return new ModelAndView("redirect:/icm/createItem.erp", map);
        } else {
            ItemCategory itemCategory = icmService.getItemCategory(catId);
            Unit unit = icmService.getUnit(unitId);

            Item item = new Item();
            item.setName(name);
            item.setDescription(description);
            item.setItemCategory(itemCategory);
            item.setApproximatePrice(approximatePrice);
            item.setUnit(unit);

            itemCategory.getItems().add(item);
            icmService.save(item);
            return new ModelAndView("redirect:/icm/createItem.erp", map);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editItem.erp")
    public ModelAndView editItem(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Item");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("msg", "");
        map.put("item", icmService.getItem(id));
        List<ItemCategory> allItemCategory = icmService.findAllItemCategory();
        map.put("categories", allItemCategory);

        List<Unit> units = icmService.findAllUnit();
        map.put("units", units);
        return new ModelAndView("/icm/item_edit", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateItem.erp")
    public ModelAndView updateItem(@RequestParam("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("catId") Long catId) {

        Map<String, Object> map = new HashMap<String, Object>();
        Item item = icmService.getItem(id);
        ItemCategory itemCategory = icmService.getItemCategory(catId);

        item.setName(name);
        item.setDescription(description);
        item.setItemCategory(itemCategory);

        icmService.update(item);
        return new ModelAndView("redirect:/icm/itemList.erp", map);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/itemList.erp")
    public ModelAndView itemList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Item List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Map<String, Comparable>> listOfItems = icmService.findAllItemComparable();
        map.put("items", listOfItems);
        return new ModelAndView("/icm/item_list", map);
    }

    //Item In Section
    @RequestMapping(method = RequestMethod.GET, value = "/createItemIn.erp")
    public ModelAndView createItemIn() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Item In...");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Store> stores = icmService.findAllStore();
        List<Item> items = icmService.findAllItem();
        List<User> users = userDetailsService.getAllUsers();
        map.put("stores", stores);
        map.put("items", items);
        map.put("users", users);
        return new ModelAndView("/icm/item_in_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItemIn.erp")
    public ModelAndView saveItemIn(@RequestParam("itemName") String itemName,
                                   @RequestParam("source") String source,
                                   @RequestParam("qty") Double qty,
                                   @RequestParam("storeId") Long storeId) {

        Store store = icmService.getStore(storeId);
        Item item = icmService.getItem(itemName);
        User operator = new CurrentUserCredentials().user;

        Map<String, Object> map = new HashMap<String, Object>();
        ItemIn itemIn = new ItemIn();

        itemIn.setItem(item);
        itemIn.setSource(source);
        itemIn.setQty(qty);
        itemIn.setTo(store);
        itemIn.setDateIn(new Date());
        itemIn.setStoreBy(operator);
        itemIn.setChecked("Yes");
        itemIn.setApprove("Yes");
        itemIn.setApproveBy(null);
        itemIn.setCheckedBy(null);

        store.getItemIn().add(itemIn);
        item.getItemIns().add(itemIn);
        icmService.save(itemIn);
        return new ModelAndView("redirect:/icm/createItemIn.erp", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/itemInList.erp")
    public ModelAndView itemInList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Stored Item List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<ItemIn> itemIns = icmService.findAllItemIn();
        List<Map<String, Comparable>> list2 = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> map2;
        for (ItemIn itemIn : itemIns) {
            map2 = new HashMap<String, Comparable>();
            map2.put("source", itemIn.getSource());
            map2.put("item", itemIn.getItem().getName());
            map2.put("qty", itemIn.getQty());
            map2.put("dateIn", itemIn.getDateIn());
            map2.put("storeBy", itemIn.getStoreBy().getToken().getName());
            map2.put("to", itemIn.getTo().getName());
            map2.put("approveBy", itemIn.getApproveBy() == null ? "Not Approved" : itemIn.getApproveBy().getToken().getName());
            map2.put("checkedBy", itemIn.getCheckedBy() == null ? "Not Checked" : itemIn.getCheckedBy().getToken().getName());
            list2.add(map2);
        }
        Collections.sort(list2, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o2.get("dateIn").compareTo(o1.get("dateIn"));
            }
        });
        map.put("itemIns", itemIns);
        map.put("list2", list2);
        return new ModelAndView("/icm/item_in_list", map);
    }

    //Item Out Section
    @RequestMapping(method = RequestMethod.GET, value = "/createItemOut.erp")
    public ModelAndView createItemOut(@RequestParam("requestFor") Double requestFor,
                                      @RequestParam("itemId") Long itemId,
                                      @RequestParam("userId") Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Item Out");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Store> stores = icmService.findAllStore();
        map.put("stores", stores);
        //List<Item> items = icmService.findAllItem();
        //map.put("items", items);
        //List itemSummarises = icmService.getAllRequisitionSummary();
        // map.put("itemSummarises", itemSummarises);
        Item item = icmService.getItem(itemId);
        String userName = userDetailsService.getUser(userId).getToken().getName();
        map.put("userName", userName);
        map.put("userId", userId);
        map.put("itemName", item.getName());
        map.put("unitName", item.getUnit().getName());
        map.put("available", icmService.getAvailable(item));
        map.put("requestFor", requestFor);


        return new ModelAndView("/icm/item_out_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItemOut.erp")
    public ModelAndView saveItemOut(@RequestParam("itemName") String itemName,
                                    @RequestParam("storeName") String storeName,
                                    @RequestParam("qty") Double qty,
                                    @RequestParam("userId") Long userId) {
        //Date In, in by
        Store store = icmService.getStore(storeName);
        Item item = icmService.getItem(itemName);
        User operator = new CurrentUserCredentials().user;
        User itemFor = userDetailsService.getUser(userId);

        Map<String, Object> map = new HashMap<String, Object>();
        ItemOut itemOut = new ItemOut();

        itemOut.setItem(item);
        itemOut.setOutFor(itemFor);
        itemOut.setQty(qty);
        itemOut.setFrom(store);
        itemOut.setDateOut(new Date());
        itemOut.setOutBy(operator);
        itemOut.setStatus("Not Received");

        store.getItemOuts().add(itemOut);
        item.getItemOuts().add(itemOut);
        icmService.save(itemOut);
        return new ModelAndView("redirect:/icm/viewWaitingItemList.erp?listFor=all", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/itemOutList.erp")
    public ModelAndView itemOutList(@RequestParam("status") String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Disbursed Item List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<ItemOut> itemOuts = icmService.findAllItemOut();
        map.put("itemOuts", itemOuts);
        return new ModelAndView("/icm/item_out_list", map);
    }

    //Reporting
    @RequestMapping(method = RequestMethod.GET, value = "/viewItemSummary.erp")
    public ModelAndView viewItemSummary(@RequestParam("category") String category) {
        //TODO... for category wise result
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Item Summary");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List itemSummarises = icmService.getAllRequisitionSummary();
        map.put("itemSummarises", itemSummarises);
        return new ModelAndView("icm/report/item_summary", map);
    }

    //viewMyItem
    @RequestMapping(method = RequestMethod.GET, value = "/viewMyItem.erp")
    public ModelAndView viewMyItem() {
        //TODO... for category wise result
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "My Item Summary");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        User user = new CurrentUserCredentials().user;

        List<Map<String, Object>> empItemSummarises = icmService.getItemSummary(user);
        map.put("empItemSummarises", empItemSummarises);
        return new ModelAndView("icm/my/my_item", map);
    }

    //viewEmpItem
    @RequestMapping(method = RequestMethod.GET, value = "/viewEmpItem.erp")
    public ModelAndView viewEmpItem(@RequestParam("id") Long id) {
        //TODO... for category wise result
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Emp Item Summary");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<User> users = userDetailsService.getAllUsers();
        User user = id == -1 ? new CurrentUserCredentials().user : userDetailsService.getUser(id);

        List<Map<String, Object>> empItemSummarises = icmService.getItemSummary(user);

        map.put("users", users);
        map.put("empItemSummarises", empItemSummarises);
        return new ModelAndView("icm/report/emp_item", map);
    }

    //createItemUsageHistory
    @RequestMapping(method = RequestMethod.GET, value = "/createItemUsageHistory.erp")
    public ModelAndView createItemUsageHistory(@RequestParam("itemName") String itemName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Item Usage History");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Map<String, Object>> myItemSummarises = icmService.getItemSummary(new CurrentUserCredentials().user);
        for (Map<String, Object> summary : myItemSummarises) {
            if (summary.get("itemName").equals(itemName)) {
                map.put("itemName", summary.get("itemName"));
                map.put("received", summary.get("received"));
                map.put("used", summary.get("used"));
                map.put("lost", summary.get("lost"));
                map.put("back", summary.get("back"));
                map.put("rest", summary.get("rest"));
                map.put("notVerified", summary.get("notVerified"));
                map.put("unitName", summary.get("unitName"));
            }
        }

        return new ModelAndView("/icm/item_history_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveItemUsageHistory.erp")
    public ModelAndView saveItemUsageHistory(@RequestParam("itemName") String itemName,
                                             @RequestParam("status") String status,
                                             @RequestParam("qty") Double qty,
                                             @RequestParam("note") String note) {
        //Date In, in by
        Item item = icmService.getItem(itemName);
        ItemUsageHistory iuh = new ItemUsageHistory();
        iuh.setItem(item);
        iuh.setQuantity(qty);
        iuh.setStatus(status);
        iuh.setNote(note);
        iuh.setHistoryDate(new Date());
        iuh.setOwner(new CurrentUserCredentials().user);
        iuh.setVerified("No");
        item.getItemUsageHistories().add(iuh);
        icmService.save(iuh);
        return new ModelAndView("redirect:/icm/viewEmpItem.erp?id=-1");
    }

    //itemUsageHistoryList
    @RequestMapping(method = RequestMethod.GET, value = "/itemUsageHistoryList.erp")
    public ModelAndView itemUsageHistoryList(@RequestParam("isVerified") String isVerified) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Usage History Verification");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        map.put("isVerified", isVerified);
        List<ItemUsageHistory> histories = icmService.findAllItemUsageHistory();

        map.put("histories", histories);

        return new ModelAndView("/icm/home/item_usage_history_list", map);
    }

    //updateItemUsageHistory
    @RequestMapping(method = RequestMethod.GET, value = "/updateItemUsageHistory.erp")
    public ModelAndView updateItemUsageHistory(@RequestParam("isVerified") String isVerified,
                                               @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Waiting List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        Store defaultStore = icmService.getStore(new Long(1L));
        ItemUsageHistory history = icmService.getItemUsageHistory(id);

        history.setId(id);
        history.setVerified(isVerified);
        history.setVerifiedBy(new CurrentUserCredentials().user);
        icmService.update(history);

        if (history.getStatus().toUpperCase().equals("BACK")) {
            ItemIn itemIn = new ItemIn();
            itemIn.setItem(history.getItem());
            itemIn.setSource("Back");
            itemIn.setQty(history.getQuantity());
            itemIn.setTo(defaultStore);
            itemIn.setDateIn(new Date());
            itemIn.setStoreBy(new CurrentUserCredentials().user);
            itemIn.setChecked("Yes");
            itemIn.setApprove("Yes");

            defaultStore.getItemIn().add(itemIn);
            history.getItem().getItemIns().add(itemIn);
            icmService.save(itemIn);
        }
        return new ModelAndView("redirect:/icm/itemUsageHistoryList.erp?isVerified=No");
    }

    //Unit
    @RequestMapping(method = RequestMethod.GET, value = "/unitList.erp")
    public ModelAndView unitList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Measurement Unit List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Unit> units = icmService.findAllUnit();
        map.put("units", units);
        return new ModelAndView("/icm/unit_list", map);
    }

    //Store                                                storeList
    @RequestMapping(method = RequestMethod.GET, value = "/storeList.erp")
    public ModelAndView storeList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Store List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Store> stores = icmService.findAllStore();
        map.put("stores", stores);
        return new ModelAndView("/icm/store_list", map);
    }

    //Category
    @RequestMapping(method = RequestMethod.GET, value = "/itemCategoryList.erp")
    public ModelAndView itemCategoryList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Store List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<ItemCategory> categories = icmService.findAllItemCategory();
        map.put("categories", categories);
        return new ModelAndView("/icm/category_list", map);
    }

    //Internal Requisition
    @RequestMapping(method = RequestMethod.GET, value = "/createInternalRequisition.erp")
    public ModelAndView createInternalRequisition() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Create Internal Requisition");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<Item> items = icmService.findAllItem();
        map.put("items", items);

        return new ModelAndView("/icm/internal_requisition_create", map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveInternalRequisition.erp")
    public ModelAndView saveInternalRequisition(@RequestParam("itemName") String itemName,
                                                @RequestParam("note") String note,
                                                @RequestParam("qty") Double qty) {
        Item item = icmService.getItem(itemName);
        //User user = ;
        InternalRequisition ir = new InternalRequisition();
        ir.setItem(item);
        ir.setNote(note);
        ir.setQty(qty);
        ir.setStatus("Requested");
        ir.setRequestBy(new CurrentUserCredentials().user);
        ir.setRequestDate(new Date());

        item.getInternalRequisitions().add(ir);
        //user.getInternalRequisitions().add(ir);
        icmService.save(ir);
        return new ModelAndView("redirect:/icm/myInternalRequisitionList.erp?status=Requested");
    }

    //TODO...
    @RequestMapping(method = RequestMethod.GET, value = "/internalRequisitionList.erp")
    public ModelAndView internalRequisitionList(@RequestParam("status") String status) {
        if (status.toUpperCase().equals("A")) status = "Accepted";
        else if (status.toUpperCase().equals("R")) status = "Rejected";
        else if (!status.toUpperCase().equals("R") && !status.toUpperCase().equals("A")) status = "Requested";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Internal Requisition  (" + status + ")");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<InternalRequisition> irs = icmService.findAllInternalRequisition();
        map.put("irs", irs);
        map.put("status", status);
        return new ModelAndView("/icm/home/internal_requisition_list", map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myInternalRequisitionList.erp")
    public ModelAndView myInternalRequisitionList(@RequestParam("status") String status) {
        if (status.toUpperCase().equals("A")) status = "Accepted";
        else if (status.toUpperCase().equals("R")) status = "Rejected";
        else if (!status.toUpperCase().equals("R") && !status.toUpperCase().equals("A")) status = "Requested";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "My Internal Requisition (" + status + ")");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<InternalRequisition> irs = icmService.findAllInternalRequisition();
        map.put("irs", irs);
        map.put("status", status);
        map.put("userName", new CurrentUserCredentials().username);

        return new ModelAndView("/icm/my/my_internal_requisition", map);
    }

    //updateInternalRequisition
    @RequestMapping(method = RequestMethod.GET, value = "/updateInternalRequisition.erp")
    public ModelAndView updateInternalRequisition(@RequestParam("status") String status,
                                                  @RequestParam("reqId") Long reqId) {

        InternalRequisition ir = icmService.getInternalRequisition(reqId);
        //TODO...

        ir.setStatus(status);
        ir.setAcceptedBy(new CurrentUserCredentials().user);
        icmService.update(ir);
        return new ModelAndView("redirect:/icm/viewWaitingItemList.erp?listFor=all");
    }

    //viewStatusOfRequestedItem
    @RequestMapping(method = RequestMethod.GET, value = "/viewStatusOfRequestedItem.erp")
    public ModelAndView viewStatusOfRequestedItem(@RequestParam("itemId") Long itemId,
                                                  @RequestParam("reqQty") String reqQty,
                                                  @RequestParam("reqId") String reqId,
                                                  @RequestParam("userId") Long userId) {
        User user = userDetailsService.getUser(userId);
        Item item = icmService.getItem(itemId);
        Map<String, Object> map = icmService.getItemDetails(user, item);

        map.put("item", item);
        map.put("user", user);
        map.put("reqQty", reqQty);
        map.put("reqId", reqId);

        map.put("PageTitle", "Status of requested Item");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        return new ModelAndView("/icm/home/requested_item_status_view", map);
    }

    //viewComingItem
    @RequestMapping(method = RequestMethod.GET, value = "/viewComingItem.erp")
    public ModelAndView viewComingItem(@RequestParam("status") String status) {
        if (status.toUpperCase().equals("N")) status = "Not Received";
        if (status.toUpperCase().equals("R")) status = "Received";
        if (status.toUpperCase().equals("B")) status = "Back";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Coming Item List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<ItemOut> itemOuts = icmService.findAllItemOut();
        map.put("itemOuts", itemOuts);

        map.put("status", status);
        map.put("userId", new CurrentUserCredentials().authorizeId);
        return new ModelAndView("/icm/my/my_coming_item", map);
    }

    //disburseWaitingList
    @RequestMapping(method = RequestMethod.GET, value = "/viewWaitingItemList.erp")
    public ModelAndView viewWaitingItemList(@RequestParam("listFor") String listFor) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Waiting List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);
        List<List<Map<String, Comparable>>> waitingItems = icmService.getWaitingItems();

        map.put("waitingItems", waitingItems);

        return new ModelAndView("/icm/home/waiting_item_list", map);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/updateItemOut.erp")
    public ModelAndView updateItemOut(@RequestParam("status") String status,
                                      @RequestParam("ioId") Long ioId) {

        ItemOut io = icmService.getItemOut(ioId);

        io.setStatus(status);
        icmService.update(io);
        return new ModelAndView("redirect:/icm/viewComingItem.erp?status=N");
    }

    //viewFixedAsset
    @RequestMapping(method = RequestMethod.GET, value = "/viewAsset.erp")
    public ModelAndView viewAsset(@RequestParam("type") String type) {
        if (type.equals("FA")) type = "Fixed Asset";
        else if (type.equals("ST")) type = "Stationary";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PageTitle", "Fixed Asset List");
        map.put("DashboardLink", MirrorConstants.DASHBOARD_LINK);

        List<Map<String, Comparable>> assetList = icmService.findAllItemStatus();
        map.put("assetList", assetList);
        map.put("userId", new CurrentUserCredentials().authorizeId);
        map.put("parentCat", type);
        return new ModelAndView("/icm/report/asset_list", map);
    }

}
