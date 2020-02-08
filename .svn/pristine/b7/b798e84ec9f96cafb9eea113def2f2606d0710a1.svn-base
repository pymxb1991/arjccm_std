/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

/**
 * 工作日志Entity
 * @author arj
 * @version 2018-03-08
 */
public class PlmWorkEmail extends DataEntity<PlmWorkEmail> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	private String title;		// 标题
	private String content;		// 内容
	private String files;		// 附件
	private String status;		// 状态     0已发送  1草稿箱
	private String createName;  // 创建人姓名
	private String readNum;		// 已读
	private String unReadNum;	// 未读
	private boolean isSelf;		// 是否只查询自己的通知
	private String readFlag;	// 本人阅读状态
	private String readStatus;	// 收件分类    1收件箱    2已删除   3彻底删除
	private String isStar;		// 是否星标邮件     1是0否
	private String readFlagLable;	//用于app接口列表显示;
	private Boolean view;		//判断页面跳转    true星标  false其他
	private Office office;	// 归属部门
	private String typeLable;	//用于app接口列表显示;
	private Boolean isC;	//是否抄送
	private Boolean isM;	//是否密送
	
	@JsonIgnore
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
	
	public PlmWorkEmail() {
		super();
	}

	public PlmWorkEmail(String id){
		super(id);
	}

	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=100, message="标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(String unReadNum) {
		this.unReadNum = unReadNum;
	}
	/*发送人*/
	private List<PlmWorkEmailRead> plmWorkEmailSReadList = Lists.newArrayList();
	
	public List<PlmWorkEmailRead> getPlmWorkEmailSReadList() {
		return plmWorkEmailSReadList;
	}

	public void setPlmWorkEmailSReadList(List<PlmWorkEmailRead> plmWorkEmailSReadList) {
		this.plmWorkEmailSReadList = plmWorkEmailSReadList;
	}
	
	/**
	 * 获取通知发送记录用户ID
	 * @return
	 */
	public String getPlmWorkEmailSReadIds() {
		return Collections3.extractToString(plmWorkEmailSReadList, "user.id", ",") ;
	}
	
	/**
	 * 设置通知发送记录用户ID
	 * @return
	 */
	public void setPlmWorkEmailSReadIds(String oaNotifyRecord) {
		this.plmWorkEmailSReadList = Lists.newArrayList();
		for (String id : StringUtils.split(oaNotifyRecord, ",")){
			PlmWorkEmailRead entity = new PlmWorkEmailRead();
			entity.setId(IdGen.uuid());
			entity.setPlmWorkEmail(this);
			entity.setUser(new User(id));
			entity.setType("1");
			entity.setReadFlag("0");
			this.plmWorkEmailSReadList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * @return
	 */
	public String getPlmWorkEmailSReadNames() {
		return Collections3.extractToString(plmWorkEmailSReadList, "user.name", ",") ;
	}
	
	/*抄送人*/
	private List<PlmWorkEmailRead> plmWorkEmailCReadList = Lists.newArrayList();
	
	public List<PlmWorkEmailRead> getPlmWorkEmailCReadList() {
		return plmWorkEmailCReadList;
	}

	public void setPlmWorkEmailCReadList(List<PlmWorkEmailRead> plmWorkEmailCReadList) {
		this.plmWorkEmailCReadList = plmWorkEmailCReadList;
	}
	
	/**
	 * 获取通知发送记录用户ID
	 * @return
	 */
	public String getPlmWorkEmailCReadIds() {
		return Collections3.extractToString(plmWorkEmailCReadList, "user.id", ",") ;
	}
	
	/**
	 * 设置通知发送记录用户ID
	 * @return
	 */
	public void setPlmWorkEmailCReadIds(String oaNotifyRecord) {
		this.plmWorkEmailCReadList = Lists.newArrayList();
		for (String id : StringUtils.split(oaNotifyRecord, ",")){
			PlmWorkEmailRead entity = new PlmWorkEmailRead();
			entity.setId(IdGen.uuid());
			entity.setPlmWorkEmail(this);
			entity.setUser(new User(id));
			entity.setType("2");
			entity.setReadFlag("0");
			this.plmWorkEmailCReadList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * @return
	 */
	public String getPlmWorkEmailCReadNames() {
		return Collections3.extractToString(plmWorkEmailCReadList, "user.name", ",") ;
	}	

	/*密送人*/
	private List<PlmWorkEmailRead> plmWorkEmailMReadList = Lists.newArrayList();
	
	public List<PlmWorkEmailRead> getPlmWorkEmailMReadList() {
		return plmWorkEmailMReadList;
	}

	public void setPlmWorkEmailMReadList(List<PlmWorkEmailRead> plmWorkEmailMReadList) {
		this.plmWorkEmailMReadList = plmWorkEmailMReadList;
	}
	
	/**
	 * 获取通知发送记录用户ID
	 * @return
	 */
	public String getPlmWorkEmailMReadIds() {
		return Collections3.extractToString(plmWorkEmailMReadList, "user.id", ",") ;
	}
	
	/**
	 * 设置通知发送记录用户ID
	 * @return
	 */
	public void setPlmWorkEmailMReadIds(String oaNotifyRecord) {
		this.plmWorkEmailMReadList = Lists.newArrayList();
		for (String id : StringUtils.split(oaNotifyRecord, ",")){
			PlmWorkEmailRead entity = new PlmWorkEmailRead();
			entity.setId(IdGen.uuid());
			entity.setPlmWorkEmail(this);
			entity.setUser(new User(id));
			entity.setType("3");
			entity.setReadFlag("0");
			this.plmWorkEmailMReadList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * @return
	 */
	public String getPlmWorkEmailMReadNames() {
		return Collections3.extractToString(plmWorkEmailMReadList, "user.name", ",") ;
	}	
	
	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getTypeLable() {
		return typeLable;
	}
	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}
	public String getReadFlagLable() {
		return readFlagLable;
	}
	public void setReadFlagLable(String readFlagLable) {
		this.readFlagLable = readFlagLable;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getIsStar() {
		return isStar;
	}
	public void setIsStar(String isStar) {
		this.isStar = isStar;
	}
	public Boolean getView() {
		return view;
	}
	public void setView(Boolean view) {
		this.view = view;
	}
	public Boolean getIsC() {
		return isC;
	}
	public void setIsC(Boolean isC) {
		this.isC = isC;
	}
	public Boolean getIsM() {
		return isM;
	}
	public void setIsM(Boolean isM) {
		this.isM = isM;
	}
}