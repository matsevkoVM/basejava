package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.XMLStreamStrategy;

class XMLPathStorageTest extends AbstractStorageTest {

    protected XMLPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new XMLStreamStrategy()));
    }
}