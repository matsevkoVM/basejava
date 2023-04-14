package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.ObjectStreamPathStrategy;

class ObjectPathStorageTest extends AbstractStorageTest {

    protected ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamPathStrategy()));
    }
}