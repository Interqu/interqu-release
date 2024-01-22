package com.interqu.email;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.interqu.InterquReleaseApplication;
import com.interqu.user.User;
import com.interqu.utils.Constants;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService{

	@Autowired
	private JavaMailSender emailSender;
	
	private final String fromAddress = "interqu.service@gmail.com";
	private final String senderName = "Interqu System";

	private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
	
	@Async
	public void sendUserVerificationCode(User user) throws MessagingException, UnsupportedEncodingException{
			String subject = "Interqu User Verification";
			String registerURL = InterquReleaseApplication.SITE_URL + "/register" + Constants.USER_VERIFICATION_URL +  user.getVerificationCode();	
			String content = "Hi " + user.getFirstName()+ ", \n Please click here to verify your account! + \n" + registerURL;
			
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom(fromAddress, senderName);
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			
			// helper.setText(content,true);
			//TODO send from file instead
			message.setContent("<!DOCTYPE html>\r\n" + //
					"<html>\r\n" + //
					"<head>\r\n" + //
					"  <meta charset=\"UTF-8\">\r\n" + //
					"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
					"  <title>Email Verification</title>\r\n" + //
					"  <style>\r\n" + //
					"    body {\r\n" + //
					"      margin: 0;\r\n" + //
					"      padding: 0;\r\n" + //
					"      background-color: #f5f5f5;\r\n" + //
					"      font-family: Arial, sans-serif;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .container {\r\n" + //
					"      max-width: 600px;\r\n" + //
					"      margin: 0 auto;\r\n" + //
					"      padding: 20px;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .logo {\r\n" + //
					"      text-align: center;\r\n" + //
					"      margin-bottom: 20px;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .logo img {\r\n" + //
					"      max-width: 150px;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .message {\r\n" + //
					"      background-color: #fff;\r\n" + //
					"      border-radius: 5px;\r\n" + //
					"      padding: 20px;\r\n" + //
					"      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .title {\r\n" + //
					"      text-align: center;\r\n" + //
					"      font-size: 24px;\r\n" + //
					"      margin-bottom: 10px;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .content {\r\n" + //
					"      margin-bottom: 20px;\r\n" + //
					"      line-height: 1.6;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .btn {\r\n" + //
					"      display: inline-block;\r\n" + //
					"      background-color: #007bff;\r\n" + //
					"      color: #fff;\r\n" + //
					"      text-decoration: none;\r\n" + //
					"      padding: 10px 20px;\r\n" + //
					"      border-radius: 5px;\r\n" + //
					"    }\r\n" + //
					"    \r\n" + //
					"    .btn:hover {\r\n" + //
					"      background-color: #0056b3;\r\n" + //
					"    }\r\n" + //
					"  </style>\r\n" + //
					"</head>\r\n" + //
					"<body>\r\n" + //
					"  <div class=\"container\">\r\n" + //
					"    <div class=\"logo\">\r\n" + //
					"      <img src=\"https://example.com/logo.png\" alt=\"Interqu Logo\">\r\n" + //
					"    </div>\r\n" + //
					"    <div class=\"message\">\r\n" + //
					"      <h1 class=\"title\">Email Verification</h1>\r\n" + //
					"      <p class=\"content\">Dear "+user.getFirstName()+",</p>\r\n" + //
					"      <p class=\"content\">Thank you for signing up. To complete your registration, please click the button below to verify your email address:</p>\r\n" + //
					"      <p class=\"content\">\r\n" + //
					"        <a class=\"btn\" href=\""+registerURL+"\">Verify Email</a>\r\n" + //
					"      </p>\r\n" + //
					"      <p class=\"content\">If you didn't create an account, you can safely ignore this email.</p>\r\n" + //
					"      <p class=\"content\">Best regards,<br>Interqu Devs</p>\r\n" + //
					"    </div>\r\n" + //
					"  </div>\r\n" + //
					"</body>\r\n" + //
					"</html>\r\n" + //
					"","text/html");
			emailSender.send(message);
			logger.info("Verification Code Email Sent Successfully To: " + user.getEmail());
	}
	
}
