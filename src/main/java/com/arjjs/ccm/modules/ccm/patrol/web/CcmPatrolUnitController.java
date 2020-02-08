/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolMissionsService;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceUserService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.MessageTools;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUnit;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUnitService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 巡逻单位Controller
 * @author lijiupeng
 * @version 2019-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolUnit")
public class CcmPatrolUnitController extends BaseController {

	@Autowired
	private CcmPatrolUnitService ccmPatrolUnitService;

	@Autowired
	private CcmPatrolMissionsService ccmPatrolMissionsService;

	@Autowired
	private OfficeService officeService;

	@Autowired
	private SystemService systemService;



    /**
     * 任务类型标识 自定义
     */
    private final String TYPE="patrol_time_unit";

	@ModelAttribute
	public CcmPatrolUnit get(@RequestParam(required=false) String id) {
		CcmPatrolUnit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPatrolUnitService.get(id);
		}
		if (entity == null){
			entity = new CcmPatrolUnit();
		}
		return entity;
	}
	
	@RequiresPermissions("patrol:ccmPatrolUnit:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPatrolUnit ccmPatrolUnit, HttpServletRequest request, HttpServletResponse response,String sid, Model model) {
		User user = UserUtils.getUser();

		Page<CcmPatrolUnit> page=null;
		if(user.getId().equals(officeService.get(user.getOffice().getId()).getPrimaryPerson().getId())){

			if(StringUtils.isNotBlank(sid)){
				ccmPatrolUnit.setMissionsIds(new String[]{sid});
				page=ccmPatrolUnitService.findPage(new Page<>(request, response), ccmPatrolUnit);
			}else{

			CcmPatrolMissions ccmPatrolMissions = new CcmPatrolMissions();
			ccmPatrolMissions.setStatus("2");
			ccmPatrolMissions.setOffice(user.getOffice().getId());
			if (!UserUtils.getUser().isAdmin()) {
				Page<CcmPatrolMissions> tempPage = ccmPatrolMissionsService.findPage(new Page<>(request, response), ccmPatrolMissions);
				Set<String> collect = tempPage.getList().stream().map(item -> item.getId()).collect(Collectors.toSet());
				String[] ids = collect.toArray(new String[collect.size()]);
				if (ids.length > 0) {
					ccmPatrolUnit.setMissionsIds(ids);
				} else {
					ccmPatrolUnit.setMissionsIds(new String[]{"-10000"});
				}
			}
			page=ccmPatrolUnitService.findPage(new Page<>(request, response), ccmPatrolUnit);
			}

		}else {
			ccmPatrolUnit.setUser(new User(user.getId()));
			page = ccmPatrolUnitService.findPage(new Page<CcmPatrolUnit>(request, response), ccmPatrolUnit);
		}


		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolUnitList";
	}


	/**
	 *如果为null 则没有权限查询
	 */
	private String idToUserName(String [] strings){
		StringBuffer name=new StringBuffer();
		for (int i = 0; i < strings.length; i++) {

			User user = systemService.getUser(strings[i]);
			if(user==null){
				continue;
			}
			if(name.length()>0){
				name.append(",");
			}
			name.append(user.getName());
		}
		return name.toString();
	}

	@RequiresPermissions("patrol:ccmPatrolUnit:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolUnit ccmPatrolUnit, Model model) {
		CcmPatrolUnit ccmPatrolUnit1 = new CcmPatrolUnit();
		ccmPatrolUnit1.setMissions(new CcmPatrolMissions(ccmPatrolUnit.getId()));
		List<CcmPatrolUnit> list = ccmPatrolUnitService.findList(ccmPatrolUnit1);
		if(list.size()>0){
			model.addAttribute("isccmPatrolUnit", "yes");
			model.addAttribute("ccmPatrolUnit", list.get(0));
			model.addAttribute("id",ccmPatrolUnit.getId());
		}else{
			model.addAttribute("isccmPatrolUnit", "not");
			model.addAttribute("ccmPatrolUnit", ccmPatrolUnit);
			model.addAttribute("id",ccmPatrolUnit.getId());
		}
		return "ccm/patrol/ccmPatrolUnitForm";
	}
	@RequiresPermissions("patrol:ccmPatrolUnit:view")
	@RequestMapping(value = "form2")
	public String form2(CcmPatrolUnit ccmPatrolUnit, Model model) {
		model.addAttribute("id",ccmPatrolUnit.getMissions().getId());
		model.addAttribute("ccmPatrolUnit", ccmPatrolUnit);
		return "ccm/patrol/ccmPatrolUnitForm2";
	}

	@RequiresPermissions("patrol:ccmPatrolUnit:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolUnit ccmPatrolUnit, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, ccmPatrolUnit)){
//			return form(ccmPatrolUnit, model);
//		}

		if(ccmPatrolUnitService.get(ccmPatrolUnit.getId()) ==null){

		    //巡逻任务的id 在 ccmPatrolUnit的id里 转换回来
            ccmPatrolUnit.setMissions(new CcmPatrolMissions(ccmPatrolUnit.getId()));

			//修改任务状态 2是 ccm_patrol_missions_status 里的进行中
				int i = ccmPatrolMissionsService.updateStatus("2", ccmPatrolUnit.getMissions().getId());
				String userId = ccmPatrolUnit.getUserIds();

					String[] split = userId.split(",");

					for (int i1 = 0; i1 < split.length; i1++) {
						ccmPatrolUnit.setId(null);
						ccmPatrolUnit.setUser(new User(split[i1]));
						ccmPatrolUnit.setStatus(CcmPatrolMissionsController.PROCESSING_TASK);
						ccmPatrolUnit.setDepartureTime(new Date());
						ccmPatrolUnitService.save(ccmPatrolUnit);

                        //通知到手机APP
                        Map<String, String> map = Maps.newHashMap();
                        map.put("type",TYPE);
                        map.put("id", ccmPatrolUnit.getId());
                        map.put("name", ccmPatrolMissionsService.get(ccmPatrolUnit.getMissions().getId()).getPatrolContent());
                        if(MessageTools.sendAppMsgByUserId(split[i], map)){
                            logger.info("巡逻单位任务发送app通知成功：id"+ccmPatrolUnit.getId());
                        }else{
                            logger.error("巡逻单位任务发送app通知失败：id"+ccmPatrolUnit.getId());
                        }
					}

		}else{
			ccmPatrolUnitService.save(ccmPatrolUnit);
		}
		addMessage(redirectAttributes, "保存巡逻单位成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolUnit/?repage";
	}

	/**
	 * 获取单位的人员树
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String, Object>> getTreeData(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		CcmPatrolMissions ccmPatrolMissions = ccmPatrolMissionsService.get(id);
		if(EntityTools.isEmpty(ccmPatrolMissions)){
			return null;
		}

		String officeIds = ccmPatrolMissions.getOffice();
		//部门的ids
		String split []=null;
		if(officeIds.indexOf(",")!=-1){
			 split =officeIds.split(",");
		}else if (officeIds.length()>0){
			split=new String[1];
			split[0]=officeIds;
		}else{
			return null;
		}

		List<Map<String, Object>> mapList = Lists.newArrayList();
		//数据填进去
		for(int i = 0; i < split.length; ++i) {
			Office e = this.officeService.get(split[i]);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParent().getId());
			map.put("name", e.getName());
			if (officeIds.indexOf(e.getId())!=-1) {
				map.put("isParent", true);
			}
			mapList.add(map);

			List<User> userByOfficeId = systemService.findUserByOfficeId(e.getId());

			userByOfficeId.forEach(item->{
				Map<String, Object> tempMap = Maps.newHashMap();
				tempMap.put("id",item.getId());
				tempMap.put("pId", e.getId());
				tempMap.put("name",item.getName());
				mapList.add(tempMap);
			});
//			}
		}


		return mapList;
	}

	
	@RequiresPermissions("patrol:ccmPatrolUnit:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolUnit ccmPatrolUnit, RedirectAttributes redirectAttributes) {
		ccmPatrolUnitService.delete(ccmPatrolUnit);
		addMessage(redirectAttributes, "删除巡逻单位成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolUnit/?repage";
	}

}