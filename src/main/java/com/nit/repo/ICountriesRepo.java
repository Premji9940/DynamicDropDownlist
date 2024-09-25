package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.CountryEntity;

public interface ICountriesRepo extends JpaRepository<CountryEntity, Integer> {

}
