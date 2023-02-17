package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListStorageTest {

    private final Storage storage = new ListStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume R1 = new Resume(UUID_1);
    private static final Resume R2 = new Resume(UUID_2);
    private static final Resume R3 = new Resume(UUID_3);

    @BeforeEach
    public void setUp(){
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    void get() {
        Resume r = new Resume("uuid1");
        assertGet(r);
    }

    @Test
    void getNotExist() throws NotExistResumeException {
        Assertions.assertThrows(NotExistResumeException.class, () -> storage.get("dummy"));
    }

    @Test
    void update() {
        Resume r = new Resume("uuid3");
        storage.update(r);
        assertGet(r);
    }

    @Test
    void updateNotExist() throws NotExistResumeException{
        Resume r = new Resume("dummy");
        Assertions.assertThrows(NotExistResumeException.class, () -> storage.update(r));
    }

    @Test
    void getAll() {
        Resume[] resumes = new Resume[] {R1,R2,R3};
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        assertGet(r);
        assertSize(4);
    }

    @Test
    void saveAlreadyExist() throws ExistResumeException {
        Assertions.assertThrows(ExistResumeException.class, () -> storage.save(R1));
    }

    @Test
    void delete() throws NotExistResumeException {
        storage.delete(UUID_2);
        assertSize(2);
        Assertions.assertThrows(NotExistResumeException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() throws NotExistResumeException{
        Assertions.assertThrows(NotExistResumeException.class, () -> storage.delete("dummy"));
    }

    private void assertSize(int s){
        Assertions.assertEquals(s, storage.size());
    }

    private void assertGet(Resume r){
        Assertions.assertEquals(r, storage.get(r.getUuid()));
    }
}