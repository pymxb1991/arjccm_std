package com.arjjs.ccm.modules.flat.realtimeSituation.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.realtimeSituation.entity.DataList;
import com.arjjs.ccm.modules.flat.realtimeSituation.service.RealtimeSituationService;
import com.arjjs.ccm.tool.LayUIBean;
import com.arjjs.ccm.tool.geoJson.GeoJSON;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Controller
@RequestMapping(value = "${adminPath}/flat/realtimeSituation")
public class RealtimeSituationController extends BaseController {

	@Autowired
	private RealtimeSituationService realtimeSituationService;
	
	@RequiresPermissions("flat:realtimeSituation:view")
	@RequestMapping(value = "realtimeSituation")
	public String realtimeSituation(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "flat/realtimeSituation/realtimeSituationList";
	}
	
	@RequestMapping(value = "hotspotAnalysis")
	public String hotspotAnalysis(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "flat/home/bphHotspotAnalysisList";
	}
	
	@ResponseBody
	@RequestMapping(value = "findCircumData")
	public void findCircumData(HttpServletRequest request, HttpServletResponse response,DataList dataList) throws IOException{
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
		DataList findDdataList = realtimeSituationService.findList(dataList);
		response.getWriter().print(JSONObject.fromObject(findDdataList,jsonConfig));
	}
	
	@RequestMapping(value = "findCarDevice")
	public void getCarDevice(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().print(realtimeSituationService.getCarDevice());
	}
	
	@ResponseBody
	@RequestMapping(value = "findPeopleInfo")
	public GeoJSON findPeopleInfo(HttpServletRequest request, HttpServletResponse response) {
		return realtimeSituationService.findPeopleInfo();
	}
	
	/**
	 * @desc 地图警情派警查询警员列表
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryPolice")
	public LayUIBean queryPolice(DataList dataList) {
		return realtimeSituationService.queryPolice(dataList);
	}
}
