package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.mappers.RentalMapper;
import com.capgemini.service.OfficeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.RentalTO;
import com.capgemini.types.RentalTOWithEntities;



@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	
	
	@Autowired
	OfficeDao officeDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	CarDao carDao;
	
	@Autowired
	RentalDao rentalDao;
	
	@Autowired
	ClientDao clientDao;
	
	
	

	@Override
	public OfficeTO addOffice(OfficeTO officeTO) {
		
		OfficeEntity officeEntity = officeDao.save(OfficeMapper.mapToOfficeEntity(officeTO));
		
		return OfficeMapper.mapToOfficeTO(officeEntity);
	}

	@Override
	public void removeOffice(OfficeTO officeTO) {


		officeDao.delete(officeTO.getId());
		
	}

	@Override
	public OfficeTO updateOffice(OfficeTO officeTO) {
		
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		
		OfficeMapper.update(officeTO, officeEntity);
		
		
		return findOfficeById(officeTO.getId());
	}

	@Override
	public EmployeeTO addEmployee(EmployeeTO employeeTO) {

		EmployeeEntity employeeEntity = employeeDao.save(EmployeeMapper.mapToEmployeeEntity(employeeTO));
		
		return EmployeeMapper.mapToEmployeeTO(employeeEntity);
	}

	@Override
	public EmployeeTO addEmployeeToOffice(EmployeeTO employeeTO, OfficeTO officeTO) {

		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeEntity.addEmployee(employeeEntity);
		
		return EmployeeMapper.mapToEmployeeTO(employeeEntity);
	}

	@Override
	public EmployeeTO removeEmployeeFromOffice(EmployeeTO employeeTO, OfficeTO officeTO) {

		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());

		return EmployeeMapper.mapToEmployeeTO(officeEntity.removeEmployee(employeeEntity));
	}

	@Override
	public List<EmployeeTO> findEmployeesFromOffice(OfficeTO officeTO) {
		
		List<EmployeeEntity> employeeEntityList = officeDao.findOne(officeTO.getId()).getEmployees();
	
		return EmployeeMapper.mapToListTOs(employeeEntityList);
	}

	@Override
	public List<EmployeeTO> findEmployeesFromOfficeSupervisingCar(OfficeTO officeTO, CarTO carTO) {
		// TODO Auto-generated method stub
		
		List<EmployeeEntity> employeeEntityList = employeeDao.findEmployeesByOfficeAndCar(officeTO.getId(), carTO.getId());
		
	
		return EmployeeMapper.mapToListTOs(employeeEntityList);
	}
	
	//czy to potrzebne
	@Override
	public void removeEmployee(EmployeeTO employee) {
		// TODO Auto-generated method stub
		
	}
	//czy to potrzebne???
	@Override
	public EmployeeTO updateEmployee(EmployeeTO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTO findEmployeeById(Long employeeId) {
		
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeId);
		
		return EmployeeMapper.mapToEmployeeTO(employeeEntity);
	}

	@Override
	public OfficeTO findOfficeById(Long officeId) {

		OfficeEntity officeEntity = officeDao.findOne(officeId);
		
		return OfficeMapper.mapToOfficeTO(officeEntity);
	}

	@Override
	public RentalTO makeRental(RentalTO rentalTO) {

		RentalTOWithEntities rentalTOWithEntities = RentalTOWithEntities.builder()
				.rentalTO(rentalTO)
				.client(clientDao.findOne(rentalTO.getClientId()))
				.car(carDao.findOne(rentalTO.getCarId()))
				.pickUpOffice(officeDao.findOne(rentalTO.getPickUpOfficeId()))
				.returnOffice(officeDao.findOne(rentalTO.getReturnOfficeId()))
				.build();
		
		RentalEntity rentalEntity = RentalMapper.mapToRentalEntity(rentalTOWithEntities);
		RentalEntity savedRentalEntity = rentalDao.save(rentalEntity);
		

		
		return RentalMapper.mapToRentalTO(savedRentalEntity);
	}

	@Override
	public RentalTO findRentalById(RentalTO rentalTO) {

		RentalEntity rentalEntity = rentalDao.findOne(rentalTO.getId());
		
		return RentalMapper.mapToRentalTO(rentalEntity);
	}

	@Override
	public List<RentalTO> findRentalsByCar(CarTO carTO) {
		
		List<RentalEntity> rentalEntityList = rentalDao.findRentalsByCar(carTO.getId());
		
		return RentalMapper.mapToListTOs(rentalEntityList);
	}

	@Override
	public ClientTO addClient(ClientTO clientTO) {
		
		ClientEntity clientEntity = clientDao.save(ClientMapper.mapToClientEntity(clientTO));
		
		
		return ClientMapper.mapToClientTO(clientEntity);
	}

	
	
	
	
	
	
	
	
	
	

}
