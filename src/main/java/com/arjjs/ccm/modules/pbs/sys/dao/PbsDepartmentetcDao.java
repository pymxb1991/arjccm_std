/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsDepartmentetc;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;

/**
 * 党员部门扩展信息DAO接口
 * 
 * @author lc
 * @version 2018-04-10
 */
@MyBatisDao
public interface PbsDepartmentetcDao extends CrudDao<PbsDepartmentetc> {

	public int checkExist(PbsDepartmentetc pbsDepartmentetc);

	public List<PbsOffice> getAllOfficeWithName(PbsOffice id);

	public List<PbsOffice> getAllOfficeWithMem(@Param("list")List<PbsOffice> list);
}