package com.capgemini.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.enums.CarType;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	
	@Autowired
	private CarDao carDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<CarTO> findCarsByType(CarType carType) {
		
		List<CarEntity> carEntityList = carDao.findCarByType(carType);
		
		return CarMapper.mapToListTOs(carEntityList);
	}

	@Override
	public List<CarTO> findCarsByModel(String model) {

		List<CarEntity> carEntityList = carDao.findCarByModel(model);
		
		return CarMapper.mapToListTOs(carEntityList);
	}
	

	@Override
	public List<CarTO> findCarsBySupervisor(EmployeeTO employeeTO) {

		List<CarEntity> carEntityList = employeeDao.findOne(employeeTO.getId()).getCarsUnderSupervision();
		
		return CarMapper.mapToListTOs(carEntityList);
	}

	@Override
	public CarTO update(CarTO carTO) {
		
		CarEntity carEntity = carDao.findOne(carTO.getId());

		return CarMapper.update(carTO, carEntity);

	}

	@Override
	public CarTO saveCar(CarTO carTO) {
		CarEntity carEntity = carDao.save(CarMapper.mapToCarEntity(carTO));
		return CarMapper.mapToCarTO(carEntity);
	}

	@Override
	public void removeCar(CarTO carTO) {

		carDao.delete(carTO.getId());
	}

	@Override
	public void assignSupervisor(CarTO carTO, EmployeeTO employeeTO) {
		CarEntity carEntity = carDao.findOne(carTO.getId());
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		carEntity.addSupervisor(employeeEntity);

		
	}

	@Override
	public CarTO findCarById(Long carId) {

		CarEntity carEntity = carDao.findOne(carId);
		
		return CarMapper.mapToCarTO(carEntity);
	}

	@Override
	public List<CarTO> findCarsByModelAndType(String model, CarType carType) {

		List<CarEntity> carEntityList = carDao.findCarByModelAndType(model, carType);
		
		return CarMapper.mapToListTOs(carEntityList);
	}
	
	
	
	

	
	
	
	
	
}
