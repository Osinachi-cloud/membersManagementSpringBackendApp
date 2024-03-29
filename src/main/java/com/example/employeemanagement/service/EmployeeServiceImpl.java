package com.example.employeemanagement.service;

import com.example.employeemanagement.exception.UserNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository ){

        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    @Override

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
        .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteEmployeeById(id);
    }



}
