package com.urise.webapp.storage;

import com.urise.webapp.utils.Config;

class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }

}