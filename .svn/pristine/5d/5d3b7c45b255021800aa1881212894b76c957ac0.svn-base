/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskoprec;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskrec;
import com.arjjs.ccm.modules.pbs.task.dao.PbsTaskoprecDao;
import com.arjjs.ccm.modules.pbs.task.dao.PbsTaskrecDao;

/**
 * 工作安排操作记录Service
 * 
 * @author lc
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class PbsTaskoprecService extends CrudService<PbsTaskoprecDao, PbsTaskoprec> {

	@Autowired
	private PbsTaskrecDao pbsTaskrecDao;

	public PbsTaskoprec get(String id) {
		return super.get(id);
	}

	public List<PbsTaskoprec> findList(PbsTaskoprec pbsTaskoprec) {
		return super.findList(pbsTaskoprec);
	}

	public Page<PbsTaskoprec> findPage(Page<PbsTaskoprec> page, PbsTaskoprec pbsTaskoprec) {
		return super.findPage(page, pbsTaskoprec);
	}

	@Transactional(readOnly = false)
	public void save(PbsTaskoprec pbsTaskoprec) {
		boolean insertflag = StringUtils.isBlank(pbsTaskoprec.getId());
		super.save(pbsTaskoprec);
		// 新增 的 操作表记录 更新 主表
		if (insertflag) {
			// 更新操作记录 更新主表 进程状态
			PbsTaskrec pbsTaskrec = pbsTaskrecDao.get(pbsTaskoprec.getsTaskid());
			// 实时更新 主表的 状态
			pbsTaskrec.setsOptstatus(pbsTaskoprec.getSOptstatus());
			// 当前  为评价阶段 
			if(("4").equals(pbsTaskoprec.getSType())){
				pbsTaskrec.setSStatus("99");
			} 
			// 撤销也可以 关闭任务
			if(("99").equals(pbsTaskoprec.getSType())){
				pbsTaskrec.setSStatus("99");
			} 
			pbsTaskrecDao.update(pbsTaskrec);
		}
	}

	@Transactional(readOnly = false)
	public void delete(PbsTaskoprec pbsTaskoprec) {
		super.delete(pbsTaskoprec);
	}

	// 判断该人 是处理过该任务在 同一个阶段
	public boolean checkExist(PbsTaskoprec pbsTaskoprec) {
		return dao.checkExist(pbsTaskoprec) == 0 ? false : true;
	}
	
	//  删除 taskid
	public void deleteBysTaskid(PbsTaskoprec pbsTaskoprec){
		dao.deleteBysTaskid(pbsTaskoprec);
	}
}