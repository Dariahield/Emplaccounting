package com.kursovaya1.kursovaya1.Service;

import com.kursovaya1.kursovaya1.entity.Employee;
import com.kursovaya1.kursovaya1.exception.EmployeeAlreadyAddedException;
import com.kursovaya1.kursovaya1.exception.EmployeeNotFoundException;
import com.kursovaya1.kursovaya1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private static final int MAX_EMPLOYEES_COUNT = 5;

    private final Map<Integer, Employee> employeeByHashCode = new HashMap<>();

    public Employee add(String firstName, String lastName) {

        if (employeeByHashCode.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Хранилище сотрудников переполнено");

        }
        Employee employee = new Employee(firstName, lastName);
        int employeeHashCode = employee.hashCode();

        if (employeeByHashCode.containsKey(employeeHashCode)) {
            throw new EmployeeAlreadyAddedException("В Хранилище уже есть такой сотрудник.");

        }
        employeeByHashCode.put(employeeHashCode, employee);

        return employee;
    }


    public Employee find(String firstName, String lastName) {

        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.get(employeeHashCode);

        presentCheck(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        //  Employee employee = find(firstName, lastName);
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.remove(employeeHashCode);

        presentCheck(employee);
        return employee;
    }

    public List<Employee> getAll() {
        return employeeByHashCode.values().stream().collect(Collectors.toList());
    }

    private int getEmployeeKey(String firstName, String lastName) {
        return Objects.hash(firstName, lastName);
    }

    private void presentCheck(Employee employee) {
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

    }
}
