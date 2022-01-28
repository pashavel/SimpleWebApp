package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

//    @GetMapping("employee/{id}")
//    public Employee rje(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> byId(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id).orElse(new Employee()));
    }
    @GetMapping
    public ResponseEntity<List<Employee>> all()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public  ResponseEntity<String> edit(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
//@GetMapping(/{id}) для по id
//@PathVariable Long id
//@RequestParam("id") для ?"параметр" = "значение"