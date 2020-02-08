package com.arjjs.ccm.modules.dma.eventheme.web;

import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.dma.eventheme.entity.CountEventByReportTypeVo;
import com.arjjs.ccm.modules.dma.eventheme.service.DmaEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/eventType/dmaEventTypeCount")
public class DmaEventTypeController {
    @Autowired
    private CcmEventIncidentService ccmEventIncidentService;

    @Autowired
    private DmaEventTypeService dmaEventTypeService;


    /**
     *   跳转事件分类统计
     * @param model
     * @return
     */
    @RequestMapping(value = { "eventTypeIndex", "" })
    public String eventTypeIndex(Model model) {
        return "dma/eventheme/eventTypeList";
    }
    /**
     *   跳转事件分区域->分类统计
     * @param model
     * @return
     */
    @RequestMapping(value = { "eventAreaTypeIndex", "" })
    public String eventAreaTypeIndex(Model model) {
        return "dma/eventheme/eventAreaTypeIndex";
    }
    /**
     *   跳转事件分单位->处警类型统计
     * @param model
     * @return
     */
    @RequestMapping(value = { "eventOfficeIndex", "" })
    public String eventOfficeIndex(Model model) {
        return "dma/eventheme/eventOfficeIndex";
    }


    /**
     *  ( 事件报警数量统计) 统计某一段时间内各上报类型 中案件 的数量
     * @param beginHappenDate
     * @param endHappenDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "countEventPreviewByReportType")
    public List<CountEventByReportTypeVo> countEventPreviewByReportType(String beginHappenDate, String endHappenDate) {
        return ccmEventIncidentService.countEventPreviewByReportType(beginHappenDate,endHappenDate);
    }

    /**
     *  (事件实际发生数量统计) 统计某一段时间内各类型事件的数量
     * @param beginHappenDate
     * @param endHappenDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "countEventByReportType")
    public List<CountEventByReportTypeVo> countEventByReportType(String beginHappenDate, String endHappenDate) {
        return ccmEventIncidentService.countEventByReportType(beginHappenDate,endHappenDate);
    }

    /**
     *  (事件分区域统计) 统计某一段时间内 各区域的 - >各类型事件的数量
     * @param beginHappenDate
     * @param endHappenDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "countAreaEventByReportType")
    public Map<String,Object> countAreaEventByReportType(String beginHappenDate, String endHappenDate) {

        return ccmEventIncidentService.countAreaEventByReportType(beginHappenDate,endHappenDate);
    }
    /**
     *  (各单位工作量) 根据处警人部门，统计各部门 - >各类型事件的数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "countOfficeEvent")
    public Map<String,Object> countOfficeEvent() {
        return dmaEventTypeService.countOfficeEvent();
    }
}
