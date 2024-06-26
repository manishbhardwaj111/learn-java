package org.learn.cloning;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@AllArgsConstructor
class Address implements Cloneable {
    String city;
    String state;

    @Override
    public Object clone() throws CloneNotSupportedException {
        BigDecimal bg;
        return super.clone(); // Shallow copy
    }
}

@Data
@AllArgsConstructor
class Person implements Cloneable {
    String name;
    Address address;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone(); // Shallow copy
        cloned.address = (Address) address.clone(); // Deep copy of address
        return cloned;
    }
}

public class DeepCloningExample {
    public static void main(String[] args) {
        try {
            Address address = new Address("New York", "NY");
            Person person1 = new Person("John", address);

            Person person2 = (Person) person1.clone(); // Deep cloning

            System.out.println("Before modification:");
            System.out.println("Person 1: " + person1);
            System.out.println("Person 2: " + person2);

            CopyOnWriteArrayList k;

            // Modify the address of the cloned person
            person2.address.city = "Los Angeles";

            System.out.println("\nAfter modification:");
            System.out.println("Person 1: " + person1);
            System.out.println("Person 2: " + person2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
