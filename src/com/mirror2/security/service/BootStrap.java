package com.mirror2.security.service;

public interface BootStrap {
    public void createDefaultAdmin();

    public boolean createComponents();

    public boolean createFeatures();
}
