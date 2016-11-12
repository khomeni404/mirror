package com.mirror2.csd.threads;

import com.mirror2.csd.model.MoneyReceipt;
import com.mirror2.csd.service.CsdService;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class ReceiptThread extends Thread {
    private MoneyReceipt mr;
    private CsdService service;

    public ReceiptThread(MoneyReceipt mr, CsdService service) {
        this.mr = mr;
        this.service = service;
    }

    public void run() {
        service.save(mr);
    }

}
