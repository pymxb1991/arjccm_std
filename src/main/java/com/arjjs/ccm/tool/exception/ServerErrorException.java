package com.arjjs.ccm.tool.exception;

/**
 * 自定义异常
 * @author yuedongyang  
 * @date 2018年8月9日 下午3:36:49  
 */
public class ServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 271404730114753358L;

	public ServerErrorException() {
		super();
	}

	public ServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerErrorException(String message) {
		super(message);
	}

	public ServerErrorException(Throwable cause) {
		super(cause);
	}
	
	

}
