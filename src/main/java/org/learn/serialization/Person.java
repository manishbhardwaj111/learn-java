package org.learn.serialization;

import java.io.*;

class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    // Method to serialize the object
    public static void serialize(Person person, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(person);
            System.out.println("Serialization Successful: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to deserialize the object
    public static Person deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Person person = (Person) ois.readObject();
            System.out.println("Deserialization Successful: " + person);
            return person;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("John Doe", 30);
        String fileName = "person.ser";

        // Serialize the object
        serialize(person, fileName);

        // Deserialize the object
        Person deserializedPerson = deserialize(fileName);
        System.out.println(deserializedPerson);
    }
}
