/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepaction.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.stepaction.dao.BphStepActionDao;
import com.arjjs.ccm.modules.flat.stepaction.entity.BphStepAction;

/**
 * 过程动作关联表Service
 * @author liu
 * @version 2018-11-17
 */
@Service
@Transactional(readOnly = true)
public class BphStepActionService extends CrudService<BphStepActionDao, BphStepAction> {

	public BphStepAction get(String id) {
		return super.get(id);
	}
	
	public List<BphActionInfo> getActionByStepId(BphStepAction bphStepAction) {
		return dao.getActionByStepId(bphStepAction);
	}
	
	public List<BphStepAction> findList(BphStepAction bphStepAction) {
		return super.findList(bphStepAction);
	}
	
	public Page<BphStepAction> findPage(Page<BphStepAction> page, BphStepAction bphStepAction) {
		return super.findPage(page, bphStepAction);
	}
	
	@Transactional(readOnly = false)
	public void save(BphStepAction bphStepAction) {
		if(bphStepAction.getIsNewRecord()) {
			BphStepAction bphStepActionSort = new BphStepAction();
			bphStepActionSort = findLastSort(bphStepAction);
			if(bphStepActionSort != null) {
				bphStepAction.setSort(bphStepActionSort.getSort()+1);
			}else {
				bphStepAction.setSort(1);
			}
		}
		super.save(bphStepAction);
	}
	
	@Transactional(readOnly = false)
	public void delete(BphStepAction bphStepAction) {
		super.delete(bphStepAction);
	}
	
	@Transactional(readOnly = false)
	public void deleteByStepIdAndActionId(BphStepAction bphStepAction) {
		dao.deleteByStepIdAndActionId(bphStepAction);
	}
	
	public BphStepAction findLastSort(BphStepAction bphStepAction) {
		return dao.findLastSort(bphStepAction);
	}
}