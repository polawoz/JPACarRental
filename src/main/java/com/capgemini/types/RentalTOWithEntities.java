package com.capgemini.types;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.OfficeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RentalTOWithEntities {
	
	
	RentalTO rentalTO;
	private ClientEntity client;
	private CarEntity car;
	private OfficeEntity pickUpOffice;
	private OfficeEntity returnOffice;
	
}
