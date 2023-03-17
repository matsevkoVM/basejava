package com.urise.webapp.exception;

public class NotExistResumeException extends StorageException {

    public NotExistResumeException(String uuid) {
        super("Resume " + uuid + " does not exist", uuid);
    }
}
