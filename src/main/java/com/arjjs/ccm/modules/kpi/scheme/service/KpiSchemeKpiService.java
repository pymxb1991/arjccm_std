/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;
import com.arjjs.ccm.modules.kpi.special.dao.KpiSpecialScoreDao;
import com.arjjs.ccm.modules.kpi.special.entity.KpiSpecialScore;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReportCount;
import com.arjjs.ccm.modules.kpi.scheme.dao.KpiSchemeKpiDao;

/**
 * 绩效考评KPIService
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiSchemeKpiService extends CrudService<KpiSchemeKpiDao, KpiSchemeKpi> {

	@Autowired
	private KpiSchemeKpiDao kpiSchemeKpiDao;
	@Autowired
	private KpiSpecialScoreDao kpiSpecialScoreDao;
	
	public KpiSchemeKpi get(String id) {
		return super.get(id);
	}
	
	public List<KpiSchemeKpi> findList(KpiSchemeKpi kpiSchemeKpi) {
		return super.findList(kpiSchemeKpi);
	}
	
	public Page<KpiSchemeKpi> findPage(Page<KpiSchemeKpi> page, KpiSchemeKpi kpiSchemeKpi) {
		return super.findPage(page, kpiSchemeKpi);
	}

	public Page<KpiSchemeKpi> findPageSubjective(Page<KpiSchemeKpi> page, KpiSchemeKpi kpiSchemeKpi) {
		kpiSchemeKpi.setPage(page);
		List<KpiSchemeKpi> kpiList = kpiSchemeKpiDao.findPageSubjective(kpiSchemeKpi);
		page.setList(kpiList);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(KpiSchemeKpi kpiSchemeKpi) {
		super.save(kpiSchemeKpi);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiSchemeKpi kpiSchemeKpi) {
		super.delete(kpiSchemeKpi);
	}

	//查找状态
	public KpiSchemeKpi getAll(KpiSchemeKpi kpiSchemeKpi) {
		return kpiSchemeKpiDao.getAll(kpiSchemeKpi);
	}
	
	public CcmWorkReportCount getccmWorkReportCountSource(KpiSchemeKpi kpiSchemeKpi) {
		KpiSpecialScore kpiSpecialScore = new KpiSpecialScore();
		List<KpiSpecialScore> list = kpiSpecialScoreDao.findList(kpiSpecialScore);
		CcmWorkReportCount ccmWorkReportCount = new CcmWorkReportCount();
		int num = 0;
		for(int i=0;i<list.size();i++) {
			num +=  Integer.valueOf(list.get(i).getWeights());
		}
		int weight = 0;
		int source = 0;
		int temp = 0;
		source = kpiSchemeKpi.getScore().intValue();
		for(int j=0;j<list.size();j++) {
			if(list.get(j).getKeyname().equals("orgTailCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setOrgTailCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("tenantCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setHouseTailCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("popSpecialTailCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setPopSpecialTailCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("popUpdateCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setPopUpdateCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("eventDealCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setEventDealCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("eventIncidentCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setEventIncidentCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("reportCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setReportCount(temp/num);
				
			}else if(list.get(j).getKeyname().equals("houseCount")) {
				weight = Integer.valueOf(list.get(j).getWeights()).intValue();
				temp = weight*source;
				ccmWorkReportCount.setHouseCount(temp/num);
				
			}else {
				continue;
			}
		}
		return ccmWorkReportCount;
	}
}