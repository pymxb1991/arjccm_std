/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.planinfo.dao.BphPlanInfoDao;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;

/**
 * 数字化预案Service
 * @author zhanghao
 * @version 2018-11-14
 */
@Service
@Transactional(readOnly = true)
public class BphPlanInfoService extends CrudService<BphPlanInfoDao, BphPlanInfo> {
	public BphPlanInfo get(String id) {
		return super.get(id);
	}
	
	public List<BphPlanInfo> findList(BphPlanInfo bphPlanInfo) {
		return super.findList(bphPlanInfo);
	}
	
	public Page<BphPlanInfo> findPage(Page<BphPlanInfo> page, BphPlanInfo bphPlanInfo) {
		return super.findPage(page, bphPlanInfo);
	}
	
	public List<BphPlanInfo> findByIsImportantAndTypeCode(String isImportant,String typeCode){
		BphPlanInfo bphPlanInfo = new BphPlanInfo();
		bphPlanInfo.setIsImportant(isImportant);
		bphPlanInfo.setTypeCode(typeCode);
		return dao.findByIsImportantAndTypeCode(bphPlanInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BphPlanInfo bphPlanInfo) {
		super.save(bphPlanInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BphPlanInfo bphPlanInfo) {
		super.delete(bphPlanInfo);
	}
	
	@Transactional(readOnly = false)
	public BphPlanInfo checkPlanName(BphPlanInfo bphPlanInfo) {
		return this.dao.checkPlanName(bphPlanInfo);
	}
	
	//门户预案调用的次数统计
	public List<BphPlanInfo> findPlanInvokeCount(){
		return this.dao.findPlanInvokeCount();
	}
}