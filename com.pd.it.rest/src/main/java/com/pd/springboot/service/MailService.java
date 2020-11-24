package com.pd.springboot.service;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.pd.businessobject.MailVO;

@Named
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(MailVO mailVO) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(mailVO.getMailSender());// 邮件发送人
			simpleMailMessage.setTo(mailVO.getMailTo());// 邮件接收人
			simpleMailMessage.setSubject(mailVO.getSubject());// 邮件主题
			simpleMailMessage.setText(mailVO.getMailContent());// 邮件内容
			javaMailSender.send(simpleMailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
