package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.domain.Address;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.ClientTO;

public class ClientMapper {

	public static ClientTO mapToClientTO(ClientEntity clientEntity) {

		if (clientEntity == null) {
			return null;
		}

		ClientTO clientTO = ClientTO.builder().id(clientEntity.getId()).lastName(clientEntity.getLastName())
				.firstName(clientEntity.getFirstName()).address(mapToAddressTO(clientEntity.getAddress()))
				.dateOfBirth(clientEntity.getDateOfBirth()).phone(clientEntity.getPhone())
				.email(clientEntity.getEmail()).pan(clientEntity.getPan())
				.rentalsId(mapEntityCollectionToLong(clientEntity.getRentals())).build();

		return clientTO;

	}

	private static List<Long> mapEntityCollectionToLong(List<RentalEntity> rentalList) {

		List<Long> rentalListTO = new ArrayList<>();

		if (rentalList == null) {
			return rentalListTO;
		}

		for (RentalEntity ren : rentalList) {
			rentalListTO.add(ren.getId());

		}
		return rentalListTO;

	}

	private static Address mapToAddressTO(Address adressEntity) {

		Address addressTO = Address.builder().street(adressEntity.getStreet())
				.buildingNumber(adressEntity.getBuildingNumber()).flatNumber(adressEntity.getFlatNumber())
				.postalCode(adressEntity.getPostalCode()).town(adressEntity.getTown())
				.country(adressEntity.getCountry()).build();

		return addressTO;
	}

	public static ClientEntity mapToClientEntity(ClientTO clientTO) {

		if (clientTO == null) {
			return null;
		}

		Address addressEntity = Address.builder().street(clientTO.getAddress().getStreet())
				.buildingNumber(clientTO.getAddress().getBuildingNumber())
				.flatNumber(clientTO.getAddress().getFlatNumber()).postalCode(clientTO.getAddress().getPostalCode())
				.town(clientTO.getAddress().getTown()).country(clientTO.getAddress().getCountry()).build();

		ClientEntity clientEntity = ClientEntity.builder().id(clientTO.getId()).lastName(clientTO.getLastName())
				.firstName(clientTO.getFirstName()).address(addressEntity).dateOfBirth(clientTO.getDateOfBirth())
				.phone(clientTO.getPhone()).email(clientTO.getEmail()).pan(clientTO.getPan()).build();

		return clientEntity;

	}

	public static List<ClientTO> mapToListTOs(List<ClientEntity> clientEntityList) {

		List<ClientTO> mappedList = new ArrayList<>();

		for (ClientEntity c : clientEntityList) {
			mappedList.add(mapToClientTO(c));
		}

		return mappedList;

	}

}
