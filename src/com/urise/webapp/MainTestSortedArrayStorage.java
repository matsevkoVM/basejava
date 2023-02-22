package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    private static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "");
        final Resume r2 = new Resume("uuid2", "");
        final Resume r3 = new Resume("uuid3", "");
        final Resume r4 = new Resume("uuid4", "");
        final Resume r5 = new Resume("uuid5", "");

        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);
        System.out.println("---------------------------------------------------");
        /*System.out.println("Testing: try to save resume which is already exists::");
        SORTED_ARRAY_STORAGE.save(r3);
        System.out.println("---------------------------------------------------");
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: try to get resume which does not exist::");
        System.out.println("Get dummy: " + SORTED_ARRAY_STORAGE.get("dummy"));
        System.out.println("---------------------------------------------------");
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: update");
        SORTED_ARRAY_STORAGE.update(r2);
        System.out.println("Updated " + r2);
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: try to update resume which does not exist::");
        SORTED_ARRAY_STORAGE.update(r5);
        printAll();*/
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: insertion in position inside of the array::");
        SORTED_ARRAY_STORAGE.save(r2);
        printAll();
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: delete");
        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println("---------------------------------------------------");
        /*System.out.println("Testing: try to delete resume which does not exist::");
        SORTED_ARRAY_STORAGE.delete(r5.getUuid());*/
        System.out.println("---------------------------------------------------");
        System.out.println("Testing: clear");
        SORTED_ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("Get All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

