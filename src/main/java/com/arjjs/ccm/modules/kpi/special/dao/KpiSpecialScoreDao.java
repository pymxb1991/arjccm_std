/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.special.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.kpi.special.entity.KpiSpecialScore;

/**
 * 考核细则DAO接口
 * @author yiqingxuan
 * @version 2019-07-12
 */
@MyBatisDao
public interface KpiSpecialScoreDao extends CrudDao<KpiSpecialScore> {
	
		//获取网格得分情况排行
		public List<KpiSpecialScore> getForWG(KpiSpecialScore kpiSpecialScore);
		
		//获取社区得分情况排行
		public List<KpiSpecialScore> getForSQ(KpiSpecialScore kpiSpecialScore);
		
		//获取区县得分情况排行
		public List<KpiSpecialScore> getForQX(KpiSpecialScore kpiSpecialScore);
}