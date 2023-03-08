package com.kursovaya1.kursovaya1.exception;

public class EmployeeStorageIsFullException extends  RuntimeException{
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
