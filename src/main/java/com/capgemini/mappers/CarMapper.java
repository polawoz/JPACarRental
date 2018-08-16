package com.capgemini.mappers;


import java.util.ArrayList;
import java.util.List;


import com.capgemini.domain.CarEntity;

import com.capgemini.types.CarTO;

public class CarMapper {
	
	
	public static CarTO mapToCarTO(CarEntity carEntity){
		if(carEntity==null){
			return null;
		}
		
		//CarTO carTO = new CarTO();
//		carTO.setCarType(carEntity.getCarType());
//		carTO.setColor(carEntity.getColor());
//		carTO.setEngineSize(carEntity.getEngineSize());
//		carTO.setId(carEntity.getId());
//		carTO.setMileage(carEntity.getMileage());
//		carTO.setModel(carEntity.getModel());
//		carTO.setPower(carEntity.getPower());
//		carTO.setProductionYear(carEntity.getProductionYear());
		
		
		CarTO carTO = CarTO.builder()
				.carType(carEntity.getCarType())
				.color(carEntity.getColor())
				.engineSize(carEntity.getEngineSize())
				.id(carEntity.getId())
				.mileage(carEntity.getEngineSize())
				.model(carEntity.getModel())
				.power(carEntity.getPower())
				.productionYear(carEntity.getProductionYear())
				.created(carEntity.getCreated())
				.build();
		
		return carTO;
		
	}
	
	public static CarEntity update(CarTO carTO, CarEntity carEntity){
		
		if(carTO.getCarType()!=null){
			carEntity.setCarType(carTO.getCarType());
		}
		if(carTO.getColor()!=null){
			carEntity.setColor(carTO.getColor());
		}
		if(carTO.getEngineSize()!=null){
			carEntity.setEngineSize(carTO.getEngineSize());
		}
		if(carTO.getId()!=null){
			carEntity.setId(carTO.getId());
		}
		if(carTO.getMileage()!=null){
			carEntity.setMileage(carTO.getMileage());
		}
		if(carTO.getModel()!=null){
			carEntity.setModel(carTO.getModel());
		}
		if(carTO.getPower()!=null){
			carEntity.setPower(carTO.getPower());
		}
		if(carTO.getProductionYear()!=null){
			carEntity.setProductionYear(carTO.getProductionYear());
		}
		
		return carEntity;
		
	}
	
	
	public static CarEntity mapToCarEntity(CarTO carTO){
		
		if(carTO==null){
			return null;
		}
		
		//CarEntity carEntity = new CarEntity();
//		carEntity.setCarType(carTO.getCarType());
//		carEntity.setColor(carTO.getColor());
//		carEntity.setEngineSize(carTO.getEngineSize());
//		//co z tym Id??
//		carEntity.setId(carTO.getId());
//		carEntity.setMileage(carTO.getMileage());
//		carEntity.setModel(carTO.getModel());
//		carEntity.setPower(carTO.getPower());
//		carEntity.setProductionYear(carTO.getProductionYear());
		
		
		CarEntity carEntity= CarEntity.builder()
				.carType(carTO.getCarType())
				.color(carTO.getColor())
				.engineSize(carTO.getEngineSize())
				.id(carTO.getId())
				.mileage(carTO.getMileage())
				.model(carTO.getModel())
				.power(carTO.getPower())
				.productionYear(carTO.getProductionYear())
				.build();
		
		
		
		return carEntity;
		
	}

	public static List<CarTO> mapToListTOs(List<CarEntity> carEntityList) {
		
		
		List<CarTO> mappedList = new ArrayList<>();
		
		for(CarEntity c: carEntityList){
			mappedList.add(mapToCarTO(c));
		}
		
		
		return mappedList;
	}
	
	
	

}
