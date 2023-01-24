package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        boolean isResumeExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                isResumeExist = true;
            }
        }
        if (isResumeExist) {
            System.out.println("Such a resume already exists");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume oldR, String newUuid) {
        boolean isResumeExist = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(oldR.getUuid())) {
                storage[i].setUuid(newUuid);
            } else {
                isResumeExist = false;
            }
        }
        if (!isResumeExist) {
            System.out.println("No match found, cannot update");
        }
    }

    public Resume get(String uuid) {
        boolean isResumeExist = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            } else {
                isResumeExist = false;
            }
        }
        if (!isResumeExist) {
            System.out.println("No match found, cannot get such resume");
        }
        return null;
    }

    public void delete(String uuid) {
        boolean isResumeExist = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else {
                isResumeExist = false;
            }
        }
        if (!isResumeExist) {
            System.out.println("No match found, cannot delete");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void isExist(int cof, String message) {
        if (cof == 0) {
            System.out.println(message);
        }
    }
}
