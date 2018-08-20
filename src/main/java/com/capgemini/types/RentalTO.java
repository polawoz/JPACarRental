package com.capgemini.types;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter

public class RentalTO {
	
	
	private Long id;
	private Timestamp startDatetime;
	private Timestamp endDatetime;
	private Float loanAmount;
	private Long clientId;
	private Long carId;
	private Long pickUpOfficeId;
	private Long returnOfficeId;
	
	
	public RentalTO(Long id, Timestamp startDatetime, Timestamp endDatetime, Float loanAmount, Long clientId,
			Long carId, Long pickUpOfficeId, Long returnOfficeId) {
		this.id = id;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.loanAmount = loanAmount;
		this.clientId = clientId;
		this.carId = carId;
		this.pickUpOfficeId = pickUpOfficeId;
		this.returnOfficeId = returnOfficeId;
	}
	
	
	public static RentalTOBuilder builder(){
		
		return new RentalTOBuilder();
	}
	
	
	public static class RentalTOBuilder{
		
		
		private Long id;
		private Timestamp startDatetime;
		private Timestamp endDatetime;
		private Float loanAmount;
		private Long clientId;
		private Long carId;
		private Long pickUpOfficeId;
		private Long returnOfficeId;
		
		public RentalTOBuilder(){
			super();
			
		}
		
		public  RentalTOBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public  RentalTOBuilder startDatetime(Timestamp startDatetime) {
			this.startDatetime = startDatetime;
			return this;
		}
		public  RentalTOBuilder endDatetime(Timestamp endDatetime) {
			this.endDatetime = endDatetime;
			return this;
		}
		public  RentalTOBuilder loanAmount(Float loanAmount) {
			this.loanAmount = loanAmount;
			return this;
		}
		public  RentalTOBuilder clientId(Long clientId) {
			this.clientId = clientId;
			return this;
		}
		public  RentalTOBuilder carId(Long carId) {
			this.carId = carId;
			return this;
		}
		public  RentalTOBuilder pickUpOfficeId(Long pickUpOfficeId) {
			this.pickUpOfficeId = pickUpOfficeId;
			return this;
		}
		public  RentalTOBuilder returnOfficeId(Long returnOfficeId) {
			this.returnOfficeId = returnOfficeId;
			return this;
		}
		
		public RentalTO build(){
			return new RentalTO(id, startDatetime, endDatetime, loanAmount, clientId, carId, pickUpOfficeId, returnOfficeId);
		}
		
		
		
		
		
	}
	
	

}
