package com.capgemini.service;

import java.util.List;

import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

public interface OfficeService {
	
	OfficeTO addOffice(OfficeTO office);
	
	void removeOffice(OfficeTO office);
	
	OfficeTO findOfficeById(OfficeTO office);
	
	OfficeTO updateOffice(OfficeTO office);
	
	EmployeeTO addEmployee(EmployeeTO employee);
	
	EmployeeTO addEmployeeToOffice(EmployeeTO employee, OfficeTO office);
	
	EmployeeTO removeEmployeeFromOffice(EmployeeTO employee, OfficeTO office);
	
	List<EmployeeTO> findEmployeesFromOffice(OfficeTO office);
	
	List<EmployeeTO> findEmployeesFromOfficeSupervisingCar(OfficeTO office, CarTO car);
	
	void removeEmployee(EmployeeTO employee);
	
	EmployeeTO updateEmployee(EmployeeTO employee);
	
	EmployeeTO findEmployeeById(EmployeeTO employee);
	
	
	
	
	

}
