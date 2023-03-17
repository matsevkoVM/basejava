package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected static List<Resume> listStorage = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Collections.binarySearch(listStorage, searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey > 0;
    }

    @Override
    protected void specificSave(Resume r, Integer searchKey) {
        listStorage.add(-searchKey - 1, r);
    }

    @Override
    protected void specificUpdate(Resume r, Integer searchKey) {
        listStorage.set(searchKey, r);
    }

    @Override
    protected Resume specificGet(Integer searchKey) {
        return listStorage.get(searchKey);
    }

    @Override
    protected void specificDelete(Integer searchKey) {
        listStorage.remove(listStorage.get(searchKey));
    }

    @Override
    protected List<Resume> specificGetAll() {
        return listStorage;
    }

    public void clear() {
        listStorage.clear();
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}
