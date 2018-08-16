package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.Address;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.OfficeTO;

public class OfficeMapper {

	public static OfficeTO mapToOfficeTO(OfficeEntity officeEntity) {

		if (officeEntity == null) {
			return null;
		}


		OfficeTO officeTO = OfficeTO.builder()
				.id(officeEntity.getId())
				.address(mapToAddressTO(officeEntity.getAddress()))
				.phone(officeEntity.getPhone())
				.email(officeEntity.getEmail())
				.employeesIdList(mapEntityCollectionToLong(officeEntity.getEmployees()))
				.build();

		return officeTO;

	}

	private static List<Long> mapEntityCollectionToLong(List<EmployeeEntity> employeeList){
		
		
		
		List<Long> employeeListTO=new ArrayList<>();
		
		if(employeeList==null){
			return employeeListTO;
		}
		
		for(EmployeeEntity emp: employeeList){
			employeeListTO.add(emp.getId());
			
		}
		return employeeListTO;
	
	}
	
	
	private static Address mapToAddressTO(Address adressEntity){
		
		Address addressTO = Address.builder().street(adressEntity.getStreet())
				.buildingNumber(adressEntity.getBuildingNumber())
				.flatNumber(adressEntity.getFlatNumber())
				.postalCode(adressEntity.getPostalCode()).town(adressEntity.getTown())
				.country(adressEntity.getCountry()).build();
		
		return addressTO;
	}

	public static OfficeEntity update(OfficeTO officeTO, OfficeEntity officeEntity) {

		if(officeTO.getAddress()!=null){
			officeEntity.setAddress(mapToAddressTO(officeEntity.getAddress()));
		}
		if(officeTO.getEmail()!=null){
			officeEntity.setEmail(officeEntity.getEmail());
		}
		if(officeTO.getPhone()!=null){
			officeEntity.setPhone(officeEntity.getPhone());
		}
		
		return officeEntity;

	}

	public static OfficeEntity mapToOfficeEntity(OfficeTO officeTO) {

		if (officeTO == null) {
			return null;
		}

		Address addressEntity = Address.builder().street(officeTO.getAddress().getStreet())
				.buildingNumber(officeTO.getAddress().getBuildingNumber())
				.flatNumber(officeTO.getAddress().getFlatNumber()).postalCode(officeTO.getAddress().getPostalCode())
				.town(officeTO.getAddress().getTown()).country(officeTO.getAddress().getCountry()).build();

		OfficeEntity officeEntity = OfficeEntity.builder().id(officeTO.getId()).address(addressEntity)
				.phone(officeTO.getPhone()).email(officeTO.getEmail()).build();

		return officeEntity;

	}

	public static List<OfficeTO> mapToListTOs(List<OfficeEntity> officeEntityList) {
		
		List<OfficeTO> mappedList = new ArrayList<>();

		for (OfficeEntity o : officeEntityList) {
			mappedList.add(mapToOfficeTO(o));
		}

		return mappedList;

	}

}
