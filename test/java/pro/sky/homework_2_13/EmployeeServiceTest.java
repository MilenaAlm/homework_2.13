package pro.sky.homework_2_13;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.homework_2_13.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.homework_2_13.Exceptions.EmployeeIncorrectFirstNameException;
import pro.sky.homework_2_13.Exceptions.EmployeeIncorrectLastNameException;
import pro.sky.homework_2_13.Exceptions.EmployeeNotFoundException;
import pro.sky.homework_2_13.services.EmployeeService;
import pro.sky.homework_2_13.services.ValidatorService;

import java.util.stream.Stream;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());


    @BeforeEach
    public void beforeEach() {
        employeeService.add("Ivan", "Ivanov", 1, 57);
        employeeService.add("Petr", "Petrov", 2, 67);
        employeeService.add("Sidor", "Sidorov", 3, 98);
    }

    @AfterEach
    public void afterEach() {
        employeeService.getAll()
                .forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName()));
    }

    public static Stream<Arguments> addWithIncorrectFirstNameTestParams() {
        return Stream.of(
                Arguments.of("Ivan3"),
                Arguments.of("Ivan!"),
                Arguments.of("Ivan@")
        );
    }

    public static Stream<Arguments> addWithIncorrectLastNameTestParams() {
        return Stream.of(
                Arguments.of("Ivanov3"),
                Arguments.of("Ivanov!"),
                Arguments.of("Ivanov@")
        );
    }

    @Test
    public void addTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Roman", "Ivanov", 1, 87);

        Assertions.assertThat(employeeService.add("Roman", "Ivanov", 1, 87))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeeService.find("Roman", "Ivanov")).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectFirstNameTestParams")
    public void addWithIncorrectFirstNameTest(String IncorrectFirstName) {
        Assertions.assertThatExceptionOfType(EmployeeIncorrectFirstNameException.class)
                .isThrownBy(() -> employeeService.add(
                        IncorrectFirstName,
                        "Ivanov",
                        1,
                        87));
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectLastNameTestParams")
    public void addWithIncorrectLastNameTest(String IncorrectLastName) {
        Assertions.assertThatExceptionOfType(EmployeeIncorrectLastNameException.class)
                .isThrownBy(() -> employeeService.add(
                        "Roman",
                        IncorrectLastName,
                        1,
                        89));
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Ivan", "Ivanov", 1, 88));
    }

    @Test
    public void addWhenStorageIsFullTest() {
        Stream.iterate(1, i -> i +1)
                .limit(7)
                .map(number -> new Employee(
                        "Roma" + ((char) ('a' + number)),
                        "Romanov" + ((char) ('a' + number)),
                        number,
                        89 + number)
                )
                .forEach(employee ->
                        employeeService.add(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getDepartment(),
                                employee.getSalary()
                        ));
    }

    @Test
    public void removeTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 87);

        Assertions.assertThat(employeeService.remove("Ivan", "Ivanov"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Ivanov", "Ivanov"));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Vasia", "Vasilyev"));
    }

    @Test
    public void findTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 87);

        Assertions.assertThat(employeeService.find("Ivan", "Ivanov"))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(beforeCount);
    }

    @Test
    public void findWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Vasia", "Vasilyev"));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Ivan", "Ivanov", 1, 57),
                        new Employee("Petr", "Petrov", 2, 67),
                        new Employee("Sidor", "Sidorov", 3, 98)
                );
    }
}