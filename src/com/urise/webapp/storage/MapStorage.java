package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapStorage extends AbstractStorage{
    protected static Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume get(String uuid) {
        Resume r = null;
        for (Map.Entry<String, Resume> pair : mapStorage.entrySet()){
            if (Objects.equals(uuid, pair.getKey())) {
                r = pair.getValue();
            }
        }
        if (r == null){
            throw new NotExistResumeException(uuid);
        } else {
            return r;
        }
    }

    @Override
    public void update(Resume r) {
        for (Map.Entry<String, Resume> pair : mapStorage.entrySet()){
            if (Objects.equals(r.getUuid(), pair.getKey())) {
                pair.setValue(r);
            }
        }
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public void save(Resume r) {
        if (mapStorage.isEmpty()) {
            mapStorage.put(r.getUuid(), r);
        } else if (mapStorage.containsKey(r.getUuid())){
            throw new ExistResumeException(r.getUuid());
        } else {
            mapStorage.put(r.getUuid(), r);
        }
    }

    @Override
    public void delete(String uuid) {
        if (!mapStorage.containsKey(uuid)){
            throw new NotExistResumeException(uuid);
        } else {
            mapStorage.entrySet().removeIf(entry -> Objects.equals(entry.getKey(), uuid));
        }
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
