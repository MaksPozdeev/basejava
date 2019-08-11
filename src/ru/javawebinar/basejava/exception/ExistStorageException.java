package ru.javawebinar.basejava.exception;

/**
 * Maksim P.
 * 10.08.19
 */

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
