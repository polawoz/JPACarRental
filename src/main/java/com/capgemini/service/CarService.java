package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.enums.CarType;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

public interface CarService {
	
	void assignSupervisor(CarTO carTO, EmployeeTO employeeTO);

	List<CarTO> findCarsByType(CarType carType);

	List<CarTO> findCarsByModel(String model);

	List<CarTO> findCarsBySupervisor(EmployeeTO employeeTO);
	
	CarTO findCarById(Long carId);
	
	
	CarTO update(CarTO car);

	CarTO saveCar(CarTO car);
	
	void removeCar(CarTO car);
	
	
	
	

}
