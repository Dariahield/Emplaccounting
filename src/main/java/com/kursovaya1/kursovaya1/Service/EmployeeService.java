package com.kursovaya1.kursovaya1.Service;

import com.kursovaya1.kursovaya1.entity.Employee;
import com.kursovaya1.kursovaya1.exception.EmployeeAlreadyAddedException;
import com.kursovaya1.kursovaya1.exception.EmployeeNotFoundException;
import com.kursovaya1.kursovaya1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private static final int MAX_EMPLOYEES_COUNT = 5;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник.");
        }
        employees.add(employee);
        return employee;
    }


    public Employee find(String firstName, String lastName) {
        Employee employee = null;
        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }

        if(employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }

        return employee;

    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);


        for (Employee e : employees) {
            if (e.equals(employee)) {
                return e;
            }
        }

        return employee;
    }

    public List<Employee> getAll() {
        return employees;
    }
}
