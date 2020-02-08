/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;
import com.arjjs.ccm.modules.pbs.thinkingreport.dao.PbsThinkingreportDao;

/**
 * 思想汇报信息Service
 * 
 * @author lc
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class PbsThinkingreportService extends CrudService<PbsThinkingreportDao, PbsThinkingreport> {

	public PbsThinkingreport get(String id) {
		return super.get(id);
	}

	public List<PbsThinkingreport> findList(PbsThinkingreport pbsThinkingreport) {
		return super.findList(pbsThinkingreport);
	}

	public Page<PbsThinkingreport> findPage(Page<PbsThinkingreport> page, PbsThinkingreport pbsThinkingreport) {
		return super.findPage(page, pbsThinkingreport);
	}

	@Transactional(readOnly = false)
	public void save(PbsThinkingreport pbsThinkingreport) {
		if (StringUtils.isNoneBlank(pbsThinkingreport.getSContent())) {
			pbsThinkingreport.setSContent(StringEscapeUtils.unescapeHtml4(pbsThinkingreport.getSContent()));
		}
		super.save(pbsThinkingreport);
	}

	@Transactional(readOnly = false)
	public void delete(PbsThinkingreport pbsThinkingreport) {
		super.delete(pbsThinkingreport);
	}

	public List<PbsThinkingreport> findListByTransmem(PbsThinkingreport p) {
		return dao.findListByTransmem(p);
	}

	// 被转发的人的思想汇报信息
	public Page<PbsThinkingreport> findPageByTransmem(Page<PbsThinkingreport> page, PbsThinkingreport thinkingreport) {
		thinkingreport.setPage(page);
		page.setList(dao.findListByTransmem(thinkingreport));
		return page;
	}
}