package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.CitiesEntity;

public interface ICitiesRepo extends JpaRepository<CitiesEntity, Integer> {
	
	@Query("from CitiesEntity where STATE_ID=:sid")
	List<CitiesEntity> getAllCitiesByStateId(Integer sid);

}
