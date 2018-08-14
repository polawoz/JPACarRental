package com.capgemini.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@Override
	public EmployeeEntity findEmployee(EmployeeTO employeeTO) {

		
		return null;

	}

	@Override
	public EmployeeEntity findEmployeeById(Long employeeId) {
		EmployeeEntity emp = entityManager.find(EmployeeEntity.class, employeeId);
		return emp;
	}

	

	
	
	

}
