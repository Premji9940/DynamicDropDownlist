package com.nit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "country_master")
@Data
public class CountryEntity {
@Id
@GeneratedValue
private Integer	COUNTRY_ID  ;
private String COUNTRY_CODE ;
private String COUNTRY_NAME ;
}
