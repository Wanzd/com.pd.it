package com.pd.springboot.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

public class VirtualFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String url = request.getRequestURI();
		if (url.matches("/.+[.](html|js|png|css|gif|jpg)")) {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("static/" + url.substring("/".length()));
			try {
				IOUtils.copy(in, arg1.getOutputStream());
			} catch (Exception e) {
				ServletOutputStream os = arg1.getOutputStream();
				os.println("404");
				os.flush();
			} finally {
				IOUtils.closeQuietly(in);
			}
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
