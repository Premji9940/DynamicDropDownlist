package com.nit.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nit.entity.UserAccount;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailUtils {
	@Autowired JavaMailSender sender;
	public MailUtils() {
log.info("creating MailUtils instance");
}
	
	public void sendMail(UserAccount ac) throws MessagingException {
		log.debug("started sendMail from MailUtils");
		MimeMessage createMimeMessage = sender.createMimeMessage();
		MimeMessageHelper mh=new MimeMessageHelper(createMimeMessage,true);
		mh.setTo(ac.getMail());
		mh.setSubject("UnLock Account");
		
		String acc_lock ="<h1 style=color:blue;align:center> Account Unlock<h1><br> hello Mr/Ms "+ac.getFname()+"\t"+ac.getLname()+" you registerd successfully with "
				+ac.getId()+"<br>"
				+"Your Unlock account Temporary Password is <h4 style=color:red;>"+ac.getPassword()+"</h4>.<br>"
				+"<a href=http://localhost:4646/unlock?mail="+ac.getMail()+">click here to unlock the account</a>"
				;
		mh.setText(acc_lock,true);
		log.info("sending Mail from  sendMail() of  MailUtils");

		sender.send(createMimeMessage);
		System.out.println("message sent");
				
		log.debug("ended sendMail from MailUtils");
		
		
	
	}

}
