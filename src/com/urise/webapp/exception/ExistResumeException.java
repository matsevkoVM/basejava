package com.urise.webapp.exception;

public class ExistResumeException extends StorageException{

    public ExistResumeException(String uuid) {
        super("Resume " + uuid + " already exist",uuid);
    }
}
