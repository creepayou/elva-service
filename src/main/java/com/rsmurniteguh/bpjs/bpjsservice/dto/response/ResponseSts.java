package com.rsmurniteguh.bpjs.bpjsservice.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseSts<T> {
	private Boolean success;
	private T data;
	private String message;
	
	public static <T> ResponseSts<T> Fail(String message) {
		return Fail(message, null);
	}

	public static <T> ResponseSts<T> Fail(String message, ResponseSts<T> response) {
		if(response == null) response = new ResponseSts<>();
		response.setSuccess(false).setMessage(message);
		return response;
	}
	
	private static <T> ResponseSts<T> Success(ResponseSts<T> response) {
		if(response == null) response = new ResponseSts<>();
		response.setSuccess(true);
		return response;
	}
	
	public static <T> ResponseSts<T> Success(T data) {
		return Success(data, null);
	}

	public static <T> ResponseSts<T> Success(T data, ResponseSts<T> response) {
		return Success(response).setData(data);
	}
}
