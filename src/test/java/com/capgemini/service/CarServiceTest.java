package com.capgemini.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.enums.CarType;
import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceTest.class);
	
	@Autowired
	CarService carService;

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
		assertTrue(foundCarsList.stream().anyMatch(b -> b.getModel().equals(model)));
		assertTrue(foundCarsList.size() == 1);

	}

	@Test
	public void testFindCarsByModel() {
		fail("Not yet implemented");
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
	public void testSaveCar() {

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
		
		LOGGER.info("Creation time: " + selectedCar.getCreated());

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
	public void testRemoveCar() {

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
