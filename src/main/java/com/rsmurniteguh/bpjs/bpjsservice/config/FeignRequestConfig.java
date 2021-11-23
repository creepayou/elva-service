package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignRequestConfig {

	@Autowired
	private HttpServletRequest request;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return (RequestTemplate requestTemplate) -> {
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
		};
	}
}
