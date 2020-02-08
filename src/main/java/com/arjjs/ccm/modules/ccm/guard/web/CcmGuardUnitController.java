/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.guard.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.patrol.web.CcmPatrolMissionsController;
import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;
import com.arjjs.ccm.modules.ccm.security.service.CcmPatrolSecurityService;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.JobDataMap;
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
import com.arjjs.ccm.modules.ccm.guard.entity.CcmGuardUnit;
import com.arjjs.ccm.modules.ccm.guard.service.CcmGuardUnitService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 警卫单位Controller
 * @author lijiupeng
 * @version 2019-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/guard/ccmGuardUnit")
public class CcmGuardUnitController extends BaseController {

    @Autowired
    private CcmGuardUnitService ccmGuardUnitService;

    @Autowired
    private CcmPatrolSecurityService ccmPatrolSecurityService;
    @Autowired
    private OfficeService officeService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private OaNotifyService oaNotifyService;

    /**
     * 任务类型标识 自定义
     */
    private final String TYPE="guard_time_task";
    /**
     * 定时任务组的名称
     */
    final String GROUP_NAME = "ccmPatrolSecurity";

    @ModelAttribute
    public CcmGuardUnit get(@RequestParam(required=false) String id) {
        CcmGuardUnit entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = ccmGuardUnitService.get(id);
        }
        if (entity == null){
            entity = new CcmGuardUnit();
        }
        return entity;
    }

    @RequiresPermissions("guard:ccmGuardUnit:view")
    @RequestMapping(value = {"list", ""})
    public String list(CcmGuardUnit ccmGuardUnit,String sid, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(StringUtils.isNotBlank(sid)){
            ccmGuardUnit.setSecurity(new CcmPatrolSecurity(sid));
        }
        Page<CcmGuardUnit> page = ccmGuardUnitService.findPage(new Page<CcmGuardUnit>(request, response), ccmGuardUnit);
        model.addAttribute("page", page);
        return "ccm/guard/ccmGuardUnitList";
    }

    @RequiresPermissions("guard:ccmGuardUnit:view")
    @RequestMapping(value = "form")
    public String form(CcmGuardUnit ccmGuardUnit, Model model) {

        model.addAttribute("ccmGuardUnit", ccmGuardUnit);
        return "ccm/guard/ccmGuardUnitForm";
    }

    @RequiresPermissions("guard:ccmGuardUnit:edit")
    @RequestMapping(value = "save")
    public void save(CcmGuardUnit ccmGuardUnit, Model model,HttpServletResponse response) {


        //不知道为什么从前台传过来最后面多个 ,
        if (ccmGuardUnit.getSecurity().getId().indexOf(",")!=-1) {
            ccmGuardUnit.getSecurity().setId(ccmGuardUnit.getSecurity().getId().split(",")[0]);
        }

        int i = ccmPatrolSecurityService.updateStatus("2", ccmGuardUnit.getSecurity().getId());
        String userId = ccmGuardUnit.getUserIds();
        //判断是修改是保存
        if(ccmGuardUnitService.get(ccmGuardUnit.getId())==null){
            String[] split = userId.split(",");
            CcmPatrolSecurity ccmPatrolSecurity = ccmPatrolSecurityService.get(ccmGuardUnit.getSecurity().getId());
            for (int i1 = 0; i1 < split.length; i1++) {
                ccmGuardUnit.setId(null);
                ccmGuardUnit.setUser(new User(split[i1]));
                ccmGuardUnit.setStatus(CcmPatrolMissionsController.PROCESSING_TASK);
                ccmGuardUnit.setDepartureTime(ccmPatrolSecurity.getSecurityTime());
                ccmGuardUnitService.save(ccmGuardUnit);
                //通知到手机APP
                Map<String, String> map = Maps.newHashMap();
                map.put("type",TYPE);
                map.put("id", ccmGuardUnit.getId());
                map.put("name",ccmPatrolSecurity.getTitle());
                if(MessageTools.sendAppMsgByUserId(split[i1], map)){
                    logger.info("警卫单位任务发送app通知成功：id"+ccmGuardUnit.getId());
                }else{
                    logger.error("警卫单位任务发送app通知失败：id"+ccmGuardUnit.getId());
                }
            }

            if (ccmPatrolSecurity.getSecurityTime().getTime()>System.currentTimeMillis()) {
                String taskUrl="/security/ccmPatrolSecurity/form?id="+ccmPatrolSecurity.getId();
                JobDataMap jobDataMap=new JobDataMap();
                jobDataMap.put("oaNotifyService",oaNotifyService);
                jobDataMap.put("officeService",officeService);
                jobDataMap.put("userIds",ccmPatrolSecurity.getOffice());
                jobDataMap.put("title",ccmPatrolSecurity.getTitle());
                jobDataMap.put("content",ccmPatrolSecurity.getGuardLine());
                jobDataMap.put("status","1");
                jobDataMap.put("taskUrl",taskUrl);
                jobDataMap.put("thisId",ccmPatrolSecurity.getId());
                String strTime="";
                Date time = ccmPatrolSecurity.getSecurityTime();
                try {
                    strTime =DateTools.getSecondByDate(time)+" "+ DateTools.getMinuteByDate(time)+" "+DateTools.getHourByDate(time)+" "+DateTools.getDayByDate(time)+" "+DateTools.getMonthByDate(time)+" ? ";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //添加定时任务
                if(JobDetailTools.addJobDeteil(SendMessageByQuartz.class,jobDataMap,ccmGuardUnit.getId(), GROUP_NAME,strTime,0)) {
                    logger.info("添加定时通知成功'警卫路线' id:" + ccmPatrolSecurity.getId());
                } else{
                    logger.error("添加定时通知失败'警卫路线' id:"+ccmPatrolSecurity.getId());
                }
            }else{
                logger.error("添加定时通知失败'警卫路线' 不是未来时间 id:"+ccmPatrolSecurity.getId());
            }


        }else{
            ccmGuardUnitService.save(ccmGuardUnit);
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommUtil.openWinExpDiv(writer,"保存成功");
    }

    @RequiresPermissions("guard:ccmGuardUnit:edit")
    @RequestMapping(value = "delete")
    public String delete(CcmGuardUnit ccmGuardUnit, RedirectAttributes redirectAttributes) {
        ccmGuardUnitService.delete(ccmGuardUnit);
        addMessage(redirectAttributes, "删除警卫单位成功");
        return "redirect:"+Global.getAdminPath()+"/guard/ccmGuardUnit/?repage";
    }

    @RequestMapping(value = "securityForm")
    public String form(CcmPatrolSecurity ccmPatrolSecurity, Model model) {

        if(EntityTools.isEmpty(ccmPatrolSecurity) && ccmPatrolSecurity.getId()==null ){
            return "ccm/guard/ccmGuardUnitFormSee";
        }

        model.addAttribute("sid",ccmPatrolSecurity.getId());

        //设置警卫单位的数据
        CcmGuardUnit ccmGuardUnit=new CcmGuardUnit();
        ccmGuardUnit.setSecurity(ccmPatrolSecurity);

        List<CcmGuardUnit> list = ccmGuardUnitService.findList(ccmGuardUnit);

        if(list.size()>0){
            CcmGuardUnit ccmGuardUnit1 = list.get(0);
            model.addAttribute("ccmGuardUnit",ccmGuardUnit1);
        }

        return "ccm/guard/ccmGuardUnitFormSee";
    }


    @RequestMapping(value = "securityFormSee")
    public String formSee(CcmPatrolSecurity ccmPatrolSecurity, Model model) {

        if(EntityTools.isEmpty(ccmPatrolSecurity) && ccmPatrolSecurity.getId()==null){
            return "ccm/guard/ccmPatrolSecurityForm";
        }
        ccmPatrolSecurity=ccmPatrolSecurityService.get(ccmPatrolSecurity.getId());

        //部门1
        List<String> split =new ArrayList<>();
        if( ccmPatrolSecurity.getOffice().indexOf(",") !=-1 &&  ccmPatrolSecurity.getOffice().length()>0){
            String [] temp=ccmPatrolSecurity.getOffice().split(",");
            //id插入到list
            for (int i = 0; i < temp.length; i++) {
                split.add(temp[i]);
            }
        }else{
            split.add(ccmPatrolSecurity.getOffice());
        }

        for (int i = 0; i < split.size(); i++) {
            Office office =officeService.get(split.get(i));
            if(office==null){
                return "error/403";
            }
            ccmPatrolSecurity.setOfficeName(i==0 ? office.getName() : ccmPatrolSecurity.getOfficeName()+","+office.getName());
        }


        model.addAttribute("ccmPatrolSecurity", ccmPatrolSecurity);
        return "ccm/guard/ccmPatrolSecurityForm";
    }



    @RequestMapping(value = "securityList")
    public String list(CcmPatrolSecurity ccmPatrolSecurity, HttpServletRequest request, HttpServletResponse response, Model model) {

        User sessionUser= UserUtils.getUser();
        Page<CcmPatrolSecurity> page = null;
        if ("admin".equals(sessionUser.getLoginName())) {
            page = ccmPatrolSecurityService.findPage(new Page<>(request, response), ccmPatrolSecurity);
        } else {
            ccmPatrolSecurity.setOffice(sessionUser.getOffice().getId());
            page = ccmPatrolSecurityService.findPage(new Page<>(request, response), ccmPatrolSecurity);
        }

        if(page==null){
            return "ccm/guard/ccmPatrolSecurityList";

        }
        page.getList().forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
                idToOfficeName(item.getOffice().split(",")) :
                item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""
        ));
        model.addAttribute("page", page);
        return "ccm/guard/ccmPatrolSecurityList";
    }
    /**
     *如果为null 则没有权限查询
     */
    private String idToOfficeName(String [] strings){
        String name="";
        for (int i = 0; i < strings.length; i++) {

            Office office = officeService.get(strings[i]);
            if(office==null){
                return null;
            }
            if(i==0){
                name=office.getName();
            }else{
                name+=","+office.getName();
            }
        }
        return name;
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
        CcmPatrolSecurity ccmPatrolSecurity = ccmPatrolSecurityService.get(id);
        if(EntityTools.isEmpty(ccmPatrolSecurity)){
            return null;
        }

        String officeIds = ccmPatrolSecurity.getOffice();
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
}