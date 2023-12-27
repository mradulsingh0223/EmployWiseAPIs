package com.assesment.employwise_api.controller;

import com.assesment.employwise_api.model.Employee;
import com.assesment.employwise_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return service.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployee(){
        return service.findAllEmployee();
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "Health Check Passed";
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getEmployeePagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        List<Employee> employees = service.findAllEmployeepagination(page, size, sortBy);

        if (employees.isEmpty()) {
            return new ResponseEntity<>("No employees found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/manager/{level}")
    public ResponseEntity<?> getNthLevelManager(
            @PathVariable String employeeId,
            @PathVariable int level
    ) {
        Employee manager = service.getNthLevelManager(employeeId, level);

        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No manager found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> modifyEmployee(@RequestBody Employee employee, @PathVariable String employeeId)
    {
        return service.updateEmployee(employee, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId)
    {
        return service.deleteEmployee(employeeId);
    }
}
