package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 *
 * @autor Maksim P.
 */
public class ArrayStorage implements Storage {

    private static final int SIZE_STORAGE = 10000;
    private int countResume = 0;

    private Resume[] storage = new Resume[SIZE_STORAGE];

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void save(Resume resume) {
        if (countResume == SIZE_STORAGE) {
            System.out.println("Cannot be saved: the storage is full!");
            return;
        }
        if (resume != null) {
            if (findIndex(resume.getUuid()) != -1) {
                System.out.println("ru.javawebinar.basejava.model.Resume " + resume.getUuid() + "already exists!");
                return;
            }
            storage[countResume] = resume;
            countResume++;
        }
    }

    public void update(Resume resume) {
        if (resume != null) {
            int index = findIndex(resume.getUuid());
            if (index != -1) {
                storage[index] = resume;
            } else {
                System.out.println("ru.javawebinar.basejava.model.Resume not found");
            }
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume (uuid: " + uuid + ") not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
        } else {
            System.out.println("ru.javawebinar.basejava.model.Resume (uuid: " + uuid + ") not found");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume);
    }

    public int size() {
        return countResume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}