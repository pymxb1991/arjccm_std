/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 值班日志表Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkBeondutylogExport extends DataEntity<CcmWorkBeondutylogExport> {

	private static final long serialVersionUID = 1L;
	private Date datas;		// 时间
	private String type;		// 记录类型
	private String adds;		// 值班地点
	private String details;		// 内容
	private Date beginDatas;		// 开始 时间
	private Date endDatas;		// 结束 时间
	private Office office;	//部门

	public CcmWorkBeondutylogExport() {
		super();
	}

	public CcmWorkBeondutylogExport(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatas() {
		return datas;
	}

	@ExcelField(title="时间", align=2, sort=10)
	public String getDatasString(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(datas);
	}

	@ExcelField(title="人员", align=2, sort=20)
	public String getUpdateName() {
		return updateBy.getName();
	}

	public void setDatas(Date datas) {
		this.datas = datas;
	}
	
	@Length(min=0, max=2, message="记录类型长度必须介于 0 和 2 之间")
	@ExcelField(title="记录类型", align=2, sort=40,dictType="ccm_work_beondutylog_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="值班地点长度必须介于 0 和 100 之间")
	@ExcelField(title="值班地点", align=2, sort=50)
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public Date getBeginDatas() {
		return beginDatas;
	}

	public void setBeginDatas(Date beginDatas) {
		this.beginDatas = beginDatas;
	}
	
	public Date getEndDatas() {
		return endDatas;
	}

	public void setEndDatas(Date endDatas) {
		this.endDatas = endDatas;
	}

	@ExcelField(title="登记部门", align=2, sort=30)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
}