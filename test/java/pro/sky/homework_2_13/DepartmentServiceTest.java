package pro.sky.homework_2_13;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework_2_13.Exceptions.DepartmentNotFoundException;
import pro.sky.homework_2_13.services.DepartmentService;
import pro.sky.homework_2_13.services.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> employees;

    public static Stream<Arguments> maxSalaryFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1, 60),
                Arguments.of(2, 90),
                Arguments.of(3, 100));
    }

    public static Stream<Arguments> minSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 30),
                Arguments.of(2, 70),
                Arguments.of(3, 100)
        );
    }
    public static Stream<Arguments> sumSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 90),
                Arguments.of(2, 160),
                Arguments.of(3, 100),
                Arguments.of(4, 0)
        );
    }

    public static Stream<Arguments> employeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Ivan", "Ivanov", 1, 60),
                                new Employee("Maria", "Ivanova", 1, 30)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Petr", "Petrov", 2, 70),
                                new Employee("Irina", "Petrova", 2, 90)
                        )
                ),
                Arguments.of(
                        3,
                        Collections.singletonList(
                                new Employee("Elena", "Ivanova", 3, 100)
                        )
                ),
                Arguments.of(
                        4,
                        Collections.emptyList()
                )
        );
    }


    @BeforeEach
    public void beforeEach() {
        employees = List.of(
                new Employee("Ivan", "Ivanov", 1, 60),
                new Employee("Maria", "Ivanova", 1, 30),
                new Employee("Petr", "Petrov", 2, 70),
                new Employee("Irina", "Petrova", 2, 90),
                new Employee("Elena", "Ivanova", 3, 100)
        );
        Mockito.when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("maxSalaryFromDepartmentParams")
    public void maxSalaryFromDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.maxSalaryFromDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void maxSalaryFromDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("minSalaryFromDepartmentTestParams")
    public void minSalaryFromDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.minSalaryFromDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void minSalaryFromDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.minSalaryFromDepartment(4));
    }
    @ParameterizedTest
    @MethodSource("sumSalaryFromDepartmentTestParams")
    public void sumSalaryFromDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.sumSalaryFromDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void sumSalaryFromDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.sumSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentTestParams")
    public void employeesFromDepartmentTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.employeesFromDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void employeeGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Ivan", "Ivanov", 1, 60),
                        new Employee("Maria", "Ivanova", 1, 30)
                ),
                2,
                List.of(
                        new Employee("Petr", "Petrov", 2, 70),
                        new Employee("Irina", "Petrova", 2, 90)
                ),
                3,
                Collections.singletonList(new Employee("Elena", "Ivanova", 3, 100))
        );
        Assertions.assertThat(departmentService.employeesGroupedByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
