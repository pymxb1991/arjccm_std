/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseKym;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SysConfigInit;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;
import com.arjjs.ccm.modules.ccm.house.dao.CcmHouseKymDao;

/**
 * 重点青少年管理模块Service
 * 
 * @author arj
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class CcmHouseKymService extends CrudService<CcmHouseKymDao, CcmHouseKym> {

	@Autowired
	private CcmHouseKymDao ccmHouseKymDao;
	//上传上级平台记录
	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	
	
	
	//重点青少年分析
	public List<SearchTab> getnumPopFlowTable(){
		return ccmHouseKymDao.getnumPopFlowTable();
	}
	
	//
	public CcmHouseKym get(String id) {
		return super.get(id);
	}

	public List<CcmHouseKym> findList(CcmHouseKym ccmHouseKym) {
		return super.findList(ccmHouseKym);
	}

	public Page<CcmHouseKym> findPage(Page<CcmHouseKym> page, CcmHouseKym ccmHouseKym) {
		return super.findPage(page, ccmHouseKym);
	}

	@Transactional(readOnly = false)
	public void save(CcmHouseKym ccmHouseKym) {
		boolean isNew = false;
		if (ccmHouseKym.getId() == null || "".equals(ccmHouseKym.getId())) {//无主键ID，则是新记录
			isNew = true;
		}
		if (ccmHouseKym.getIsNewRecord()) {//指定了是新增记录，则算新记录
			isNew = true;
		}
		super.save(ccmHouseKym);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			if (isNew) {//新增数据
				uploadLog.setOperateType("1");
				uploadLog.setRemarks("新增重点青少年数据：" + ccmHouseKym.getPeopleId());
			} else {//编辑数据
				uploadLog.setOperateType("2");
				uploadLog.setRemarks("编辑重点青少年数据：" + ccmHouseKym.getPeopleId());
			}
			uploadLog.setTableName("ccm_house_kym");
			uploadLog.setDataId(ccmHouseKym.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	@Transactional(readOnly = false)
	public void delete(CcmHouseKym ccmHouseKym) {
		super.delete(ccmHouseKym);

		//上传上级平台记录
		if (!SysConfigInit.UPPER_URL.equals("")) {//有上级平台地址时，才存入上传上级平台记录的日志
			CcmUploadLog uploadLog = new CcmUploadLog();
			uploadLog.setOperateType("3");
			uploadLog.setRemarks("删除重点青少年数据：" + ccmHouseKym.getPeopleId());
			uploadLog.setTableName("ccm_house_kym");
			uploadLog.setDataId(ccmHouseKym.getId());
			uploadLog.setUploadStatus("1");
			User user = UserUtils.getUser();
			if (user == null || StringUtils.isBlank(user.getId())){
				uploadLog.setCreateBy(new User("1"));
				uploadLog.setUpdateBy(new User("1"));
			}
			ccmUploadLogService.save(uploadLog);
		}
	}

	// 获取 所有信息根据 id
	public CcmHouseKym getPeopleALL(String id) {
		return ccmHouseKymDao.getItemByPeopleId(id);
	}
	
	//重点青少年帮扶方式统计
	public Map<String, Object> getNumKymByAssistMethod(){
		//查询各帮扶方式数量
		List<EchartType> list = ccmHouseKymDao.getNumKymByAssistMethod();
		//查询帮扶方式总数
		EchartType echart = ccmHouseKymDao.getAllNumKymByAssistMethod();
		//判断查询的总数是否为空，为空的话赋值为其他类型的和
		if(StringUtils.isNotEmpty(echart.getValue())) {
			echart.setType("总数");
		}else {
			int num = 0;
			for(int i=0;i<list.size();i++) {
				num = num + Integer.parseInt(list.get(i).getValue());
			}
			echart.setValue(String.valueOf(num));
			echart.setType("总数");
		}
		String[] dataX = new String[list.size()];
		int[] dataY = new int[list.size()];
		int[] bgNum = new int[list.size()];
		for(int j=0;j<list.size();j++) {
			dataX[j] = list.get(j).getType();
			dataY[j] = Integer.parseInt(list.get(j).getValue());
			bgNum[j] = Integer.parseInt(echart.getValue());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("dataX", dataX);
		result.put("dataY", dataY);
		result.put("bgNum", bgNum);
		return result;
	}
	
	//重点青少年分析（曲梁）
	public Map<String, Object> getnumPopFlowTableQL(){
		List<EchartType> list = ccmHouseKymDao.getnumPopFlowTableQL();
		List<Map<String, Object>> resultlist = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		String[] name = new String[list.size()];
		int addsum = 0;
		int redsum = 0;
		int[] addMAX = new int[list.size()];
		int[] redMAX = new int[list.size()];
		int[] addvalue = new int[list.size()];
		int[] redvalue = new int[list.size()];
		int[] addProportion = new int[list.size()];
		int[] redProportion = new int[list.size()];
		EchartType temp = null;
		for(int i=0;i<list.size();i++) {
			temp = new EchartType();
			map = new HashMap<String, Object>();
			temp = list.get(i);
			if(temp.getValue2().equals("02")) {
				name[i] = "有不良行为青少年";
				map.put("name", "有不良行为青少年");
				map.put("value", temp.getValue());
			}else {
				name[i] = temp.getType();
				map.put("name", temp.getType());
				map.put("value", temp.getValue());
			}
			resultlist.add(map);
			addMAX[i] = -500;
			redMAX[i] = 500;
			addvalue[i] = Integer.parseInt(temp.getValue());
			redvalue[i] = Integer.parseInt(temp.getValue1());
			addsum = addsum + Integer.parseInt(temp.getValue());
			redsum = redsum + Integer.parseInt(temp.getValue1());
		}
		for(int j=0;j<addProportion.length;j++) {
			if(addsum!=0){
				addProportion[j] = -((addvalue[j]*500)/addsum);
			} else {
				addProportion[j] = 0;
			}
			if(redsum!=0){
				redProportion[j] = (redvalue[j]*500)/redsum;
			} else {
				redProportion[j] = 0;
			}

		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", name);
		result.put("resultlist", resultlist);
		result.put("addMAX", addMAX);
		result.put("addvalue", addvalue);
		result.put("addProportion", addProportion);
		result.put("redMAX", redMAX);
		result.put("redvalue", redvalue);
		result.put("redProportion", redProportion);
		return result; 
	}

}