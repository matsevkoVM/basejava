package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    public static final int STORAGE_LIMIT = 10;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void replaceDeleted(int index);

    protected abstract void addNew(Resume r, int index);

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected void specificSave(Resume r, Integer index) {
        index = getSearchKey(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            addNew(r, index);
            size++;
        }
    }

    @Override
    protected void specificUpdate(Resume r, Integer searchKey) {
        searchKey = getSearchKey(r.getUuid());
        if (searchKey < 0) {
            throw new NotExistResumeException(r.getUuid());
        } else {
            storage[searchKey] = r;
        }
    }

    @Override
    protected Resume specificGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void specificDelete(Integer searchKey) {
        replaceDeleted(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected List<Resume> specificGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }
}
