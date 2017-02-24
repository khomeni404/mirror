package com.mirror2.util;

public interface MirrorDataList {

    public String DISBURSEMENT_PROP_MAP = "disbursement.property.map";
    public String PAYMENT_DETAIL_BEAN = "payment.detail.bean";

    String[][] APP_STATUS = {{"0", "Applied"}, {"1", "Approved"}, {"2", "Executed"}, {"3", "Rejected"}};

    String CUST_STATUS_APPROVE = "Approved";
    String CUST_STATUS_CANCELLED = "Cancelled";
    String CUST_STATUS_PROCESSING = "Processing";
    String CUST_STATUS_REFUNDED = "Refunded";


}
