package com.pd.businessobject;

import lombok.Data;

@Data
public class MailVO {
	private String mailSender;
	private String[] mailTo;
	private String[] mailCc;
	private String subject;
	private String mailContent;
}
