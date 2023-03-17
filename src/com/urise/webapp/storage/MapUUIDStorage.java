package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage<String> {
    protected static Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return mapStorage.containsKey(String.valueOf(searchKey));
    }

    @Override
    protected void specificSave(Resume r, String searchKey) {
        mapStorage.put(searchKey, r);
    }

    @Override
    protected void specificUpdate(Resume r, String searchKey) {
        mapStorage.put(searchKey, r);

    }

    @Override
    protected Resume specificGet(String searchKey) {
        return mapStorage.get((String) searchKey);
    }

    @Override
    protected void specificDelete(String searchKey) {
        mapStorage.entrySet().removeIf(entry -> Objects.equals(entry.getKey(), searchKey));
    }

    @Override
    protected List<Resume> specificGetAll() {
        return new ArrayList<>(mapStorage.values());

    }

    public void clear() {
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
