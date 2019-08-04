package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 *
 * @autor Maksim P.
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == SIZE_STORAGE) {
            System.out.println("Cannot be saved: the storage is full!");
            return;
        }
        if (resume != null) {
            if (getIndex(resume.getUuid()) != -1) {
                System.out.println("ru.javawebinar.basejava.model.Resume " + resume.getUuid() + "already exists!");
                return;
            }
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        if (resume != null) {
            int index = getIndex(resume.getUuid());
            if (index != -1) {
                storage[index] = resume;
            } else {
                System.out.println("ru.javawebinar.basejava.model.Resume not found");
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume (uuid: " + uuid + ") not found");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}