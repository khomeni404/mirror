package com.mirror2.messaging.test;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 02/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 02/09/15
 * Version : 1.0
 */

public class Downloader extends Thread {
    private String url;

    public Downloader(String url, String number) {
        this.url = url;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            FileDownload fd = new FileDownload();
            fd.download(this.url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
