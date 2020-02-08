package com.arjjs.ccm.modules.ccm.sys.web;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.ccm.moral.entity.CcmMoral;
import com.arjjs.ccm.modules.ccm.moral.service.CcmMoralService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.org.service.SysAreaService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 党建架构图像数据提供
 *
 * @author: li jiupeng
 * @create: 2019-06-19 10:58
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/map")
public class CcmPartyConstructionController {

    //人口
    @Autowired
    private CcmPeopleService ccmPeopleService;

    //数据字典
    @Autowired
    private SysDictsService sysDictsService;
    //区域
    @Autowired
    private SysAreaService sysAreaService;

    @Autowired
    private CcmOrgAreaService ccmOrgAreaService;

    @Autowired
    private CcmMoralService ccmMoralService;

    /*
     * 各级组织党员分布统计 数据接口 Distribution statistics of party members at all levels(DSOPMAAL)
     *
     * */
    @RequestMapping(value = "getDSOPMAALData")
    @ResponseBody
    public Map<String, Object> getDSOPMAALData() {

        CcmPeople ccmPeople = new CcmPeople();

        List<SysDicts> dictsList = sysDictsService.findAllListByType("ccm_buss_cate");

        String[] title = new String[dictsList.size()];

        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < dictsList.size(); i++) {
            //标题添加进去
            title[i] = dictsList.get(i).getLabel();
            ccmPeople.setUnitCategory(dictsList.get(i).getValue());
            ccmPeople.setPolitics("01"); //中国共产党党员
            Map<String, Object> tempMap = new HashMap<>();
            //查询并添加进去数量以及title
            tempMap.put("value", ccmPeopleService.findList(ccmPeople).size());
            tempMap.put("name", dictsList.get(i).getLabel());
            listData.add(tempMap);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("data", listData);
        return data;
    }

    /*
     * 镇办在职人员及党员统计
     *Statistics of in-service personnel and party members in the township
     * */
    @RequestMapping(value="getTownshipStatisticsData")
    @ResponseBody
    public Map<String, Object> getTownshipStatisticsData() {
        SysArea sysArea = new SysArea();
        sysArea.setType("5");
        List<SysArea> list = sysAreaService.findList(sysArea);
        //声明需要变量
        String[] name = new String[list.size()];
        int[] partyMembers = new int[list.size()];
        int[] notPartyMembers = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            CcmOrgArea ccmOrgArea = new CcmOrgArea();
            sysArea = list.get(i);
            ccmOrgArea.setAreaId(sysArea.getId());
//            System.out.println("AreaID="+sysArea.getId());
//            System.out.println(sysArea.toString());
            //获取当前数据的扩展表数据
//            System.out.println("扩展表的数据="+ccmOrgAreaService.findList(ccmOrgArea).size());

            CcmOrgArea ccmOrgArea1 = ccmOrgAreaService.findList(ccmOrgArea).get(0);

//            System.out.println(ccmOrgArea1.toString());

//            //赋值
            name[i] = sysArea.getName();
            partyMembers[i] = ccmOrgArea1.getPartyMembersNum();
            notPartyMembers[i] = ccmOrgArea1.getNetPeoNum()-ccmOrgArea1.getPartyMembersNum();

        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("partyMembers", partyMembers);
        data.put("notPartyMembers", notPartyMembers);


        return data;
    }
    /*
     * 村居党员统计
     *Village  party statistics
     * */
    @RequestMapping(value="getVillagePartyStatisticsData")
    @ResponseBody
    public Map<String, Object> getVillagePartyStatisticsData() {
        SysArea sysArea = new SysArea();
        sysArea.setType("6");
        Page<SysArea> areaPage=sysAreaService.findPage(new Page<SysArea>(1,15),sysArea);
        List<SysArea> list = areaPage.getList();
        //声明需要变量
        String[] name = new String[list.size()];
        int[] partyMembers = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            CcmOrgArea ccmOrgArea = new CcmOrgArea();
            sysArea = list.get(i);
            ccmOrgArea.setAreaId(sysArea.getId());

            CcmOrgArea ccmOrgArea1 = ccmOrgAreaService.findList(ccmOrgArea).get(0);

//            //赋值
            name[i] = sysArea.getName();
            partyMembers[i] = ccmOrgArea1.getPartyMembersNum();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("partyMembers", partyMembers);

        return data;
    }
    /*
     * 党建架构 节点一数据接口
     * Party structure
     * */
    @RequestMapping(value="getPartyStructureNodeOneData")
    @ResponseBody
    public Map<String, Object> getPartyStructureNodeOneData() {
        SysArea sysArea = new SysArea();
        sysArea.setType("4");
        Page<SysArea> areaPage=sysAreaService.findPage(new Page<SysArea>(1,15),sysArea);
        List<SysArea> list = areaPage.getList();

        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        return data;
    }
    /*
     * 党建架构 节点二数据接口
     * Party structure
     * id为空的时候会直接返回null
     * */
    @RequestMapping(value="getPartyStructureNodeTwoData")
    @ResponseBody
    public Map<String, Object> getPartyStructureNodeTwoData(String id) {
        if(id=="" || id==null){
            return null;
        }
        SysArea fatherSysArea=new SysArea();
        fatherSysArea.setId(id);
        SysArea sysArea = new SysArea();
        sysArea.setType("5");
        sysArea.setParent(fatherSysArea);

        Page<SysArea> areaPage=sysAreaService.findPage(new Page<SysArea>(1,30),sysArea);
        List<SysArea> list = areaPage.getList();

        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        return data;
    }
    /*
     * 党建架构 节点三数据接口
     * Party structure
     * */
    @RequestMapping(value="getPartyStructureNodeThreeData")
    @ResponseBody
    public Map<String, Object> getPartyStructureNodeThreeData(String id) {
        if(id=="" || id==null){
            return null;
        }
        SysArea fatherSysArea=new SysArea();
        fatherSysArea.setId(id);
        //那father的对象set
        SysArea sysArea = new SysArea();
        sysArea.setType("6");
        sysArea.setParent(fatherSysArea);
        Page<SysArea> areaPage=sysAreaService.findPage(new Page<SysArea>(1,30),sysArea);
        List<SysArea> list = areaPage.getList();

        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        return data;
    }

    /*
    * 五员式管理
    * */
    @RequestMapping(value="getFiveMemberManagementData")
    @ResponseBody
    public Map<String,Object> getFiveMemberManagementData(){

        SysArea area = new SysArea();
        area.setType("5");
        Page<CcmOrgArea> OrgAreaPage=ccmOrgAreaService.findTownPage(new Page<CcmOrgArea>(1,5),new CcmOrgArea());
        List<CcmOrgArea> list = OrgAreaPage.getList();
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getArea().getName();
            int[] num = new int[5];
            num[0] = list.get(i).getDirectorPovertyAlleviation();
            num[1] = list.get(i).getFirstSecretary();
            num[2] = list.get(i).getPoliceAssistant();
            num[3] = list.get(i).getPoliticalLegalDeployment();
            num[4] = list.get(i).getPeopleLivelihoodSupervisor();
            Map<String,Object> temp=new HashMap<>();
            temp.put("name",name);
            temp.put("num",num);
            mapList.add(temp);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", mapList);
        return data;
    }

    /*
    * 道德模范
    * */
    @RequestMapping(value="getMoralData")
    @ResponseBody
    public Map<String,Object> getMoralData(){

        List<CcmMoral> list = ccmMoralService.findList(new CcmMoral());
        System.out.println("数量"+list.size());
        Map<String,Object> data=new HashMap<>();
        data.put("data",list);
        return data;
    }


}
