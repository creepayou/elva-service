package com.rsmurniteguh.bpjs.bpjsservice.base.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@Log
public class BaseController {
	
	private final List<SimpleDateFormat> dateFormatList = Arrays.asList(
		new SimpleDateFormat("dd-MMM-yyyy HH:mm"),
		new SimpleDateFormat("dd-MMM-yyyy"),
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	);
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
	        public void setAsText(String value) {
                Date parsedDate = null;
                ParsePosition position = new ParsePosition(0);
                for(SimpleDateFormat sdf : dateFormatList) {
					parsedDate = sdf.parse(value, position);
					if(parsedDate != null && position.getIndex() == value.length()){
						break;
					}
					position.setIndex(0);
					parsedDate = null;
				}
                
                if(parsedDate == null) {
                	try {
						setValue(new Timestamp(Long.parseLong(value)));
					} catch(Exception e) {
						log.log(Level.SEVERE, e.getMessage(), e);
						setValue(null);
					}
                } else {
                	setValue(new Timestamp(parsedDate.getTime()));
                }
	        }
	    });
	
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
	        public void setAsText(String value) {
				if(StringUtils.hasText(value)) {
					setValue(value);
				} else {
					setValue(null);
				}
	        }
	    });
	}
}
