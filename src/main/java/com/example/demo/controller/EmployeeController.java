package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    final IService<Employee, Long> employeeService;

    public EmployeeController(IService<Employee, Long> employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> byId(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id).orElse(new Employee()));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}