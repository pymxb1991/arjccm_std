/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;

import com.arjjs.ccm.modules.pbs.sys.dao.PbsGeneralDao;

/**
 * 党员部门扩展信息Service
 * 
 * @author lc
 * @version 2018-04-10
 */
@Service
@Transactional(readOnly = true)
public class PbsGeneralService {

	@Autowired
	private PbsGeneralDao pbsGeneralDao;

	// 检测 当前的 数据是否存在
	public boolean checkExist(GeneralMethod General) {
		return pbsGeneralDao.checkExist(General) == 0 ? false : true;
	}
	
	// 检测 当前的 数据是否存在
		public String checkExistForString(GeneralMethod General) {
			return pbsGeneralDao.checkExist(General) == 0 ? "false" : "true";
		}
		

}