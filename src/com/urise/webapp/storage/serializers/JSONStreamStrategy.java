package com.urise.webapp.storage.serializers;

import com.urise.webapp.model.Resume;
import com.urise.webapp.utils.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONStreamStrategy implements SerializationStrategy {

    @Override
    public void fileWriter(Resume r, OutputStream output) throws IOException {
        try (Writer writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)) {
            JSONParser.write(r, writer);
        }
    }

    @Override
    public Resume fileReader(InputStream input) throws IOException {
        try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            return JSONParser.read(reader, Resume.class);
        }
    }
}
