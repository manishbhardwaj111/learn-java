package org.learn.streams;

import java.util.*;
import java.util.stream.Collectors;

record Employee (String name, String department, double salary, boolean isActive){
}

public class EmployeeStreamQuestions {

  public static void main(String[] args) {
    List<Employee> employees = List.of(
            new Employee("Xbc", "Sales", 95000.0, false),
            new Employee("Alice", "Engineering", 100000.0, true),
            new Employee("Bob", "Sales", 85000.0,true),
            new Employee("Charlie", "Engineering", 120000.0, true),
            new Employee("David", "Marketing", 90000.0, true),
            new Employee("Eve", "Sales", 70000.0, true)
    );

    Map<String, Optional<Employee>> maxSalaryByDepartment = employees.stream()
        .collect(Collectors.groupingBy(Employee::department,  LinkedHashMap::new,
            Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
    System.out.println(maxSalaryByDepartment);

    Map<String, List<String>> topEmployees =
            employees.stream()
                    .collect(Collectors.groupingBy(x -> x.isActive() ? "Active" : "Inactive",
                            Collectors.mapping(Employee::name, Collectors.toList()))
                    );
    System.out.println(topEmployees);

  }
}
