/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmLiveVideo;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmDeviceService;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmLiveVideoService;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmMapCollectZtree;
import com.arjjs.ccm.modules.ccm.tree.service.CcmMapCollectZtreeService;
import com.arjjs.ccm.modules.kpi.special.entity.KpiSpecialScore;

/**
 * 视频监控Controller
 * @author pengjianqiang
 * @version 2018-01-25
 */
@Controller
@RequestMapping(value = "${adminPath}/ccmsys/ccmLiveVideo")
public class CcmLiveVideoController extends BaseController {

	@Autowired
	private CcmLiveVideoService ccmLiveVideoService;

	@Autowired
	private CcmDeviceService ccmDeviceService;
	
	@Autowired
	private CcmMapCollectZtreeService ccmMapCollectZtreeService;
	
	@ModelAttribute
	public CcmLiveVideo get(@RequestParam(required=false) String id) {
		CcmLiveVideo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmLiveVideoService.get(id);
		}
		if (entity == null){
			entity = new CcmLiveVideo();
		}
		return entity;
	}
	
	@RequiresPermissions("ccmsys:ccmLiveVideo:view")
	@RequestMapping(value = {"index", ""})
	public String index(CcmLiveVideo ccmLiveVideo, Model model) {
		return "ccm/ccmsys/ccmLiveVideoIndex";
	}
	

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmDevice> list = ccmLiveVideoService.findAreaDeviceList(new CcmDevice());
		for (int i=0; i<list.size(); i++){
			CcmDevice e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				map.put("type", e.getTypeId());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@RequiresPermissions("ccmsys:ccmLiveVideo:view")
	@RequestMapping(value = {"vedioPlay", ""})
	public String vedioPlay(CcmDevice ccmDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		CcmDevice ccmDeviceInfo = new CcmDevice();
		if (ccmDevice != null && !"".equals(ccmDevice.getId())) {
			ccmDeviceInfo = ccmDeviceService.get(ccmDevice); 
		}
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		CcmMapCollectZtree ccmMapCollectZtree = new CcmMapCollectZtree();
		ccmMapCollectZtree.setType("camera");
		List<CcmMapCollectZtree> list = ccmMapCollectZtreeService.findList(ccmMapCollectZtree); 
		for(int i=0;i<list.size();i++) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", list.get(i).getExtend1());
			map.put("name", list.get(i).getName());
			mapList.add(map);
		}
		model.addAttribute("mapList", JSONArray.fromObject(mapList));
		model.addAttribute("ccmDevice", ccmDeviceInfo);
		return "ccm/ccmsys/ccmLiveVideoPlay";
	}
	
	
	@RequestMapping(value = {"getvedioPlay"})
	@ResponseBody
	public List<CcmDevice> getvedioPlay(String param, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CcmDevice> reslist = Lists.newArrayList();
		if(param!=null) {
			String newJson = StringEscapeUtils.unescapeHtml4(param);
			JSONArray arr = JSONArray.fromObject(newJson);
			for(int i=0;i<arr.size();i++) {
				JSONObject object = (JSONObject) arr.get(i);
				CcmDevice ccmDevice = new CcmDevice();
				ccmDevice.setId((String)object.get("id"));
				reslist.add(ccmDeviceService.get(ccmDevice));
			}
		}
		return reslist;
	}
	

}