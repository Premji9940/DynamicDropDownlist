package com.nit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.text.log.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UserMiniProject1Application {

	public static void main(String[] args) {
		log.trace("project is started");
		log.debug("main method is started");
		SpringApplication.run(UserMiniProject1Application.class, args);
		log.debug("main method is ended");	
		log.trace("prjoct is about to complete..");
	}
}
