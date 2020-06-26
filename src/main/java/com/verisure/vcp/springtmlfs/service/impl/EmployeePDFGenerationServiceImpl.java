package com.verisure.vcp.springtmlfs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;
import com.verisure.vcp.springtmlfs.pdf.EmployeePDFGenerator;
import com.verisure.vcp.springtmlfs.service.EmployeePDFGenerationService;
import com.verisure.vcp.springtmlfs.service.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePDFGenerationServiceImpl implements EmployeePDFGenerationService{
	
	@Autowired
	EmployeePDFGenerator pdfGenerator;
	
	@Override
    public byte[] generatePDF(String templateName, Employee employee) {
		byte[] pdf = new byte[0];
        try {
			pdf = pdfGenerator.generatePdf(templateName, employee);
		} catch (Exception e) {
			LOGGER.error("Error generating PDF", e);
			throw new ServiceException(e);
		}
        return pdf;
    }
	

}
