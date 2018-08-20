package com.capgemini.types;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.OfficeEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RentalTOWithEntities {
	
	
	private RentalTO rentalTO;
	private ClientEntity client;
	private CarEntity car;
	private OfficeEntity pickUpOffice;
	private OfficeEntity returnOffice;
	
	
	public RentalTOWithEntities(RentalTO rentalTO, ClientEntity client, CarEntity car, OfficeEntity pickUpOffice,
			OfficeEntity returnOffice) {
		this.rentalTO = rentalTO;
		this.client = client;
		this.car = car;
		this.pickUpOffice = pickUpOffice;
		this.returnOffice = returnOffice;
	}
	
	
	public static RentalTOWithEntitiesBuilder builder(){
		return new RentalTOWithEntitiesBuilder();
	}
	
	public static class RentalTOWithEntitiesBuilder{
		
		private RentalTO rentalTO;
		private ClientEntity client;
		private CarEntity car;
		private OfficeEntity pickUpOffice;
		private OfficeEntity returnOffice;
		
		public RentalTOWithEntitiesBuilder(){
			super();
		}

		public RentalTOWithEntitiesBuilder rentalTO(RentalTO rentalTO) {
			this.rentalTO = rentalTO;
			return this;
		}

		public RentalTOWithEntitiesBuilder client(ClientEntity client) {
			this.client = client;
			return this;
		}

		public RentalTOWithEntitiesBuilder car(CarEntity car) {
			this.car = car;
			return this;
		}

		public RentalTOWithEntitiesBuilder pickUpOffice(OfficeEntity pickUpOffice) {
			this.pickUpOffice = pickUpOffice;
			return this;
		}

		public RentalTOWithEntitiesBuilder returnOffice(OfficeEntity returnOffice) {
			this.returnOffice = returnOffice;
			return this;
		}
		
		public RentalTOWithEntities build(){
			return new RentalTOWithEntities(rentalTO, client, car, pickUpOffice, returnOffice);
		}
		
		
		
		
		
	}
	
	
	
	
	
}
