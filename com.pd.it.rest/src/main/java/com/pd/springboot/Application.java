package com.pd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.pd.it.common.util.SpringUtil;
import com.pd.springboot.filter.VirtualFilter;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		SpringUtil.setContext(context);
	}

	@Bean // 必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
	public RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
		httpRequestFactory.setConnectTimeout(30 * 3000);
		httpRequestFactory.setReadTimeout(30 * 3000);
		return new RestTemplate(httpRequestFactory);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean(new VirtualFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@Bean
	public ISqlInjector sqlInjector() {
		return new DefaultSqlInjector();
	}

	@Bean
	public OracleKeyGenerator oracleKeyGenerator() {
		return new OracleKeyGenerator();
	}
}
