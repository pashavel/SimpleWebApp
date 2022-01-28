package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.Dao;
import com.example.demo.repository.EmployeeDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements Dao<Employee,Long> {
    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Optional<Long> saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
        return Optional.empty();
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee oldEmployee = employeeDao.getEmployeeById(employee.getEmployeeId()).orElse(new Employee());
        if(employee.getJobTitle() != null) oldEmployee.setJobTitle(employee.getJobTitle());
        if(employee.getGender() != null) oldEmployee.setGender(employee.getGender());
        if(employee.getDateOfBirth() != null) oldEmployee.setDateOfBirth(employee.getDateOfBirth());
        if(employee.getDepartmentId() != null) oldEmployee.setDepartmentId(employee.getDepartmentId());
        if(employee.getLastName() != null) oldEmployee.setLastName(employee.getLastName());
        if(employee.getFirstName() != null) oldEmployee.setFirstName(employee.getFirstName());
        employeeDao.updateEmployee(oldEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.deleteEmployee(id);
    }
}
