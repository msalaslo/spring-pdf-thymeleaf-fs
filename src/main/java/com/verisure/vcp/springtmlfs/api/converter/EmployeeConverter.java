package com.verisure.vcp.springtmlfs.api.converter;

import org.mapstruct.Mapper;

import com.verisure.vcp.springtmlfs.api.dto.EmployeeDTO;
import com.verisure.vcp.springtmlfs.domain.entity.Employee;

/**
 * Employee converter.
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
@Mapper(componentModel = "spring")
public interface EmployeeConverter {

    EmployeeDTO toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDTO employee);

}