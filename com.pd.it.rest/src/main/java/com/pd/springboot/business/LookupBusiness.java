package com.pd.springboot.business;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.service.LookupService;

@Named
public class LookupBusiness {
    @Inject
    private LookupService lookupService;

    private static Map<String, String> city2ProvinceMap = null;

    public List<LookupVO> getCityList() throws BusinessException {
        LookupFO lookupFO = new LookupFO();
        lookupFO.setType("市");
        return lookupService.queryList(lookupFO);
    }

    public String getProvinceByCity(String city) throws BusinessException {
        if (city2ProvinceMap == null) {
            List<LookupVO> cityList = getCityList();
            city2ProvinceMap = cityList.stream().collect(Collectors.toMap(vo -> vo.getCode().replaceAll("市", ""),
                    vo -> vo.getParentCode().replaceAll("省", ""), (k1, k2) -> k1));
        }
        return city2ProvinceMap.get(city);
    }
}
