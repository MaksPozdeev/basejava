package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 *
 * @autor Maksim P.
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int SIZE_STORAGE = 10000;
    protected Resume[] storage = new Resume[SIZE_STORAGE];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume (uuid: " + uuid + ") not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

}