package com.capgemini.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.domain.Address;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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


	
	public ClientTO(Long id, String lastName, String firstName, Address address, Date dateOfBirth, String phone,
			String email, String pan, List<Long> rentalsId) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.email = email;
		this.pan = pan;
		this.rentalsId = rentalsId;
	}

	
	
	public static ClientTOBuilder builder(){
		return new ClientTOBuilder();
		
	}

	public static class ClientTOBuilder{
		
		
		private Long id;
		private String lastName;
		private String firstName;
		private Address address;
		private Date dateOfBirth;
		private String phone;
		private String email;
		private String pan;
		private List<Long> rentalsId = new ArrayList<>();
		
		
		public ClientTOBuilder(){
			super();
		}


		public ClientTOBuilder id(Long id) {
			this.id = id;
			return this;
		}


		public ClientTOBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}


		public ClientTOBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}


		public ClientTOBuilder address(Address address) {
			this.address = address;
			return this;
		}


		public ClientTOBuilder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}


		public ClientTOBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}


		public ClientTOBuilder email(String email) {
			this.email = email;
			return this;
		}


		public ClientTOBuilder pan(String pan) {
			this.pan = pan;
			return this;
		}


		public ClientTOBuilder rentalsId(List<Long> rentalsId) {
			this.rentalsId = rentalsId;
			return this;
		}
		
		public ClientTO build(){
			
			return new ClientTO(id, lastName, firstName, address, dateOfBirth, phone, email, pan, rentalsId);
		}
		
		
	}

}
