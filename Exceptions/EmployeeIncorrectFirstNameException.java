package pro.sky.homework_2_13.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeIncorrectFirstNameException extends RuntimeException {
}
