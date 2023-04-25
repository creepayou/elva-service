package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestConfig implements RequestInterceptor {

	@Autowired
	private HttpServletRequest request;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		try {
			Enumeration<String> e = request.getHeaderNames();
			while (e.hasMoreElements()) {
				String header = e.nextElement();
				if (header.startsWith("mt-")) {
					String value = request.getHeader(header);
					requestTemplate.header(header, value);
				}
			}
		} catch (IllegalStateException e) {
			// Do nothing
		}
	}
}
