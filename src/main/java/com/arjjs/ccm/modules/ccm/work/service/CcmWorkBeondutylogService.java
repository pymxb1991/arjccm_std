/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.work.dao.CcmWorkBeondutylogDao;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkBeondutylog;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkBeondutylogExport;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 值班日志表Service
 * @author liang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkBeondutylogService extends CrudService<CcmWorkBeondutylogDao, CcmWorkBeondutylog> {

	public CcmWorkBeondutylog get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkBeondutylog> findList(CcmWorkBeondutylog ccmWorkBeondutylog) {
		return super.findList(ccmWorkBeondutylog);
	}
	
	public Page<CcmWorkBeondutylog> findPage(Page<CcmWorkBeondutylog> page, CcmWorkBeondutylog ccmWorkBeondutylog) {
		User user = UserUtils.getUser();
		if(user==null){
			return new Page<CcmWorkBeondutylog>();
		}
		ccmWorkBeondutylog.getSqlMap().put("dsf", dataScopeFilter(user, "o", "u4"));
		return super.findPage(page, ccmWorkBeondutylog);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkBeondutylog ccmWorkBeondutylog) {
		super.save(ccmWorkBeondutylog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkBeondutylog ccmWorkBeondutylog) {
		super.delete(ccmWorkBeondutylog);
	}

	public List<CcmWorkBeondutylogExport> exportList(CcmWorkBeondutylog ccmWorkBeondutylog) {
		User user = UserUtils.getUser();
		if(user==null){
			return new ArrayList<CcmWorkBeondutylogExport>();
		}
		ccmWorkBeondutylog.getSqlMap().put("dsf", dataScopeFilter(user, "o", "u4"));
		return dao.exportList(ccmWorkBeondutylog);
	}

}