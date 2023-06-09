package pro.sky.homework_2_13.services;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_13.Exceptions.EmployeeNotFoundException;
import pro.sky.homework_2_13.Exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService  {
    private static final int LIMIT  = 10;
    private final List<Employee> employees = new ArrayList<>();

    private final ValidatorService validatorService;
    private int department;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }
    public Employee add(String firstName,
                        String lastName,
                        int department,
                        double salary) {
        Employee employee = new Employee(
                validatorService.validateFirstName(firstName),
                validatorService.validateLastName(lastName),
                department,
                salary
        );
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    public Employee remove(String firstName,
                           String lastName) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String firstName,
                         String lastName) {
        return employees.stream()
                .filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }

}