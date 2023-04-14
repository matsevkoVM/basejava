package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.ObjectStreamFIleStrategy;

class ObjectFIleStorageTest extends AbstractStorageTest {

    public ObjectFIleStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamFIleStrategy()));
    }
}