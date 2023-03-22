package com.urise.webapp.storage.serializers;

import com.urise.webapp.model.*;
import com.urise.webapp.utils.XMLParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLStreamStrategy implements SerializationStrategy{
    private final XMLParser xmlParser;

    public XMLStreamStrategy(){
        xmlParser = new XMLParser(Resume.class, Organization.class, Link.class, OrganizationSection.class, TextSection.class,
                ListSection.class, Organization.Position.class);
    }

    @Override
    public void fileWriter(Resume r, OutputStream output) throws IOException {
        try(Writer writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)){
            xmlParser.marshal(r, writer);
        }
    }

    @Override
    public Resume fileReader(InputStream input) throws IOException {
        try(Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)){
           return xmlParser.unmarshal(reader);
        }
    }
}
