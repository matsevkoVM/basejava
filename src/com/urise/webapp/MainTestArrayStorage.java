package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage{
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {

        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");
        final Resume r4 = new Resume("uuid4");
        final Resume r5 = new Resume("uuid5");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        System.out.println("---------------------------------------------------");
        /*System.out.println("Testing: try to save resume which is already exists::");
        ARRAY_STORAGE.save(r3);*/
        System.out.println("---------------------------------------------------");
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: try to get resume which does not exist::");
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("---------------------------------------------------");
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: update");
        ARRAY_STORAGE.update(r2);
        System.out.println("Updated " + r2);
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: try to update resume which does not exist::");
        ARRAY_STORAGE.update(r5);
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: delete");
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: try to delete resume which does not exist::");
        ARRAY_STORAGE.delete(r5.getUuid());
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: clear");
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }
    static void printAll() {
        System.out.println("Get All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
