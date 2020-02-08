/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 公众信息上报Entity
 * @author fuxinshuang
 * @version 2018-03-17
 */
public class CcmServiceWechat extends DataEntity<CcmServiceWechat> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 上报分类
	private String content;		// 事件描述
	private String file1;		// 附件1
	private String file2;		// 附件2
	private String file3;		// 附件3
	private String name;		// 姓名
	private String phone;		// 电话
	private String workunit;		// 工作单位
	private String ip;		// IP
	private String reName;		// 回复人
	private String reContent;		// 回复内容
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private String email;		// 邮箱
	private boolean isSelf;		// 是否只查询自己的上报信息
	
	public CcmServiceWechat() {
		super();
		this.delFlag = DEL_FLAG_AUDIT;
	}

	public CcmServiceWechat(String id){
		super(id);
	}

	@Length(min=0, max=2, message="上报分类长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="事件描述长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="附件1长度必须介于 0 和 256 之间")
	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
	
	@Length(min=0, max=256, message="附件2长度必须介于 0 和 256 之间")
	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}
	
	@Length(min=0, max=256, message="附件3长度必须介于 0 和 256 之间")
	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}
	
	@Length(min=0, max=100, message="姓名长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="电话长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="工作单位长度必须介于 0 和 100 之间")
	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}
	
	@Length(min=0, max=100, message="IP长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=64, message="回复人长度必须介于 0 和 64 之间")
	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}
	
	@Length(min=0, max=100, message="回复内容长度必须介于 0 和 100 之间")
	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}
	
}