package com.urise.webapp.storage.serializers;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamFIleStrategy implements SerializationStrategy {

    @Override
    public void fileWriter(Resume r, OutputStream output) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume fileReader(InputStream input) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Failed to read resume", null, e);
        }
    }
}
