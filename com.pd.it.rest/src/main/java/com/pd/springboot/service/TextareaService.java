package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.TextareaFO;
import com.pd.businessobject.TextareaVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ITextareaDao;

@Named
public class TextareaService extends BaseService<TextareaFO, TextareaVO, ITextareaDao> {}
