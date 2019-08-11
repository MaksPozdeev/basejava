package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage NOT_SORTED_ARRAY_STORAGE = new ArrayStorage();
    private static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    //    private static final Storage ARRAY_STORAGE = NOT_SORTED_ARRAY_STORAGE;
    private static final Storage ARRAY_STORAGE = SORTED_ARRAY_STORAGE;

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
//        r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid2");
//        r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid3");
//        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
