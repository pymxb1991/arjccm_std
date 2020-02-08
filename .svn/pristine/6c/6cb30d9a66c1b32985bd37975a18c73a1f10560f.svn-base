/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDomain;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.AreaService;
import com.arjjs.ccm.tool.Tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.arjjs.ccm.modules.ccm.ccmsys.dao.CcmDomainDao;

/**
 * 下级域服务器管理Service
 * @author pengjianqiang
 * @version 2018-05-09
 */
@Service
@Transactional(readOnly = true)
public class CcmDomainService extends CrudService<CcmDomainDao, CcmDomain> {

	@Autowired
	private AreaService areaService;
	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	
	public CcmDomain get(String id) {
		return super.get(id);
	}
	
	public List<CcmDomain> findList(CcmDomain ccmDomain) {
		return super.findList(ccmDomain);
	}
	
	public Page<CcmDomain> findPage(Page<CcmDomain> page, CcmDomain ccmDomain) {
		return super.findPage(page, ccmDomain);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmDomain ccmDomain) {
		super.save(ccmDomain);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmDomain ccmDomain) {
		super.delete(ccmDomain);
	}

	@Transactional(readOnly = false)
	public boolean updateDomain(CcmDomain ccmDomain) {
		//获取本级主节点的数据对象
		List<Area> areaUpper = areaService.getByCode(ccmDomain.getUpperAreaCode());
		if (areaUpper != null && areaUpper.size() != 1) {
			return false;
		}
		
		/**获取下级域中区域的数据**/
		//通过code获取下级域区域主节点的信息
		String urlArea = ccmDomain.getUrl() + "/app/rest/system/getAreaByCode?code=" + ccmDomain.getLowerAreaCode();
		String strContents = Tool.getRestReturn(urlArea);
		JSONObject jsonRet = (JSONObject) JSONObject.fromObject(strContents);
		if(jsonRet.isNullObject() || CcmRestType.OK != jsonRet.getInt("code")){
			return false;
		}
		String jsonContent = jsonRet.getString("result");
		JSONObject jsonObject = JSONObject.fromObject(jsonContent);
		Area areaMain = (Area) JSONObject.toBean(jsonObject, Area.class);
		areaMain.setCreateBy(new User("1"));
		areaMain.setUpdateBy(new User("1"));
		Area parent = new Area();
//		parent.setId(jsonObject.getString("parentId"));//Area实体中没有parentId
		parent.setId(areaUpper.get(0).getId());//Area实体中没有parentId
		areaMain.setParent(parent);
		
		//判断本级中是否存在下级的数据，若存在则isNewRecord=false
		boolean isNewRecord = true;
		List<Area> areaL = areaService.getByCode(areaMain.getCode());
		if (areaL != null && areaL.size() >= 1) {
			isNewRecord = false;
		}
		areaMain.setIsNewRecord(isNewRecord);
		areaService.save(areaMain);

		//通过下级域区域主节点ID获取其下面所有子节点的信息
		String urlAreaChildren = ccmDomain.getUrl() + "/app/rest/system/getAreaListByParentID?id=" + areaMain.getId();
		String strChildrenContents = Tool.getRestReturn(urlAreaChildren);
		JSONObject jsonChildren = (JSONObject) JSONObject.fromObject(strChildrenContents);
		if(jsonChildren.isNullObject() || CcmRestType.OK != jsonChildren.getInt("code")){
			return false;
		}
		/**将获取的区域数据循环插入到本级域中去**/
		JSONArray areaChildrenArray = (JSONArray) JSONObject.fromObject(jsonChildren).get("result");
		for ( int i = 0 ; i < areaChildrenArray.size(); i++) {
			Area areaChild = (Area) JSONObject.toBean((JSONObject)areaChildrenArray.get(i), Area.class);
			areaChild.setCreateBy(new User("1"));
			areaChild.setUpdateBy(new User("1"));
			Area parentArea = new Area();
			JSONObject obj = (JSONObject)areaChildrenArray.get(i);
			parentArea.setId(obj.getString("parentId"));
			areaChild.setParent(parentArea);
			//判断本级中是否存在下级的数据，若存在则isNewRecord=false
			boolean isNewData = true;
			Area area = areaService.get(areaChild.getId());
			if (area != null && area.getId() != null && !"".equals(area.getId())) {
				isNewData = false;
			}
			areaChild.setIsNewRecord(isNewData);
			areaService.save(areaChild);
		}

		//通过下级域区域主节点ID获取其下面所有子节点的辖区信息ccm_org_area
		String urlOrgAreaChildren = ccmDomain.getUrl() + "/app/rest/orgArea/getOrgAreaByParentID?areaId=" + areaMain.getId();
		String strOrgChildrenContents = Tool.getRestReturn(urlOrgAreaChildren);
		JSONObject jsonOrgChildren = (JSONObject) JSONObject.fromObject(strOrgChildrenContents);
		if(jsonOrgChildren.isNullObject() || CcmRestType.OK != jsonOrgChildren.getInt("code")){
			return false;
		}
		/**将获取的区域数据循环插入到本级域中去**/
		JSONArray areaOrgChildrenArray = (JSONArray) JSONObject.fromObject(jsonOrgChildren).get("result");
		for ( int i = 0 ; i < areaOrgChildrenArray.size(); i++) {
			CcmOrgArea orgAreaChild = (CcmOrgArea) JSONObject.toBean((JSONObject)areaOrgChildrenArray.get(i), CcmOrgArea.class);
			orgAreaChild.setCreateBy(new User("1"));
			orgAreaChild.setUpdateBy(new User("1"));
			Area refArea = new Area();
			refArea.setId(orgAreaChild.getAreaId());
			orgAreaChild.setArea(refArea);
			//判断本级中是否存在下级的数据，若存在则isNewRecord=false
			boolean isNewData = true;
			CcmOrgArea orgArea = ccmOrgAreaService.get(orgAreaChild.getId());
			if (orgArea != null && orgArea.getId() != null && !"".equals(orgArea.getId())) {
				isNewData = false;
			}
			orgAreaChild.setIsNewRecord(isNewData);
			ccmOrgAreaService.save(orgAreaChild);
		}
		
		
		return true;
	}
	
}