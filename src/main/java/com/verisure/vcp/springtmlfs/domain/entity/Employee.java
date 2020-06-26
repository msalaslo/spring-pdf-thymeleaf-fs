package com.verisure.vcp.springtmlfs.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee domain entity.
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;

}
