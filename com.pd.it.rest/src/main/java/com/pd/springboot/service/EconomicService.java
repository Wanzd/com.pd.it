package com.pd.springboot.service;

import javax.inject.Named;

@Named
public class EconomicService {

    public Object queryFutureList() {
        return "futureList";
    }
}
