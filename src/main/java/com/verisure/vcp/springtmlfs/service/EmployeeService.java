package com.verisure.vcp.springtmlfs.service;


import java.util.List;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;

/**
 * Employee service interface.
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
public interface EmployeeService {

	/**
	 * Creates an employee.
	 * 
	 * @param employee.
	 */
    void createEmployee(Employee employeee);

	/**
	 * Gets all the employees.
	 * 
	 * @return The list of employees.
	 */
    List<Employee> getEmployees();

}
