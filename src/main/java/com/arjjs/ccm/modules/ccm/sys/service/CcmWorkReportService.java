/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.sys.dao.CcmWorkReportDao;
import com.arjjs.ccm.modules.ccm.sys.dao.CcmWorkReportReadDao;
import com.arjjs.ccm.modules.ccm.sys.entity.*;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 工作日志Service
 * 
 * @author arj
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkReportService extends CrudService<CcmWorkReportDao, CcmWorkReport> {

	@Autowired
	private CcmWorkReportReadDao ccmWorkReportReadDao;
	@Autowired
	private CcmWorkReportDao ccmWorkReportDao;
	@Autowired
	private UserDao userDao;
	
	
	public CcmWorkReport get(String id) {
		return super.get(id);
	}

	public List<CcmWorkReport> findList(CcmWorkReport ccmWorkReport) {
		return super.findList(ccmWorkReport);
	}

	public Page<CcmWorkReport> findPage(Page<CcmWorkReport> page, CcmWorkReport ccmWorkReport) {
		return super.findPage(page, ccmWorkReport);
	}
	
	public Page<CcmWorkReport> findJobPage(Page<CcmWorkReport> page, CcmWorkReport ccmWorkReport) {
		ccmWorkReport.setPage(page);
		List<CcmWorkReport> list = ccmWorkReportDao.findJobPage(ccmWorkReport);
		return page.setList(list);
	}
	
	public List<CcmWorkReportTO> exportJob(CcmWorkReport ccmWorkReport){
		List<CcmWorkReportTO> list = ccmWorkReportDao.exportJobList(ccmWorkReport);
		return list;
	}
	
	public Page<CcmWorkReport> findVisitPage(Page<CcmWorkReport> page, CcmWorkReport ccmWorkReport) {
		ccmWorkReport.setPage(page);
		List<CcmWorkReport> list = ccmWorkReportDao.findVisitPage(ccmWorkReport);
		return page.setList(list);
	}
	
	public List<CcmWorkReportVO> exportVisit(CcmWorkReport ccmWorkReport){
		List<CcmWorkReportVO> list = ccmWorkReportDao.exportList(ccmWorkReport);
		return list;
	}
	
	public Page<CcmWorkReport> findEventProcessPage(Page<CcmWorkReport> page, CcmWorkReport ccmWorkReport) {
		ccmWorkReport.setPage(page);
		List<CcmWorkReport> list = ccmWorkReportDao.findEventProcessPage(ccmWorkReport);
		return page.setList(list);
	}
	
	public List<CcmWorkReportVO> exportEvent(CcmWorkReport ccmWorkReport){
		List<CcmWorkReportVO> list = ccmWorkReportDao.exportList(ccmWorkReport);
		return list;
	}

	public List<CcmWorkReportExport> exportWorkReport(CcmWorkReport ccmWorkReport){
		List<CcmWorkReportExport> list = ccmWorkReportDao.exportWorkReport(ccmWorkReport);
		return list;
	}
	public List<CcmSelfExport> exportSelf(CcmWorkReport ccmWorkReport){
		List<CcmSelfExport> list = ccmWorkReportDao.exportSelf(ccmWorkReport);
		return list;
	}
	public Page<CcmWorkReport> findCountPage(Page<CcmWorkReport> page, CcmWorkReport ccmWorkReport) {
		ccmWorkReport.setPage(page);
		List<CcmWorkReport> list = ccmWorkReportDao.findCountPage(ccmWorkReport);
		return page.setList(list);
	}
	
	public List<CcmWorkReportYO> exportCount(CcmWorkReport ccmWorkReport){
		List<CcmWorkReportYO> list = ccmWorkReportDao.findCountExportList(ccmWorkReport);
		return list;
	}
	
	public CcmWorkReport findByID(CcmWorkReport ccmWorkReport) {
		CcmWorkReport ccmworkreport = ccmWorkReportDao.findByID(ccmWorkReport);
		return ccmworkreport;
	}

	@Transactional(readOnly = false)
	public void save(CcmWorkReport ccmWorkReport) {
		ccmWorkReport.setCreateDate(new Date());
		super.save(ccmWorkReport);
		// 更新发送接受人记录
		ccmWorkReportReadDao.deleteByWorkReportId(ccmWorkReport.getId());
		if (ccmWorkReport.getCcmWorkReportReadList().size() > 0) {
			ccmWorkReportReadDao.insertAll(ccmWorkReport.getCcmWorkReportReadList());
		}
	}

	@Transactional(readOnly = false)
	public void delete(CcmWorkReport ccmWorkReport) {
		super.delete(ccmWorkReport);
	}

	@Transactional(readOnly = false)
	public void updateReadFlag(CcmWorkReport ccmWorkReport) {
		CcmWorkReportRead ccmWorkReportRead = new CcmWorkReportRead(ccmWorkReport);
		ccmWorkReportRead.setReportId(ccmWorkReport.getId());
		ccmWorkReportRead.setUser(ccmWorkReport.getCurrentUser());
		ccmWorkReportRead.setReadTime(new Date());
		ccmWorkReportRead.setReadFlag("1");
		ccmWorkReportReadDao.update(ccmWorkReportRead);
	}
	
	@Transactional(readOnly = false)
	public void updateAppReadFlag(CcmWorkReport ccmWorkReport, String id) {
		CcmWorkReportRead ccmWorkReportRead = new CcmWorkReportRead(ccmWorkReport);
		ccmWorkReportRead.setReportId(ccmWorkReport.getId());
		ccmWorkReportRead.setUserId(id);
		ccmWorkReportRead.setReadTime(new Date());
		ccmWorkReportRead.setReadFlag("1");
		ccmWorkReportRead.setUpdateDate(new Date());
		ccmWorkReportReadDao.updateRead(ccmWorkReportRead);
	}
	
	@Transactional(readOnly = false)
	public void updateJob(CcmWorkReport ccmWorkReport) {
		CcmWorkReport ccmworkreport = new CcmWorkReport();
		ccmworkreport.setId(ccmWorkReport.getId());
		ccmworkreport.setType(ccmWorkReport.getType());
		ccmworkreport.setEventtypeid(ccmWorkReport.getEventtypeid());
		ccmworkreport.setBeginDate(ccmWorkReport.getBeginDate());
		ccmworkreport.setEndDate(ccmWorkReport.getEndDate());
		ccmworkreport.setPlace(ccmWorkReport.getPlace());
		ccmworkreport.setTitle(ccmWorkReport.getTitle());
		ccmworkreport.setContent(ccmWorkReport.getContent());
		ccmworkreport.setFiles(ccmWorkReport.getFiles());
		ccmworkreport.setTelephone(ccmWorkReport.getTelephone());
		ccmWorkReportDao.updateByID(ccmworkreport);
	}

	/**
	 * 获取工作日志发送记录
	 * 
	 * @param oaNotify
	 * @return
	 */
	public CcmWorkReport getRecordList(CcmWorkReport ccmWorkReport) {
		CcmWorkReportRead c = new CcmWorkReportRead();
		c.setReportId(ccmWorkReport.getId());
		ccmWorkReport.setCcmWorkReportReadList(ccmWorkReportReadDao.findList(c));
		return ccmWorkReport;
	}

	/**
	 * 获取工作日志数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(CcmWorkReport ccmWorkReport) {
		return dao.findCount(ccmWorkReport);
	}

	//报表:工作日志
	public List<CcmWorkReportCount> findLogBook(CcmWorkReportCount ccmWorkReportCount) {
		return ccmWorkReportDao.findLogBook(ccmWorkReportCount);
	}

	public CcmWorkReport findUsers(String ccmWorkReportReadIds){
		CcmWorkReport ccmWorkReport = new CcmWorkReport();
		
		List<CcmWorkReportRead>  ccmWorkReportReadList = Lists.newArrayList();
		for (String id : StringUtils.split(ccmWorkReportReadIds, ",")){
			User user = userDao.get(id);
			if(user != null){
				CcmWorkReportRead entity = new CcmWorkReportRead();
				entity.setId(IdGen.uuid());
				entity.setUser(user);
				entity.setReadFlag("0");
				ccmWorkReportReadList.add(entity);
			}
		}
		String names = Collections3.extractToString(ccmWorkReportReadList, "user.name", ",");
		ccmWorkReport.setCcmWorkReportReadIds(Collections3.extractToString(ccmWorkReportReadList, "user.id", ","));
		ccmWorkReport.setCcmWorkReportReadNames(names);
		return ccmWorkReport;
	}
	

	
	
	
}