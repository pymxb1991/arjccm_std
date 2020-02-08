package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.pbs.common.config.Global;
import com.arjjs.ccm.tool.CommUtilRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 房屋接口类
 * 
 * @author fuxinshuang
 * @version 2018-02-03
 */
@Controller
@RequestMapping(value = "${appPath}/rest/house")
public class CcmRestHouse extends BaseController {

	@Autowired
	private CcmPopTenantService ccmPopTenantService;


	/**
	 * @see  获取单个房屋信息
	 * @param id  房屋ID
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》房屋id : " + id + "  userId : " + userId);
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (id == null || "".equals(id)) {//参数id不对
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		
		CcmPopTenant ccmPopTenant = ccmPopTenantService.get(id);
		String file = ccmPopTenant.getImage();
		if(StringUtils.isNotEmpty(file)) {
			ccmPopTenant.setImage(Global.getConfig("FILE_UPLOAD_URL")+ ccmPopTenant.getImage());
		}

		result.setCode(CcmRestType.OK);
		result.setResult(ccmPopTenant);
		
		return result;
	}
	
	/**
	 * @see  查询房屋信息
	 * @param houseBuild  房屋编号 
	 * @param housePlace  房屋地址
	 * @param houseName  房主姓名
	 * @param pageNo  页码
	 * @param pageSize  分页大小
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String buildingid,String userId,CcmPopTenant ccmPopTenant,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》buildingid : " + buildingid + "  userId : " + userId +"  ccmPopTenant : " + String.valueOf(ccmPopTenant));
		CcmRestResult result = new CcmRestResult();
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		ccmPopTenant.setCheckUser(sessionUser);
		ccmPopTenant.setBuildingId(new CcmHouseBuildmanage(buildingid));
		Page<CcmPopTenant> page = ccmPopTenantService
				.findPage(new Page<CcmPopTenant>(req, resp), ccmPopTenant);

		if(page.getList().size()>0){
			for(CcmPopTenant res : page.getList()){
				if(StringUtils.isNotEmpty(res.getImage())){
					res.setImage(Global.getConfig("FILE_UPLOAD_URL") + res.getImage());
				}
			}
		}
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	
	/**
	 * @see  修改房屋信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmPopTenant ccmPopTenant,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopTenant : " + String.valueOf(ccmPopTenant));
		CcmRestResult result = new CcmRestResult();
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		if (sessionUser== null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		String sessionUserId = sessionUser.getId();
		if (userId== null || "".equals(userId) ||!userId.equals(sessionUserId)) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			return result;
		}
		if (ccmPopTenant.getId()!= null && !"".equals(ccmPopTenant.getId())) {
			CcmPopTenant ccmPopTenantDB = ccmPopTenantService.get(ccmPopTenant.getId());
			if (ccmPopTenantDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
            //CommUtilRest.updateDataWithPart(ccmPopTenantDB,ccmPopTenant);
            if(StringUtils.isNotBlank(ccmPopTenant.getTranceId())){
            	ccmPopTenantDB.setTranceId(ccmPopTenant.getTranceId());
            }
			if(StringUtils.isNotBlank(ccmPopTenant.getHouseBuild())){
				ccmPopTenantDB.setHouseBuild(ccmPopTenant.getHouseBuild());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHousePlace())){
				ccmPopTenantDB.setHousePlace(ccmPopTenant.getHousePlace());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHousePrup())){
				ccmPopTenantDB.setHousePrup(ccmPopTenant.getHousePrup());
			}
			if(ccmPopTenant.getBuildingId() != null){
				if(StringUtils.isNotBlank(ccmPopTenant.getBuildingId().getId())){					
					ccmPopTenantDB.setBuildingId(ccmPopTenant.getBuildingId());
				}
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getBuildDoorNum())){
				ccmPopTenantDB.setBuildDoorNum(ccmPopTenant.getBuildDoorNum());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getBuildDoorName())){
				ccmPopTenantDB.setBuildDoorName(ccmPopTenant.getBuildDoorName());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getFloorNum())){
				ccmPopTenantDB.setFloorNum(ccmPopTenant.getFloorNum());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getDoorNum())){
				ccmPopTenantDB.setDoorNum(ccmPopTenant.getDoorNum());
			}
			if(ccmPopTenant.getHouseArea() != null){
				ccmPopTenantDB.setHouseArea(ccmPopTenant.getHouseArea());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getPropertyType())){
				ccmPopTenantDB.setPropertyType(ccmPopTenant.getPropertyType());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getBuildingYears())){
				ccmPopTenantDB.setBuildingYears(ccmPopTenant.getBuildingYears());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getBuildingType())){
				ccmPopTenantDB.setBuildingType(ccmPopTenant.getBuildingType());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHouseType())){
				ccmPopTenantDB.setHouseType(ccmPopTenant.getHouseType());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getIdenCode())){
				ccmPopTenantDB.setIdenCode(ccmPopTenant.getIdenCode());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getIdenNum())){
				ccmPopTenantDB.setIdenNum(ccmPopTenant.getIdenNum());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHouseName())){
				ccmPopTenantDB.setHouseName(ccmPopTenant.getHouseName());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHouseTl())){
				ccmPopTenantDB.setHouseTl(ccmPopTenant.getHouseTl());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHouseCur())){
				ccmPopTenantDB.setHouseCur(ccmPopTenant.getHouseCur());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getRentPur())){
				ccmPopTenantDB.setRentPur(ccmPopTenant.getRentPur());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getHazard())){
				ccmPopTenantDB.setHazard(ccmPopTenant.getHazard());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getTenantId())){
				ccmPopTenantDB.setTenantId(ccmPopTenant.getTenantId());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getTenantName())){
				ccmPopTenantDB.setTenantName(ccmPopTenant.getTenantName());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getTenantTl())){
				ccmPopTenantDB.setTenantTl(ccmPopTenant.getTenantTl());
			}
			if(ccmPopTenant.getArea() != null){
				if(StringUtils.isNotBlank(ccmPopTenant.getArea().getId())){					
					ccmPopTenantDB.setArea(ccmPopTenant.getArea());
				}
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getAreaMap())){
				ccmPopTenantDB.setAreaMap(ccmPopTenant.getAreaMap());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getAreaPoint())){
				ccmPopTenantDB.setAreaPoint(ccmPopTenant.getAreaPoint());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getImage())){
				String file = ccmPopTenant.getImage();
				if(StringUtils.isNotEmpty(file)) {
					if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
						ccmPopTenantDB.setImage(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
					}else {
						ccmPopTenantDB.setImage(file);
					}
				}
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getMore1())){
				ccmPopTenantDB.setMore1(ccmPopTenant.getMore1());
			}
			if(StringUtils.isNotBlank(ccmPopTenant.getCount())){
				ccmPopTenantDB.setCount(ccmPopTenant.getCount());
			}
			if (StringUtils.isNotEmpty(ccmPopTenant.getRemarks())) {
				ccmPopTenantDB.setRemarks(ccmPopTenant.getRemarks());
			}
			if(ccmPopTenant.getCheckUser() != null){
				if(StringUtils.isNotBlank(ccmPopTenant.getCheckUser().getId())){
					ccmPopTenantDB.setCheckUser(ccmPopTenant.getCheckUser());
				}
			}
			if(ccmPopTenant.getCurrentUser() != null){
				if(StringUtils.isNotBlank(ccmPopTenant.getCurrentUser().getId())){
					ccmPopTenantDB.setCurrentUser(ccmPopTenant.getCurrentUser());
				}
			}
			ccmPopTenant = ccmPopTenantDB;
		}else{
			ccmPopTenant.setAreaPoint("");
			ccmPopTenant.setAreaMap("");
			ccmPopTenant.setImage("");
		}
		if (ccmPopTenant.getCreateBy()== null) {
			ccmPopTenant.setCreateBy(new User(userId));
		}
		ccmPopTenant.setUpdateBy(new User(userId));
		ccmPopTenantService.save(ccmPopTenant);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	


	/**
	 * @see  保存房屋信息（支持新增和编辑,数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/saveSyn", method = RequestMethod.POST)
	public CcmRestResult saveSyn(String userId,CcmPopTenant ccmPopTenant,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopTenant : " + String.valueOf(ccmPopTenant));
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		ccmPopTenant.setCreateBy(user);
		ccmPopTenant.setUpdateBy(user);
		
		ccmPopTenantService.save(ccmPopTenant);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	/**
	 * @see  删除房屋信息（数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSyn", method = RequestMethod.POST)
	public CcmRestResult deleteSyn(String userId,CcmPopTenant ccmPopTenant,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》userId : " + userId +"  ccmPopTenant : " + String.valueOf(ccmPopTenant));
		CcmRestResult result = new CcmRestResult();
		ccmPopTenantService.delete(ccmPopTenant);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	

}