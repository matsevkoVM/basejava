package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapHashCodeStorage extends AbstractStorage {
    protected static Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        return uuid.hashCode();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        for (String key : mapStorage.keySet()) {
            if (key.hashCode() == (Integer) searchKey) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void specificSave(Resume r, Object searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void specificUpdate(Resume r, Object searchKey) {
        for (Map.Entry<String, Resume> pair : mapStorage.entrySet()) {
            if (Objects.equals(searchKey, pair.getKey().hashCode())) {
                pair.setValue(r);
            }
        }
    }

    @Override
    protected Resume specificGet(Object searchKey) {
        for (String key : mapStorage.keySet()) {
            if (key.hashCode() == (Integer) searchKey) {
                return mapStorage.get(key);
            }
        }
        return null;
    }

    @Override
    protected void specificDelete(Object searchKey) {
        mapStorage.entrySet().removeIf(entry -> Objects.equals(entry.getKey().hashCode(), searchKey));
    }

    @Override
    protected List<Resume> specificGetAll() {
        Collection<Resume> listedMap = mapStorage.values();
        return Collections.list(Collections.enumeration(listedMap));
    }

    public void clear() {
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
