package com.example.employee_demo.service;

import com.example.employee_demo.dao.EmployeeDAO;
import com.example.employee_demo.dao.EmployeeRepository;
import com.example.employee_demo.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, EmployeeRepository employeeRepository) {
        this.employeeDAO = employeeDAO;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        //return this.employeeDAO.findAll();
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        //return this.employeeDAO.findById(id);
        //return this.employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("hata"));

    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.employeeDAO.deleteById(id);
    }
}
