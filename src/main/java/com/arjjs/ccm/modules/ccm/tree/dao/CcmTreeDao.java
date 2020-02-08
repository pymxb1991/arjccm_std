/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.dao;

import com.arjjs.ccm.common.persistence.TreeDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.sys.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 树DAO接口
 * @author liang
 * @version 2018-03-02
 */
@MyBatisDao
public interface CcmTreeDao extends TreeDao<CcmTree> {
	/***
	 * 地图标注树状图
	 */
	List<CcmTree> findListTree(CcmTree ccmTree, @Param("type") String type, Area area);

	/***
	 * 社区网格楼栋房屋
	 */
	List<CcmTree> findListArea(CcmTree ccmTree, @Param("type") String type);

	//地图标注树状图app
	List<CcmTree> findListTreeApp(CcmTree ccmTree);

	//获取区域列表信息并生成树形
	List<Area> findTreeAll(CcmTree ccmTree);
	//电子围栏
	List<CcmTree> findListTreeElecFence(CcmTree ccmTree);
}