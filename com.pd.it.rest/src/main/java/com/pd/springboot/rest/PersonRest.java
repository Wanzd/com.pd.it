package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.PersonFO;
import com.pd.businessobject.PersonVO;
import com.pd.springboot.dao.IPersonDao;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("personRest")
public class PersonRest extends BaseRest<PersonFO, PersonVO, IPersonDao> {

}
