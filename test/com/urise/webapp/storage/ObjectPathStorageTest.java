package com.urise.webapp.storage;

class ObjectPathStorageTest extends AbstractStorageTest {

    protected ObjectPathStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR_FOR_PATH));
    }
}