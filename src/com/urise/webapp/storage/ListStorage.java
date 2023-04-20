package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected static List<Resume> listStorage = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void specificSave(Resume r, Integer searchKey) {
        listStorage.add(r);
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
        listStorage.remove(searchKey.intValue());
    }

    @Override
    protected List<Resume> specificGetAll() {
        return new ArrayList<>(listStorage);
    }

    public void clear() {
        listStorage.clear();
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}
