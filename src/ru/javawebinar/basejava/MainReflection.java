package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.*;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume("uuidValue_01");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "uuidValue_02");
        System.out.println(resume);

        Class<? extends Resume> class1 = resume.getClass();
        //        class methods
        /*System.out.println("Methods");
        Method[] methods = clss.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println("-> " + Modifier.toString(method.getModifiers()));
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter: parameters){
                System.out.println(parameter.getName());
                System.out.println(parameter.getType().getName());
            }
            System.out.println();
        }*/

        System.out.println();
        Method method = class1.getMethod("toString");
        String strResult = (String) method.invoke(resume);
        System.out.println("Running \"toString\" from Reflection: "+ strResult);
    }
}
