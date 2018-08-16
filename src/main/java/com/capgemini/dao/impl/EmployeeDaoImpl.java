package com.capgemini.dao.impl;



import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;


@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {


	@Override
	public EmployeeEntity findEmployeeById(Long employeeId) {
		EmployeeEntity emp = entityManager.find(EmployeeEntity.class, employeeId);
		return emp;
	}

	

	
	
	

}
