package com.capgemini.dao.impl;


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


	

	

}
