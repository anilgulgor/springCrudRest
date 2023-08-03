package com.example.employee_demo.rest;

import com.example.employee_demo.dao.EmployeeDAO;
import com.example.employee_demo.entity.Employee;
import com.example.employee_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // inject employee Service
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose /employees and return list of employee
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return this.employeeService.findAll();
    }

    // expose /employees/:employeeId and return employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = this.employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }

        return employee;
    }

    // expose /employees and create employee post
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee dbEmployee = this.employeeService.save(employee);
        return dbEmployee;
    }

    // expose /employees and update employee put
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = this.employeeService.save(employee);
        return dbEmployee;
    }

    // expose /employees/{employeeId} and delete employee delete
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employeeWillBeDeleted = this.employeeService.findById(employeeId);

        if (employeeWillBeDeleted == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }

        // delete if it exists
        this.employeeService.deleteById(employeeId);

        return "Deleted employee id: " + employeeId;

    }

}
