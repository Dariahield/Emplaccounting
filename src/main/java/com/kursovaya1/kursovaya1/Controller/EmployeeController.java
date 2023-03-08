package com.kursovaya1.kursovaya1.Controller;


import com.kursovaya1.kursovaya1.Service.EmployeeService;
import com.kursovaya1.kursovaya1.entity.Employee;
import com.kursovaya1.kursovaya1.exception.EmployeeAlreadyAddedException;
import com.kursovaya1.kursovaya1.exception.EmployeeNotFoundException;
import com.kursovaya1.kursovaya1.exception.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException (EmployeeNotFoundException e) {
        return String.format("%s %s",HttpStatus.NOT_FOUND.value(),e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException (EmployeeAlreadyAddedException e) {
        return String.format("%s %s",HttpStatus.NOT_FOUND.value(),e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handleException (EmployeeStorageIsFullException e) {
        return String.format("%s %s",HttpStatus.NOT_FOUND.value(),e.getMessage());
    }

    private final EmployeeService employeeService;
    private EmployeeService employeeService1;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")

    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);

    }

    @GetMapping(path = "/findAll")
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }
}
