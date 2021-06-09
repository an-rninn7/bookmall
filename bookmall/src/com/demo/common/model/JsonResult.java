package com.demo.common.model;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {
	private String errorCode;
	private String message;
	private Map<String, Object> data = new HashMap<String, Object>();

	public JsonResult() {
	}

	public JsonResult(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public void addData(String key, Object value) {
		data.put(key, value);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
