/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.email.dao.PlmWorkEmailDao;
import com.arjjs.ccm.modules.plm.email.dao.PlmWorkEmailReadDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmail;
import com.arjjs.ccm.modules.plm.email.entity.PlmWorkEmailRead;

/**
 * 工作日志Service
 * 
 * @author arj
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class PlmWorkEmailService extends CrudService<PlmWorkEmailDao, PlmWorkEmail> {

	@Autowired
	private PlmWorkEmailReadDao plmWorkEmailReadDao;
	
	
	public PlmWorkEmail get(String id) {
		return super.get(id);
	}

	public List<PlmWorkEmail> findList(PlmWorkEmail plmWorkEmail) {
		return super.findList(plmWorkEmail);
	}

	public Page<PlmWorkEmail> findPage(Page<PlmWorkEmail> page, PlmWorkEmail plmWorkEmail) {
		return super.findPage(page, plmWorkEmail);
	}
	/**
	 * 保存到草稿箱
	 */
	@Transactional(readOnly = false)
	public void save(PlmWorkEmail plmWorkEmail) {
		plmWorkEmail.setIsStar("0");
		if(!plmWorkEmail.getIsC()){
			plmWorkEmail.setPlmWorkEmailCReadIds("");
		}
		if(!plmWorkEmail.getIsM()){
			plmWorkEmail.setPlmWorkEmailMReadIds("");
		}
		if (plmWorkEmail.getContent()!=null){
			plmWorkEmail.setContent(StringEscapeUtils.unescapeHtml4(plmWorkEmail.getContent()));
		}
		super.save(plmWorkEmail);
		// 更新发送接受人记录
		plmWorkEmailReadDao.deleteByWorkReportId(plmWorkEmail.getId());
		//如果1,保存草稿箱
		if("1".equals(plmWorkEmail.getStatus())){
			if (plmWorkEmail.getPlmWorkEmailSReadList().size() > 0) {
				plmWorkEmailReadDao.insertCaoGaoAll(plmWorkEmail.getPlmWorkEmailSReadList());
			}
			if (plmWorkEmail.getPlmWorkEmailCReadList().size() > 0) {
				plmWorkEmailReadDao.insertCaoGaoAll(plmWorkEmail.getPlmWorkEmailCReadList());
			}
			if (plmWorkEmail.getPlmWorkEmailMReadList().size() > 0) {
				plmWorkEmailReadDao.insertCaoGaoAll(plmWorkEmail.getPlmWorkEmailMReadList());
			}
		}else{
			if (plmWorkEmail.getPlmWorkEmailSReadList().size() > 0) {
				plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailSReadList());
			}
			if (plmWorkEmail.getPlmWorkEmailCReadList().size() > 0) {
				plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailCReadList());
			}
			if (plmWorkEmail.getPlmWorkEmailMReadList().size() > 0) {
				plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailMReadList());
			}
		}

		
	}
