package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    protected abstract void writeFile(Resume r, File file) throws IOException;

    protected abstract List<Resume> getAllFromFile();

    protected abstract Resume getFromFile(File file);

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void specificSave(Resume r, File file) {
        try {
            file.createNewFile();
            writeFile(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }

    }

    @Override
    protected void specificUpdate(Resume r, File file) {
        try {
            writeFile(r, file);
        }catch (IOException e){
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume specificGet(File file) {
            return getFromFile(file);
    }

    @Override
    protected void specificDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> specificGetAll() {
        File[] files = directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        if (files != null) {
            for (File f : files) {
                resumes.add(getFromFile(f));
            }
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null){
            for (File f : files){
                f.delete();
            }
        }
    }

    @Override
    public int size() {
        int size = 0;
        File[] files = directory.listFiles();
        if (files != null){
            size = files.length;
        }
        return size;
    }
}
