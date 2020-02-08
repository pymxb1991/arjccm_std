/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.home.dao.PlmPortalDictDao;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDict;

/**
 * 门户字典Service
 * @author liuxue
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PlmPortalDictService extends CrudService<PlmPortalDictDao, PlmPortalDict> {

	public PlmPortalDict get(String id) {
		return super.get(id);
	}
	
	public List<PlmPortalDict> findList(PlmPortalDict plmPortalDict) {
		return super.findList(plmPortalDict);
	}
	
	public Page<PlmPortalDict> findPage(Page<PlmPortalDict> page, PlmPortalDict plmPortalDict) {
		return super.findPage(page, plmPortalDict);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmPortalDict plmPortalDict) {
		super.save(plmPortalDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmPortalDict plmPortalDict) {
		super.delete(plmPortalDict);
	}
	//根据内容链接查询   行数
	public  int line(String content) {
		PlmPortalDict plmPortalDict =new PlmPortalDict();
		plmPortalDict.setContent(content);
		List<PlmPortalDict> plmPortalDictList=findList(plmPortalDict);
		int line=5;
		if(StringUtils.isNotBlank(plmPortalDictList.get(0).getLine())) { 
			line= Integer.parseInt(plmPortalDictList.get(0).getLine());
			}
		
		return line;
	}
	
	
}