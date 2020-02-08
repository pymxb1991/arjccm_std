/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.shake.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.modules.ccm.shake.entity.CcmShakePovertyPeople;

import java.util.List;

/**
 * 脱贫攻坚实体类DAO接口
 * @author chenboyuan
 * @version 2019-06-21
 */
@MyBatisDao
public interface CcmShakePovertyPeopleDao extends CrudDao<CcmShakePovertyPeople> {
	
	public List<CcmShakePovertyPeople> findGroupByTown(String type);
	
	public List<CcmShakePovertyPeople> findByBirNum();
	
	public List<CcmShakePovertyPeople> findByIncome();
	
	public List<CcmShakePovertyPeople> findCount();
	
	public List<CcmShakePovertyPeople> skilllist();
	
	public List<CcmShakePovertyPeople> Healthlist();
	
	public List<CcmShakePovertyPeople> selectAll();
	public List<CcmShakePovertyPeople> selectAreaid();
	public List<CcmShakePovertyPeople> selectAreaCountId();
	public List<CcmShakePovertyPeople> selectJDLK();
	public List<CcmShakePovertyPeople> selectTP();
	public List<CcmShakePovertyPeople> selectWTP();
	public List<CcmShakePovertyPeople>  selectTMD();

    /*
     * 在扶贫攻坚表里找有多少个镇
     *
     * */
    List<SysArea> findTown();
	List<CcmShakePovertyPeople> findPoorList(CcmShakePovertyPeople ccmShakePovertyPeople);
}