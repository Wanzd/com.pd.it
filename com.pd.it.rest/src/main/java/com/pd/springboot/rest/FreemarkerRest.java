package com.pd.springboot.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.business.FreemarkerService;

@RestController
@RequestMapping("freemarkerRest")
public class FreemarkerRest {
	@Inject
	protected FreemarkerService freemarkerService;

	@RequestMapping("/generate")
	public Object generate(@RequestBody(required = false) Object fo) throws BusinessException {
		return freemarkerService.generate(fo);
	}
}
