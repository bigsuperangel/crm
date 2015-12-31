package com.crm.common.pojo;

import java.io.Serializable;

public class JsonMsg implements Serializable {

	private static final long serialVersionUID = -5178025051086403519L;

	private boolean success;

	private String msg;

	public JsonMsg(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
