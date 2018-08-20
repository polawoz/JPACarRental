package com.capgemini.service;

import java.util.List;

import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.RentalTO;

public interface OfficeService {
	
	OfficeTO addOffice(OfficeTO office);
	
	void removeOffice(OfficeTO office);
	
	OfficeTO findOfficeById(Long officeId);
	
	OfficeTO updateOffice(OfficeTO office);
	
	EmployeeTO addEmployee(EmployeeTO employee);
	
	EmployeeTO addEmployeeToOffice(EmployeeTO employee, OfficeTO office);
	
	EmployeeTO removeEmployeeFromOffice(EmployeeTO employee, OfficeTO office);
	
	List<EmployeeTO> findEmployeesFromOffice(OfficeTO office);
	
	List<EmployeeTO> findEmployeesSupervisingCar(CarTO car);
	
	List<EmployeeTO> findEmployeesFromOfficeSupervisingCar(OfficeTO office, CarTO car);

	List<EmployeeTO> findEmployeesByPosition(EmployeeTO employee);
	
	EmployeeTO findEmployeeById(Long employeeId);
	
	RentalTO makeRental(RentalTO rentalTO);
	
	RentalTO findRentalById(RentalTO rentalTO);
	
	List<RentalTO> findRentalsByCar(CarTO carTO);
	
	ClientTO addClient(ClientTO clientTO);
	
	ClientTO findClientById(ClientTO clientTO);
	
	void getLoggerInfoOnCreationAndUpdate(Object objectTO);
	
	
	
	
	
	
	

}
