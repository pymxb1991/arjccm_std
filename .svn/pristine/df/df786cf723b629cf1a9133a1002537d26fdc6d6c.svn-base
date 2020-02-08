package com.arjjs.ccm.modules.ccm.ccmsys.entity;

import java.util.Date;
import java.util.Map;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.common.utils.StringUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CcmLog extends DataEntity<CcmLog> {

	private static final long serialVersionUID = 1L;
    private String type;
    private String title;
    private String remoteAddr;
    private String requestUri;
    private String method;
    private String params;
    private String userAgent;
    private String exception;
    private Date beginDate;
    private Date endDate;
    public static final String TYPE_ACCESS = "1";
    public static final String TYPE_EXCEPTION = "2";
    private Date createDate;
    
    public CcmLog() {
		// TODO Auto-generated constructor stub
	}

    public CcmLog(String id) {
        super(id);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ExcelField(title="操作菜单", align=1)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @ExcelField(title="操作者IP", align=2, sort=2)
    public String getRemoteAddr() {
        return this.remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    
    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @ExcelField(title="URI", align=2, sort=3)
    public String getRequestUri() {
        return this.requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    @ExcelField(title="提交方式", align=2,sort=4)
    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    
    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setParams(Map paramMap) {
        if (paramMap != null) {
            StringBuilder params = new StringBuilder();
            Iterator var4 = paramMap.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, String[]> param = (Entry)var4.next();
                params.append(("".equals(params.toString()) ? "" : "&") + (String)param.getKey() + "=");
                String paramValue = param.getValue() != null && ((String[])param.getValue()).length > 0 ? ((String[])param.getValue())[0] : "";
                params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((CharSequence)param.getKey(), "password") ? "" : paramValue, 100));
            }

            this.params = params.toString();
        }
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    @ExcelField(title="创造时间", align=2,sort=5)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
}
