/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsChoiceItem;
import com.arjjs.ccm.modules.pbs.question.dao.PbsChoiceItemDao;

/**
 * 选择题选项Service
 * @author lc
 * @version 2018-06-09
 */
@Service
@Transactional(readOnly = true)
public class PbsChoiceItemService extends CrudService<PbsChoiceItemDao, PbsChoiceItem> {

	public PbsChoiceItem get(String id) {
		return super.get(id);
	}
	
	public List<PbsChoiceItem> findList(PbsChoiceItem pbsChoiceItem) {
		return super.findList(pbsChoiceItem);
	}
	
	public Page<PbsChoiceItem> findPage(Page<PbsChoiceItem> page, PbsChoiceItem pbsChoiceItem) {
		return super.findPage(page, pbsChoiceItem);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsChoiceItem pbsChoiceItem) {
		// 选项和数值同序
		pbsChoiceItem.setISort(pbsChoiceItem.getSKey());
		super.save(pbsChoiceItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsChoiceItem pbsChoiceItem) {
		super.delete(pbsChoiceItem);
	}
	
	// 根据试卷获取所有的选项内容信息
	public List<PbsChoiceItem> findListByPaper(PbsChoiceItem item){
		return dao.findListByPaper(item);
	}
	
	public List<PbsChoiceItem> findListByParentId(List<String> list,String flag){
		return dao.findListByParentId(list,flag);
	}
}