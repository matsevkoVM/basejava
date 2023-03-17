package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected static Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void specificSave(Resume r, Resume searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void specificUpdate(Resume r, Resume searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected Resume specificGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void specificDelete(Resume searchKey) {
        mapStorage.remove(searchKey.getUuid());
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
