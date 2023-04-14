package com.urise.webapp.storage;

import com.urise.webapp.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(SqlStorage.sqlStorage);
    }

    @Test
    void getCredentials() {
        assertEquals("jdbc:postgresql://localhost:5432/resumes", SqlStorage.getCredentials(Config.getInstance().getDbUrl(), "db.url"));
        assertEquals("postgres", SqlStorage.getCredentials(Config.getInstance().getDbUrl(), "db.user"));
        assertEquals("848867", SqlStorage.getCredentials(Config.getInstance().getDbUrl(), "db.password"));
    }
}