package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	List<EmployeeEntity> findEmployeesByOfficeAndCar(Long officeId, Long carId);

	List<EmployeeEntity> findEmployeesByPosition(String position);

}
