package com.arjjs.ccm.modules.dma.peoplecount.web;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @title: 实有单位统计
 *
 * @description: TODO
 * @author yi
 * @date 2019-09-03
 */

@Controller
@RequestMapping(value = "${adminPath}/statistics/countStatistics/")
public class StatisticsNumberController extends BaseController {


    @Autowired
    private CcmBasePlaceService ccmBasePlaceService;

    @Autowired
    private CcmOrgNpseService ccmOrgNpseService;

    @Autowired
    private SysConfigService sysConfigService;

    //行业字典 父id 截取位置和长度
    public static final String DICTS = "f001";
    public static final String DICTSSTART = "8";
    public static final String DICTSEND = "2";

    @RequestMapping(value = { "list", "" })
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "dma/statistics/statisticCountList";
    }


    /**
     * 根据行业类型统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrgNpseBysysdicts")
    public Map<String, Object> getOrgNpseBysysdicts(){
        EchartType echartType = new EchartType();
        echartType.setValue(DICTS);
        echartType.setValue1(DICTSSTART);
        echartType.setValue2(DICTSEND);
        List<EchartType> reslist =  ccmOrgNpseService.getOrgNpseBysysdicts(echartType);
        String[] type = new String[reslist.size()];
        String[] value = new String[reslist.size()];

        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < reslist.size(); i++) {
            type[i] = reslist.get(i).getType();
            value[i] = reslist.get(i).getValue();
            Map<String, Object> listmap = Maps.newHashMap();
            listmap.put("value", value[i]);
            listmap.put("name", type[i]);
            listData.add(listmap);
        }

        Map<String, Object> data = Maps.newHashMap();
        data.put("name", type);
        data.put("value", listData);
        return data;
    }


    /**
     * 根据场所类型统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCountbyplaceType")
    public Map<String, Object> getCountbyplaceType(){
        List<EchartType> reslist =  ccmBasePlaceService.getCountbyplaceType();
        String[] type = new String[reslist.size()];
        String[] value = new String[reslist.size()];

        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < reslist.size(); i++) {
            type[i] = reslist.get(i).getType();
            value[i] = reslist.get(i).getValue();
            Map<String, Object> listmap = Maps.newHashMap();
            listmap.put("value", value[i]);
            listmap.put("name", type[i]);
            listData.add(listmap);
        }

        Map<String, Object> data = Maps.newHashMap();
        data.put("name", type);
        data.put("value", listData);
        return data;
    }



    /**
     * 各区域企业分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrgNpseBysysArea")
    public Map<String, Object> getOrgNpseBysysArea(){
        List<EchartType> reslist =  ccmOrgNpseService.getOrgNpseBysysArea();
        String[] type = new String[reslist.size()];
        String[] value = new String[reslist.size()];

        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < reslist.size(); i++) {
            type[i] = reslist.get(i).getType();
            value[i] = reslist.get(i).getValue();
            Map<String, Object> listmap = Maps.newHashMap();
            listmap.put("value", value[i]);
            listmap.put("name", type[i]);
            listData.add(listmap);
        }

        Map<String, Object> data = Maps.newHashMap();
        data.put("name", type);
        data.put("value", listData);
        return data;
    }


    /**
     * 矛盾纠纷排查化解
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value ="map")
    public String map(CcmEventAmbi ccmEventAmbi, HttpServletRequest request, HttpServletResponse response, Model model) {
        SysConfig sysConfig = new SysConfig();
        sysConfig = sysConfigService.get("system_level");
        String level = sysConfig.getParamStr();
        model.addAttribute("level", level);
        model.addAttribute("ccmEventAmbi", ccmEventAmbi);
        return "dma/statistics/ccmEventAmbiMap";
    }



    /**
     * 各区域企业注册资金分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrgNpseregisteredBysysArea")
    public Map<String, Object> getOrgNpseregisteredBysysArea(){
        List<EchartType> reslist =  ccmOrgNpseService.getOrgNpseregisteredBysysArea();
        String[] type = new String[reslist.size()];
        String[] value = new String[reslist.size()];

        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < reslist.size(); i++) {
            type[i] = reslist.get(i).getType();
            value[i] = reslist.get(i).getValue();
            Map<String, Object> listmap = Maps.newHashMap();
            listmap.put("value", value[i]);
            listmap.put("name", type[i]);
            listData.add(listmap);
        }

        Map<String, Object> data = Maps.newHashMap();
        data.put("name", type);
        data.put("value", listData);
        return data;
    }


}
