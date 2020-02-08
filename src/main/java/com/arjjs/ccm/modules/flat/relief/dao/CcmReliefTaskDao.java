/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.relief.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.flat.relief.entity.CcmReliefTask;

import java.util.List;

/**
 * 备勤任务实体类DAO接口
 *
 * @author lgh
 * @version 2019-07-25
 */
@MyBatisDao
public interface CcmReliefTaskDao extends CrudDao<CcmReliefTask> {


}