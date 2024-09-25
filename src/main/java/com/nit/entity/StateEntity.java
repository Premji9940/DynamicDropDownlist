package com.nit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "states_master")
@Data
public class StateEntity {
@Id
@GeneratedValue
private Integer	STATE_ID  ;
private Integer COUNTRY_ID ;
private String STATE_NAME ;
}
