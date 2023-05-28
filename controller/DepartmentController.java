package pro.sky.homework_2_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.services.DepartmentService;

import java.util.List;
@RestController
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("/departments")
    public String welcome() {
        return "Welcome to departments";
    }
    @GetMapping("/departments/max-salary")
    public Employee getEmpWithMaxSalary(@RequestParam("departmentId") Integer id) {
            return service.getEmpWithMaxSalary(id);
        }

        @GetMapping("/departments/min-salary")
        public Employee getEmpWithMinSalary(@RequestParam("departmentId") Integer id) {
            return service.getEmpWithMinSalary(id);
        }

        @GetMapping("/departments/all")
        public List<Employee> getEmpByDepartment(@RequestParam(value = "departmentId",
                required = false) Integer id) {
            if ((id == null)) {
                return service.showAll();
            }
            return service.showByDepartment(id);
        }
    }

