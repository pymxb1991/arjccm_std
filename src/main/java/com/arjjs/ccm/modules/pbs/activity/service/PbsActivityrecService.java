/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitysignin;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActinotificationsDao;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivityrecDao;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivitysigninDao;

/**
 * 活动信息Service
 * 
 * @author lc
 * @version 2018-05-14
 */
@Service
@Transactional(readOnly = true)
public class PbsActivityrecService extends CrudService<PbsActivityrecDao, PbsActivityrec> {

	@Autowired
	private PbsActinotificationsDao pbsActinotificationsDao;
	@Autowired
	private PbsActivitysigninDao pbsActivitysigninDao;
	
	public PbsActivityrec get(String id) {
		return super.get(id);
	}

	public List<PbsActivityrec> findList(PbsActivityrec pbsActivityrec) {
		return super.findList(pbsActivityrec);
	}

	public Page<PbsActivityrec> findPage(Page<PbsActivityrec> page, PbsActivityrec pbsActivityrec) {
		return super.findPage(page, pbsActivityrec);
	}

	@Transactional(readOnly = false)
	public void save(PbsActivityrec pbsActivityrec) {
		super.save(pbsActivityrec);
		pbsActivityrec.setPbsActinotificationIds(pbsActivityrec.getPbsActinotificationIds());
		// 获取所有的 通知类信息
		List<PbsActinotifications> list = pbsActivityrec.getPbsActinotificationList();
		if (list.size() > 0) {
			// 清除所有 的 通知信息 并开始添加 新的 通知人员。
			pbsActinotificationsDao.DeleteByActivityid(pbsActivityrec);
			pbsActinotificationsDao.insertAll(list);
		}
	}

	@Transactional(readOnly = false)
	public void saveRecord(List<PbsActivitysignin> signList) {
		if (signList.size() > 0) {
			pbsActivitysigninDao.saveRecord(signList);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsActivityrec pbsActivityrec) {
		super.delete(pbsActivityrec);
	}

	// 根据 当前 的 用户 获取列表
	public List<PbsActivityrec> findListBysAcceptorid(PbsActivityrec pbsActivityrec) {
		return dao.findListBysAcceptorid(pbsActivityrec);
	}
	
	
	// 更新 签到、请假后对于总数的 影响
	@Transactional(readOnly = false)
	public void updateNum(PbsActivityrec pbsActivityrec){
		dao.updateNum(pbsActivityrec);
	}
}