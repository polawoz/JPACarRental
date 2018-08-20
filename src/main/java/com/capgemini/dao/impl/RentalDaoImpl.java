package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.RentalDao;
import com.capgemini.domain.RentalEntity;

@Repository
public class RentalDaoImpl extends AbstractDao<RentalEntity, Long> implements RentalDao {

	@Override
	public List<RentalEntity> findRentalsByCar(Long carId) {

		TypedQuery<RentalEntity> query = entityManager.createQuery("SELECT rental FROM RentalEntity rental "

				+ "WHERE rental.car.id =:carId", RentalEntity.class);
		query.setParameter("carId", carId);
		return query.getResultList();
	}

}
