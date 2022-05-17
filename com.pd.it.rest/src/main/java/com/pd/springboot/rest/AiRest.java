package com.pd.springboot.rest;

import static com.pd.it.common.util.StaticTool.eq;
import static com.pd.it.common.util.StaticTool.getBean;
import static com.pd.it.common.util.StaticTool.invoke;
import static com.pd.it.common.util.StaticTool.toStr;
import static com.pd.it.common.util.StringTool.decap;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.businessobject.PageVO;

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

	@ResponseBody
	@RequestMapping(value = "/query{domain}PagedList/{pageSize}/{curPage}", method = { RequestMethod.POST })
	public Object queryDomainPagedList(@RequestBody Object fo, @PathVariable("domain") String domain,
			@PathParam(value = "") PageVO page) {
		Object domainBean = getBean(decap(domain) + "Rest");
		return invoke(domainBean, "queryPagedList", fo, page);
	}
}
