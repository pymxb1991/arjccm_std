/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlownode;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlowdefinitionDao;
import com.arjjs.ccm.modules.pbs.flow.dao.PbsFlownodeDao;

/**
 * 流程定义信息Service
 * 
 * @author lc
 * @version 2018-04-19
 */
@Service
@Transactional(readOnly = true)
public class PbsFlowdefinitionService extends CrudService<PbsFlowdefinitionDao, PbsFlowdefinition> {

	@Autowired
	private PbsFlownodeDao pbsFlownodeDao;

	public PbsFlowdefinition get(String id) {
		return super.get(id);
	}

	public List<PbsFlowdefinition> findList(PbsFlowdefinition pbsFlowdefinition) {
		return super.findList(pbsFlowdefinition);
	}

	public Page<PbsFlowdefinition> findPage(Page<PbsFlowdefinition> page, PbsFlowdefinition pbsFlowdefinition) {
		return super.findPage(page, pbsFlowdefinition);
	}

	@Transactional(readOnly = false)
	public void save(PbsFlowdefinition pbsFlowdefinition) {
		boolean flagExist = StringUtils.isBlank(pbsFlowdefinition.getId());
		super.save(pbsFlowdefinition);
		if (flagExist) {
			// 起始节点
			PbsFlownode pbsFlownodeBeg = new PbsFlownode();
			pbsFlownodeBeg.preInsert();
			pbsFlownodeBeg.setsFlowid(pbsFlowdefinition);
			pbsFlownodeBeg.setSName("起始节点");
			pbsFlownodeBeg.setsNodetype("0");
			pbsFlownodeBeg.setsSort("0");
			// 终止节点
			PbsFlownode pbsFlownodeEnd = new PbsFlownode();
			pbsFlownodeEnd.preInsert();
			pbsFlownodeEnd.setsFlowid(pbsFlowdefinition);
			pbsFlownodeEnd.setSName("结束节点");
			pbsFlownodeEnd.setsNodetype("99");
			pbsFlownodeEnd.setsSort("99");
			//准备 添加 节点 
			pbsFlownodeBeg.setsNextnodeid(pbsFlownodeEnd);
			pbsFlownodeEnd.setsPrevnodeid(pbsFlownodeBeg);
			pbsFlownodeDao.insert(pbsFlownodeBeg);
			pbsFlownodeDao.insert(pbsFlownodeEnd);
		}
	}

	@Transactional(readOnly = false)
	public void delete(PbsFlowdefinition pbsFlowdefinition) {
		super.delete(pbsFlowdefinition);
		// 删除了所有 节点
		PbsFlownode pbsFlownodeDto = new PbsFlownode();
		pbsFlownodeDto.setsFlowid(pbsFlowdefinition);
		pbsFlownodeDao.deleteBysFlowid(pbsFlownodeDto);
	}

}