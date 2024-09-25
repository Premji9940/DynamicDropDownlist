package com.nit.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.constants.AppConstants;
import com.nit.entity.CitiesEntity;
import com.nit.entity.CountryEntity;
import com.nit.entity.StateEntity;
import com.nit.entity.UserAccount;
import com.nit.mail.MailUtils;
import com.nit.pwdutils.PwdUtils;
import com.nit.repo.ICitiesRepo;
import com.nit.repo.ICountriesRepo;
import com.nit.repo.IStatesRepo;
import com.nit.repo.IUserRepo;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepo repo;
	@Autowired private MailUtils mailUtil;
	
	@Autowired ICountriesRepo crepo;
	@Autowired IStatesRepo srepo;
	@Autowired ICitiesRepo cityRepo;
	public UserServiceImpl() {
    log.info("Creating UserServiceImpl instance");
}
	@Override
	public Map<Integer, String> getAllCountries() {
	log.debug("getAllContries-UserServiceImpl stared");
		log.debug("get All Countties from userService Impl");
		
		Map<Integer,String> m=new LinkedHashMap<>();
		log.info("Calling findAll() from  All Countties from userService Impl");	
		List<CountryEntity> allCountries = crepo.findAll();
		allCountries.forEach(data->{
			m.put(data.getCOUNTRY_ID(), data.getCOUNTRY_NAME());	
		});	
		log.debug("getAllCountri-UserService imple is ended..");
		return m;
	}

	@Override
	public Map<Integer, String> getAllStates(Integer countryId) {
		log.debug("started getAllStates from  UserServiceImpl--Service");
		Map<Integer,String> m=new LinkedHashMap<>();
		log.info("calling getAllStatesByCountryId() from getAllStates of UserImpl");
		  List<StateEntity> allCountries = srepo.getAllStatesByCountryId(countryId);
		  allCountries.forEach(data->{ m.put(data.getSTATE_ID(), data.getSTATE_NAME());
		  });
			log.debug("ended getAllStates from  UserServiceImpl--Service");
		return m;
	}

	@Override
	public Map<Integer, String> getAllCitiesByStateId(Integer stateId) {
		log.debug("started getAllCitiesByStateId() from getAllCitiesByStateId of UserServiceImpl");
		Map<Integer,String> m=new LinkedHashMap<>();
		log.info("calling getAllCitiesByStateId(stateId) from getAllCitiesByStateId of UserServiceImpl");
	
		  List<CitiesEntity> allCities = cityRepo.getAllCitiesByStateId(stateId);
		  System.out.println(allCities);
		  allCities.forEach(data->{ m.put(data.getCITY_ID(), data.getCITY_NAME());
		  });
			log.debug("ended getAllCitiesByStateId() from getAllCitiesByStateId of UserServiceImpl");	 
		return m;
	}

	@Override
	public String upsertUser(UserAccount ua) throws MessagingException {
	log.debug("started  upsertUser() from UserServiceImpl");
		
		if(ua.getId()==null) {
			
			try {
			String tempPwd=	PwdUtils.generateTempPassword(AppConstants.TEMP_PWD_LENGTH);
			ua.setPassword(tempPwd);
			ua.setStatus(AppConstants.LOCKED_STR);
			log.info("calling repo.save() for insert from upsertUser() of UserServiceImpl");
		UserAccount user = repo.save(ua);
		log.info("calling sendMail() forfrom upsertUser() of UserServiceImpl");
		mailUtil.sendMail(user);

			}catch(Exception e) {
				return "problem in registration";
				
			}
			log.debug("ended  upsertUser() from UserServiceImpl");
		
		return "Please check your email to unlock account";
		}
		if(ua.getId()!=null) {
			log.info("calling repo.save() for update from upsertUser() of UserServiceImpl");
			UserAccount save = repo.save(ua);
			log.debug("ended  upsertUser() from UserServiceImpl");

			return "user Updated successfully with "+save.getId();	
		}
		return null;
	}
	@Override
	public String checkingMail(String mail) {
		log.debug("started checkingMail() of UserServiceIMpl");
		Integer checkEmail = repo.checkEmail(mail);
		if(checkEmail==1) {
			log.debug("started checkingMail() of UserServiceIMpl");
			return "email is already used try another one";
		}
		log.debug("started checkingMail() of UserServiceIMpl");
		return "";
	}
	@Override
	public String getTempPwd(String mail) {
		log.debug("started getTempPwd from UserServiceImpl");
		log.debug("ended getTempPwd from UserServiceImpl");

		return repo.getTempPassword(mail);
	}
	@Override
	public int updatePwdAndStatus(String mail, String pwd, String status) {
		log.debug("started updatePwdAndStatus from UserServiceImpl");
		log.debug("ended updatePwdAndStatus from UserServiceImpl");
		
		return repo.UpdatePasswordAndStatus(pwd, status, mail);
	}
	@Override
	public int checkCredential(String mail, String pwd) {
		log.debug("started checkCredential from UserServiceImpl");
		log.debug("ended checkCredential from UserServiceImpl");
	
		return repo.checkCrdential(mail, pwd);
	}
	@Override
	public String checkStatus(String mail, String pwd) {
		log.debug("started checkStatus from UserServiceImpl");
		log.debug("ended checkStatus from UserServiceImpl");
	
		return repo.checkStatus(mail, pwd);
	}

}
