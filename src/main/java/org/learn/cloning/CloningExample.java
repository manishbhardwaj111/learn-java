package org.learn.cloning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class MyClass implements Cloneable {
    private int id;
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return this;
        return super.clone();
    }
}

public class CloningExample {
    public static void main(String[] args) {
        // Creating an object
        MyClass originalObject = new MyClass(1, "Original");

        try {
            // Cloning the object
            MyClass clonedObject = (MyClass) originalObject.clone();

            // Modifying the cloned object
            clonedObject.setName("Cloned");

            // Printing original and cloned objects
            System.out.println("Original object: " + originalObject);
            System.out.println("Cloned object: " + clonedObject);
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }
}
