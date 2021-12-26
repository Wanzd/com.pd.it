package com.pd.springboot.service;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import com.pd.it.common.annotations.Timeout;

@Named
public class TestService {

    @Timeout(300)
    public int testTimeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
