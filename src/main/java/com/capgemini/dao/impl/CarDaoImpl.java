package com.capgemini.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.enums.CarType;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@Override
	public List<CarEntity> findCarByType(CarType carType) {

		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.carType) like concat(upper(:carType), '%')",
				CarEntity.class);
		query.setParameter("carType", carType.toString());
		return query.getResultList();

	}

	@Override
	public List<CarEntity> findCarByModel(String model) {

		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.model) like concat(upper(:model), '%')",
				CarEntity.class);
		query.setParameter("model", model);
		return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarByModelAndType(String model, CarType carType) {

		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car "
						+ "where (upper(car.model) like concat(upper(:model), '%') AND upper(car.carType) like concat(upper(:carType), '%'))",
				CarEntity.class);

		query.setParameter("model", model);
		query.setParameter("carType", carType.toString());
		return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarsRentedByClientsOfNumberMoreThan(Long clientsNumber) {

		TypedQuery<CarEntity> query = entityManager.createQuery("SELECT car FROM CarEntity car where car.id in "
				+ "(select rental.car.id from RentalEntity rental group by rental.car.id "
				+ "having count(distinct rental.client.id)> :clientsNumber)"

		, CarEntity.class);
		query.setParameter("clientsNumber", clientsNumber);

		return query.getResultList();

	}

	@Override
	public Long countCarsInRentalBetweenTimeperiod(Timestamp startTimestamp, Timestamp endTimestamp) {

		TypedQuery<Long> query = entityManager
				.createQuery(
						"SELECT COUNT(car) from CarEntity car JOIN car.rentals rental "
								+ "WHERE (rental.startDatetime BETWEEN :startDatetime AND :endDatetime) OR "
								+ "(rental.endDatetime BETWEEN :startDatetime AND :endDatetime) OR "
								+ "(rental.startDatetime<:startDatetime AND rental.endDatetime>:endDatetime)",
						Long.class);
		query.setParameter("startDatetime", startTimestamp);
		query.setParameter("endDatetime", endTimestamp);

		return query.getSingleResult();
	}

}
