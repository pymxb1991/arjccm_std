/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.notebook.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.notebook.entity.CcmNotebook;

/**
 * 记事本DAO接口
 * @author liuyongjian
 * @version 2019-06-18
 */
@MyBatisDao
public interface CcmNotebookDao extends CrudDao<CcmNotebook> {
	
}