/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetailHis;
import com.arjjs.ccm.modules.pbs.ncount.dao.PbsNcountdetailHisDao;

/**
 * 统计信息历史Service
 * @author lc
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class PbsNcountdetailHisService extends CrudService<PbsNcountdetailHisDao, PbsNcountdetailHis> {

	public PbsNcountdetailHis get(String id) {
		return super.get(id);
	}
	
	public List<PbsNcountdetailHis> findList(PbsNcountdetailHis pbsNcountdetailHis) {
		return super.findList(pbsNcountdetailHis);
	}
	
	public Page<PbsNcountdetailHis> findPage(Page<PbsNcountdetailHis> page, PbsNcountdetailHis pbsNcountdetailHis) {
		return super.findPage(page, pbsNcountdetailHis);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsNcountdetailHis pbsNcountdetailHis) {
		super.save(pbsNcountdetailHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsNcountdetailHis pbsNcountdetailHis) {
		super.delete(pbsNcountdetailHis);
	}

	@Transactional(readOnly = false)
	public void copyData(List<PbsNcountdetail> PbsNcountdetails){
		dao.copyData(PbsNcountdetails);
	}
}