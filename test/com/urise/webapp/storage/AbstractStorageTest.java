package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.*;
import com.urise.webapp.utils.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final String FULL_NAME_1 = "Laszlo";
    private static final String FULL_NAME_2 = "Giorgio";
    private static final String FULL_NAME_3 = "Scarlett";
    private static final String FULL_NAME_4 = "Frenkel";

    private static final Resume R_1;
    private static final Resume R_2;
    private static final Resume R_3;
    private static final Resume R_4;

    static {
        R_1 = new Resume(UUID_1, FULL_NAME_1);
        R_2 = new Resume(UUID_2, FULL_NAME_2);
        R_3 = new Resume(UUID_3, FULL_NAME_3);
        R_4 = new Resume(UUID_4, FULL_NAME_4);
        R_1.addContact(ContactType.MAIL, "mail@something.else");
        R_1.addContact(ContactType.PHONE, "+52 998 200 0078");
        R_4.addContact(ContactType.LINKED_IN, "some//linkedIN.com");
        R_4.addContact(ContactType.GITHUB, "r_4.github.com");
        R_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        R_1.addSection(SectionType.PERSONAL, new TextSection("Personal 1"));
        R_1.addSection(SectionType.ACHIEVEMENTS, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "GitHub"));
        R_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization_1", "https://www.something.else",
                                new Organization.Position(2010, Month.APRIL, "position_1", "content_1"),
                                new Organization.Position(2008, Month.JUNE, 2010, Month.MARCH, "position_2", "content_2"))));
        R_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Universal Univercity", "https://www.univer.net",
                                new Organization.Position(2007, Month.SEPTEMBER, 2008, Month.MAY, "magister", "mag_content"),
                                new Organization.Position(2003, Month.SEPTEMBER, 2007, Month.JULY, "student", "stud_content"))));
        R_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(new Organization("College", "https://www.college.xz")));

        R_2.addContact(ContactType.SKYPE, "SomeSkype");
        R_2.addContact(ContactType.HOME_PHONE, "45-109");
        R_2.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization_3", "https://www.something.else",
                                new Organization.Position(2021, Month.APRIL, "position_3", "content_3"))));
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
        Resume sample = new Resume(UUID_1, "Sample name");
        R_1.addContact(ContactType.MAIL, "NEWmail@something.else(UPDATED)");
        R_1.addContact(ContactType.LINKED_IN, "some//linkedIN.com");
        R_1.addContact(ContactType.GITHUB, "r_4.github.com");
        storage.update(sample);
        assertEquals(sample, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() throws NotExistResumeException {
        assertThrows(NotExistResumeException.class, () -> storage.update(new Resume("dummy", "")));
    }

    @Test
    void delete() throws NotExistResumeException {
        storage.delete(UUID_2);
        assertSize(2);
        assertThrows(NotExistResumeException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() throws NotExistResumeException {
        assertThrows(NotExistResumeException.class, () -> storage.delete("dummy"));
    }

    @Test
    void getAllSorted() {
        List<Resume> resumeList = storage.getAllSorted();
        assertEquals(3, resumeList.size());
        List<Resume> sortedResumes = Arrays.asList(R_2, R_1, R_3);
        for (int i = 0; i < resumeList.size(); i++) {
            assertEquals(resumeList.get(i), sortedResumes.get(i));
        }
    }

    @Test
    void get() {
        assertGet(R_1);
        assertGet(R_2);
        assertGet(R_3);
    }

    @Test
    void getNotExisted() throws NotExistResumeException {
        assertThrows(NotExistResumeException.class, () -> storage.get("dummy"));
    }

    private void assertSize(int s) {
        assertEquals(s, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

}