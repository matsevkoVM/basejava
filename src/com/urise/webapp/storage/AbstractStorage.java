package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SearchKey getSearchKey(String uuid);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract void specificSave(Resume r, SearchKey searchKey);

    protected abstract void specificUpdate(Resume r, SearchKey searchKey);

    protected abstract Resume specificGet(SearchKey searchKey);

    protected abstract void specificDelete(SearchKey searchKey);

    protected abstract List<Resume> specificGetAll();

    public void save(Resume r) {
        LOG.info("Save " + r);
        SearchKey searchKey = getNotExistedKey(r.getUuid());
        specificSave(r, searchKey);

    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SearchKey searchKey = getExistedKey(r.getUuid());
        specificUpdate(r, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SearchKey searchKey = getExistedKey(uuid);
        return specificGet(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SearchKey searchKey = getExistedKey(uuid);
        specificDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("Get All Sorted");
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> sortedList = specificGetAll();
        sortedList.sort(resumeComparator);
        return sortedList;
    }

    private SearchKey getExistedKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist!");
            throw new NotExistResumeException(uuid);
        }
        return searchKey;
    }

    private SearchKey getNotExistedKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist!");
            throw new ExistResumeException(uuid);
        }
        return searchKey;
    }
}
