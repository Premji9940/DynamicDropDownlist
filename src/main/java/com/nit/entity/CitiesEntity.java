package com.nit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "cities_master")
@Data
public class CitiesEntity {
@Id
@GeneratedValue
private Integer	CITY_ID  ;
private Integer STATE_ID ;
private String CITY_NAME ;
}
