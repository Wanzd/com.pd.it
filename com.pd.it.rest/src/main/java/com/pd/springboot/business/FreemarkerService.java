package com.pd.springboot.business;

import static com.pd.it.common.util.StaticTool.toObj;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import javax.inject.Named;

import com.pd.businessobject.FreemarkerFO;
import com.pd.it.common.factory.ResultVOFactory;

import freemarker.template.Template;

@Named
public class FreemarkerService {

	public Object generate(Object fo) {
		FreemarkerFO freemarkerFO = toObj(fo, FreemarkerFO.class);
		try {
			Template template = new Template("test", new StringReader(freemarkerFO.getTemplate()));

			Writer out = new StringWriter();
			template.process(toObj(freemarkerFO.getSource(),HashMap.class), out);
			return out.toString();
		} catch (Exception e) {
			return ResultVOFactory.error(e.getMessage());
		}
	}

}
