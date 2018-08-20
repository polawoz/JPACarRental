package com.capgemini.dao;


import java.sql.Timestamp;
import java.util.List;


import com.capgemini.domain.CarEntity;
import com.capgemini.domain.enums.CarType;


public interface CarDao extends Dao<CarEntity, Long> {

	List<CarEntity> findCarByType(CarType carType);
	
	List<CarEntity> findCarByModel(String model);
	
	List<CarEntity> findCarByModelAndType(String model, CarType carType);

	List<CarEntity> findCarsRentedByClientsOfNumberMoreThan(Long clientsNumber);

	Long countCarsInRentalBetweenTimeperiod(Timestamp startTimestamp, Timestamp endTimestamp);
	
}
