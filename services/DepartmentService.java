package pro.sky.homework_2_13.services;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_13.Employee;

import java.util.*;
import java.util.stream.Collectors;

    @Service
    public class DepartmentService{
         /*  private  final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;*/

        private static final int SIZE = 10;
        private Employee[] employees = new Employee[SIZE];

        DepartmentService() {
            employees[0] = new Employee("Ivan","Ivanov1", 1, 56);
            employees[1] = new Employee("Ivan","Ivanov2", 2, 98);
            employees[2] = new Employee("Ivan","Ivanov3", 3, 56);
            employees[3] = new Employee("Ivan","Ivanov4", 2, 45);
            employees[4] = new Employee("Ivan","Ivanov6", 1, 39);
            employees[5] = new Employee("Ivan","Ivanov6", 2, 79);
            employees[6] = new Employee("Ivan","Ivanov7", 3, 59);

        }
        public Employee getEmpWithMaxSalary (int department)  {
            return Arrays.stream(employees)
                    .filter(e -> e.getDepartment() == department)
                    .max(Comparator.comparingDouble(Employee::getSalary))
                    .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
        }
        public Employee getEmpWithMinSalary (int department){
            return Arrays.stream(employees)
                    .filter(e -> e.getDepartment() == department)
                    .min(Comparator.comparingDouble(Employee::getSalary))
                    .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
        }
        public List<Employee> showAll(){
            return  Arrays.stream(employees)
                    .filter(e-> e!= null)
                    .sorted(Comparator.comparingInt(Employee::getDepartment))
                    .collect(Collectors.toList());
        }
        public List<Employee> showByDepartment(Integer id) {
            return Arrays.stream(employees)
                    .filter(e -> e != null)
                    .filter(e -> e.getDepartment() == id)
                    .collect(Collectors.toList());
        }
    }


/*
    public Employee employeeWithMaxSalary (int department){
    return employeeService.getAll().stream()
            .filter(employee -> employee.getDepartment() == department)
            .max(Comparator.comparingDouble(Employee::getSalary))
            .orElseThrow(EmployeeNotFoundException::new);
    }
}

*/

/*
public Employee getEmpWithMaxSalary (int department)  {
        return employeeService.getAll().stream()
        .filter(e -> e.getDepartment() == department)
        .max(Comparator.comparingDouble(Employee::getSalary))
        .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
        }
public Employee getEmpWithMinSalary (int department){
        return Arrays.stream(employees)
        .filter(e -> e.getDepartment() == department)
        .min(Comparator.comparingDouble(Employee::getSalary))
        .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
        }
public List<Employee> showAll(){
        return  Arrays.stream(employees)
        .filter(e-> e!= null)
        .sorted(Comparator.comparingInt(Employee::getDepartment))
        .collect(Collectors.toList());
        }
public List<Employee> showByDepartment(Integer id) {
        return Arrays.stream(employees)
        .filter(e -> e != null)
        .filter(e -> e.getDepartment() == id)
        .collect(Collectors.toList());
        }
}
*/

/*@Service
public class DepartmentService implements DepartmentServiceInt {
    private  final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }
    public void changeDepartment(Employee employee, int newDepartment){
        employeeService.getAll().stream()
                .filter(value -> Object.equals(employee, value))
                .findFirst()
                .ifPresent(value ->value.setDepartment(newDepartment));
    }
    @Override
    public Employee getEmpWithMaxSalary(int department)  {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
    }
    @Override
    public Employee getEmpWithMinSalary (int department){
        return Arrays.stream(employees)
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("Department number is invalid"));
    }
    public List<Employee> showAll(){
        return  Arrays.stream(employees)
                .filter(e-> e!= null)
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());
    }
    public List<Employee> showByDepartment(Integer id) {
        return Arrays.stream(employees)
                .filter(e -> e != null)
                .filter(e -> e.getDepartment() == id)
                .collect(Collectors.toList());
    }
}*/
/*

}*/
