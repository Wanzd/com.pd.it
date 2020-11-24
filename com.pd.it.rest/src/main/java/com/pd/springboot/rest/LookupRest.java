package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.springboot.service.LookupItemService;
import com.pd.springboot.service.LookupTypeService;
import com.pd.standard.web.IDimensionStandardRest;

@RestController
@RequestMapping("lookupRest")
public class LookupRest implements IDimensionStandardRest {
    @Autowired
    private LookupTypeService type;
    @Autowired
    private LookupItemService item;
}
