package com.urise.webapp;

public class LazySingleton {

    private LazySingleton() {
    }

    private static final class InstanceHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
