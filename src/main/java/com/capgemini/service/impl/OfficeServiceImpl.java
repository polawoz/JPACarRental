package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
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
	public OfficeTO addOffice(OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOffice(OfficeTO office) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OfficeTO updateOffice(OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTO addEmployee(EmployeeTO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTO addEmployeeToOffice(EmployeeTO employee, OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTO removeEmployeeFromOffice(EmployeeTO employee, OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeTO> findEmployeesFromOffice(OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeTO> findEmployeesFromOfficeSupervisingCar(OfficeTO office, CarTO car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployee(EmployeeTO employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeTO updateEmployee(EmployeeTO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTO findEmployeeById(EmployeeTO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfficeTO findOfficeById(OfficeTO office) {
		// TODO Auto-generated method stub
		return null;
	}

}
