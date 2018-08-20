package com.capgemini.types;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class EmployeeTO {

	private Long id;
	private String position;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private Long officeId;
	
	public EmployeeTO(Long id, String position, String lastName, String firstName, Date dateOfBirth, Long officeId) {

		this.id = id;
		this.position = position;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.officeId = officeId;
	}
	
	public EmployeeTO(){
		
		
	}
	
	public static EmployeeTOBuilder builder(){
		return new EmployeeTOBuilder();
		
	}

	
	
	public static class EmployeeTOBuilder {
		
		private Long id;
		private String position;
		private String lastName;
		private String firstName;
		private Date dateOfBirth;
		private Long officeId;
		
		
		public EmployeeTOBuilder(){
			super();
		}


		public EmployeeTOBuilder id(Long id) {
			this.id = id;
			return this;
		}


		public EmployeeTOBuilder position(String position) {
			this.position = position;
			return this;
		}


		public EmployeeTOBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}


		public EmployeeTOBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}


		public EmployeeTOBuilder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}


		public EmployeeTOBuilder officeId(Long officeId) {
			this.officeId = officeId;
			return this;
		}
		
		
		public EmployeeTO build(){
			return new EmployeeTO(id, position, lastName, firstName, dateOfBirth, officeId);
		}
		

		
		
		
	}
	
	

}
