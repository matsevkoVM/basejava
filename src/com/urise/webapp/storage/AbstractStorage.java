package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public abstract Resume get(String uuid);

    public abstract void update(Resume r);

    public abstract Resume[] getAll();

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

}
