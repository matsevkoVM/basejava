package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void specificSave(Resume r, Object searchKey);

    protected abstract void specificUpdate(Resume r, Object searchKey);

    protected abstract Resume specificGet(Object searchKey);

    protected abstract void specificDelete(Object searchKey);

    protected abstract List<Resume> specificGetAll();

    public void save(Resume r) {
        Object searchKey = getNotExistedKey(r.getUuid());
        specificSave(r, searchKey);

    }

    public void update(Resume r) {
        Object searchKey = getExistedKey(r.getUuid());
        specificUpdate(r, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedKey(uuid);
        return specificGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedKey(uuid);
        specificDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> sortedList = specificGetAll();
        sortedList.sort(resumeComparator);
        return sortedList;
    }

    private Object getExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistResumeException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistResumeException(uuid);
        }
        return searchKey;
    }
}
