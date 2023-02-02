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

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

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
        assertSize(0);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        Resume r = new Resume("uuid1");
        assertGet(r);
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
        assertGet(r);
    }

    @Test
    public void updateNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.update(new Resume("dummy"));
        });
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        assertGet(r);
        assertSize(4);
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
            try {
                fillStorage(storage);
            } catch (Exception e){
                Assertions.fail("Storage overflowed too soon");
            }
            storage.save(new Resume("uuidDummy"));
        });
    }

    @Test
    public void delete() throws NotExistResumeException {
        storage.delete("uuid2");
        assertSize(2);
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.get("uuid2");
        });
    }

    @Test
    public void deleteNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> {
            storage.delete("dummy");
        });
    }

    private void fillStorage(Storage storage) {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    private void assertSize(int s) {
        Assertions.assertEquals(s, storage.size());
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(r, storage.get(r.getUuid()));
    }
}