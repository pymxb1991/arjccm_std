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
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskoprec;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskrec;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.task.dao.PbsTaskrecDao;

/**
 * 工作安排记录Service
 * 
 * @author lc
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class PbsTaskrecService extends CrudService<PbsTaskrecDao, PbsTaskrec> {

	// 任务操作 Service
	@Autowired
	private PbsTaskoprecService pbsTaskoprecService;

	public PbsTaskrec get(String id) {
		return super.get(id);
	}

	public List<PbsTaskrec> findList(PbsTaskrec pbsTaskrec) {
		return super.findList(pbsTaskrec);
	}

	public Page<PbsTaskrec> findPage(Page<PbsTaskrec> page, PbsTaskrec pbsTaskrec) {
		return super.findPage(page, pbsTaskrec);
	}

	@Transactional(readOnly = false)
	public void save(PbsTaskrec pbsTaskrec) {
		boolean InsertFlag = StringUtils.isBlank(pbsTaskrec.getId());
		// 补充 未存在 的几个要素 1.当前的部门 2.当前的 用户 3.当前的
		/*pbsTaskrec.setsExecdepartment(UserUtils.getUser().getOffice());
		pbsTaskrec.setsExecutor(UserUtils.getPartymem());*/
		if (InsertFlag) {
			// 当前为创建任务
			pbsTaskrec.setsOptstatus("CREATE");
		}
		super.save(pbsTaskrec);
		// 如果 为 新增的内容 则同时 插入 一个创建的操作 记录
		if (InsertFlag) {
			PbsTaskoprec pbsTaskoprec = new PbsTaskoprec();
			pbsTaskoprec.setsTaskid(pbsTaskrec);
			pbsTaskoprec.setSSort("1"); // 当前的 一个顺序游标
			pbsTaskoprec.setSType("1"); // 当前的 阶段类别 ：创建
			pbsTaskoprec
					.setSContent(UserUtils.getUser().getName() + "于" + DateUtils.getDate() + "创建任务：" + pbsTaskrec.getSResume());
			// 当前的 部门 - 负责人 -执行党员
			pbsTaskoprec.setsExecdepartment(pbsTaskrec.getsExecdepartment());
			pbsTaskoprec.setsExecutor(pbsTaskrec.getsExecutor());
			pbsTaskoprec.setsOperator(UserUtils.getUser());
			pbsTaskoprec.setsBindmember(UserUtils.getPartymem());
			// 当前的状态位创建
			pbsTaskoprec.setSOptstatus("CREATE");
			// 插入 当前的 操作数据
			pbsTaskoprecService.save(pbsTaskoprec);
		}

	}

	@Transactional(readOnly = false)
	public void delete(PbsTaskrec pbsTaskrec) {
		// 删除 工作安排的 记录
		super.delete(pbsTaskrec);
		//  删除 工作安排的 操作记录
		PbsTaskoprec pbsTaskoprecDto = new PbsTaskoprec();
		pbsTaskoprecDto.setsTaskid(pbsTaskrec);	
		pbsTaskoprecService.deleteBysTaskid(pbsTaskoprecDto) ;
	}

}