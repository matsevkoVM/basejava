package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.JSONStreamStrategy;

class JSONFileStorageTest extends AbstractStorageTest {

    public JSONFileStorageTest() {
        super(new FileStorage(STORAGE_DIR_FOR_FILE, new JSONStreamStrategy()));
    }
}