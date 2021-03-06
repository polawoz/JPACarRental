package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;

public class CarMapper {

	public static CarTO mapToCarTO(CarEntity carEntity) {
		if (carEntity == null) {
			return null;
		}

		CarTO carTO = CarTO.builder().carType(carEntity.getCarType()).color(carEntity.getColor())
				.engineSize(carEntity.getEngineSize()).id(carEntity.getId()).mileage(carEntity.getEngineSize())
				.model(carEntity.getModel()).power(carEntity.getPower()).productionYear(carEntity.getProductionYear())
				.supervisorsIdList(mapEntityCollectionToLong(carEntity.getCarsSupervisors())).build();

		return carTO;

	}

	private static List<Long> mapEntityCollectionToLong(List<EmployeeEntity> supervisorsList) {

		List<Long> supervisorsListTO = new ArrayList<>();

		if (supervisorsList == null) {
			return supervisorsListTO;
		}

		for (EmployeeEntity sup : supervisorsList) {
			supervisorsListTO.add(sup.getId());

		}
		return supervisorsListTO;

	}

	public static CarTO update(CarTO carTO, CarEntity carEntity) {

		if (carTO.getCarType() != null) {
			carEntity.setCarType(carTO.getCarType());
		}
		if (carTO.getColor() != null) {
			carEntity.setColor(carTO.getColor());
		}
		if (carTO.getEngineSize() != null) {
			carEntity.setEngineSize(carTO.getEngineSize());
		}
		if (carTO.getId() != null) {
			carEntity.setId(carTO.getId());
		}
		if (carTO.getMileage() != null) {
			carEntity.setMileage(carTO.getMileage());
		}
		if (carTO.getModel() != null) {
			carEntity.setModel(carTO.getModel());
		}
		if (carTO.getPower() != null) {
			carEntity.setPower(carTO.getPower());
		}
		if (carTO.getProductionYear() != null) {
			carEntity.setProductionYear(carTO.getProductionYear());
		}

		return mapToCarTO(carEntity);

	}

	public static CarEntity mapToCarEntity(CarTO carTO) {

		if (carTO == null) {
			return null;
		}

		CarEntity carEntity = CarEntity.builder().carType(carTO.getCarType()).color(carTO.getColor())
				.engineSize(carTO.getEngineSize()).id(carTO.getId()).mileage(carTO.getMileage()).model(carTO.getModel())
				.power(carTO.getPower()).productionYear(carTO.getProductionYear()).build();

		return carEntity;

	}

	public static List<CarTO> mapToListTOs(List<CarEntity> carEntityList) {

		List<CarTO> mappedList = new ArrayList<>();

		for (CarEntity c : carEntityList) {
			mappedList.add(mapToCarTO(c));
		}

		return mappedList;
	}

}
