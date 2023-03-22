package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializers.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private final SerializationStrategy strategy;

    protected PathStorage(String dir, SerializationStrategy strategy) {
        directory = Paths.get(dir);
        this.strategy = strategy;
        Objects.requireNonNull(directory, "directory must be not null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void specificSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Cannot create file \"" + path + "\" ", getFileName(path), e);
        }
        specificUpdate(r, path);
    }

    @Override
    protected void specificUpdate(Resume r, Path path) {
        try {
            strategy.fileWriter(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", r.getUuid(), e);
        }
    }

    @Override
    protected Resume specificGet(Path path) {
        try {
            return strategy.fileReader(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error " + path, getFileName(path), e);
        }
    }

    @Override
    protected void specificDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Fail to delete ", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> specificGetAll() {
        return getListOfFiles().map(this::specificGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getListOfFiles().forEach(this::specificDelete);
    }

    @Override
    public int size() {
        return (int) getListOfFiles().count();
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getListOfFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Error, wrong path", e);
        }
    }
}