package com.urise.webapp.storage;

import com.urise.webapp.storage.serializers.XMLStreamStrategy;

class XMLFIleStorageTest extends AbstractStorageTest {

    public XMLFIleStorageTest() {
        super(new FileStorage(XML_STORAGE_DIR_FOR_FILE, new XMLStreamStrategy()));
    }
}