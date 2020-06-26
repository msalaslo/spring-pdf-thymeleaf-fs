package com.verisure.vcp.springtmlfs.service;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;

public interface EmployeePDFGenerationService {

//	public byte[] generatePDF(String templateName, Collection<Employee> employees);
	public byte[] generatePDF(String templateName, Employee employees);

}
