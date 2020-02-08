/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.service;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.tree.dao.CcmTreeDao;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.sys.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 树Service
 * @author liang
 * @version 2018-03-02
 */
@Service
@Transactional(readOnly = true)
public class CcmTreeService extends TreeService<CcmTreeDao, CcmTree> {

	@Autowired
	private CcmTreeDao ccmTreeDao;
	
	
	public CcmTree get(String id) {
		return super.get(id);
	}
	
	public List<CcmTree> findList(CcmTree ccmTree) {
		if (StringUtils.isNotBlank(ccmTree.getParentIds())){
			ccmTree.setParentIds(","+ccmTree.getParentIds()+",");
		}
		return super.findList(ccmTree);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmTree ccmTree) {
		super.save(ccmTree);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmTree ccmTree) {
		super.delete(ccmTree);
	}

	/***
	 * 地图标注树状图
	 */
	public List<CcmTree> findListTree(CcmTree ccmTree, String type , Area area) {
		// TODO Auto-generated method stub
		return ccmTreeDao.findListTree(ccmTree,type ,area);
	}
	
	
	/***
	 * 社区网格楼栋房屋
	 */
	public List<CcmTree> findListArea(CcmTree ccmTree, String type) {
		// TODO Auto-generated method stub
		return ccmTreeDao.findListArea(ccmTree,type);
	}

	//地图标注树状图app
	public List<CcmTree> findListTreeApp(CcmTree ccmTree) {
		// TODO Auto-generated method stub
		return ccmTreeDao.findListTreeApp(ccmTree);
	}

	//获取区域列表信息并生成树形
	public List<Area> findTreeAll(CcmTree ccmTree) {
		return ccmTreeDao.findTreeAll(ccmTree);
	}
	//电子围栏信息并生成树形
	public List<CcmTree> findListTreeElecFence(CcmTree ccmTree) {
		return ccmTreeDao.findListTreeElecFence(ccmTree);
	}
}