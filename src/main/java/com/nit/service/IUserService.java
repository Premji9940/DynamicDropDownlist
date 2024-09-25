package com.nit.service;

import java.util.Map;

import com.nit.entity.UserAccount;

import jakarta.mail.MessagingException;

public interface IUserService {
	
	public Map<Integer,String> getAllCountries();
	public Map<Integer,String> getAllStates(Integer countryId);
	public Map<Integer,String> getAllCitiesByStateId(Integer stateId);
	
	public String upsertUser(UserAccount ua) throws MessagingException;
	
	public String checkingMail(String mail);
	
	public String getTempPwd(String mail);
	
	public int updatePwdAndStatus(String mail,String pwd,String status);

	public int checkCredential(String mail,String pwd);

	public String checkStatus(String mail,String pwd);

	
}
