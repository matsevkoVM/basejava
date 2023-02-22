package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage{
    protected static Map<String, Resume> mapStorage = new HashMap<>();
    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey(String.valueOf(searchKey));
    }

    @Override
    protected void specificSave(Resume r, Object searchKey) {
        mapStorage.put((String)searchKey, r);
    }

    @Override
    protected void specificUpdate(Resume r, Object searchKey) {
        for (Map.Entry<String, Resume> pair : mapStorage.entrySet()) {
            if (Objects.equals(searchKey, pair.getKey())) {
                pair.setValue(r);
            }
        }

    }

    @Override
    protected Resume specificGet(Object searchKey) {
        return mapStorage.get((String) searchKey);
    }

    @Override
    protected void specificDelete(Object searchKey) {
        mapStorage.entrySet().removeIf(entry -> Objects.equals(entry.getKey(), searchKey));
    }

    @Override
    protected List<Resume> specificGetAll() {
        Collection<Resume> listedMap = mapStorage.values();
        return Collections.list(Collections.enumeration(listedMap));
    }

    public void clear(){
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
