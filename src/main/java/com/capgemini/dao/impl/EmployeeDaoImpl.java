package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@Override
	public List<EmployeeEntity> findEmployeesByOfficeAndCar(Long officeId, Long carId) {

		TypedQuery<EmployeeEntity> query = entityManager
				.createQuery("SELECT emp FROM EmployeeEntity emp JOIN emp.carsUnderSupervision car "

						+ "WHERE (emp.office.id = :officeId AND car.id= :carId)", EmployeeEntity.class);
		query.setParameter("officeId", officeId);
		query.setParameter("carId", carId);
		return query.getResultList();

	}

	@Override
	public List<EmployeeEntity> findEmployeesByPosition(String position) {

		TypedQuery<EmployeeEntity> query = entityManager
				.createQuery("SELECT emp FROM EmployeeEntity emp WHERE emp.position = :position", EmployeeEntity.class);
		query.setParameter("position", position);
		return query.getResultList();

	}

}
