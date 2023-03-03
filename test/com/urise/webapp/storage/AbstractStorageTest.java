package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    protected

    final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_UUID_1 = "Laszlo";
    private static final String FULL_NAME_UUID_2 = "Giorgio";
    private static final String FULL_NAME_UUID_3 = "Scarlett";
    private static final String FULL_NAME_UUID_4 = "Frenkel";

    private static final Resume R_1;
    private static final Resume R_2;
    private static final Resume R_3;
    private static final Resume R_4;

    static {
        R_1 = new Resume(UUID_1, FULL_NAME_UUID_1);
        R_2 = new Resume(UUID_2, FULL_NAME_UUID_2);
        R_3 = new Resume(UUID_3, FULL_NAME_UUID_3);
        R_4 = new Resume(UUID_4, FULL_NAME_UUID_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
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
    void save() {
        storage.save(R_4);
        assertSize(4);
        assertGet(R_4);
    }

    @Test
    void saveExist() throws ExistResumeException {
        assertThrows(ExistResumeException.class, () -> storage.save(R_2));
    }

    @Test
    void update() {
        storage.update(R_2);
        assertGet(R_2);
    }

    @Test
    void updateNotExist() throws NotExistResumeException {
        assertThrows(NotExistResumeException.class, () -> storage.update(new Resume("dummy", "")));
    }

    @Test
    void delete() throws NotExistResumeException{
        storage.delete(UUID_2);
        assertSize(2);
        assertThrows(NotExistResumeException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() throws NotExistResumeException{
        assertThrows(NotExistResumeException.class, () -> storage.delete("dummy"));
    }

    @Test
    void getAllSorted() {
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(R_2);
        resumeList.add(R_1);
        resumeList.add(R_3);
        assertEquals(resumeList, storage.getAllSorted());
    }

    private void assertSize(int s) {
        assertEquals(s, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}