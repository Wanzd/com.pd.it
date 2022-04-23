package com.pd.it.common.businessobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResultVO<T> {
    public static final String SUCCESS="200";
    public static final String ERROR="500";
	public static final String TIMEOUT="408";
    
	private String code;
	private String msg;
	private T data;
	private long useTime;
	private long startTime;
	private long endTime;
}
