/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.payment.entity.PbsPayment;
import com.arjjs.ccm.modules.pbs.payment.dao.PbsPaymentDao;

/**
 * 党员缴费信息管理Service
 * @author qixuesong
 * @version 2018-09-05
 */
@Service
@Transactional(readOnly = true)
public class PbsPaymentService extends CrudService<PbsPaymentDao, PbsPayment> {

	public PbsPayment get(String id) {
		return super.get(id);
	}
	
	public List<PbsPayment> findList(PbsPayment pbsPayment) {
		return super.findList(pbsPayment);
	}
	
	public Page<PbsPayment> findPage(Page<PbsPayment> page, PbsPayment pbsPayment) {
		return super.findPage(page, pbsPayment);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsPayment pbsPayment) {
		super.save(pbsPayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsPayment pbsPayment) {
		super.delete(pbsPayment);
	}
	
	@Transactional(readOnly = false)
	public int insert(PbsPayment pbsPayment) {
		return dao.insert(pbsPayment);
	}
	
	@Transactional(readOnly = false)
	public int update(PbsPayment pbsPayment) {
		return dao.update(pbsPayment);
	}
}