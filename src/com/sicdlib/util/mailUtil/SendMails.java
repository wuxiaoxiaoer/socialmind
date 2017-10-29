package com.sicdlib.util.mailUtil;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*; 
import javax.mail.internet.*;
import javax.activation.*;

public class SendMails {
	
	public static String FROM = "boyprettysun@sina.com";
	public static String FROM_PWD = "wlw123";
	public static String SMTP_HOST = "smtp.sina.com";
	public static String MAIL_PROTOCAL = "smtp";
	public static int SMTP_PORT = 25;
	//邮件主题
	public static String SUBJECT = "微博舆情分析系统 - 账号激活验证码";
	
	//发送邮件
	public static void sendMail(String dest_email, String content) throws MessagingException{
		Session session = sessionInfo();
		//Mime发送邮件
		Message message = new MimeMessage(session);
		Address address = new InternetAddress(FROM);
		message.setFrom(address);
		InternetAddress[] Arr_Internet_Address = {new InternetAddress(dest_email)};
		message.setRecipients(Message.RecipientType.TO, Arr_Internet_Address);
		message.setSubject(SUBJECT);
		message.setSentDate(new Date());
		message.setContent(content, "text/html;charset=utf-8");
		//发送邮件
		Transport ts = session.getTransport("smtp");
		ts.connect("smtp.sina.com", "boyprettysun", "wlw123");
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
//		Transport.send(message);
	}
	//配置SMTP信息
	public static Session sessionInfo(){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", SMTP_HOST);
		properties.put("mail.store.protocal", MAIL_PROTOCAL);
		properties.put("mail.smtp.port", SMTP_PORT);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.debug", "true");
		Authenticator mail_auth = new Authenticator() {
			//口令认证
			@SuppressWarnings("unused")
			protected PasswordAuthentication get_pwd_auth() {
				return new PasswordAuthentication(FROM, FROM_PWD);
			}
		};
		
		Session session = Session.getDefaultInstance(properties,mail_auth);
		return session;
	}

}
