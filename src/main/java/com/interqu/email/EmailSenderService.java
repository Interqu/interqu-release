package com.interqu.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.interqu.InterquReleaseApplication;
import com.interqu.user.PendingUser;
import com.interqu.utils.Constants;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService{

	@Autowired
	private JavaMailSender emailSender;
	
	private final String fromAddress = "interqu.service@gmail.com";
	private final String senderName = "Interqu System";
	
	@Async
	public void sendTestUserRegistrationEmail(PendingUser user) {
		try {
			String subject = "Interqu Beta User Registration";
			String registerURL = InterquReleaseApplication.SITE_URL + "/dev" + Constants.BETA_USER_REGISTER_URL +  user.getRegistrationCode();	
			String content = "Hi " + user.getFullName()+ ", \n Please register here for the beta users + \n" + registerURL;
			
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom(fromAddress, senderName);
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			
			helper.setText(content,true);
			emailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
