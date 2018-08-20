package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.RentalEntity;
import com.capgemini.types.RentalTO;
import com.capgemini.types.RentalTOWithEntities;

public class RentalMapper {

	public static RentalTO mapToRentalTO(RentalEntity rentalEntity) {

		if (rentalEntity == null) {
			return null;
		}

		RentalTO rentalTO = RentalTO.builder().id(rentalEntity.getId()).startDatetime(rentalEntity.getStartDatetime())
				.endDatetime(rentalEntity.getEndDatetime()).loanAmount(rentalEntity.getLoanAmount())
				.clientId(rentalEntity.getClient().getId()).carId(rentalEntity.getCar().getId())
				.pickUpOfficeId(rentalEntity.getId()).returnOfficeId(rentalEntity.getId()).build();

		return rentalTO;

	}

	public static RentalEntity mapToRentalEntity(RentalTOWithEntities rentalTOWithEntities) {

		if (rentalTOWithEntities == null || rentalTOWithEntities.getRentalTO() == null) {
			return null;
		}

		RentalEntity rentalEntity = RentalEntity.builder().id(rentalTOWithEntities.getRentalTO().getId())
				.startDatetime(rentalTOWithEntities.getRentalTO().getStartDatetime())
				.endDatetime(rentalTOWithEntities.getRentalTO().getEndDatetime())
				.loanAmount(rentalTOWithEntities.getRentalTO().getLoanAmount()).client(rentalTOWithEntities.getClient())
				.car(rentalTOWithEntities.getCar()).pickUpOffice(rentalTOWithEntities.getPickUpOffice())
				.returnOffice(rentalTOWithEntities.getReturnOffice()).build();

		return rentalEntity;

	}

	public static List<RentalTO> mapToListTOs(List<RentalEntity> rentalEntityList) {

		List<RentalTO> mappedList = new ArrayList<>();

		for (RentalEntity r : rentalEntityList) {
			mappedList.add(mapToRentalTO(r));
		}

		return mappedList;

	}

}
