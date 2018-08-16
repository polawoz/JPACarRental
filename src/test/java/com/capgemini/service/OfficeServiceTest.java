package com.capgemini.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.Address;
import com.capgemini.domain.enums.CarType;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OfficeServiceTest {

	@Autowired
	CarService carService;

	@Autowired
	OfficeService officeService;

	@Test
	public void testShouldAddOffice() {
		// given

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO officeTO = OfficeTO.builder().address(address).phone(phone).email(email).build();

		// when
		OfficeTO savedOffice = officeService.addOffice(officeTO);

		// then
		OfficeTO office = officeService.findOfficeById(savedOffice.getId());
		assertNotNull(office);
		assertEquals(office.getEmail(), email);

	}

	@Test
	public void testShouldRemoveOffice() {

		// given
		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO officeTO = OfficeTO.builder().address(address).phone(phone).email(email).build();

		OfficeTO savedOffice = officeService.addOffice(officeTO);

		OfficeTO officeToRemove = OfficeTO.builder().id(savedOffice.getId()).build();
		// when
		officeService.removeOffice(officeToRemove);

		// then
		OfficeTO officeFound = officeService.findOfficeById(savedOffice.getId());
		assertNull(officeFound);

	}

	@Test
	public void testShouldFindOfficeById() {

		// given
		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO officeTO = OfficeTO.builder().address(address).phone(phone).email(email).build();

		OfficeTO savedOffice = officeService.addOffice(officeTO);

		// when
		OfficeTO officeFound = officeService.findOfficeById(savedOffice.getId());

		// then
		assertEquals(savedOffice.getId(), officeFound.getId());
		assertEquals(savedOffice.getEmail(), officeFound.getEmail());
		assertEquals(savedOffice.getPhone(), officeFound.getPhone());

	}

	@Test
	public void testShouldUpdateOffice() {

		// given
		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO officeTO = OfficeTO.builder().address(address).phone(phone).email(email).build();

		OfficeTO savedOffice = officeService.addOffice(officeTO);

		String newEmail = "poz1@carrental.com";
		OfficeTO updateParam = OfficeTO.builder().id(savedOffice.getId()).email(newEmail).build();

		// when
		OfficeTO updatedTO = officeService.updateOffice(updateParam);

		// then
		assertEquals(updatedTO.getEmail(), email);
		assertEquals(updatedTO.getId(), savedOffice.getId());

	}

	@Test
	public void testShouldAddEmployee() {

		// given
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = new Date(1221L);

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();

		// when
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		// then
		assertNotNull(officeService.findEmployeeById(savedEmployee.getId()));

	}

	@Test
	public void testShouldAddEmployeeToOffice() {

		// given

		CarType carType = CarType.COUPE;
		String model = "Audi A5";
		Integer productionYear = 2011;
		String color = "CZARNY";
		Integer engineSize = 2000;
		Integer power = 210;
		Integer mileage = 25000;

		CarTO car = CarTO.builder().carType(carType).model(model).productionYear(productionYear).color(color)
				.engineSize(engineSize).power(power).mileage(mileage).build();

		CarTO savedCar = carService.saveCar(car);

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = new Date(1221L);

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		// when
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		OfficeTO officeTO = officeService.findOfficeById(savedOffice.getId());

		// then

		assertFalse(officeTO.getEmployeesIdList().isEmpty());
		assertTrue(officeTO.getEmployeesIdList().stream().anyMatch(b -> b.equals(savedEmployee.getId())));

	}

	@Test
	public void testShouldRemoveEmployeeFromOffice() {

		// given

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = new Date(1221L);

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		// when
		EmployeeTO removedEmployeeTO = officeService.removeEmployeeFromOffice(savedEmployee, savedOffice);
		
		
		
		//then
		EmployeeTO employeeFound = officeService.findEmployeeById(savedEmployee.getId());
		OfficeTO officeFound = officeService.findOfficeById(savedOffice.getId());
		
		assertNotNull(employeeFound);
		assertNull(employeeFound.getOfficeId());
		assertFalse(officeFound.getEmployeesIdList().contains(employeeFound.getId()));
		
		

	}

	@Test
	public void testFindEmployeesFromOffice() {
		fail("Not yet implemented");
	}

	@Test
	public void testShouldFindEmployeesFromOfficeSupervisingCar() {

		// given

		CarType carType = CarType.COUPE;
		String model = "Audi A5";
		Integer productionYear = 2011;
		String color = "CZARNY";
		Integer engineSize = 2000;
		Integer power = 210;
		Integer mileage = 25000;

		CarTO car = CarTO.builder().carType(carType).model(model).productionYear(productionYear).color(color)
				.engineSize(engineSize).power(power).mileage(mileage).build();

		CarTO savedCar = carService.saveCar(car);

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = new Date(1221L);

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		// when
		carService.assignSupervisor(savedCar, savedEmployee);

		// then
		EmployeeTO employeeTO = officeService.findEmployeeById(savedEmployee.getId());
		CarTO carTO = carService.findCarById(savedCar.getId());

		List<EmployeeTO> employeesFromOfficeSupervisingCar = officeService
				.findEmployeesFromOfficeSupervisingCar(savedOffice, carTO);

		assertNotNull(employeesFromOfficeSupervisingCar);
		assertFalse(employeesFromOfficeSupervisingCar.isEmpty());
		assertTrue(employeesFromOfficeSupervisingCar.stream().anyMatch(b -> b.getId().equals(employeeTO.getId())));
		assertTrue(
				employeesFromOfficeSupervisingCar.stream().anyMatch(b -> b.getOfficeId().equals(savedOffice.getId())));
		assertTrue(employeesFromOfficeSupervisingCar.size() == 1);

	}

	@Test
	public void testRemoveEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindEmployeeById() {
		fail("Not yet implemented");
	}

}
