/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.report.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.report.entity.CcmPeopleAmount;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SearchTabMore;
import com.google.common.collect.Lists;
import com.arjjs.ccm.modules.ccm.report.dao.CcmPeopleAmountDao;

/**
 * 人口总数统计Service
 * @author arj
 * @version 2018-01-20
 */
@Service
@Transactional(readOnly = true)
public class CcmPeopleAmountService extends CrudService<CcmPeopleAmountDao, CcmPeopleAmount> {

	@Autowired
	private CcmPeopleAmountDao ccmPeopleAmountDao;

	//实有人口统计情况
	public SearchTab getAnalyzePopData() {
		return ccmPeopleAmountDao.getAnalyzePopData();
	}
	//特殊人群分析
	public SearchTabMore getSpecialPopData() {
		return ccmPeopleAmountDao.getSpecialPopData();
	}
	//实有人口总数、新增
	public EchartType getCameraTotal() {
		return ccmPeopleAmountDao.getCameraTotal();
	}
	//本月人口信息getnumPopFollowPop
	public SearchTabMore getnumPopFollowPop() {
		return ccmPeopleAmountDao.getnumPopFollowPop();
	}
	//重点青少年统计
	public List<EchartType> getnumPopFollowEcharts() {
		return ccmPeopleAmountDao.getnumPopFollowEcharts();
	}
	
	//
	public CcmPeopleAmount get(String id) {
		return super.get(id);
	}
	
	public List<CcmPeopleAmount> findList(CcmPeopleAmount ccmPeopleAmount) {
		return super.findList(ccmPeopleAmount);
	}
	
	public Page<CcmPeopleAmount> findPage(Page<CcmPeopleAmount> page, CcmPeopleAmount ccmPeopleAmount) {
		return super.findPage(page, ccmPeopleAmount);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPeopleAmount ccmPeopleAmount) {
		super.save(ccmPeopleAmount);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPeopleAmount ccmPeopleAmount) {
		super.delete(ccmPeopleAmount);
	}
	
	public List<EchartType> findListBySum(String column) {
		return ccmPeopleAmountDao.findListBySum(column);
	}

	public List<EchartType> findListByMon(String column) {
		return ccmPeopleAmountDao.findListByMon(column);
	}
	
	/**
	 * @see 社区特殊人群类型统计
	 * @return
	 */
	public List<SearchTabMore> findPopByArea(CcmPeopleAmount ccmPeopleAmount) {
		return ccmPeopleAmountDao.findPopByArea(ccmPeopleAmount);
	}
	
	/**
	 * @see 社区特殊人群变化趋势
	 * @return
	 */
	public List<SearchTabMore> findPopTrendByArea(CcmPeopleAmount ccmPeopleAmount) {
		return ccmPeopleAmountDao.findPopTrendByArea(ccmPeopleAmount);
	}
	
	/**
	 * @see 首页社区弹框：人口结构
	 * @return
	 */
	public CcmPeopleAmount findMonthAreaAmount(CcmPeopleAmount ccmPeopleAmount) {
		return ccmPeopleAmountDao.findMonthAreaAmount(ccmPeopleAmount);
	}
	//按街道查询所有人员及事件总和
	public List<EchartType> findPeopleAndEventByArea() {
		return ccmPeopleAmountDao.findPeopleAndEventByArea();
	}
	
	//各街道人口数据对比（重点人群与特殊人群关系）-大屏-滨海新区社会网格化管理信息平台
	public List<SearchTab> getStreetPopData() {
		return ccmPeopleAmountDao.getStreetPopData();
	}


	public List<EchartType> findListBySumNum() {
		return ccmPeopleAmountDao.findListBySumNum();
	}

	
	//本月特殊信息人口getnumPopFollowPop
	public Map<String, Object> getnumPopFollowPopQL() {
		SearchTabMore obj = ccmPeopleAmountDao.getnumPopFollowPopQL();
		int[] num = new int[12];
		num[0] = Integer.parseInt(obj.getValue1());
		num[1] = Integer.parseInt(obj.getValue2());
		num[2] = Integer.parseInt(obj.getValue3());
		num[3] = Integer.parseInt(obj.getValue4());
		num[4] = Integer.parseInt(obj.getValue5());
		num[5] = Integer.parseInt(obj.getValue6());
		num[6] = Integer.parseInt(obj.getValue7());
		num[7] = Integer.parseInt(obj.getValue8());
		num[8] = Integer.parseInt(obj.getValue9());
		num[9] = Integer.parseInt(obj.getValue10());
		num[10] = Integer.parseInt(obj.getValue11());
		num[11] = Integer.parseInt(obj.getValue12());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("num", num);
		return result;
	}
	
	private List<String> getMonth(Integer number) {
        // 月份正常传入 如：1=1
        List<String> dateList = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        for (int i = number; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i);
            Date date = calendar.getTime();
            dateList.add(sdf.format(date));
        }

        return dateList;
    }
}