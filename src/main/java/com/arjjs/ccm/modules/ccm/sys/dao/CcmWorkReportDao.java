/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.sys.entity.*;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;

import java.util.List;

/**
 * 工作日志DAO接口
 * @author arj
 * @version 2018-03-08
 */
@MyBatisDao
public interface CcmWorkReportDao extends CrudDao<CcmWorkReport> {

	List<Count> getByCount();
	/**
	 * 获取工作日志数目
	 * @param ccmWorkReport
	 * @return
	 */
	public Long findCount(CcmWorkReport ccmWorkReport);

	//报表:工作日志
	public List<CcmWorkReportCount> findLogBook(CcmWorkReportCount ccmWorkReportCount);
	
	public List<CcmWorkReport> findJobPage(CcmWorkReport ccmWorkReport);
	
	public List<CcmWorkReport> findVisitPage(CcmWorkReport ccmWorkReport);
	
	public List<CcmWorkReport> findEventProcessPage(CcmWorkReport ccmWorkReport);

	public List<CcmEventExport> exportEvent(CcmWorkReport ccmWorkReport);

	public List<CcmWorkReportExport> exportWorkReport(CcmWorkReport ccmWorkReport);

	public List<CcmSelfExport> exportSelf(CcmWorkReport ccmWorkReport);

	public List<CcmWorkReport> findCountPage(CcmWorkReport ccmWorkReport);
	
	public CcmWorkReport findByID(CcmWorkReport ccmWorkReport);
	
	public void updateByID(CcmWorkReport ccmWorkReport);
	
	public List<CcmWorkReportVO> exportList(CcmWorkReport ccmWorkReport);
	
	public List<CcmWorkReportTO> exportJobList(CcmWorkReport ccmWorkReport);

	public List<CcmWorkReportYO> findCountExportList(CcmWorkReport ccmWorkReport);
}