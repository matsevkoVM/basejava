package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(Storage storage){
        super(storage);
    }

    @Test
    void saveStorageOverflow() throws StorageException{
        try {
            int fullNameEnumeration = 0;
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(fullNameEnumeration + " name"));
                fullNameEnumeration ++;
            }
        } catch (StorageException e){
            Assertions.fail();
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("uuid", "dummy")));
    }

}