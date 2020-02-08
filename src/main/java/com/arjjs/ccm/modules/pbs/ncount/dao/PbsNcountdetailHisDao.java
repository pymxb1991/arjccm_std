/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetailHis;

/**
 * 统计信息历史DAO接口
 * 
 * @author lc
 * @version 2018-07-19
 */
@MyBatisDao
public interface PbsNcountdetailHisDao extends CrudDao<PbsNcountdetailHis> {

	public void copyData(List<PbsNcountdetail> PbsNcountdetails);
}