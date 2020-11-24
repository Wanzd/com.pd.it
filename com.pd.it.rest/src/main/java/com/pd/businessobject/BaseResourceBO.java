package com.pd.businessobject;

import java.util.Date;

import lombok.Data;

@Data
public class BaseResourceBO {
	private String createdBy;
	private Date creationDate;
	private String lastUpdateBy;
	private Date lastUpdateDate;
}
