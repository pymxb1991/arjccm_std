/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.home.dao.PlmHomeDao;
import com.arjjs.ccm.modules.plm.home.entity.PlmHome;

/**
 * 个人门户Service
 * @author liuxue
 * @version 2018-06-29
 */
@Service
@Transactional
public class PlmHomeService extends CrudService<PlmHomeDao, PlmHome> {
	@Autowired
	PlmHomeDao plmhomedao;
	
	public PlmHome get(String id) {
		return super.get(id);
	}
	
	public List<PlmHome> findList(PlmHome plmHome) {
		return super.findList(plmHome);
	}
	
	public List<PlmHome> findList2( PlmHome plmHome) {
		return plmhomedao.findList2(plmHome);
	}
	
	public Page<PlmHome> findPage(Page<PlmHome> page, PlmHome plmHome) {
		return super.findPage(page, plmHome);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmHome plmHome) {
		super.save(plmHome);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmHome plmHome) {
		super.delete(plmHome);
	}
	/**
	 * 根据User删除
	 * @param plmHome
	 */
	@Transactional(readOnly = false)
	public void deleteUser(PlmHome plmHome) {
		plmhomedao.deleteUser(plmHome);
	}
	/**
	 * delete真删除
	 * @param plmHome
	 */
	@Transactional(readOnly = false)
	public void zdelete(PlmHome plmHome) {
		plmhomedao.zdelete(plmHome);
	}
	/**
	 * 恢复删除
	 * @param plmHome
	 */
	@Transactional(readOnly = false)
	public void undelete(PlmHome plmHome) {
		plmhomedao.undelete(plmHome);
	}
	 /**
	  * 删除 除Type之外
	  * @param plmHome
	  */
	@Transactional(readOnly = false)
	public void deleteType(PlmHome plmHome) {
		plmhomedao.deleteType(plmHome);
	}
	/**
	 * 恢复Type删除
	 * @param plmHome
	 */
	@Transactional(readOnly = false)
	public void undeleteType(PlmHome plmHome) {
		plmhomedao.undeleteType(plmHome);
	}
	
	public void deleteData(PlmHome plmHome) {
		dao.deleteData(plmHome);
	}
}