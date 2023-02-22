package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage{
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_UUID_1 = "Antonio";
    private static final String FULL_NAME_UUID_2 = "Donald";
    private static final String FULL_NAME_UUID_3 = "Antonio";
    private static final String FULL_NAME_UUID_4 = "Charlie";

    private static final Resume R_1;
    private static final Resume R_2;
    private static final Resume R_3;
    private static final Resume R_4;

    static {
        R_1 = new Resume(UUID_1, FULL_NAME_UUID_1);
        R_2 = new Resume(UUID_2, FULL_NAME_UUID_2);
        R_3 = new Resume(UUID_3, FULL_NAME_UUID_3);
        R_4 = new Resume(UUID_4, FULL_NAME_UUID_4);
    }

    public static void main(String[] args) {

        ARRAY_STORAGE.save(R_1);
        ARRAY_STORAGE.save(R_2);
        ARRAY_STORAGE.save(R_3);
        ARRAY_STORAGE.save(R_4);

        printAll();
        ARRAY_STORAGE.getAllSorted();
        printAll();
    }
    static void printAll() {
        System.out.println("Get All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
