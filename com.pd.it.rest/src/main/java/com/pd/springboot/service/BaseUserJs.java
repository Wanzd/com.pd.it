package com.pd.springboot.service;

import com.pd.it.common.annotations.Timeout;

import javax.inject.Named;
import java.util.concurrent.TimeUnit;

@Named
public class BaseUserJs {

    @Timeout(300)
    public int testTimeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
