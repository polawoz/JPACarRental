package com.capgemini.types;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTO {

	private Long id;
	private String position;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	
	

}
