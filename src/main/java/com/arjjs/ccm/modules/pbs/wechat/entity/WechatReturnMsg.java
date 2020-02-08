package com.arjjs.ccm.modules.pbs.wechat.entity;

/**
 *  微信返回信息
 * @author lc
 *
 */
public class WechatReturnMsg {

	private  String errcode;
	
	private String errmsg;
	
	private String msg_id;
	
	private String access_token;
	
	private String  expires_in;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "errcode: ["+getErrcode() +"],errmsg:["+getErrmsg()+"],msg_id:["+getMsg_id()+"]";
	}
}
