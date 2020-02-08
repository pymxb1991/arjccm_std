/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.service;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.sys.dao.SysDictsDao;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典树Service
 * @author liang
 * @version 2018-10-16
 */
@Service
@Transactional(readOnly = true)
public class SysDictsService extends TreeService<SysDictsDao, SysDicts> {

	@Autowired
	private SysDictsDao sysDictsDao;
	
	
	public SysDicts get(String id) {
		return super.get(id);
	}
	
	public List<SysDicts> findList(SysDicts sysDicts) {
		if (StringUtils.isNotBlank(sysDicts.getParentIds())){
			sysDicts.setParentIds(","+sysDicts.getParentIds()+",");
		}
		return super.findList(sysDicts);
	}
	
	@Transactional(readOnly = false)
	public void save(SysDicts sysDicts) {
		super.save(sysDicts);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysDicts sysDicts) {
		super.delete(sysDicts);
	}

	public List<String> findTypeList(SysDicts sysDicts) {
		return sysDictsDao.findTypeList(sysDicts);
	}
	
	//根据类型名称查询警情状态信息
	public List<SysDicts> findAlarmInfoByTypeName(SysDicts sysDicts) {
		return sysDictsDao.findAlarmInfoByTypeName(sysDicts);
	}

	/**
	 * 按类型查找全部列表
	 * @param type 类型名称
	 * */
	public List<SysDicts> findAllListByType(String type){
		return sysDictsDao.findAllListByType(type);
	}	/**
	 * 按类型查找字典列表
	 * @param type 类型名称
	 * */
	public List<SysDicts> findValueByType(String type){
		return sysDictsDao.findValueByType(type);
	}
}