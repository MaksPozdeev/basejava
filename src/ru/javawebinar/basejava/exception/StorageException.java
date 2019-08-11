package ru.javawebinar.basejava.exception;

/**
 * Maksim P.
 * 10.08.19
 */

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
