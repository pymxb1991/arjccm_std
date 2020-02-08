/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.unit.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.patrol.web.CcmPatrolMissionsController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.relief.entity.CcmReliefTask;
import com.arjjs.ccm.modules.flat.relief.service.CcmReliefTaskService;
import com.arjjs.ccm.modules.flat.unit.entity.CcmReliefUnit;
import com.arjjs.ccm.modules.flat.unit.service.CcmReliefUnitService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.MessageTools;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 备勤单位实体类Controller
 *
 * @author lgh
 * @version 2019-07-26
 */
@Controller
@RequestMapping(value = "${adminPath}/unit/ccmReliefUnit")
public class CcmReliefUnitController extends BaseController {

    @Autowired
    private CcmReliefUnitService ccmReliefUnitService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private CcmReliefTaskService ccmReliefTaskService;
    @Autowired
    private SystemService systemService;


    /**
     * 任务类型标识 自定义
     */
    private final String TYPE="spare_time_unit";

    @ModelAttribute
    public CcmReliefUnit get(@RequestParam(required = false) String id) {
        CcmReliefUnit entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = ccmReliefUnitService.get(id);
        }
        if (entity == null) {
            entity = new CcmReliefUnit();
        }
        return entity;
    }

    @RequiresPermissions("unit:ccmReliefUnit:view")
    @RequestMapping(value = {"list", ""})
    public String list(CcmReliefUnit ccmReliefUnit, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CcmReliefUnit> page = null;
        //如果是管理员所哟都能看到
        if (UserUtils.getUser().isAdmin()) {
            page = ccmReliefUnitService.findPage(new Page<>(request, response), ccmReliefUnit);
        } else {
            //如果是负责人能看到自己单位下的
            if (UserUtils.getUser().getOffice() != null && UserUtils.getUser().getId().equals(UserUtils.getUser().getOffice().getPrimaryPerson().getId())) {
                ccmReliefUnit.setMore1(UserUtils.getUser().getOffice().getId());
                page = ccmReliefUnitService.findPage(new Page<>(request, response), ccmReliefUnit);
            }
        }

        if (page==null) {
            return "flat/unit/ccmReliefUnitList";
        }
        page.getList().forEach(item -> item.setUserName(item.getUser().getId().indexOf(",") != -1 ?
                idToUserName(item.getUser().getId().split(",")) :
                item.getUser().getId().length() > 0 ? systemService.getUser(item.getUser().getId()).getName() : ""
        ));

        List<CcmReliefUnit> list = page.getList();
        for (CcmReliefUnit reliefUnit : list) {
            if (StringUtils.isNotEmpty(reliefUnit.getMissionsId())) {
            	CcmReliefTask ccmReliefTask = ccmReliefTaskService.get(reliefUnit.getMissionsId());
            	if(ccmReliefTask != null) {
            		 reliefUnit.setMissionsId(ccmReliefTask.getTaskName());
            	}
            }
        }
        page.setList(list);
        model.addAttribute("page", page);
        return "flat/unit/ccmReliefUnitList";
    }

    @RequiresPermissions("unit:ccmReliefUnit:view")
    @RequestMapping(value = "form")
    public String form(CcmReliefUnit ccmReliefUnit, Model model) {

        CcmReliefUnit ccmReliefUnit1 = new CcmReliefUnit();
        ccmReliefUnit1.setMissionsId(ccmReliefUnit.getId());
        List<CcmReliefUnit> list = ccmReliefUnitService.findList(ccmReliefUnit1);

        if (list.size() > 0) {
            list.forEach(item -> item.setUserName(item.getUser().getId().indexOf(",") != -1 ?
                    idToUserName(item.getUser().getId().split(",")) :
                    item.getUser().getId().length() > 0 ? systemService.getUser(item.getUser().getId()).getName() : ""
            ));

            model.addAttribute("isCcmReliefUnit", "yes");
            model.addAttribute("ccmReliefUnit", list.get(0));
            model.addAttribute("id", ccmReliefUnit.getId());
        } else {
            ccmReliefUnit.setMissionsId(ccmReliefUnit.getId());//！！！把task的ID传了过来但是是放在了unit的id里传过来的
            model.addAttribute("isCcmReliefUnit", "not");
            model.addAttribute("ccmReliefUnit", ccmReliefUnit);
            model.addAttribute("id", ccmReliefUnit.getId());
        }

        return "flat/unit/ccmReliefUnitForm";
    }

    @RequiresPermissions("unit:ccmReliefUnit:view")
    @RequestMapping(value = "form2")
    public String form2(CcmReliefUnit ccmReliefUnit, Model model) {
        CcmReliefUnit ccmReliefUnit1 = new CcmReliefUnit();
        ccmReliefUnit1.setMissionsId(ccmReliefUnit.getId());
        List<CcmReliefUnit> list = ccmReliefUnitService.findList(ccmReliefUnit1);

        if (list.size() > 0) {
            list.forEach(item -> item.setUserName(item.getUser().getId().indexOf(",") != -1 ?
                    idToUserName(item.getUser().getId().split(",")) :
                    item.getUser().getId().length() > 0 ? systemService.getUser(item.getUser().getId()).getName() : ""
            ));
            model.addAttribute("ccmReliefUnit", list.get(0));
            model.addAttribute("id", ccmReliefUnit.getId());
        } else {
            ccmReliefUnit.setUserName(ccmReliefUnit.getUser().getName());
            model.addAttribute("ccmReliefUnit", ccmReliefUnit);
            model.addAttribute("id", ccmReliefUnit.getId());
        }
        return "flat/unit/ccmReliefUnitForm2";
    }

    @RequiresPermissions("unit:ccmReliefUnit:edit")
    @RequestMapping(value = "savedetail")
    public String saveDetail(CcmReliefUnit ccmReliefUnit, RedirectAttributes redirectAttributes) {
        ccmReliefUnitService.save(ccmReliefUnit);
        addMessage(redirectAttributes, "保存备勤单位分配成功");
        return "redirect:" + Global.getAdminPath() + "/unit/ccmReliefUnit/?repage";
    }

    @RequiresPermissions("unit:ccmReliefUnit:edit")
    @RequestMapping(value = "save")
    public void save(CcmReliefUnit ccmReliefUnit, RedirectAttributes redirectAttributes) {

        String userIds = ccmReliefUnit.getUser().toString();
        String[] userIdArr = userIds.split(",");
        for (int i = 0; i <userIdArr.length ; i++) {
            ccmReliefUnit.setId(null);
            //任务状态进行中
            ccmReliefUnit.setStatus(CcmPatrolMissionsController.PROCESSING_TASK);
            ccmReliefUnit.setDepartureTime(new Date());
            ccmReliefUnit.setMore1(UserUtils.get(userIdArr[i]).getOffice().getId());
            ccmReliefUnit.setUser(UserUtils.get(userIdArr[i]));
            ccmReliefUnitService.save(ccmReliefUnit);
            //通知到手机APP
            Map<String, String> map = Maps.newHashMap();
            map.put("type",TYPE);
            map.put("id", ccmReliefUnit.getId());
            map.put("name", ccmReliefUnit.getMissionName());
            MessageTools.sendAppMsgByUserId(userIdArr[i], map);
        }
        addMessage(redirectAttributes, "保存备勤单位成功");
    }

    @RequiresPermissions("unit:ccmReliefUnit:edit")
    @RequestMapping(value = "delete")
    public String delete(CcmReliefUnit ccmReliefUnit, RedirectAttributes redirectAttributes) {
        ccmReliefUnitService.delete(ccmReliefUnit);
        addMessage(redirectAttributes, "删除备勤单位成功");
        return "redirect:" + Global.getAdminPath() + "/unit/ccmReliefUnit/?repage";
    }


    /**
     * 获取单位的人员树
     *
     * @return
     */
    @RequestMapping(value = "getTreeData")
    @ResponseBody
    public List<Map<String, Object>> getTreeData(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        CcmReliefTask ccmReliefTask = ccmReliefTaskService.get(id);
        if (EntityTools.isEmpty(ccmReliefTask)) {
            return null;
        }

        String officeIds = ccmReliefTask.getReliefDept();
        //部门的ids
        String split[] = null;
        if (UserUtils.getUser().isAdmin()) {
            if (officeIds.indexOf(",") != -1) {
                split = officeIds.split(",");
            } else if (officeIds.length() > 0) {
                split = new String[1];
                split[0] = officeIds;
            } else {
                return null;
            }
        } else {
            split = new String[1];
                split[0] = UserUtils.getUser().getOffice().getId();
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

    /**
     * 如果为null 则没有权限查询
     */
    private String idToUserName(String[] strings) {
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {

            User user = systemService.getUser(strings[i]);
            if (user == null) {
                continue;
            }
            if (name.length() > 0) {
                name.append(",");
            }
            name.append(user.getName());
        }
        return name.toString();
    }

}