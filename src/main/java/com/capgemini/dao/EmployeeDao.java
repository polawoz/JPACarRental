package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

public interface EmployeeDao extends Dao<EmployeeEntity, Long>{
	
	
	EmployeeEntity findEmployee(EmployeeTO employeeTO);
	
	EmployeeEntity findEmployeeById(Long employeeId);

}
