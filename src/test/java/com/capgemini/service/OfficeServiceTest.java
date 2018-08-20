package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.Address;
import com.capgemini.domain.enums.CarType;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.RentalTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
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
		officeService.getLoggerInfoOnCreationAndUpdate(savedOffice);

		String newEmail = "poz1@carrental.com";
		OfficeTO updateParam = OfficeTO.builder().id(savedOffice.getId()).email(newEmail).build();

		// when
		OfficeTO updatedTO = officeService.updateOffice(updateParam);

		// then
		officeService.getLoggerInfoOnCreationAndUpdate(updatedTO);
		assertEquals(updatedTO.getEmail(), newEmail);
		assertEquals(updatedTO.getId(), savedOffice.getId());

	}

	@Test
	public void testShouldAddEmployee() {

		// given
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1989-12-03");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();

		// when
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		// then
		EmployeeTO foundEmployee = officeService.findEmployeeById(savedEmployee.getId());
		officeService.getLoggerInfoOnCreationAndUpdate(foundEmployee);
		assertNotNull(foundEmployee);
		assertEquals(lastName, foundEmployee.getLastName());

	}

	@Test
	public void testShouldAddEmployeeToOffice() {

		// given

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1980-12-09");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);
		officeService.getLoggerInfoOnCreationAndUpdate(savedEmployee);

		// when
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		// then

		OfficeTO officeTO = officeService.findOfficeById(savedOffice.getId());
		officeService.getLoggerInfoOnCreationAndUpdate(savedEmployee);
		assertFalse(officeTO.getEmployeesIdList().isEmpty());
		assertTrue(officeTO.getEmployeesIdList().stream().anyMatch(b -> b.equals(savedEmployee.getId())));

	}

	@Test
	public void testShouldRemoveEmployeeFromOffice() {

		// given

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1987-12-12");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		// when
		officeService.removeEmployeeFromOffice(savedEmployee, savedOffice);

		// then
		EmployeeTO employeeFound = officeService.findEmployeeById(savedEmployee.getId());
		OfficeTO officeFound = officeService.findOfficeById(savedOffice.getId());

		assertNotNull(employeeFound);
		assertNull(employeeFound.getOfficeId());
		assertFalse(officeFound.getEmployeesIdList().contains(employeeFound.getId()));

	}

	@Test
	public void testShouldFindEmployeesFromOffice() {

		// given

		// Office 1
		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		int employeesFromOfficeListSizeBefore = officeService.findEmployeesFromOffice(savedOffice).size();

		// Office 2
		Address address2 = Address.builder().street("Osci").buildingNumber("12").flatNumber("1").postalCode("60-333")
				.town("Poznan").country("Polska").build();
		String phone2 = "22223454321";
		String email2 = "poz2@rental.com";

		OfficeTO office2 = OfficeTO.builder().address(address2).phone(phone2).email(email2).build();
		officeService.addOffice(office2);

		// Employee 1

		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1989-12-12");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		// Employee 2

		String position2 = "ACCOUNTANT";
		String lastName2 = "Kowalska";
		String firstName2 = "Anna";
		Date dateOfBirth2 = Date.valueOf("1979-12-12");

		EmployeeTO employee2 = EmployeeTO.builder().position(position2).lastName(lastName2).firstName(firstName2)
				.dateOfBirth(dateOfBirth2).build();
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee2);

		officeService.addEmployeeToOffice(savedEmployee2, savedOffice);

		// when
		List<EmployeeTO> employeesFromOfficeList = officeService.findEmployeesFromOffice(savedOffice);

		// then

		assertEquals(employeesFromOfficeListSizeBefore + 2, employeesFromOfficeList.size());
		assertTrue(employeesFromOfficeList.stream().anyMatch(b -> b.getId().equals(savedEmployee.getId())));
		assertTrue(employeesFromOfficeList.stream().anyMatch(b -> b.getId().equals(savedEmployee2.getId())));

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
		Date dateOfBirth = Date.valueOf("1989-12-12");

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

		int employeesFromOfficeSupervisingCarListSizeBefore = officeService
				.findEmployeesFromOfficeSupervisingCar(savedOffice, savedCar).size();

		carService.assignSupervisor(savedCar, savedEmployee);

		EmployeeTO employeeTO = officeService.findEmployeeById(savedEmployee.getId());
		CarTO carTO = carService.findCarById(savedCar.getId());
		// when

		List<EmployeeTO> employeesFromOfficeSupervisingCar = officeService
				.findEmployeesFromOfficeSupervisingCar(savedOffice, carTO);
		// then
		assertNotNull(employeesFromOfficeSupervisingCar);
		assertFalse(employeesFromOfficeSupervisingCar.isEmpty());
		assertTrue(employeesFromOfficeSupervisingCar.stream().anyMatch(b -> b.getId().equals(employeeTO.getId())));
		assertTrue(
				employeesFromOfficeSupervisingCar.stream().anyMatch(b -> b.getOfficeId().equals(savedOffice.getId())));
		assertEquals(employeesFromOfficeSupervisingCarListSizeBefore + 1, employeesFromOfficeSupervisingCar.size());

	}

	@Test
	public void testShouldFindEmployeeById() {

		// given
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1989-12-12");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();
		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		// when
		EmployeeTO foundEmploye = officeService.findEmployeeById(savedEmployee.getId());

		// then
		assertEquals(savedEmployee.getId(), foundEmploye.getId());
		assertEquals(position, foundEmploye.getPosition());
		assertEquals(lastName, foundEmploye.getLastName());
		assertEquals(firstName, foundEmploye.getFirstName());
		assertEquals(dateOfBirth, foundEmploye.getDateOfBirth());

	}

	@Test
	public void testShouldMakeRental() {

		// given

		// Client
		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient = officeService.addClient(clientTO);

		// Office
		Address officeAddress = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String officePhone = "22223454321";
		String officeEmail = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(officeAddress).phone(officePhone).email(officeEmail).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		// Car 1
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

		// Car 2
		CarType carType2 = CarType.COUPE;
		String model2 = "Audi A5";
		Integer productionYear2 = 2011;
		String color2 = "CZARNY";
		Integer engineSize2 = 2000;
		Integer power2 = 210;
		Integer mileage2 = 25000;

		CarTO car2 = CarTO.builder().carType(carType2).model(model2).productionYear(productionYear2).color(color2)
				.engineSize(engineSize2).power(power2).mileage(mileage2).build();

		CarTO savedCar2 = carService.saveCar(car2);

		// Rental
		RentalTO rentalTO = RentalTO.builder().startDatetime(Timestamp.valueOf("2017-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2017-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-10-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-10-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		int rentalsByCarListBeforeSize = officeService.findRentalsByCar(savedCar).size();

		// when
		RentalTO madeRental = officeService.makeRental(rentalTO);
		RentalTO madeRental2 = officeService.makeRental(rentalTO2);
		RentalTO madeRental3 = officeService.makeRental(rentalTO3);

		// then

		ClientTO foundClient = officeService.findClientById(savedClient);

		assertEquals(rentalsByCarListBeforeSize + 2, officeService.findRentalsByCar(savedCar).size());
		assertTrue(foundClient.getRentalsId().contains(madeRental.getId()));
		assertTrue(foundClient.getRentalsId().contains(madeRental2.getId()));
		assertTrue(foundClient.getRentalsId().contains(madeRental3.getId()));
		assertTrue(officeService.findRentalById(madeRental).getCarId() == savedCar.getId());

	}

	@Test
	public void testShouldFindRentalById() {

		// given

		// Client
		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient = officeService.addClient(clientTO);

		// Office
		Address officeAddress = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String officePhone = "22223454321";
		String officeEmail = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(officeAddress).phone(officePhone).email(officeEmail).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		// Car 1
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

		// Car 2
		CarType carType2 = CarType.COUPE;
		String model2 = "Audi A5";
		Integer productionYear2 = 2011;
		String color2 = "CZARNY";
		Integer engineSize2 = 2000;
		Integer power2 = 210;
		Integer mileage2 = 25000;

		CarTO car2 = CarTO.builder().carType(carType2).model(model2).productionYear(productionYear2).color(color2)
				.engineSize(engineSize2).power(power2).mileage(mileage2).build();

		CarTO savedCar2 = carService.saveCar(car2);

		// Rental

		RentalTO rentalTO = RentalTO.builder().startDatetime(Timestamp.valueOf("2017-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2017-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-10-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-10-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO madeRental = officeService.makeRental(rentalTO);
		officeService.makeRental(rentalTO2);
		officeService.makeRental(rentalTO3);

		// when
		RentalTO foundRental = officeService.findRentalById(madeRental);

		// then
		assertEquals(madeRental.getId(), foundRental.getId());
		assertEquals(madeRental.getCarId(), foundRental.getCarId());
		assertEquals(madeRental.getClientId(), foundRental.getClientId());

	}

	@Test
	public void testSouldFindRentalsByCar() {

		// given

		// Client
		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient = officeService.addClient(clientTO);

		// Office
		Address officeAddress = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String officePhone = "22223454321";
		String officeEmail = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(officeAddress).phone(officePhone).email(officeEmail).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		// Car 1
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

		// Car 2
		CarType carType2 = CarType.COUPE;
		String model2 = "Audi A5";
		Integer productionYear2 = 2011;
		String color2 = "CZARNY";
		Integer engineSize2 = 2000;
		Integer power2 = 210;
		Integer mileage2 = 25000;

		CarTO car2 = CarTO.builder().carType(carType2).model(model2).productionYear(productionYear2).color(color2)
				.engineSize(engineSize2).power(power2).mileage(mileage2).build();

		CarTO savedCar2 = carService.saveCar(car2);

		// Rental

		RentalTO rentalTO = RentalTO.builder().startDatetime(Timestamp.valueOf("2017-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2017-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-10-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-10-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		int rentalsByCarListBeforeSize = officeService.findRentalsByCar(savedCar).size();

		RentalTO madeRental = officeService.makeRental(rentalTO);
		RentalTO madeRental2 = officeService.makeRental(rentalTO2);
		officeService.makeRental(rentalTO3);

		// when
		List<RentalTO> foundRentalsList = officeService.findRentalsByCar(savedCar);

		// then
		assertEquals(rentalsByCarListBeforeSize + 2, foundRentalsList.size());
		assertTrue(foundRentalsList.stream().anyMatch(b -> b.getId().equals(madeRental.getId())));
		assertTrue(foundRentalsList.stream().anyMatch(b -> b.getId().equals(madeRental2.getId())));

	}

	@Test
	public void testShouldRemoveRentalsAfterRemovingCar() {

		// given

		// Client
		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient = officeService.addClient(clientTO);

		// Office
		Address officeAddress = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String officePhone = "22223454321";
		String officeEmail = "poz1@rental.com";

		OfficeTO office = OfficeTO.builder().address(officeAddress).phone(officePhone).email(officeEmail).build();
		OfficeTO savedOffice = officeService.addOffice(office);

		// Car 1
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

		// Car 2
		CarType carType2 = CarType.COUPE;
		String model2 = "Audi A5";
		Integer productionYear2 = 2011;
		String color2 = "CZARNY";
		Integer engineSize2 = 2000;
		Integer power2 = 210;
		Integer mileage2 = 25000;

		CarTO car2 = CarTO.builder().carType(carType2).model(model2).productionYear(productionYear2).color(color2)
				.engineSize(engineSize2).power(power2).mileage(mileage2).build();

		CarTO savedCar2 = carService.saveCar(car2);

		// Rental

		RentalTO rentalTO = RentalTO.builder().startDatetime(Timestamp.valueOf("2017-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2017-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-07-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-07-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(Timestamp.valueOf("2018-10-07 12:15:30"))
				.endDatetime(Timestamp.valueOf("2018-10-09 12:15:30")).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO madeRental = officeService.makeRental(rentalTO);
		officeService.makeRental(rentalTO2);
		officeService.makeRental(rentalTO3);

		CarTO carToRemove = CarTO.builder().id(savedCar.getId()).build();

		int rentalsByCarListBeforeSize = officeService.findRentalsByCar(savedCar2).size();

		// when
		carService.removeCar(savedCar);

		// then
		assertEquals(null, carService.findCarById(savedCar.getId()));
		assertTrue(officeService.findRentalsByCar(carToRemove).isEmpty());
		assertEquals(rentalsByCarListBeforeSize, officeService.findRentalsByCar(savedCar2).size());
		assertEquals(null, officeService.findRentalById(madeRental));

	}

	@Test
	public void testShouldAddClient() {

		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		// when
		ClientTO savedClient = officeService.addClient(clientTO);

		// then
		ClientTO foundClient = officeService.findClientById(savedClient);
		assertEquals(savedClient.getId(), foundClient.getId());
		assertEquals(clientEmail, foundClient.getEmail());

	}

	@Test
	public void testShouldFindClientById() {

		Address clientAddress = Address.builder().street("Wawrzyniaka").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();

		String clientPhone = "22224444321";
		String clientEmail = "nowak.a@gmail.com";
		Date dateOfBirth = Date.valueOf("1966-12-12");
		String pan = "0000222233334444";

		ClientTO clientTO = ClientTO.builder().lastName("Nowak").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient = officeService.addClient(clientTO);

		// when
		ClientTO foundClient = officeService.findClientById(savedClient);

		// then
		assertEquals(savedClient.getId(), foundClient.getId());
		assertEquals(clientEmail, foundClient.getEmail());

	}

	@Test
	public void testShouldFindEmployeesSupervisingCar() {

		// given

		// ---Car 1
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

		// ---Car 2
		CarType carType2 = CarType.COUPE;
		String model2 = "Audi A5";
		Integer productionYear2 = 2011;
		String color2 = "CZERWONY";
		Integer engineSize2 = 2000;
		Integer power2 = 210;
		Integer mileage2 = 25100;

		CarTO car2 = CarTO.builder().carType(carType2).model(model2).productionYear(productionYear2).color(color2)
				.engineSize(engineSize2).power(power2).mileage(mileage2).build();

		CarTO savedCar2 = carService.saveCar(car2);

		// ---Employee 1
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1968-03-12");

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		// ---Employee 2
		String position2 = "SALESPERSON";
		String lastName2 = "Kowalczyk";
		String firstName2 = "Jacek";
		Date dateOfBirth2 = Date.valueOf("1968-03-12");

		// ---Office

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();

		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();

		EmployeeTO employee2 = EmployeeTO.builder().position(position2).lastName(lastName2).firstName(firstName2)
				.dateOfBirth(dateOfBirth2).build();

		EmployeeTO savedEmployee = officeService.addEmployee(employee);
		EmployeeTO savedEmployee2 = officeService.addEmployee(employee2);
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);
		officeService.addEmployeeToOffice(savedEmployee2, savedOffice);

		int employeesSupervisingCarListBeforeSize = officeService.findEmployeesSupervisingCar(savedCar).size();

		carService.assignSupervisor(savedCar, savedEmployee);
		carService.assignSupervisor(savedCar2, savedEmployee);
		carService.assignSupervisor(savedCar, savedEmployee2);

		// when

		List<EmployeeTO> employeesSupervisingCarList = officeService.findEmployeesSupervisingCar(savedCar);

		// then

		assertEquals(employeesSupervisingCarListBeforeSize + 2, employeesSupervisingCarList.size());
		assertTrue(employeesSupervisingCarList.stream().anyMatch(b -> b.getId().equals(savedEmployee.getId())));
		assertTrue(employeesSupervisingCarList.stream().anyMatch(b -> b.getId().equals(savedEmployee2.getId())));

	}

	@Test
	public void testShouldFindEmployeesByPosition() {

		// given
		EmployeeTO employeeTOWithPosition = EmployeeTO.builder().position("MANAGER").build();
		int foundEmployeesListBeforeSize = officeService.findEmployeesByPosition(employeeTOWithPosition).size();

		// Employee 1
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1989-12-03");

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();

		EmployeeTO savedEmployee = officeService.addEmployee(employee);

		// Employee 2
		String position2 = "ACCOUNTANT";
		String lastName2 = "Kowalska";
		String firstName2 = "Anna";
		Date dateOfBirth2 = Date.valueOf("1979-12-12");

		EmployeeTO employee2 = EmployeeTO.builder().position(position2).lastName(lastName2).firstName(firstName2)
				.dateOfBirth(dateOfBirth2).build();
		officeService.addEmployee(employee2);

		// Employee 3
		String position3 = "MANAGER";
		String lastName3 = "Kowal";
		String firstName3 = "Jan";
		Date dateOfBirth3 = Date.valueOf("1981-12-03");

		EmployeeTO employee3 = EmployeeTO.builder().position(position3).lastName(lastName3).firstName(firstName3)
				.dateOfBirth(dateOfBirth3).build();

		EmployeeTO savedEmployee3 = officeService.addEmployee(employee3);

		// EmployeeTO

		officeService.getLoggerInfoOnCreationAndUpdate(savedEmployee3);

		// when
		List<EmployeeTO> foundEmployeesList = officeService.findEmployeesByPosition(employeeTOWithPosition);

		// then
		assertEquals(foundEmployeesListBeforeSize + 2, foundEmployeesList.size());
		assertTrue(foundEmployeesList.stream().anyMatch(b -> b.getId().equals(savedEmployee.getId())));
		assertTrue(foundEmployeesList.stream().anyMatch(b -> b.getId().equals(savedEmployee3.getId())));

	}

}
