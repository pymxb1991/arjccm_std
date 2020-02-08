/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.course.dao.PbsCourseoperateDao;

/**
 * 课程操作信息Service
 * 
 * @author lc
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class PbsCourseoperateService extends CrudService<PbsCourseoperateDao, PbsCourseoperate> {

	
	public PbsCourseoperate get(String id) {
		return super.get(id);
	}

	public List<PbsCourseoperate> findList(PbsCourseoperate pbsCourseoperate) {
		return super.findList(pbsCourseoperate);
	}

	public Page<PbsCourseoperate> findPage(Page<PbsCourseoperate> page, PbsCourseoperate pbsCourseoperate) {
		return super.findPage(page, pbsCourseoperate);
	}

	@Transactional(readOnly = false)
	public void save(PbsCourseoperate pbsCourseoperate) {
		// 如果当前的时长为空
		if(StringUtils.isEmpty(pbsCourseoperate.getITime())) {
			pbsCourseoperate.setITime("0");
		}
		// 设置当前的党员信息
		pbsCourseoperate.setsBindmember(UserUtils.getPartymem());
		super.save(pbsCourseoperate);
	}

	@Transactional(readOnly = false)
	public void delete(PbsCourseoperate pbsCourseoperate) {
		super.delete(pbsCourseoperate);
	}

	public int getSumByUser(String sParentid){
		return dao.getSumByUser(sParentid);
	}

	public int getCounts(String sParentid){
		return dao.getCounts(sParentid);
	}
	public List<PbsCourseoperate> getSumByMemberId(String sBindmemeber) {
		return dao.getSumByMemberId(sBindmemeber);
	}
	
	public List<PbsCourseoperate> getDepartmentPeriod(PbsCourseoperate pbsCourseoperate){
		return dao.getDepartmentPeriod(pbsCourseoperate);
	}
	
	public List<PbsCourseoperate> getPersonnelPeriod(PbsCourseoperate pbsCourseoperate){
		return dao.getPersonnelPeriod(pbsCourseoperate);
	}
}