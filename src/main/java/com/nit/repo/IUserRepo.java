package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nit.entity.UserAccount;



public interface IUserRepo extends JpaRepository<UserAccount, Integer>{
	
	@Query(" select count(*) from UserAccount where mail=:mailId")
	public Integer checkEmail(String mailId);
	
	@Query("select password from UserAccount where mail=:mailId")
	public String getTempPassword(String mailId);
    
	@Query("update UserAccount set password=:pwd, status=:st where mail=:mailId")
	@Modifying
	@Transactional
	public int UpdatePasswordAndStatus(String pwd,String st,String mailId);
	
	@Query("select count(*) from UserAccount where mail=:mailId and password=:pwd")
	public int checkCrdential(String mailId,String pwd);
	@Query("select status from UserAccount where mail=:mailId and password=:pwd")
	public String checkStatus(String mailId,String pwd);
}
