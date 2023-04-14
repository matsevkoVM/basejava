package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.JSONStreamStrategy;

class JSONPathStorageTest extends AbstractStorageTest {

    protected JSONPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JSONStreamStrategy()));
    }
}