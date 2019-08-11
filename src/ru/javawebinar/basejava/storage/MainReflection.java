package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("uuidValue_01");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "uuidValue_02");
        System.out.println(resume);
    }
}
