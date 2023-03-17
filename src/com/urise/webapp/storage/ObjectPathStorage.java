package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectPathStorage extends AbstractPathStorage {


    protected ObjectPathStorage(String directory) {
        super(directory);
    }

    @Override
    protected void writePath(Resume r, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(r);
        }

    }

    @Override
    protected Resume readPath(InputStream inputStream) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Failed to read resume", null, e);
        }
    }
}
