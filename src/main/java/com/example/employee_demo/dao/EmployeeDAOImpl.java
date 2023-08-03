package com.example.employee_demo.dao;

import com.example.employee_demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // define entity manager
    private EntityManager entityManager;

    // setup entity manager injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create query
        TypedQuery<Employee> query = this.entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = query.getResultList();

        // return the result

        return employees;
    }

    @Override
    public Employee findById(int id) {

        // get employee
        Employee employee = this.entityManager.find(Employee.class, id);

        // return employee
        return employee;

    }

    @Override
    public Employee save(Employee employee) {

        // merge employee
        Employee dbEmployee = this.entityManager.merge(employee);

        // return employee
        return dbEmployee;

    }

    @Override
    public void deleteById(int id) {

        // find by id
        Employee willBeDeletedEmployee = this.findById(id);

        // remove employee
        this.entityManager.remove(willBeDeletedEmployee);

    }



}