/*	*//**
	 * 发送邮件
	 *//*
	@Transactional(readOnly = false)
	public void apply(PlmWorkEmail plmWorkEmail) {
		if (plmWorkEmail.getContent()!=null){
			plmWorkEmail.setContent(StringEscapeUtils.unescapeHtml4(plmWorkEmail.getContent()));
		}	
		plmWorkEmail.setStatus("0");
		super.save(plmWorkEmail);
		// 更新发送接受人记录
		plmWorkEmailReadDao.deleteByWorkReportId(plmWorkEmail.getId());
		if (plmWorkEmail.getPlmWorkEmailSReadList().size() > 0) {
			plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailSReadList());
		}
		if (plmWorkEmail.getPlmWorkEmailCReadList().size() > 0) {
			plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailCReadList());
		}
		if (plmWorkEmail.getPlmWorkEmailMReadList().size() > 0) {
			plmWorkEmailReadDao.insertFaSongAll(plmWorkEmail.getPlmWorkEmailMReadList());
		}
	}*/

	@Transactional(readOnly = false)
	public void delete(PlmWorkEmail plmWorkEmail) {
		super.delete(plmWorkEmail);
	}

	@Transactional(readOnly = false)
	public void updateReadFlag(PlmWorkEmail plmWorkEmail) {
		PlmWorkEmailRead plmWorkEmailRead = new PlmWorkEmailRead(plmWorkEmail);
		plmWorkEmailRead.setReportId(plmWorkEmail.getId());
		plmWorkEmailRead.setUser(plmWorkEmail.getCurrentUser());
		plmWorkEmailRead.setReadTime(new Date());
		plmWorkEmailRead.setReadFlag("1");
		plmWorkEmailReadDao.update(plmWorkEmailRead);
	}

	/**
	 * 获取发送人
	 * 
	 * @param oaNotify
	 * @return
	 */
	public PlmWorkEmail getRecordList(PlmWorkEmail plmWorkEmail) {
		PlmWorkEmailRead c = new PlmWorkEmailRead();
		c.setReportId(plmWorkEmail.getId());
		c.setType("1");
		plmWorkEmail.setPlmWorkEmailSReadList(plmWorkEmailReadDao.findList(c));
		c.setType("2");
		plmWorkEmail.setPlmWorkEmailCReadList(plmWorkEmailReadDao.findList(c));
		c.setType("3");
		plmWorkEmail.setPlmWorkEmailMReadList(plmWorkEmailReadDao.findList(c));
		return plmWorkEmail;
	}

	/**
	 * 获取工作日志数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(PlmWorkEmail plmWorkEmail) {
		return dao.findCount(plmWorkEmail);
	}
	@Transactional(readOnly = false)
	public void selfDelete(PlmWorkEmailRead plmWorkEmailRead) {
		plmWorkEmailReadDao.selfDelete(plmWorkEmailRead);
	}

	public Page<PlmWorkEmail> findDefPage(Page<PlmWorkEmail> page, PlmWorkEmail plmWorkEmail) {
		plmWorkEmail.setPage(page);
		page.setList(dao.findDefList(plmWorkEmail));
		return page;
	}
	@Transactional(readOnly = false)
	public void delete2(PlmWorkEmail plmWorkEmail) {
		dao.delete2(plmWorkEmail);
	}

	public Page<PlmWorkEmail> findStarPage(Page<PlmWorkEmail> page, PlmWorkEmail plmWorkEmail) {
		plmWorkEmail.setPage(page);
		page.setList(dao.findStarList(plmWorkEmail));
		return page;
	}
	@Transactional(readOnly = false)
	public void star(PlmWorkEmail plmWorkEmail) {
		dao.star(plmWorkEmail);
	}
	@Transactional(readOnly = false)
	public void selfStar(PlmWorkEmailRead plmWorkEmailRead) {
		plmWorkEmailReadDao.selfStar(plmWorkEmailRead);
	}

	public List<Integer> findReceiveCount(PlmWorkEmail plmWorkEmailNew) {
		return dao.findReceiveCount(plmWorkEmailNew);
	}

	/*//报表:工作日志
	public List<PlmWorkEmailCount> findLogBook(PlmWorkEmailCount plmWorkEmailCount) {
		return plmWorkEmailDao.findLogBook(plmWorkEmailCount);
	}*/

/*	public PlmWorkEmail findUsers(String plmWorkEmailReadIds){
		PlmWorkEmail plmWorkEmail = new PlmWorkEmail();
		
		List<PlmWorkEmailRead>  plmWorkEmailReadList = Lists.newArrayList();
		for (String id : StringUtils.split(plmWorkEmailReadIds, ",")){
			User user = userDao.get(id);
			if(user != null){
				PlmWorkEmailRead entity = new PlmWorkEmailRead();
				entity.setId(IdGen.uuid());
				entity.setUser(user);
				entity.setReadFlag("0");
				plmWorkEmailReadList.add(entity);
			}
		}
		String names = Collections3.extractToString(plmWorkEmailReadList, "user.name", ",");
		plmWorkEmail.setPlmWorkEmailReadIds(Collections3.extractToString(plmWorkEmailReadList, "user.id", ","));
		plmWorkEmail.setPlmWorkEmailReadNames(names);
		return plmWorkEmail;
	}*/
	

	
	
	
}