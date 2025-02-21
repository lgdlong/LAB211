package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Long", 30);

        readObject();
    }

    public static void writeObject(Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/person.dat"))) {
            oos.writeObject(person);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // write person list
    public static void writeObjectList(List<Person> persons) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/persons.dat"))) {
            oos.writeObject(persons);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/person.dat"))) {
            Person person = (Person) ois.readObject();
            System.out.println(person.getName());
            System.out.println(person.getAge());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // read person list
    public static void readObjectList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persons.dat"))) {
            List<Person> persons = (List<Person>) ois.readObject();
            for (Person person : persons) {
                System.out.println(person.getName());
                System.out.println(person.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
}