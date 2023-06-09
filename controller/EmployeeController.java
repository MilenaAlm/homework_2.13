package pro.sky.homework_2_13.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework_2_13.Employee;
import pro.sky.homework_2_13.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String welcome (){
        return "добро пожаловать!";
    }

    @GetMapping("/add")
    public Employee add (
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName" ) String lastname,
            @RequestParam ("department") int department,
            @RequestParam ("salary" ) int salary) {
        return employeeService.add(firstName, lastname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove (
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName" ) String lastname,
            @RequestParam ("department") int department,
            @RequestParam ("salary" ) int salary) {
        return employeeService.remove(firstName, lastname);
    }

    @GetMapping("/find")
    public Employee find (
            @RequestParam ("firstName") String firstName,
            @RequestParam ("lastName" ) String lastname,
            @RequestParam ("department") int department,
            @RequestParam ("salary" ) int salary) {
        return employeeService.find(firstName, lastname);
    }
}
