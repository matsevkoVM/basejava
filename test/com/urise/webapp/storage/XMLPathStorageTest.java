package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.XMLStreamStrategy;

class XMLPathStorageTest extends AbstractStorageTest {

    protected XMLPathStorageTest() {
        super(new PathStorage(XML_STORAGE_DIR_FOR_PATH, new XMLStreamStrategy()));
    }
}