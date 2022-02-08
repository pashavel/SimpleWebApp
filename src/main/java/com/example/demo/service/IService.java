package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IService<T, I> {
    Optional<T> getEmployeeById(Long id);
    List<T> getAllEmployees();
    Optional<I> saveEmployee(T t);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
