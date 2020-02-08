/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;

/**
 * 党员表关系DAO接口
 * @author lc
 * @version 2018-04-03
 */
@MyBatisDao
public interface PbsPartymembindDao extends CrudDao<PbsPartymembind> {
	
	// 检测这个党员关系是否存在
	public int checkExist(PbsPartymembind pbsPartymembind);
	
	// 删除指定 用户的党员关系信息
	public int deleteByUser(PbsPartymembind pbsPartymembind);
	
	// 获取 指定用户 的党员
	public PbsPartymem getItemByUser(PbsPartymembind id);
	
}