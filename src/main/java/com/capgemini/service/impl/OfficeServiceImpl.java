package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.OfficeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;



@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	
	
	@Autowired
	OfficeDao officeDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	CarDao carDao;
	
	

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

}
