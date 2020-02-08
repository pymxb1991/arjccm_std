/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 值班表Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkBeondutyExport extends DataEntity<CcmWorkBeondutyExport> {

	private static final long serialVersionUID = 1L;
	private Date months;		// 年月
	private String datas;		// 时间段
	private User principal;		// 值班负责人
	private String principalMans;		// 值班队伍
	private String adds;		// 值班地点
	private String details;		// 工作重点
	private Date beginMonths;		// 开始 年月
	private Date endMonths;		// 结束 年月
	private Office office;	// 归属部门

	public CcmWorkBeondutyExport() {
		super();
	}

	public CcmWorkBeondutyExport(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMonths() {
		return months;
	}

	@ExcelField(title="年月", align=2, sort=10)
	public String getMonthsString(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(months);
	}

	public void setMonths(Date months) {
		this.months = months;
	}
	
	@Length(min=0, max=100, message="时间段长度必须介于 0 和 100 之间")
	@ExcelField(title="时间段", align=2, sort=20)
	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	public User getPrincipal() {
		return principal;
	}

	@ExcelField(title="值班负责人", align=2, sort=40)
	public String getPrincipalName() { return principal.getName(); }

	public void setPrincipal(User principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=1000, message="值班队伍长度必须介于 0 和 1000 之间")
	@ExcelField(title="值班队伍", align=2, sort=50)
	public String getPrincipalMans() {
		return principalMans;
	}

	public void setPrincipalMans(String principalMans) {
		this.principalMans = principalMans;
	}
	
	@Length(min=0, max=100, message="值班地点长度必须介于 0 和 100 之间")
	@ExcelField(title="值班地点", align=2, sort=60)
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
	@Length(min=0, max=1000, message="工作重点长度必须介于 0 和 1000 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public Date getBeginMonths() {
		return beginMonths;
	}

	public void setBeginMonths(Date beginMonths) {
		this.beginMonths = beginMonths;
	}
	
	public Date getEndMonths() {
		return endMonths;
	}

	public void setEndMonths(Date endMonths) {
		this.endMonths = endMonths;
	}

	@ExcelField(title="归属部门", align=2, sort=30)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
		
}