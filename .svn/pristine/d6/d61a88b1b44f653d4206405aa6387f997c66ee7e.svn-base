/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.question.entity.PbsChoiceItem;

/**
 * 选择题选项DAO接口
 * 
 * @author lc
 * @version 2018-06-09
 */
@MyBatisDao
public interface PbsChoiceItemDao extends CrudDao<PbsChoiceItem> {
	// 根据试卷的id 获取响应的所有选项的值
	public List<PbsChoiceItem> findListByPaper(PbsChoiceItem item);
	//根据题目信息获取选项信息
	public List<PbsChoiceItem> findListByParentId(@Param("list")List<String> list,@Param("flag")String flag);
}