package pro.sky.homework_2_13.controller;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;

    }
    @GetMapping(value = "/{id}/employees")
    public List<Employee> employeesFromDepartment(@PathVariable int id) {
        return departmentService.employeesFromDepartment(id);
    }
    @GetMapping( "/{id}/salary/sum")
        public double sumSalaryFromDepartment(@PathVariable int department) {
        return departmentService.sumSalaryFromDepartment(department);

    }
    @GetMapping( "/{id}/salary/max")
        public double maxSalaryFromDepartment(@PathVariable int id) {
            return departmentService.maxSalaryFromDepartment(id);
    }
    @GetMapping( "/{id}/salary/min")
        public double minSalaryFromDepartment(@PathVariable int id) {
            return departmentService.minSalaryFromDepartment(id);
    }
    @GetMapping( "/employees")
    public Map<Integer, List<Employee>> employeesGroupByDepartment() {
        return departmentService.employeesGroupedByDepartment();
    }

}