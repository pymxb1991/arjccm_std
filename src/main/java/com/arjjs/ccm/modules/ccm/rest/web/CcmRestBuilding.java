package com.arjjs.ccm.modules.ccm.rest.web;

import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildentrance;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildentranceVo;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenantVo;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.TransGPS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 楼栋接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${appPath}/rest/building")
@Api(description = "楼栋接口相关")
public class CcmRestBuilding extends BaseController {

	@Autowired
	private CcmHouseBuildmanageService ccmHouseBuildmanageService;
	@Autowired
    private CcmPopTenantService ccmPopTenantService;
	@Autowired
    private CcmPeopleService ccmPeopleService;




	/**
	 * @see  获取单个楼栋信息
	 * @param id  楼栋ID
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-02
	 */
	@ResponseBody
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId,HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》id : " + id + "  userId : " + userId);
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
		String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		CcmHouseBuildmanage ccmHouseBuild = ccmHouseBuildmanageService.get(id);
		if(StringUtils.isNotEmpty(ccmHouseBuild.getImages())){
			ccmHouseBuild.setImages(fileUrl + ccmHouseBuild.getImages());
		}
		result.setCode(CcmRestType.OK);
		result.setResult(ccmHouseBuild);
		
		return result;
	}

	/**
	 * @see  查询楼栋信息
	 * @param name  小区（单位）名称
	 * @param buildname  楼栋名称
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId,CcmHouseBuildmanage build,HttpServletRequest req, HttpServletResponse resp,Integer pageNo) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》CcmHouseBuildmanage : " + String.valueOf(build) + "  userId : " + userId);
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
		build.setCheckUser(sessionUser);
		Page<CcmHouseBuildmanage> page = ccmHouseBuildmanageService
				.findPage(new Page<CcmHouseBuildmanage>(req, resp), build);
		String fileUrl = Global.getConfig("FILE_UPLOAD_URL");
		if(page.getList().size()>0){
			for (int i = 0; i < page.getList().size(); i++) {
				page.getList().get(i).setImages(fileUrl + page.getList().get(i).getImages());
			}
		}
		page.setPageNo(pageNo);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		
		return result;
	}
	
	
	/**
	 * @see  保存楼栋信息
	 * @param 
	 * @return 
	 * @author fuxinshuang
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId,CcmHouseBuildmanage build,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》CcmHouseBuildmanage : " + String.valueOf(build) + "  userId : " + userId);
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
//		if (build.getId()!= null && !"".equals(build.getId())) {
//			CcmHouseBuildmanage buildDB = ccmHouseBuildmanageService.get(build.getId());
//			if (buildDB == null ) {//从数据库中没有取到对应数据
//				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
//				return result;
//			}
//			build.setAreaPoint(buildDB.getAreaPoint());
//			build.setAreaMap(buildDB.getAreaMap());
//			build.setImage(buildDB.getImage());
//		}/*else{
//			build.setAreaPoint("");
//			build.setAreaMap("");
//			build.setImage("");
//		}*/
		if (build.getCreateBy()== null) {
			build.setCreateBy(new User(userId));
		}
		build.setUpdateBy(new User(userId));


		// 判断是否为新增
        if (StringUtils.isEmpty(build.getId())) {   //  新增
            // 坐标经纬度的转换（GCJ坐标 -> WGS-84坐标）
            if (StringUtils.isNotEmpty(build.getAreaPoint())) {
                String piont = build.getAreaPoint();
                String areaPiont = "";
                String[] pionts = piont.split(",");

                TransGPS ins = new TransGPS();
                TransGPS.Location wcj = ins.new Location();
                wcj.setLat(Double.parseDouble(pionts[1]));
                wcj.setLng(Double.parseDouble(pionts[0]));

                TransGPS.Location wgs = ins.transformFromGCJToWGS(wcj);
                areaPiont = wgs.getLng() + ","  + wgs.getLat();
                build.setAreaPoint(areaPiont);
                build.setAreaMap(areaPiont);
            }
        }else {
        	CcmHouseBuildmanage ccmHouseBuild = ccmHouseBuildmanageService.get(build.getId());
        	if(StringUtils.isNotEmpty(build.getAreaPoint())) {
        		if(build.getAreaPoint().equals(ccmHouseBuild.getAreaPoint())) {
        			build.setAreaMap(build.getAreaPoint());
        		}else {
        			String piont = build.getAreaPoint();
                    String areaPiont = "";
                    String[] pionts = piont.split(",");

                    TransGPS ins = new TransGPS();
                    TransGPS.Location wcj = ins.new Location();
                    wcj.setLat(Double.parseDouble(pionts[1]));
                    wcj.setLng(Double.parseDouble(pionts[0]));

                    TransGPS.Location wgs = ins.transformFromGCJToWGS(wcj);
                    areaPiont = wgs.getLng() + ","  + wgs.getLat();
                    build.setAreaPoint(areaPiont);
                    build.setAreaMap(areaPiont);
        		}
        	}
        }

		String file = build.getImages();
		if(StringUtils.isNotEmpty(file)) {
			if(file.contains(Global.getConfig("FILE_UPLOAD_URL"))) {
				build.setImages(file.split(Global.getConfig("FILE_UPLOAD_URL"))[1]);
			}else {
				build.setImages(file);
			}
		}
		ccmHouseBuildmanageService.save(build);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}


	/**
	 * @see  保存楼栋信息（支持新增和编辑,数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-12
	 */
	@ResponseBody
	@RequestMapping(value="/saveSyn", method = RequestMethod.POST)
	public CcmRestResult saveSyn(String userId,CcmHouseBuildmanage build,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》CcmHouseBuildmanage : " + String.valueOf(build) + "  userId : " + userId);
		CcmRestResult result = new CcmRestResult();
		if (userId == null || "".equals(userId)) {
			userId = "1";
		}
		User user = new User(userId);
		build.setCreateBy(user);
		build.setUpdateBy(user);

		ccmHouseBuildmanageService.save(build);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

	// 根据楼栋ID获取单元列表
	@ResponseBody
	@RequestMapping(value = "getBuildentrance", method = RequestMethod.GET)
	@ApiOperation(value = "单元信息查询（buildId 不为空，查询楼栋下单元列表，tranceId 不为空，查询单元详情）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "buildId", value = "楼栋 id", paramType = "query"),
			@ApiImplicitParam(name = "tranceId", value = "单元ID id", paramType = "query")})
	public CcmRestResult getBuildentrance(String buildId,String tranceId) {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》楼栋 id : " + buildId + "  单元ID : " + tranceId);
		CcmRestResult result = new CcmRestResult();
		List<CcmHouseBuildentranceVo> buildentranceList = ccmHouseBuildmanageService.selectBuildentranceById(buildId,tranceId);
        for(CcmHouseBuildentranceVo res : buildentranceList){
            // 赋值单元下房屋数量
            List<CcmPopTenant> popTenants = ccmPopTenantService.findByTranceId(res.getId());
            if (null != popTenants && 0 != popTenants.size()) {
                res.setHouseNum(popTenants.size());
                // 赋值单元下人员数量
                int total = 0;
                for (CcmPopTenant popTenant : popTenants) {
                    List<CcmPeople> peopleList = ccmPeopleService.findByRoomId(popTenant.getId());
                    total = total + peopleList.size();
                }
                res.setResidentNum(total);
            }
        }
		result.setCode(CcmRestType.OK);
		result.setResult(buildentranceList);
		return result;
	}

	// 查询单元下房屋列表
	@ResponseBody
	@RequestMapping(value = "getPopTenant", method = RequestMethod.GET)
	@ApiOperation(value = "查询单元下房屋列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "buildId", value = "楼栋id",  paramType = "query" ,required = true),
			@ApiImplicitParam(name = "tranceId", value = "单元ID ", paramType = "query"  ,required = true)})
	public CcmRestResult getPopTenant(String buildId, String tranceId) {
		Integer doorNum = null;
		List<CcmHouseBuildentrance> trancelist = ccmHouseBuildmanageService.findBuildentrance(buildId);
		for (int i = 0; i < trancelist.size(); i++) {
			if(tranceId.equals(trancelist.get(i).getId())){
				doorNum = trancelist.get(i).getEntranceNum();
			}
		}
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》楼栋id : " + buildId + "  单元ID : " + tranceId);
		CcmRestResult result = new CcmRestResult();
		List<CcmPopTenantVo> buildentranceList = ccmHouseBuildmanageService.selectPopTenantByBuildAndDoorNum(buildId,doorNum.toString());
		for(CcmPopTenantVo res : buildentranceList){
			CcmHouseBuildmanage ccmHouseBuildmanage = ccmHouseBuildmanageService.get(res.getBuildingId());
			res.setBuildingName(ccmHouseBuildmanage!=null ? ccmHouseBuildmanage.getBuildname() : "");
		}

		result.setCode(CcmRestType.OK);
		result.setResult(buildentranceList);
		return result;
	}
	/**
	 * @see  删除楼栋信息（数据同步用）
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-17
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSyn", method = RequestMethod.POST)
	public CcmRestResult deleteSyn(String userId,CcmHouseBuildmanage build,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》CcmHouseBuildmanage : " + String.valueOf(build) + "  userId : " + userId);
		CcmRestResult result = new CcmRestResult();
		ccmHouseBuildmanageService.delete(build);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}
	
	/**
	 * @see  删除楼栋信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-05-12
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public CcmRestResult delete(String userId,CcmHouseBuildmanage build,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("当前正在执行的类名为》》》"+Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("当前正在执行的方法名为》》》"+Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("当前方法运行参数为》》》CcmHouseBuildmanage : " + String.valueOf(build) + "  userId : " + userId);
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
		if (build.getId()!= null && !"".equals(build.getId())) {
			CcmHouseBuildmanage buildDB = ccmHouseBuildmanageService.get(build.getId());
			if (buildDB == null ) {//从数据库中没有取到对应数据
				result.setCode(CcmRestType.ERROR_DB_NOT_EXIST);
				return result;
			}
		}
		ccmHouseBuildmanageService.delete(build);
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
		
	}

}