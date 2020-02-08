package com.arjjs.ccm.modules.flat.alarmdistribute.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.LayUIBean;
import com.arjjs.ccm.tool.Result;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Controller
@RequestMapping(value = "${adminPath}/alarmdistribute/bphAlarmDistribute")
public class BphAlarmDistributeController {
	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 方法说明：警情分发查询接口
	 * 
	 * @param bphAlarmInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "list")
	public LayUIBean list(BphAlarmInfo bphAlarmInfo, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		LayUIBean result = new LayUIBean();
		List<BphAlarmInfo> data = bphAlarmInfoService.findDistributeList(bphAlarmInfo);
		Integer num = bphAlarmInfoService.countDistributeList(bphAlarmInfo);
		result.setCount(num);
		result.setData(data);
		result.setCode(Result.ERROR_OK);
		result.setMsg("");
		return result;
	}
	
	/**
	 * 方法说明：查询全部数量
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "Count")
	public void Count(HttpServletRequest request, HttpServletResponse response,String beginAlarmTime,String endAlarmTime,String area,String state) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		BphAlarmInfo bphAlarmInfo = new BphAlarmInfo();
		bphAlarmInfo.setBeginAlarmTime(DateUtils.parseDate(beginAlarmTime));
		bphAlarmInfo.setEndAlarmTime(DateUtils.parseDate(endAlarmTime));
		bphAlarmInfo.setAreaId(area);
		bphAlarmInfo.setState(state);
		response.getWriter().print(JSONObject.fromObject(bphAlarmInfoService.count(bphAlarmInfo), jsonConfig));
	}

	/**
	 * 方法说明：按部门查询数量
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "byOfficeCount")
	public void byOfficeCount(HttpServletRequest request, HttpServletResponse response,String beginAlarmTime,String endAlarmTime,String area,String state) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		BphAlarmInfo bphAlarmInfo = new BphAlarmInfo();
		bphAlarmInfo.setBeginAlarmTime(DateUtils.parseDate(beginAlarmTime));
		bphAlarmInfo.setEndAlarmTime(DateUtils.parseDate(endAlarmTime));
		bphAlarmInfo.setAreaId(area);
		bphAlarmInfo.setState(state);
		response.getWriter().print(JSONArray.fromObject(bphAlarmInfoService.byOfficeCount(bphAlarmInfo), jsonConfig));
	}

	/**
	 * 方法说明：根据id查询警情详情信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "alarm")
	public void get(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONObject.fromObject(bphAlarmInfoService.get(id), jsonConfig));
	}
	
	/**
	 * 方法说明：警情派发到派出所
	 * @param request
	 * @param response
	 * @param bphAlarmInfo
	 * @throws IOException
	 */
	@RequestMapping(value = "updateBphAlarmDistribute")
	public void updateBphAlarmDistribute(HttpServletRequest request, HttpServletResponse response,BphAlarmInfo bphAlarmInfo) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser")
						|| name.equals("page") || name.equals("sqlMap") || name.equals("global")
						|| name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		Office office = officeService.get(bphAlarmInfo.getOfficeId());
		bphAlarmInfo.setOffice(office);
		bphAlarmInfo.setUpdateBy(UserUtils.getUser());
		bphAlarmInfo.setCreateDate(new Date());
		response.getWriter().print(JSONObject.fromObject(bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo), jsonConfig));	
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("user")
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = bphAlarmInfoService.findOfficeAllList(type);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParent().getId());
			map.put("pIds", e.getParentIds());
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}
}
