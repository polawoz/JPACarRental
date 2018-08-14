package com.capgemini.domain;



import javax.persistence.Embeddable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	

	private String street;
	private String buildingNumber;
	private String flatNumber;
	private String postalCode;
	private String town;
	private String country;
	
	public Address(){
		
		
	}
	
	
	

}
