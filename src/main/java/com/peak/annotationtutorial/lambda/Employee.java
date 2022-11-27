package com.peak.annotationtutorial.lambda;

/**
 * @author James Liu
 * @date 11/27/2022 -- 4:52 PM
 */
public class Employee {

    double salary;
    String department ;
    String name ;

    public Employee(double salary, String department, String name) {
        this.salary = salary;
        this.department = department;
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
