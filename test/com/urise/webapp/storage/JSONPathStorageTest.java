package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.JSONStreamStrategy;

class JSONPathStorageTest extends AbstractStorageTest {

    protected JSONPathStorageTest() {
        super(new PathStorage(JSON_STORAGE_DIR_FOR_PATH, new JSONStreamStrategy()));
    }
}