package com.isacofff.clientbase;

import com.isacofff.clientbase.modules.Manager;

public class Client {

    public static Client INSTANCE;
    public static Manager manager;

    public void init() {
        manager = new Manager();
        manager.init();
    }
}
