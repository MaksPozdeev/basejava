package ru.javawebinar.basejava.exception;

/**
 * Maksim P.
 * 10.08.19
 */

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
