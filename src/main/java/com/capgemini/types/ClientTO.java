package com.capgemini.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.domain.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTO {
	
	
	private Long id;
	private String lastName;
	private String firstName;
	private Address address;
	private Date dateOfBirth;
	private String phone;
	private String email;
	private String pan;
	private List<Long> rentalsId = new ArrayList<>();
	
	

}
