package org.learn.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record Student(int id, String name) implements Comparable<Student> {
    @Override
    public int compareTo(Student other) {
        int idComparison = Integer.compare(this.id, other.id);
        if (idComparison != 0) {
            return idComparison;
        }
        return this.name.compareTo(other.name);
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "Alice"));
        students.add(new Student(1, "Bob"));

        Collections.sort(students); // Sorts using compareTo method

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
