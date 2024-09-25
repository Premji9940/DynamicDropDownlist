package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.StateEntity;

public interface IStatesRepo extends JpaRepository<StateEntity, Integer> {
	@Query(" from StateEntity where COUNTRY_ID=:country_id")
	public List<StateEntity> getAllStatesByCountryId(Integer country_id);

}
