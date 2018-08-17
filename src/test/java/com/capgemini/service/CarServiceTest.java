package com.capgemini.service;

import static org.junit.Assert.*;

import java.sql.Date;

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
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarServiceTest {


	@Autowired
	CarService carService;
	
	@Autowired
	OfficeService officeService;

	@Test
	public void testAssignSupervisor() {

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
		String lastName= "Kowalczyk";
		String firstName = "Patryk";
		Date dateOfBirth = new Date(1221L);
		
		
		
		Address address = Address.builder()
				.street("Niepodleglosci")
				.buildingNumber("12")
				.flatNumber("1")
				.postalCode("60-333")
				.town("Poznan")
				.country("Polska")
				.build();
		String phone="22223454321";
		String email="poz1@rental.com";
		
		
		OfficeTO office = OfficeTO.builder()
				.address(address)
				.phone(phone)
				.email(email)
				.build();
		
		OfficeTO savedOffice = officeService.addOffice(office);
		
		
		EmployeeTO employee= EmployeeTO.builder()
				.position(position)
				.lastName(lastName)
				.firstName(firstName)
				.dateOfBirth(dateOfBirth)
				.build();
		
		EmployeeTO savedEmployee= officeService.addEmployee(employee);
		officeService.addEmployeeToOffice(savedEmployee, savedOffice);

		//when
		carService.assignSupervisor(savedCar, savedEmployee);
		
		
		//then
		EmployeeTO employeeTO = officeService.findEmployeeById(savedEmployee.getId());
		CarTO carTO = carService.findCarById(savedCar.getId());
		
		List<CarTO> carsSupervisedByEmployee = carService.findCarsBySupervisor(employeeTO);
		
		OfficeTO officeTO = officeService.findOfficeById(savedOffice.getId());
		
		
		assertNotNull(carsSupervisedByEmployee);
		assertFalse(carsSupervisedByEmployee.isEmpty());
		assertTrue(carsSupervisedByEmployee.stream().anyMatch(b -> b.getId().equals(carTO.getId())));
		assertTrue(carsSupervisedByEmployee.size() == 1);
		assertTrue(officeTO.getEmployeesIdList().contains(employeeTO.getId()));
		
		
	

	}

	@Test
	public void testFindCarsByType() {

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
		List<CarTO> foundCarsList = carService.findCarsByType(savedCar.getCarType());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(foundCarsList.stream().anyMatch(b -> b.getCarType().equals(carType)));
		assertTrue(foundCarsList.size() == 1);

	}

	@Test
	public void testFindCarsByModel() {

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
		List<CarTO> foundCarsList = carService.findCarsByModel(savedCar.getModel());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(foundCarsList.stream().anyMatch(b -> b.getModel().equals(model)));
		assertTrue(foundCarsList.size() == 1);
	}
	


	@Test
	public void testFindCarsByModelAndType() {

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
		List<CarTO> foundCarsList = carService.findCarsByModelAndType("Audi", savedCar.getCarType());

		// then
		assertNotNull(foundCarsList);
		assertFalse(foundCarsList.isEmpty());
		assertTrue(
				foundCarsList.stream().anyMatch(b -> (b.getModel().equals(model)) && (b.getCarType().equals(carType))));
		assertTrue(foundCarsList.size() == 1);

	}

	@Test
	public void testFindCarsBySupervisor() {

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
	
	
	
	
	
	
	
	

}
