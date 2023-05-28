package pro.sky.homework_2_13.services;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_13.Exceptions.EmployeeNotFoundException;
import pro.sky.homework_2_13.Exceptions.EmployeeStorageIsFullException;


@Service
public class EmployeeService implements EmployeeServiceInt {
    private static final int size  = 10;
    private final Employee[] employees = new Employee[size];

    private final ValidatorService validatorService;
    private Iterable<Object> all;
    private int department;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public Employee addEmployee(String firstName,
                                String lastName, int department, double salary) {
        int indexForAdd = -1;
        Employee employee = new Employee(
                validatorService.validateFirstName(firstName),
                validatorService.validateLastName(lastName),
                department,
                salary
        );
        for (int i = 0; i < employees.length ; i++) {
            if (employees[i] == null && indexForAdd == -1) {
                indexForAdd = i;
            }
            if(employees[i]!=null && employees.equals(employee)){
                throw new EmployeeAlreadyAddedException();
                }
            }
            if (indexForAdd ==-1) {
                throw new EmployeeStorageIsFullException();
            }
            employees [indexForAdd] = employee;
            return employees [indexForAdd];
        }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        return null;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return null;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        for (int i = 0; i < employees.length; i++) {
            if (employee.equals(employees[i])) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        for (Employee emp : employees) {
            if (employees.equals(emp)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Iterable<Object> getAll() {
        return all;
    }
}
