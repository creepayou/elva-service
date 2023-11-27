package com.rsmurniteguh.elva.elvaservice.util;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.util.ObjectUtils;

public class ObjectUtil extends ObjectUtils{

	private ObjectUtil(){
		throw new IllegalStateException("Utility class");
	}

	public static <T> T convertObjectToClass(Object obj, Class<T> valueType) throws IOException {
		if(isEmpty(obj)) return null;
		String jsonString = JsonUtil.toJsonString(obj);
		return JsonUtil.buildMapper().readValue(jsonString, valueType);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T convertObjectToClass(Object obj, TypeReference valueTypeRef) throws IOException {
		if(isEmpty(obj)) return null;
		String jsonString = JsonUtil.toJsonString(obj);
		return (T) JsonUtil.buildMapper().readValue(jsonString, valueTypeRef);
	}
}
