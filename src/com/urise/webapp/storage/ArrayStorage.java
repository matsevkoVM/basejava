package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage implements Storage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void replaceDeleted(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void addNew(Resume r, int index) {
        storage[size] = r;
    }
}
