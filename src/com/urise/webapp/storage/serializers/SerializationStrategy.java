package com.urise.webapp.storage.serializers;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void fileWriter(Resume r, OutputStream output) throws IOException;

    Resume fileReader(InputStream input) throws IOException;
}
