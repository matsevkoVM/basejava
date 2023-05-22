package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class MainTestSql {
    private static final File PROPS = new File("config\\resumes.properties");
    Storage storage;
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String UUID_5 = UUID.randomUUID().toString();
    private static final String UUID_6 = UUID.randomUUID().toString();
    private static final String UUID_7 = UUID.randomUUID().toString();
    private static final String UUID_8 = UUID.randomUUID().toString();


    private static final String FULL_NAME_1 = "Henry Jons Junior";
    private static final String FULL_NAME_2 = "Han Solo";
    private static final Link UNIVER_LINK = new Link("Univer", "univer.com");
    private static final Organization.Position STUDENT =new Organization.Position(2006, Month.SEPTEMBER, 2010, Month.JUNE, "student", "description");
    private static final Organization.Position MASTER =new Organization.Position(2010, Month.SEPTEMBER, 2011, Month.JUNE, "master", "master description");
    private static final List<Organization.Position> UNIVER_LIST = new ArrayList<>();
    private static final Organization UNIVER = new Organization(UNIVER_LINK, UNIVER_LIST);
    private static final Link FIRST_JOB = new Link("First job Company LTD", "first-job.com");
    private static final Link LAST_JOB = new Link("Last job Company LLC", "last-job.net");
    private static final Organization.Position TRAINEE = new Organization.Position(2012, Month.APRIL, 2012, Month.AUGUST, "trainee", "trainee description");
    private static final Organization.Position JUNIOR = new Organization.Position(2012, Month.SEPTEMBER, 2016, Month.AUGUST, "junior", "junior description");
    private static final Organization.Position MIDDLE = new Organization.Position(2016, Month.SEPTEMBER, "middle", "middle description");
    private static final List<Organization.Position> FIRST_JOB_LIST = new ArrayList<>();
    private static final List<Organization.Position> LAST_JOB_LIST = new ArrayList<>();
    private static final Organization MY_FIRST_JOB = new Organization(FIRST_JOB, FIRST_JOB_LIST);
    private static final Organization MY_LAST_JOB = new Organization(LAST_JOB, LAST_JOB_LIST);


    private static final String FULL_NAME_3 = "Tony Stark";
    private static final String FULL_NAME_4 = "Clark Kent";
    private static final String FULL_NAME_5 = "John Snow";
    private static final String FULL_NAME_6 = "Donald Duck";
    private static final String FULL_NAME_7 = "Merry Poppins";
    private static final String FULL_NAME_8 = "Tom Readl";

    private static final Resume R_1 = new Resume(UUID_1, FULL_NAME_1);
    private static final Resume R_2 = new Resume(UUID_2, FULL_NAME_2);
    private static final Resume R_3 = new Resume(UUID_3, FULL_NAME_3);
    private static final Resume R_4 = new Resume(UUID_4, FULL_NAME_4);
    private static final Resume R_5 = new Resume(UUID_5, FULL_NAME_5);
    private static final Resume R_6 = new Resume(UUID_6, FULL_NAME_6);
    private static final Resume R_7 = new Resume(UUID_7, FULL_NAME_7);
    private static final Resume R_8 = new Resume(UUID_8, FULL_NAME_8);
    static {
        UNIVER_LIST.add(STUDENT);
        UNIVER_LIST.add(MASTER);
        FIRST_JOB_LIST.add(TRAINEE);
        FIRST_JOB_LIST.add(JUNIOR);
        LAST_JOB_LIST.add(MIDDLE);
        R_1.addContact(ContactType.MAIL, "mail@something.else");
        R_1.addContact(ContactType.PHONE, "+52 998 200 0078");
        R_1.addContact(ContactType.GITHUB, "laszlo.github.com");
        R_1.addContact(ContactType.LINKED_IN, "laszlo.linkedin.com");
        R_1.addSection(SectionType.OBJECTIVE, new TextSection("Remotely organized work of several truck drivers in the USA: managed work schedule, planned routes, communicated with freight suppliers, negotiated with suppliers and providers for freight prices, kept work documentation and reports, helped drivers in different unforeseen situations."));
        R_1.addSection(SectionType.PERSONAL, new TextSection("I strive to be part of a friendly and professional team where I could improve my skills to achieve team results. I believe that having such important qualities as responsibility, purposefulness, diligence and perseverance will help me to successfully show myself at work."));
        R_1.addSection(SectionType.ACHIEVEMENTS, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "GitHub"));
        R_2.addSection(SectionType.ACHIEVEMENTS, new ListSection("R_2 Achievement1", "R_2 Achievement2", "R_2 Achievement3"));
        R_2.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "GitHub", "Tomcat", "Maven", "OOP"));
        R_2.addContact(ContactType.SKYPE, "SomeSkype");
        R_2.addContact(ContactType.HOME_PHONE, "45-109");
        R_2.addContact(ContactType.MAIL, "giorgio@yahoo.com");
        R_2.addContact(ContactType.GITHUB, "giorgio.github.com");
        R_2.addSection(SectionType.EDUCATION, new OrganizationSection(UNIVER));
        R_2.addSection(SectionType.EXPERIENCE,new OrganizationSection(MY_FIRST_JOB, MY_LAST_JOB));
        R_3.addContact(ContactType.LINKED_IN, "some//linkedin.something");
        R_3.addContact(ContactType.MOBILE, "+380974328694");
        R_3.addContact(ContactType.MAIL, "scarlett@duckduckgo.com");
        R_3.addContact(ContactType.GITHUB, "scarlett.github.com");
        R_4.addContact(ContactType.GITHUB, "some.github.com");
        R_4.addContact(ContactType.STACKOVERFLOW, "some.StackOverflow.com");
        R_4.addContact(ContactType.MAIL, "frenkel@meta.ua");
        R_4.addContact(ContactType.PHONE, "+380967270027");
        R_6.addContact(ContactType.MAIL, "donald@duck.com");
        R_5.addContact(ContactType.MAIL, "john@snow.com");
        R_7.addContact(ContactType.MAIL, "marry@poppins.com");
        R_8.addContact(ContactType.MAIL, "tom@readl.com");

    }

    private void getConnection() {
        try (InputStream inputStream = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(inputStream);
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        MainTestSql mainTestSql = new MainTestSql();
        mainTestSql.getConnection();
        mainTestSql.storage.clear();
        mainTestSql.storage.save(R_1);
        mainTestSql.storage.save(R_2);
        mainTestSql.storage.save(R_3);
        mainTestSql.storage.save(R_4);
        mainTestSql.storage.save(R_5);
        mainTestSql.storage.save(R_6);
        mainTestSql.storage.save(R_7);
        mainTestSql.storage.save(R_8);
    }
}
