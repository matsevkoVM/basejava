package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.DataStreamStrategy;

class DataFIleStorageTest extends AbstractStorageTest {

    public DataFIleStorageTest() {
        super(new FileStorage(DATA_FILE, new DataStreamStrategy()));
    }
}