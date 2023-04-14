package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.DataStreamStrategy;

class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataStreamStrategy()));
    }
}