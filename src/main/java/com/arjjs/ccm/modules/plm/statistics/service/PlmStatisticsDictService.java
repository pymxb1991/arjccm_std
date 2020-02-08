/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.statistics.dao.PlmStatisticsDictDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;

/**
 * 统计首页字典Service
 * @author liuxue
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmStatisticsDictService extends CrudService<PlmStatisticsDictDao, PlmStatisticsDict> {

	public PlmStatisticsDict get(String id) {
		return super.get(id);
	}
	
	public List<PlmStatisticsDict> findList(PlmStatisticsDict plmStatisticsDict) {
		return super.findList(plmStatisticsDict);
	}
	
	public Page<PlmStatisticsDict> findPage(Page<PlmStatisticsDict> page, PlmStatisticsDict plmStatisticsDict) {
		return super.findPage(page, plmStatisticsDict);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmStatisticsDict plmStatisticsDict) {
		super.save(plmStatisticsDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmStatisticsDict plmStatisticsDict) {
		super.delete(plmStatisticsDict);
	}
	
	//根据内容链接查询   类型和类型名
			public  PlmStatisticsDict typeAndLine(String content) {
				PlmStatisticsDict plmStatisticsDict =new PlmStatisticsDict();
				plmStatisticsDict.setContent(content);
				List<PlmStatisticsDict> plmStatisticsDictList=findList(plmStatisticsDict);
				
				if(plmStatisticsDictList!=null&&plmStatisticsDictList.size()>0) {
				if(StringUtils.isNotBlank(plmStatisticsDictList.get(0).getType())) { 
					plmStatisticsDict.setType( plmStatisticsDictList.get(0).getType());
					plmStatisticsDict.setTypeName( plmStatisticsDictList.get(0).getTypeName());
					plmStatisticsDict.setLine( plmStatisticsDictList.get(0).getLine());
					}
				}
				return plmStatisticsDict;
			}
	
}