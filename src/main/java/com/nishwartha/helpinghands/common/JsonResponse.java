package com.nishwartha.helpinghands.common;

public class JsonResponse<T> {
	public boolean success;
	public String message;
	public T data;

	public JsonResponse() {
	}

	public JsonResponse(boolean success, String message, T obj) {
		this.success = success;
		this.message = message;
		this.data = obj;
	}

	public JsonResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
}
