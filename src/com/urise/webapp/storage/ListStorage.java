package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage{
    protected static List<Resume> listStorage = new ArrayList<>();

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException(uuid);
        } else {
            return listStorage.get(index);
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistResumeException(r.getUuid());
        } else {
            listStorage.set(index, r);
        }
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistResumeException(r.getUuid());
        } else {
            listStorage.add(-index - 1, r);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0){
            throw new NotExistResumeException(uuid);
        } else {
            listStorage.remove(listStorage.get(index));
        }
    }
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(listStorage, searchKey);
    }

}
