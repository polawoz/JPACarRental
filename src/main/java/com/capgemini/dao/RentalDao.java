package com.capgemini.dao;

import java.util.List;


import com.capgemini.domain.RentalEntity;


public interface RentalDao extends Dao<RentalEntity, Long>{

	List<RentalEntity> findRentalsByCar(Long carId);
	
	
	
	


}
