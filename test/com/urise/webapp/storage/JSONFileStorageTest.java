package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.JSONStreamStrategy;

class JSONFileStorageTest extends AbstractStorageTest {

    public JSONFileStorageTest() {
        super(new FileStorage(JSON_STORAGE_DIR_FOR_FILE, new JSONStreamStrategy()));
    }
}