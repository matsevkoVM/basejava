package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage{
    protected static List<Resume> listStorage = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Collections.binarySearch(listStorage, searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer)searchKey > 0;
    }

    @Override
    protected void specificSave(Resume r, Object searchKey) {
        listStorage.add(-(Integer)searchKey - 1, r);
    }

    @Override
    protected void specificUpdate(Resume r, Object searchKey) {
        listStorage.set((Integer)searchKey, r);
    }

    @Override
    protected Resume specificGet(Object searchKey) {
        return listStorage.get((Integer)searchKey);
    }

    @Override
    protected void specificDelete(Object searchKey) {
        listStorage.remove(listStorage.get((Integer)searchKey));
    }

    @Override
    protected List<Resume> specificGetAll() {
        return listStorage;
    }

    public void clear(){
        listStorage.clear();
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}
