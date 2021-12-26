package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.eq;
import static com.pd.it.common.util.StaticTool.getBean;
import static com.pd.it.common.util.StaticTool.invoke;
import static com.pd.it.common.util.StaticTool.toStr;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aiRest")
public class AiRest {

    @PostMapping("/{domain}/{operate}")
    public Object route(@RequestBody Object fo, @PathVariable("domain") String domain,
            @PathVariable("operate") String operate) {
        Object domainBean = getBean(domain);
        if (eq(toStr(fo), "{}")) {
            return invoke(domainBean, operate);
        }
        return invoke(domainBean, operate, fo);
    }
}
