package com.pd.businessobject;

import lombok.Data;

@Data
public class FreemarkerFO extends FreemarkerVO {
	private Object source;
	private String template;
}
