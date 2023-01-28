package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public void fillStorage(Storage storage) {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }


    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assertions.assertEquals(new Resume("uuid1"), storage.get(UUID_1));
    }

    @Test
    public void getNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid3");
        storage.update(r);
        Assertions.assertEquals(r, storage.get(UUID_3));
    }

    @Test
    public void updateNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.update(new Resume("dummy"));
        });
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{storage.get("uuid1"), storage.get("uuid2"), storage.get("uuid3")};
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        Assertions.assertEquals(r, storage.get("uuid4"));
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    public void saveExist() throws ExistResumeException {
        Assertions.assertThrows(ExistResumeException.class, () -> {
            storage.save(new Resume(UUID_1));
        });
    }

    @Test
    public void storageOverflow() throws StorageException {
        Assertions.assertThrows(StorageException.class, () -> {
            storage.clear();
            fillStorage(storage);
            storage.save(new Resume("uuidDummy"));
        });
    }

    @Test
    public void delete() {
        storage.delete("uuid2");
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    public void deleteNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.delete("dummy");
        });
    }
}