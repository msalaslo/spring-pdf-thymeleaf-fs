package com.verisure.vcp.springtmlfs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;
import com.verisure.vcp.springtmlfs.domain.repository.EmployeeRepository;
import com.verisure.vcp.springtmlfs.service.EmployeeService;

/**
 * Employee service implementation.
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
      return employeeRepository.findAll();
    }

}
