package com.urise.webapp.storage.serializers;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements SerializationStrategy {

    @Override
    public void fileWriter(Resume r, OutputStream output) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(output)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), pair -> {
                dos.writeUTF(pair.getKey().name());
                dos.writeUTF(pair.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), pair -> {
                SectionType type = pair.getKey();
                Section section = pair.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENTS, QUALIFICATIONS ->
                            writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
                            writeCollection(dos, ((OrganizationSection) section).getOrganizations(), org -> {
                                dos.writeUTF(org.getHomePage().getName());
                                dos.writeUTF(org.getHomePage().getUrl());
                                writeCollection(dos, org.getPositions(), position -> {
                                    writeLocalDate(dos, position.getStartDate());
                                    writeLocalDate(dos, position.getEndDate());
                                    dos.writeUTF(position.getTitle());
                                    dos.writeUTF(position.getDescription());
                                });
                            });
                }
            });
        }
    }

    @Override
    public Resume fileReader(InputStream input) throws IOException {
        try (DataInputStream dis = new DataInputStream(input)) {
            String uuid = dis.readUTF();
            String fillName = dis.readUTF();
            Resume resume = new Resume(uuid, fillName);
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENTS, QUALIFICATIONS -> {
                return new ListSection(readCollection(dis, dis::readUTF));
            }
            case EXPERIENCE, EDUCATION -> {
                return new OrganizationSection(readCollection(dis, () ->
                        new Organization(new Link(dis.readUTF(), dis.readUTF()), readCollection(dis, () ->
                                new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF())))
                ));
            }
            default -> throw new IllegalStateException();
        }
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private <T> List<T> readCollection(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }
}
