package com.rsmurniteguh.bpjs.bpjsservice.base.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.apachecommons.CommonsLog;

@Data
@CommonsLog
@Accessors(chain = true)
public class ResponseSts<T> {
	private boolean success;
	private T data;
	private String message;
	private ErrorDto error;

	public static <T> ResponseSts<T> onFail(String message) {
		return onFail(message, null);
	}

	public static <T> ResponseSts<T> onFail(String message, ResponseSts<T> response) {
		if (response == null)
			response = new ResponseSts<>();
		response.setSuccess(false).setMessage(message);
		return response;
	}

	private static <T> ResponseSts<T> onSuccess(ResponseSts<T> response) {
		if (response == null)
			response = new ResponseSts<>();
		response.setSuccess(true);
		return response;
	}

	public static <T> ResponseSts<T> onSuccess(T data) {
		return onSuccess(data, null);
	}

	public static <T> ResponseSts<T> onSuccess(T data, ResponseSts<T> response) {
		return onSuccess(response).setData(data);
	}

	public static ResponseSts<Object> onError(HttpServletRequest request, String message, String stackTrace) {
		String path = request.getRequestURI();
		String entityCode = request.getHeader(Constant.MT_ENTITY_CODE);
		ErrorDto errorDto = new ErrorDto(Timestamp.from(Instant.now()), entityCode, message,
				path, parameterString(request.getParameterMap()), stackTrace);
		log.error(errorDto.toString());
		return onError(errorDto);
	}

	public static ResponseSts<Object> onError(ErrorDto error) {
		return new ResponseSts<>().setSuccess(false).setError(error);
	}

	private static String parameterString(Map<String, String[]> parameterMap) {
		StringBuilder stringBuilder = new StringBuilder();
		if (!parameterMap.isEmpty()) {
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				stringBuilder.append(entry.getKey() + ": " + entry.getValue()[0] + ",");
			}
		}

		String parameterString = stringBuilder.toString();
		if (parameterString.lastIndexOf(',') != -1) {
			return "(" + parameterString.substring(0, parameterString.lastIndexOf(',')) + ")";
		}
		return stringBuilder.toString();
	}
}
