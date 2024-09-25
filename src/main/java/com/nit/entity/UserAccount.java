package com.nit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
//this execute only when we perform delete operation using repository method
//@SQLDelete(sql = "update user_account set status='inactive' where id=?")
//@where is implicit condition it is implicitly added to every query 
//@Where(clause = "status != 'InActive'")
@Table(name="User_register")
public class UserAccount {
	@Id
	@GeneratedValue
	private Integer id;
	private String fname;
	private String lname;
	private String mail;
	
	private Long mobile;
	private LocalDate dob;
	private String gender;

	private String country;
	private String  state;
	private String city;
	
	private String password;
	
	private String status;
	
	

}
