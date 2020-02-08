/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.modules.pbs.sys.dao.PbsOfficeDao;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 机构Service
 * @author admin001
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class PbsOfficeService extends TreeService<PbsOfficeDao, PbsOffice> {

	public List<PbsOffice> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<PbsOffice> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<PbsOffice> findList(PbsOffice office){
		if(office != null ){
			if( office.getParentIds() !=null&&office.getParentIds() !="null" ) {
				// 相关的联系内容
				String parentids = office.getParentIds();
//				if(StringUtils.isNoneBlank(office.getId())) {
//					parentids = office.getParentIds()+office.getId();
//				}
				office.setParentIds(parentids);
			}
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<PbsOffice>();
	}
	
	@Transactional(readOnly = false)
	public void save(PbsOffice office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsOffice office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	// 新增的方法
	@Transactional(readOnly = false)
	public boolean insert(PbsOffice office) {
//		office.preInsert();
		// 新的用户
		User user = UserUtils.getUser();
		office.setCreateBy(user);
		office.setCreateDate(new Date());
		office.setUpdateBy(user);
		office.setUpdateDate(new Date());
		office.setDelFlag("0");
		// 更新
		int  count =dao.insertForexport(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		return  count==0 ?false:true;
	}
	
	// 更新的方法
	@Transactional(readOnly = false)
	public boolean update(PbsOffice office) {
		office.preUpdate();
		int count= dao.updateForexport(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
		return  count==0 ?false:true;
	}
	
}
