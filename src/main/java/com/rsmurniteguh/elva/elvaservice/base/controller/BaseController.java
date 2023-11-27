package com.rsmurniteguh.elva.elvaservice.base.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
public class BaseController {

	private final List<SimpleDateFormat> dateFormatList = Arrays.asList(
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
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
						log.error(e.getMessage(), e);
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
