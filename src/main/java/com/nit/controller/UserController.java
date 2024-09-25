package com.nit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.entity.UserAccount;
import com.nit.service.UserServiceImpl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("/")
	public String getHome() {
		log.debug("getHome() from UserController ");
	
		
		return "home";
	}
	public UserController() {
		log.info("creating UserController instance");
	}
	@GetMapping("/register")
	public String loadForm(@ModelAttribute("user") UserAccount us,Model m) {
		log.debug("started loadForm() of UserController");
		log.debug("getingn AllCountries from loadForm () of UserController");
		Map<Integer, String> allCountries = service.getAllCountries();
		log.info("getting all states from loadForm () -- UserController");
		m.addAttribute("country", allCountries);
		log.debug("loadForm () started of UserController");
		return "form";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserAccount us,Model m,RedirectAttributes rd) throws MessagingException {
		log.debug("starting register() of UserController");
		log.info("calling insert/update from register() of UserController");
		String upsertUser = service.upsertUser(us);
		
		rd.addFlashAttribute("msg",upsertUser);
		log.debug("ending register() of UserController");

		return  "redirect:/register";
	}
	@GetMapping("/states")
	@ResponseBody
	public Map<Integer, String> getAllStatesByCountryId(@RequestParam Integer cid){
		log.debug("started getAllStatesByCountryId() of userController");
		log.info("calling getAllStates() from UserController");
		Map<Integer, String> allStates = service.getAllStates(cid);
		
		log.debug("started getAllStatesByCountryId() of userController");
		return allStates;
		
	}
	
	@GetMapping("/cities")
	@ResponseBody
	public Map<Integer, String> getAllCitiesByStateId(@RequestParam Integer sid){
		log.debug("started getAllCitiesByStateId() of userController");
		log.info("calling getAllCitiesByStateId() from UserController");
	
		Map<Integer, String> allCities = service.getAllCitiesByStateId(sid);
		log.debug("ended getAllCitiesByStateId() of userController");
		return allCities;
		
	}
	@GetMapping("/check")
	@ResponseBody
	public String checkingEmail(@RequestParam String mail) {
		log.debug(" started checkingEmail() from UserController");
		log.debug(" ended checkingEmail() from UserController");

		return service.checkingMail(mail);
	}
	
	@GetMapping("/login")
	public String showLogin() {
		log.debug(" started showLogin() from UserController");
		log.debug(" ended showLogin() from UserController");

		return "login";
	}
	@PostMapping("/login")
	public String Login(@RequestParam String mail,@RequestParam String pwd,Model m) {
		log.debug(" started Login() from UserController");
		log.info(" calling checkCredential from Login() from UserController");

		int checkCredential = service.checkCredential(mail, pwd);
		String status=service.checkStatus(mail, pwd);
		
		
		if(checkCredential==0) {
			m.addAttribute("msg", "Credential are invalid");
			log.debug(" ended Login() from UserController");

			return "login";
		}
			if(status.equalsIgnoreCase("LOCKED")) {
				m.addAttribute("msg", "Accont locked");
				log.debug(" ended Login() from UserController");

				return "login";
			}
		
			log.debug(" ended Login() from UserController");
	
		return "home";
	}
	
	@GetMapping("/forgot")
	public String showForgot() {
		log.debug("started showForgot() from UserController");
		log.debug("ended showForgot() from UserController");

		return "forgot";
		
	
	}
	@PostMapping("/forgot")
	public String getForgotPwd(@RequestParam String mail,Model m) {
		log.debug("started getForgotPwd() of UserController");
		log.info("Calling getTempPwd() from getForgotPwd() of UserController");
		String pwd=	service.getTempPwd(mail);
		if(pwd==null) {
			m.addAttribute("msg", "invalid mail");
			return "forgot";
		}
		m.addAttribute("msg", "password is Send to your mail check it");
		log.debug("ended getForgotPwd() of UserController");

			return "login";
	}
	@GetMapping("/unlock")
	public String showUnlockPage(@RequestParam String mail,Model m) {
		log.debug("started showUnlockPage() from UserController");
		m.addAttribute("mail", mail);	
		log.debug("ended showUnlockPage() from UserController");

		return "unlock";
	}
	@PostMapping("/unlock")
	public String unlockingAcc(@RequestParam String mail,@RequestParam String tempPassword,@RequestParam String newPassword,@RequestParam String conPassword,Model m,RedirectAttributes rat) {
log.debug("started unlockingAcc() from UserController");
		String tempPwd=service.getTempPwd(mail);
	if(tempPwd.equals(tempPassword)) {
		if(newPassword.equals(conPassword)) {
			log.debug("calling UpdatePwdAndStatus() from unlockingACC() of UserController");
			int up = service.updatePwdAndStatus(mail, newPassword,"UNLOCKED" );
			if(up==1) {
				m.addAttribute("msg", "Account unlocked suceess, please proceed with login");
	
			}else {
				m.addAttribute("mail", mail);	

				return "unlock";
			}
			
			
	
		}else {
			m.addAttribute("msg", "password not matched");
			m.addAttribute("mail", mail);	

			return "unlock";
		}
		rat.addAttribute("msg", "Account unlocked, please proceed with login");
		return "login";
	}
	m.addAttribute("mail", mail);	
	m.addAttribute("msg", "Temparary password incorrect");
	log.debug("ended unlockingAcc() from UserController");

		return "unlock";
	}
}
