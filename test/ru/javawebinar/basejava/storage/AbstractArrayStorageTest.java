package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    //    Constructor
    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertSame(storage.get(UUID_4), RESUME_4);
    }

    @Test
    void saveExist(){
        assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_2);
        });

    }

    @Test
    void saveOverflow(){
        assertThrows(StorageException.class, () -> {
            for (int i = storage.size()+1; i <= AbstractArrayStorage.SIZE_STORAGE; i++) {
                storage.save(new Resume());
            }
            storage.save(new Resume());
        });
    }

    //        2 варианта для update:
//        1. Запись есть и нужно обновить/заменить
//        2. Обновляемой записи нет -> нечего обновлять -> notExistException (updateNotExist)
    @Test
    void update() {
        Resume resumeUpdate = new Resume(UUID_2);
        storage.update(resumeUpdate);
//        assertEquals(resumeUpdate, storage.get(UUID_2));
        assertSame(resumeUpdate, storage.get(UUID_2));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_4);
        });
    }

    //        2 варианта для delete:
//        1. Запись есть -> удаляем + размер = размер - 1 -> удалённый обект notExistException
//        2. Удаляемой записи нет -> notExistException (deleteNotExist)
    @Test
    void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_2);
        });
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_4);
        });
    }

    //        2 варианта для get:
//        1. Запись есть -> получаем сравниваем
//        2. Записи нет -> notExistException (getNotExist)
    @Test
    void get() {
        assertSame(RESUME_1, storage.get(UUID_1));
        assertSame(RESUME_2, storage.get(UUID_2));
        assertSame(RESUME_3, storage.get(UUID_3));

    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_4);
        });
    }

    @Test
    void getAll() {
        Resume[] expected = storage.getAll();
        Resume[] actual = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, actual);
    }


}