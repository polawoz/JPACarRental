package com.capgemini.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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
// @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarServiceTest {

	@Autowired
	CarService carService;

	@Autowired
	OfficeService officeService;

	@Test
	public void testShouldAssignSupervisor() {

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

		int carsSupervisedByEmployeeListBeforeSize = carService.findCarsBySupervisor(savedEmployee).size();

		// when
		carService.assignSupervisor(savedCar, savedEmployee);

		// then
		EmployeeTO employeeTO = officeService.findEmployeeById(savedEmployee.getId());
		CarTO carTO = carService.findCarById(savedCar.getId());

		List<CarTO> carsSupervisedByEmployee = carService.findCarsBySupervisor(employeeTO);

		OfficeTO officeTO = officeService.findOfficeById(savedOffice.getId());

		assertNotNull(carsSupervisedByEmployee);
		assertFalse(carsSupervisedByEmployee.isEmpty());
		assertTrue(carsSupervisedByEmployee.stream().anyMatch(b -> b.getId().equals(carTO.getId())));
		assertEquals(carsSupervisedByEmployeeListBeforeSize + 1, carsSupervisedByEmployee.size());
		assertTrue(officeTO.getEmployeesIdList().contains(employeeTO.getId()));

	}

	@Test
	public void testShouldFindCarsByType() {

		// given
		int carsByTypeListBeforeSize = carService.findCarsByType(CarType.COUPE).size();

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

		// when
		List<CarTO> foundCarsList = carService.findCarsByType(savedCar.getCarType());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(foundCarsList.stream().anyMatch(b -> b.getCarType().equals(carType)));
		assertEquals(carsByTypeListBeforeSize + 1, foundCarsList.size());

	}

	@Test
	public void testShouldFindCarsByModel() {

		// given
		int carsByModelListBeforeSize = carService.findCarsByModel("Audi A5").size();

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

		// when
		List<CarTO> foundCarsList = carService.findCarsByModel(savedCar.getModel());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(foundCarsList.stream().anyMatch(b -> b.getModel().equals(model)));
		assertEquals(carsByModelListBeforeSize + 1, foundCarsList.size());
	}

	@Test
	public void testShouldFindCarsByModelAndType() {

		// given
		int carsByModelAndTypeListBeforeSize = carService.findCarsByModelAndType("Audi", CarType.COUPE).size();

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

		// when
		List<CarTO> foundCarsList = carService.findCarsByModelAndType("Audi", savedCar.getCarType());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(
				foundCarsList.stream().anyMatch(b -> (b.getModel().equals(model)) && (b.getCarType().equals(carType))));
		assertEquals(carsByModelAndTypeListBeforeSize + 1, foundCarsList.size());

	}

	@Test
	public void testShouldFindCarsBySupervisor() {

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

		// ---Employee
		String position = "MANAGER";
		String lastName = "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = Date.valueOf("1968-03-12");

		Address address = Address.builder().street("Niepodleglosci").buildingNumber("12").flatNumber("1")
				.postalCode("60-333").town("Poznan").country("Polska").build();
		String phone = "22223454321";
		String email = "poz1@rental.com";

		// ---Office

		OfficeTO office = OfficeTO.builder().address(address).phone(phone).email(email).build();

		OfficeTO savedOffice = officeService.addOffice(office);

		EmployeeTO employee = EmployeeTO.builder().position(position).lastName(lastName).firstName(firstName)
				.dateOfBirth(dateOfBirth).build();

		EmployeeTO savedEmployee = officeService.addEmployee(employee);
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		int supervisorsCarsListBeforeSize = carService.findCarsBySupervisor(savedEmployee).size();

		carService.assignSupervisor(savedCar, savedEmployee);
		carService.assignSupervisor(savedCar2, savedEmployee);

		// when

		List<CarTO> supervisorsCarList = carService.findCarsBySupervisor(savedEmployee);

		// then

		assertEquals(supervisorsCarsListBeforeSize + 2, supervisorsCarList.size());
		assertTrue(supervisorsCarList.stream().anyMatch(b -> b.getId().equals(savedCar.getId())));
		assertTrue(supervisorsCarList.stream().anyMatch(b -> b.getId().equals(savedCar2.getId())));

	}

	@Test
	public void testShouldUpdateColor() {

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

		CarTO updateParameters = CarTO.builder().id(savedCar.getId()).color("ZIELONY").build();

		// when

		CarTO carTOAfterUpdate = carService.update(updateParameters);
		CarTO carFromDB = carService.findCarById(savedCar.getId());

		// then
		assertEquals(carTOAfterUpdate.getColor(), updateParameters.getColor());
		assertEquals(carFromDB.getColor(), updateParameters.getColor());

	}

	@Test
	public void testShouldSaveCar() {

		// given
		Long id = null;
		CarType carType = CarType.COUPE;
		String model = "Audi A5";
		Integer productionYear = 2011;
		String color = "CZARNY";
		Integer engineSize = 2000;
		Integer power = 210;
		Integer mileage = 25000;

		CarTO car = CarTO.builder().id(id).carType(carType).model(model).productionYear(productionYear).color(color)
				.engineSize(engineSize).power(power).mileage(mileage).build();

		CarTO savedCar = carService.saveCar(car);

		// when

		CarTO selectedCar = carService.findCarById(savedCar.getId());

		// then
		assertNotNull(selectedCar);
		assertEquals(savedCar.getCarType(), selectedCar.getCarType());
		assertEquals(savedCar.getModel(), selectedCar.getModel());
		assertEquals(savedCar.getProductionYear(), selectedCar.getProductionYear());
		assertEquals(savedCar.getColor(), selectedCar.getColor());
		assertEquals(savedCar.getEngineSize(), selectedCar.getEngineSize());
		assertEquals(savedCar.getPower(), selectedCar.getPower());
		assertEquals(savedCar.getMileage(), selectedCar.getMileage());

	}

	@Test
	public void testShouldFindCarById() {

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

		// when

		CarTO selectedCar = carService.findCarById(savedCar.getId());

		// then
		assertNotNull(selectedCar);
		assertEquals(savedCar.getCarType(), selectedCar.getCarType());
		assertEquals(savedCar.getModel(), selectedCar.getModel());
		assertEquals(savedCar.getProductionYear(), selectedCar.getProductionYear());
		assertEquals(savedCar.getColor(), selectedCar.getColor());
		assertEquals(savedCar.getEngineSize(), selectedCar.getEngineSize());
		assertEquals(savedCar.getPower(), selectedCar.getPower());
		assertEquals(savedCar.getMileage(), selectedCar.getMileage());

	}

	@Test
	public void testShouldRemoveCar() {

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
		CarTO selectedCar = carService.findCarById(savedCar.getId());

		// when

		carService.removeCar(CarTO.builder().id(selectedCar.getId()).build());

		// then
		CarTO searchedCar = carService.findCarById(selectedCar.getId());

		assertEquals(searchedCar, null);

	}

	@Test
	public void findCarsRentedByMoreThan10Clients() {

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
		RentalTO rentalTO = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(new Timestamp(2018, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2018, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(new Timestamp(2018, 10, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2018, 10, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		// --Car with 11 rentals
		CarType carType3 = CarType.COUPE;
		String model3 = "Audi A5";
		Integer productionYear3 = 2016;
		String color3 = "SREBRNY";
		Integer engineSize3 = 2000;
		Integer power3 = 210;
		Integer mileage3 = 25000;

		CarTO car3 = CarTO.builder().carType(carType3).model(model3).productionYear(productionYear3).color(color3)
				.engineSize(engineSize3).power(power3).mileage(mileage3).build();

		CarTO savedCar3 = carService.saveCar(car3);

		ClientTO clientTO2 = ClientTO.builder().lastName("Nowacki").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO3 = ClientTO.builder().lastName("Nowakowski").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO4 = ClientTO.builder().lastName("Nowaczek").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO5 = ClientTO.builder().lastName("Kowalczyk").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO6 = ClientTO.builder().lastName("Kownacki").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO7 = ClientTO.builder().lastName("Kolbert").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO8 = ClientTO.builder().lastName("Jurczyk").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO9 = ClientTO.builder().lastName("Juszczuk").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO10 = ClientTO.builder().lastName("Jaki").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO11 = ClientTO.builder().lastName("Taki").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();
		ClientTO clientTO12 = ClientTO.builder().lastName("Owaki").firstName("Andrzej").address(clientAddress)
				.dateOfBirth(dateOfBirth).phone(clientPhone).email(clientEmail).pan(pan).build();

		ClientTO savedClient2 = officeService.addClient(clientTO2);
		ClientTO savedClient3 = officeService.addClient(clientTO3);
		ClientTO savedClient4 = officeService.addClient(clientTO4);
		ClientTO savedClient5 = officeService.addClient(clientTO5);
		ClientTO savedClient6 = officeService.addClient(clientTO6);
		ClientTO savedClient7 = officeService.addClient(clientTO7);
		ClientTO savedClient8 = officeService.addClient(clientTO8);
		ClientTO savedClient9 = officeService.addClient(clientTO9);
		ClientTO savedClient10 = officeService.addClient(clientTO10);
		ClientTO savedClient11 = officeService.addClient(clientTO11);
		ClientTO savedClient12 = officeService.addClient(clientTO12);

		RentalTO rentalTOa2 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient2.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa3 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient2.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa4 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient2.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa5 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient2.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa6 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient2.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa7 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient7.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa8 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient8.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa9 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient9.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa10 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient10.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa11 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient11.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();
		RentalTO rentalTOa12 = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 30, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 30, 0)).loanAmount(150F).clientId(savedClient12.getId())
				.carId(savedCar3.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		int foundCarsListBeforeSize = carService.findCarsRentedByClientsOfNumberMoreThan(10L).size();

		RentalTO madeRentala2 = officeService.makeRental(rentalTOa2);
		RentalTO madeRentala3 = officeService.makeRental(rentalTOa3);
		RentalTO madeRentala4 = officeService.makeRental(rentalTOa4);
		RentalTO madeRentala5 = officeService.makeRental(rentalTOa5);
		RentalTO madeRentala6 = officeService.makeRental(rentalTOa6);
		RentalTO madeRentala7 = officeService.makeRental(rentalTOa7);
		RentalTO madeRentala8 = officeService.makeRental(rentalTOa8);
		RentalTO madeRentala9 = officeService.makeRental(rentalTOa9);
		RentalTO madeRentala10 = officeService.makeRental(rentalTOa10);
		RentalTO madeRentala11 = officeService.makeRental(rentalTOa11);
		RentalTO madeRentala12 = officeService.makeRental(rentalTOa12);

		// Other rentals
		RentalTO madeRental = officeService.makeRental(rentalTO);
		RentalTO madeRental2 = officeService.makeRental(rentalTO2);
		RentalTO madeRental3 = officeService.makeRental(rentalTO3);

		// when
		List<CarTO> foundCarsList = carService.findCarsRentedByClientsOfNumberMoreThan(10L);

		// then
		assertEquals(foundCarsListBeforeSize, foundCarsList.size());

	}

	@Test
	public void testShouldGetNumberOfCarsInRentalBetweenTimeperiod() {

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
		RentalTO rentalTO = RentalTO.builder().startDatetime(new Timestamp(2017, 7, 7, 12, 15, 0, 0))
				.endDatetime(new Timestamp(2017, 7, 9, 12, 15, 0, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO2 = RentalTO.builder().startDatetime(new Timestamp(2018, 7, 7, 12, 15, 0, 0))
				.endDatetime(new Timestamp(2018, 7, 9, 12, 15, 0, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		RentalTO rentalTO3 = RentalTO.builder().startDatetime(new Timestamp(2018, 10, 3, 12, 15, 0, 0))
				.endDatetime(new Timestamp(2018, 10, 9, 12, 15, 0, 0)).loanAmount(150F).clientId(savedClient.getId())
				.carId(savedCar2.getId()).pickUpOfficeId(savedOffice.getId()).returnOfficeId(savedOffice.getId())
				.build();

		Timestamp start_Timestamp = new Timestamp(2018, 6, 20, 11, 30, 0, 0);
		Timestamp end_Timestamp = new Timestamp(2018, 10, 6, 16, 30, 0, 0);

		Long numberOfCarsInRentalBetweenTimeperiodBefore = carService
				.countCarsInRentalBetweenTimeperiod(start_Timestamp, end_Timestamp);

		RentalTO madeRental = officeService.makeRental(rentalTO);
		RentalTO madeRental2 = officeService.makeRental(rentalTO2);
		RentalTO madeRental3 = officeService.makeRental(rentalTO3);

		// when
		Long numberOfCarsInRentalBetweenTimeperiod = carService.countCarsInRentalBetweenTimeperiod(start_Timestamp,
				end_Timestamp);

		// then
		assertEquals(numberOfCarsInRentalBetweenTimeperiodBefore.longValue() + 2L,
				numberOfCarsInRentalBetweenTimeperiod.longValue());

	}

}
