package com.urise.webapp;

/**
 This class was made for educational purposes
 */
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
