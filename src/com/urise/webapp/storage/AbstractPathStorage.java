package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must be not null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
    }

    protected abstract void writePath(Resume r, OutputStream outputStream) throws IOException;

    protected abstract Resume readPath(InputStream inputStream) throws IOException;

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(uuid).toAbsolutePath();
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void specificSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Cannot create file \"" + path + "\" ", path.getFileName().toString(), e);
        }
        specificUpdate(r, path);
    }

    @Override
    protected void specificUpdate(Resume r, Path path) {
        try {
            writePath(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume specificGet(Path path) {
        try {
            return readPath(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path read error " + path.getFileName().toAbsolutePath(), path.getFileName().toString(), e);
        }
    }

    @Override
    protected void specificDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> specificGetAll() {
        List<Resume> resumeList = new ArrayList<>();
        try (Stream<Path> stream = Files.list(directory)) {
            List<Path> pathList = stream.toList();
            for (Path p : pathList) {
                resumeList.add(specificGet(p));
            }
        } catch (IOException e) {
            throw new StorageException("Error, wrong path ", null, e);
        }
        return resumeList;
    }

    @Override
    public void clear() {
        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(this::specificDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        List<Path> list;
        try (Stream<Path> stream = Files.list(directory)) {
            list = stream.toList();
        } catch (IOException e) {
            throw new StorageException("Error, wrong path", null, e);
        }
        return list.size();
    }
}