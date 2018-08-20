package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

public class EmployeeMapper {

	public static EmployeeTO mapToEmployeeTO(EmployeeEntity employeeEntity) {

		if (employeeEntity == null) {
			return null;
		}

		EmployeeTO employeeTO = EmployeeTO.builder().id(employeeEntity.getId()).position(employeeEntity.getPosition())
				.lastName(employeeEntity.getLastName()).firstName(employeeEntity.getFirstName())
				.dateOfBirth(employeeEntity.getDateOfBirth())
				.officeId(employeeEntity.getOffice() == null ? null : employeeEntity.getOffice().getId()).build();

		return employeeTO;

	}

	public static EmployeeEntity mapToEmployeeEntity(EmployeeTO employeeTO) {

		if (employeeTO == null) {
			return null;
		}

		EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeTO.getId())
				.position(employeeTO.getPosition()).lastName(employeeTO.getLastName())
				.firstName(employeeTO.getFirstName()).dateOfBirth(employeeTO.getDateOfBirth()).build();

		return employeeEntity;

	}

	public static List<EmployeeTO> mapToListTOs(List<EmployeeEntity> employeeEntityList) {

		List<EmployeeTO> mappedList = new ArrayList<>();

		for (EmployeeEntity e : employeeEntityList) {
			mappedList.add(mapToEmployeeTO(e));
		}

		return mappedList;

	}

}
