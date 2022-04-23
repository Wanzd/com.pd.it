package com.pd.springboot.business;

import com.pd.it.common.annotations.Log;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.springboot.service.TestService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

import static com.pd.it.common.util.StaticTool.error;
import static com.pd.it.common.util.StaticTool.success;

@Named
@Slf4j
public class TestBusiness {
    @Inject
    private TestService testService;

    @Log
    public ResultVO testTimeout(MapVO fo) {
        try{
            return success(testService.testTimeout());
        }catch(Exception e){
            return error(e.getLocalizedMessage(),e);
        }
    }
}
