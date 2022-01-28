package com.example.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao<T, I> {
    Optional<T> getEmployeeById(Long id);
    List<T> getAllEmployees();
    Optional<I> saveEmployee(T t);
    void updateEmployee(T t);
    void deleteEmployee(Long id);
}