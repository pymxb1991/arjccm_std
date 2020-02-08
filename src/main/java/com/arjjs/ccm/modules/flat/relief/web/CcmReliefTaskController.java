/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.relief.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.MessageTools;
import com.google.common.collect.Maps;
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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.flat.relief.entity.CcmReliefTask;
import com.arjjs.ccm.modules.flat.relief.service.CcmReliefTaskService;

import java.util.*;

/**
 * 备勤任务实体类Controller
 *
 * @author lgh
 * @version 2019-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/relief/ccmReliefTask")
public class CcmReliefTaskController extends BaseController {

    @Autowired
    private CcmReliefTaskService ccmReliefTaskService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private OaNotifyService oaNotifyService;
    @Autowired
    private SysDictsService sysDictsService;

    /**
     * 审核通过 的状态
     */
    private final String APPROVED="2";

    /**
     * 任务类型标识 自定义
     */
    private final String TYPE="spare_time_task";

    @ModelAttribute
    public CcmReliefTask get(@RequestParam(required = false) String id) {
        CcmReliefTask entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = ccmReliefTaskService.get(id);
        }
        if (entity == null) {
            entity = new CcmReliefTask();
        }
        return entity;
    }


    @RequestMapping(value = {"summaryGraph",""})
    public String summaryGraph(){

        return "flat/relief/summaryGraph";
    }

    @RequestMapping(value = "summaryGraphData")
    @ResponseBody
    public Map<String,Object> summaryGraphData(){

        //查询type
        List<SysDicts> sysDictsList = sysDictsService.findAllListByType("ccm_patrol_missions_status");
        List<String> stringList=new ArrayList<>(sysDictsList.size());
        List<String> valList=new ArrayList<>(sysDictsList.size());
        //lable 值 and val
        sysDictsList.forEach(item->{
            valList.add(item.getValue());
            stringList.add(item.getLabel());
        });


        //查询全部并分类添加个数统计完成情况
        List<CcmReliefTask> list = ccmReliefTaskService.findList(new CcmReliefTask());
        //查询office名称
        list.forEach(item->item.setReliefDeptName(item.getReliefDeptName().indexOf(",")!=-1 ?
                idToOfficeName(item.getReliefDept().split(",")) :
                item.getReliefDept().length()>0 ? officeService.get(item.getReliefDept()).getName() :""
        ));

        //返回的总data
//		Map<String,Object> mapData=new HashMap<>();
        // 单位
        Map<String,Integer []> mapUnit=new HashMap<>();
//		Map<String,Integer> mapType=new HashMap<>(1);
        // 单位 key 值
        Set<String> strUnit=mapUnit.keySet();
        int size = valList.size();
        list.forEach(item->{
            //String [] splitId=item.getOffice().split(",");
            String [] splitName=item.getReliefDeptName().split(",");
            for (int i = 0; i < splitName.length; i++) {


                //判断是否有存 当前key
                if (mapUnit.get(splitName[i])==null){
                    //type
                    Integer [] integers=new Integer[size];
                    for (int i1 = 0; i1 < size; i1++) {
                        integers[i1]=0;
                    }
                    //循环状态 判断现在是哪个状态 插入
                    for (int i1 = 0; i1 < size; i1++) {
                        if(valList.get(i1).equals(item.getReviewStatus())){
                            integers[i1]+=1;
                        }
                    }
                    mapUnit.put(splitName[i],integers);
                }else{
                    //如果有存过+1
                    Integer [] integers=mapUnit.get(splitName[i]);
                    for (int i1 = 0; i1 < size; i1++) {
                        if(valList.get(i1).equals(item.getReviewStatus())){
                            integers[i1]+=1;
                        }
                    }
                    mapUnit.put(splitName[i],integers);

                }
            }
        });

        Map<String,Object> map=new HashMap<>();
        map.put("typeArr",stringList);
        map.put("mapUnit",mapUnit);
        map.put("key",strUnit);
        return map;
    }


    @RequiresPermissions("relief:ccmReliefTask:view")
    @RequestMapping(value = {"list"})
    public String list(CcmReliefTask ccmReliefTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CcmReliefTask> page = ccmReliefTaskService.findPage(new Page<>(request, response), ccmReliefTask);

        if (page.getCount() == 0) {
            model.addAttribute("page", page);
            return "flat/relief/ccmReliefTaskList";
        }
        page.getList()
                .forEach(item -> item
                        .setReliefDeptName(item.getReliefDept().indexOf(",") != -1 ? idToOfficeName(item.getReliefDept().split(","))
                                : item.getReliefDept().length() > 0 ? officeService.get(item.getReliefDept()).getName() : ""));

        model.addAttribute("page", page);
        return "flat/relief/ccmReliefTaskList";
    }

    @RequiresPermissions("relief:ccmReliefTask:view")
    @RequestMapping(value = "form")
    public String form(CcmReliefTask ccmReliefTask, Model model) {

        if (EntityTools.isEmpty(ccmReliefTask)) {
            return "flat/relief/ccmReliefTaskForm";
        }
        // 部门1
        List<String> split = new ArrayList<>();
        if (ccmReliefTask.getReliefDept().indexOf(",") != -1 && ccmReliefTask.getReliefDept().length() > 0) {
            String[] temp = ccmReliefTask.getReliefDept().split(",");
            // id插入到list
            for (int i = 0; i < temp.length; i++) {
                split.add(temp[i]);
            }
        } else {
            split.add(ccmReliefTask.getReliefDept());
        }

        for (int i = 0; i < split.size(); i++) {
            Office office = officeService.get(split.get(i));
            if (office == null) {
                return "error/403";
            }
            ccmReliefTask.setReliefDeptName(
                    i == 0 ? office.getName() : ccmReliefTask.getReliefDeptName() + "," + office.getName());
        }

        model.addAttribute("ccmReliefTask", ccmReliefTask);
        return "flat/relief/ccmReliefTaskForm";
    }

    @RequiresPermissions("relief:ccmReliefTask:edit")
    @RequestMapping(value = "save")
    public String save(CcmReliefTask ccmReliefTask, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, ccmReliefTask)) {
            return form(ccmReliefTask, model);
        }
        ccmReliefTaskService.save(ccmReliefTask);
        //如果审核通过才发送消息给单位负责人
        if (APPROVED.equals(ccmReliefTask.getAuditingStatus())) {
            String officeIds = ccmReliefTask.getReliefDept();
            String taskUrl = "/flat/relief/ccmReliefTaskDetails?id=" + ccmReliefTask.getId();
            // 发送通知
            if(MessageTools.sendMessageByOfficePersonInCharge(oaNotifyService, officeService, officeIds,
                    ccmReliefTask.getTaskName(), ccmReliefTask.getRemarks(), "1", taskUrl)){
                logger.info("备勤任务发送通知成功：id"+ccmReliefTask.getId());

            }else{
                logger.error("备勤任务发送通知成功：id"+ccmReliefTask.getId());

            }

            //向APP发送消息
            Map<String, Object> map = Maps.newHashMap();
            map.put("type", TYPE);
            map.put("id", ccmReliefTask.getId());
            map.put("name", ccmReliefTask.getTaskName());
            String officeIdsParam = ccmReliefTask.getReliefDept();

            if (MessageTools.sendAppMsgByOfficeId(officeService, officeIdsParam, map)) {
                logger.info("备勤任务发送app通知成功：id"+ccmReliefTask.getId());
            }else{
                logger.error("备勤任务发送app通知成功：id"+ccmReliefTask.getId());
            }
        }
        addMessage(redirectAttributes, "保存备勤任务成功");
        return "redirect:" + Global.getAdminPath() + "/relief/ccmReliefTask/?repage";
    }

    @RequiresPermissions("relief:ccmReliefTask:edit")
    @RequestMapping(value = "delete")
    public String delete(CcmReliefTask ccmReliefTask, RedirectAttributes redirectAttributes) {
        ccmReliefTaskService.delete(ccmReliefTask);
        addMessage(redirectAttributes, "删除备勤任务成功");
        return "redirect:" + Global.getAdminPath() + "/relief/ccmReliefTask/?repage";
    }

    // 查看详情
    @RequestMapping(value = "details")
    public String getDetails(String id, Model model) {
        CcmReliefTask ccmReliefTask = ccmReliefTaskService.get(id);

        if (id.length() > 0 && ccmReliefTask == null) {
            return "error/403";
        } else if (StringUtils.isBlank(id)) {
            return "error/404";
        }

        ccmReliefTask.setReliefDeptName(ccmReliefTask.getReliefDept().indexOf(",") != -1
                ? idToOfficeName(ccmReliefTask.getReliefDept().split(","))
                : ccmReliefTask.getReliefDept().length() > 0
                ? officeService.get(ccmReliefTask.getReliefDept()).getName()
                : "");
        model.addAttribute("ccmReliefTask", ccmReliefTask);
        return "flat/relief/ccmReliefTaskDetails";

    }


    // 查看详情2
    @RequestMapping(value = "details2")
    public String getDetails2(String id, Model model) {

        CcmReliefTask ccmReliefTask = ccmReliefTaskService.get(id);

        if (id.length() == 0 && ccmReliefTask != null) {
            return "error/403";
        } else if (StringUtils.isBlank(id) || ccmReliefTask==null) {
            return "error/404";
        }
        if(ccmReliefTask!=null){
            ccmReliefTask.setReliefDeptName(ccmReliefTask.getReliefDept().indexOf(",") != -1
                    ? idToOfficeName(ccmReliefTask.getReliefDept().split(","))
                    : ccmReliefTask.getReliefDept().length() > 0
                    ? officeService.get(ccmReliefTask.getReliefDept()).getName()
                    : "");
        }
        model.addAttribute("ccmReliefTask", ccmReliefTask);
        return "flat/relief/ccmReliefTaskDetails2";

    }


    // 备勤任务安排
    @RequestMapping(value = "arrangement")
    public String arrangement(CcmReliefTask ccmReliefTask, HttpServletRequest request,
                              HttpServletResponse response, Model model) {
/*        Page<CcmReliefTask> page = ccmReliefTaskService.findPage(new Page<>(request, response),
                ccmReliefTask);
        if (page.getCount() == 0) {
            model.addAttribute("page", page);
            return "flat/relief/ccmReliefTaskListArranggement";
        }

        // 按部门id查询部门名称
        page.getList()
                .forEach(item -> item
                        .setReliefDeptName(item.getReliefDept().indexOf(",") != -1 ? idToOfficeName(item.getReliefDept().split(","))
                                : item.getReliefDept().length() > 0 ? officeService.get(item.getReliefDept()).getName() : ""));*/


        // 查询是否有权限查看
        Page<CcmReliefTask> page = null;
        //如果是管理员查看所有 如果是
        if (UserUtils.getUser().isAdmin()) {//管理员看所有
            page = ccmReliefTaskService.findApp(new Page<>(request, response), ccmReliefTask);
        } else { //单位负责人看本单位
            if (UserUtils.getUser().getOffice().getPrimaryPerson() != null && UserUtils.getUser().getId().equals(UserUtils.getUser().getOffice().getPrimaryPerson().getId())) {
                ccmReliefTask.setReliefDept(UserUtils.getUser().getOffice().getId());
                page = ccmReliefTaskService.findApp(new Page<>(request, response), ccmReliefTask);
            }
        }

        if (page != null) {
            page.getList()
                    .forEach(item -> item
                            .setReliefDeptName(item.getReliefDept().indexOf(",") != -1 ? idToOfficeName(item.getReliefDept().split(","))
                                    : item.getReliefDept().length() > 0 ? officeService.get(item.getReliefDept()).getName() : ""));
            page.setCount(page.getList().size());
        }
        model.addAttribute("page", page);
        return "flat/relief/ccmReliefTaskListArranggement";
    }

    /**
     * 如果为null 则没有权限查询
     */
    private String idToOfficeName(String[] strings) {
        String name = "";
        for (int i = 0; i < strings.length; i++) {
            Office office = officeService.get(strings[i]);
            if (office == null) {
                return null;
            }
            if (i == 0) {
                name = office.getName();
            } else {
                name += "," + office.getName();
            }
        }
        return name;
    }

}