package pro.sky.homework_2_13.services;

import pro.sky.homework_2_13.Employee;

public interface EmployeeServiceInt {
    Employee addEmployee(String firstName,
                         String lastName, int department, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName, int department, double salary);

    Employee findEmployee(String firstName, String lastName, int department, double salary);

    /*Employee addEmployee(String firstName, String lastName);*/
}
