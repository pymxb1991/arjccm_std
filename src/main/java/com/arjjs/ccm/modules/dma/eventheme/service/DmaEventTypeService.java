/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.dma.eventheme.service;

import com.arjjs.ccm.modules.dma.eventheme.entity.CountAreaEventByReportTypeVo;
import com.arjjs.ccm.modules.dma.eventheme.entity.CountOfficeEventEntity;
import com.arjjs.ccm.modules.flat.handle.service.BphAlarmHandleService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author admin001
 * @version 2019-09-06
 */
@Service
@Transactional(readOnly = true)
public class DmaEventTypeService {

    public static final String [] EVENT_HANDLE_STATUS = new String []{"未处理","处理中","已处理"};

    @Autowired
    private BphAlarmHandleService bphAlarmHandleService;

    @Autowired
    private OfficeService officeService;


    /**
     * 统计单位警情
     * @return
     */
    public Map<String,Object>  countOfficeEvent(){

        //返回数据封装
        Set<String> officeName = new HashSet<>();
        Set<String> statusName = new HashSet<>();
        List<CountAreaEventByReportTypeVo> resultEventInfo = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();

        List<CountOfficeEventEntity> countOfficeEventList = bphAlarmHandleService.queryPoliceAlarm();
        //构造新的处警列表；
        List<CountOfficeEventEntity> countOfficeEventResult = new ArrayList<>();
        for (CountOfficeEventEntity officeEvent:countOfficeEventList) {
            User user = UserUtils.get(officeEvent.getHandlePoliceId());
            // 获取用户的父部门
            String parentIds = user.getOffice().getParentIds();

            List<String> parentIdList = Arrays.asList(parentIds.split(","));
           if (parentIdList.size()>2){
               Office office = officeService.get(parentIdList.get(2));
               officeEvent.setOfficeGrandParentName(office.getName());
           }else{
               officeEvent.setOfficeGrandParentName("1-1"); //TODO  如果有异常情况如何处理；
           }
            countOfficeEventResult.add(officeEvent);
        }

        // 数据为空的封装
        if (countOfficeEventResult.size() == 0) {

            List<String> eventReportTypeList = Arrays.asList(EVENT_HANDLE_STATUS);
            for (String reportType : eventReportTypeList) {
                CountAreaEventByReportTypeVo paddEventAreaInfo = new CountAreaEventByReportTypeVo();
                paddEventAreaInfo.setName(reportType);
                paddEventAreaInfo.setData(Arrays.asList("0"));
                paddEventAreaInfo.setType("bar");// 此处为固定的，
                resultEventInfo.add(paddEventAreaInfo);
            }
            resultMap.put("eventAreaData",resultEventInfo);

            resultMap.put("eventAreaNameData",Arrays.asList("中国"));
            resultMap.put("eventAreaTypeData",eventReportTypeList);
            return resultMap;
        }
        //按照处警状态进行分组
        Map<String, List<CountOfficeEventEntity>> eventHandleStatusMap =
                countOfficeEventResult.stream().collect(Collectors.groupingBy(CountOfficeEventEntity::getHandleStatus));


        //单位处警状态 二次分组
        for (Map.Entry<String, List<CountOfficeEventEntity>> entry : eventHandleStatusMap.entrySet()) {

            Map<String, List<CountOfficeEventEntity>> eventOfficeMap =
                    countOfficeEventResult.stream().collect(Collectors.groupingBy(CountOfficeEventEntity::getOfficeGrandParentName));

            CountAreaEventByReportTypeVo areaEventByReportTypeVo = new CountAreaEventByReportTypeVo();

            areaEventByReportTypeVo.setName(entry.getKey());

            statusName.add(entry.getKey());

            //单位各类型事件数量统计
            List<String> eventOfficeCount = new ArrayList<>();
            //单位类型数量统计
            for (Map.Entry<String, List<CountOfficeEventEntity>> officeEvent : eventOfficeMap.entrySet()) {
                officeName.add(officeEvent.getKey());
                eventOfficeCount.add(officeEvent.getValue().size() + "");
            }
            areaEventByReportTypeVo.setData(eventOfficeCount);
            areaEventByReportTypeVo.setType("bar");// 此处为固定的，
            resultEventInfo.add(areaEventByReportTypeVo);
        }
        //填充空数据


        //把没有状态类型存起来
        List<String> eventReportTypeList =  Arrays.asList(EVENT_HANDLE_STATUS);
        List<String> resultStatus  = getOtherInfo(eventReportTypeList,statusName);

        //查询所有顶级部门
        List<String> officeNameList = bphAlarmHandleService.queryOfficeInfo();
        List<String> resultOffice = getOtherInfo(officeNameList,officeName);

        //重新构造第一组数据

        if(resultStatus.size() > 0){
            for (String resultStat: resultStatus) {
                CountAreaEventByReportTypeVo areaEventByReportTypeVo = new CountAreaEventByReportTypeVo();
                areaEventByReportTypeVo.setName(resultStat);
                areaEventByReportTypeVo.setData(Arrays.asList("0"));
                areaEventByReportTypeVo.setType("bar");// 此处为固定的，
                resultEventInfo.add(areaEventByReportTypeVo);
            }
        }
        //整体重新构造数据
        List<CountAreaEventByReportTypeVo> resultOfficeEventInfo = new ArrayList<>();
        //为没有数据的部门构造空数据
        if (resultOffice.size() > 0){
            //构造空数据，填充 0
            int[] office = new int[resultOffice.size()];
            String resultOfficeArray = Arrays.toString(office);
            String start = resultOfficeArray.substring(1,resultOfficeArray.length()-1);
//            String end = resultOfficeArray.substring(resultOfficeArray.indexOf("]"));
            List<String> officeNull = Arrays.asList(start.split(","));

            for (CountAreaEventByReportTypeVo officeEventInfo: resultEventInfo) {
                  CountAreaEventByReportTypeVo areaEventByReportTypeVo = new CountAreaEventByReportTypeVo();
                  BeanUtils.copyProperties(officeEventInfo,areaEventByReportTypeVo);

                  List<String> resultOfficeCount = new ArrayList<>();
                  List<String> data = officeEventInfo.getData();
                  resultOfficeCount.addAll(data);
                  resultOfficeCount.addAll(officeNull);
                  areaEventByReportTypeVo.setData(resultOfficeCount);

                 resultOfficeEventInfo.add(areaEventByReportTypeVo);
            }
        }


        resultMap.put("eventAreaData",resultOfficeEventInfo);
        resultMap.put("eventAreaNameData",resultOffice);
        resultMap.put("eventAreaTypeData",eventReportTypeList);
        return resultMap;
    }

    /**
     *  返回 数据中未用到集合
     * @param sourceList
     * @param targetList
     * @return
     */
    private List<String> getOtherInfo(List<String> sourceList, Set<String> targetList) {
        List<String> resultStatus  = new ArrayList<>();
        if (targetList.size() != 3) {
            for (String source : sourceList) {
                for (String target : targetList) {
                    if (!source.equals(target)) {
                        resultStatus.add(source);
                    }
                }
            }
        }
        return resultStatus;
    }

}
