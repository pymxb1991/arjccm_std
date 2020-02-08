package com.arjjs.ccm.tool;
/**
 * 
 * @author lenovo
 *
 */

public class RetrueJson {
	private Boolean Success;//是否成功
	
	private String message;//返回的信息

	private Object object1;//返回的object对象1
	
	private Object object2;//返回的object队像2

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject1() {
		return object1;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}

	public Boolean getSuccess() {
		return Success;
	}

	public void IsSuccess(Boolean success) {
		Success = success;
	}
	
	

	
	
}
