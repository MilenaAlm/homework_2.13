package pro.sky.homework_2_13.services;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.Exceptions.DepartmentNotFoundException;
import pro.sky.homework_2_13.controller.DepartmentController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    public final EmployeeService employeeService;
    private final DepartmentController departmentController;

    public DepartmentService(EmployeeService employeeService, DepartmentController departmentController) {
        this.employeeService = employeeService;
        this.departmentController = departmentController;
    }
    public double minSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double maxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }
    public double sumSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    public List<Employee> employeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> employeesGroupedByDepartment(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}


