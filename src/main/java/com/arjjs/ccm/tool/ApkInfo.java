package com.arjjs.ccm.tool;

public class ApkInfo {
	
    /**
     * icon
     */
    private String icon;
    
    /**
     * versionName
     */
    private String versionName;
    
    /**
     * versionCode
     */
    private Long versionCode;
    
    /**
     * sess
     */
    private boolean sess;
    
    /**
     * sess
     */
    private String opMsg;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Long getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Long versionCode) {
		this.versionCode = versionCode;
	}

	public boolean isSess() {
		return sess;
	}

	public void setSess(boolean sess) {
		this.sess = sess;
	}

	public String getOpMsg() {
		return opMsg;
	}

	public void setOpMsg(String opMsg) {
		this.opMsg = opMsg;
	}
}
