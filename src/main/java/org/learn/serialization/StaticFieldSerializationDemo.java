package org.learn.serialization;

import java.io.*;

// Define a class with a static field and an instance field
class MyClass implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    // Static field
    public static String staticField = "Initial static value";
    
    // Instance field
    public String instanceField;
    
    // Constructor
    public MyClass(String instanceField) {
        this.instanceField = instanceField;
    }
}

public class StaticFieldSerializationDemo {
    public static void main(String[] args) {
        MyClass object = new MyClass("Initial instance value");

        // Serialize the object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.ser"))) {
            oos.writeObject(object);
            System.out.println("Object serialized with staticField: " + MyClass.staticField +
                               " and instanceField: " + object.instanceField);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Modify the static field after serialization
        MyClass.staticField = "Modified static value";

        // Deserialize the object
        MyClass deserializedObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.ser"))) {
            deserializedObject = (MyClass) ois.readObject();
            System.out.println("Object deserialized with staticField: " + MyClass.staticField +
                               " and instanceField: " + deserializedObject.instanceField);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
