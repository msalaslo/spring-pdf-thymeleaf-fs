package com.verisure.vcp.springtmlfs.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * Employee repository
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
@Slf4j
@Repository
public class EmployeeRepository {

	public List<Employee> findAll() {

		LOGGER.debug("Retrieving the whole list of employees!");

		Employee employee1 = Employee.builder().firstName("Test1").lastName("lastName1").build();
		Employee employee2 = Employee.builder().firstName("Test2").lastName("lastName2").build();
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		return employees;
	}

	public void save(Employee employee) {

		LOGGER.debug("Employee with name {} added", employee.getFirstName());
	}

}
