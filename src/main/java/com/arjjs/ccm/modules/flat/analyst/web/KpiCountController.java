package com.arjjs.ccm.modules.flat.analyst.web;

import java.util.*;

import com.arjjs.ccm.modules.ccm.guard.dao.CcmGuardUnitDao;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolUnitDao;
import com.arjjs.ccm.modules.flat.unit.dao.CcmReliefUnitDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.ccm.citycomponents.dao.CcmCityComponentsDao;
import com.arjjs.ccm.modules.ccm.log.dao.CcmLogTailDao;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgNpseDao;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPeopleDao;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPopTenantDao;
import com.arjjs.ccm.modules.ccm.sys.dao.CcmWorkReportDao;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.kpi.count.entity.CcmKpiCount;
import com.arjjs.ccm.modules.kpi.count.service.CcmKpiCountService;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.User;
import com.google.common.collect.Maps;

/**
 * @Author: Li
 * @CreateTime: 2019-07-17 17:30
 * @Description: ${Description}
 */
@Controller
@RequestMapping(value = "${adminPath}/kpi/count")
public class KpiCountController {

    @Autowired
    private CcmLogTailDao ccmLogTailDao;
    @Autowired
    private CcmPeopleDao ccmPeopleDao;
    @Autowired
    private CcmPopTenantDao ccmPopTenantDao;
    @Autowired
    private CcmOrgNpseDao ccmOrgNpseDao;
    @Autowired
    private CcmCityComponentsDao ccmCityComponentsDao;
    @Autowired
    private CcmWorkReportDao ccmWorkReportDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CcmKpiCountService ccmKpiCountService;
    @Autowired
    private CcmPatrolUnitDao ccmPatrolUnitDao;
    @Autowired
    private CcmReliefUnitDao ccmReliefUnitDao;
    @Autowired
    private CcmGuardUnitDao ccmGuardUnitDao;

    @RequestMapping(value = "/toKpi")
    public String toKpi() {
        return "/flat/analyst/kpiRanking";
    }

    @ResponseBody
    @RequestMapping(value = "getRank")
    public Object getRank() {

        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("data", ccmKpiCountService.getRank());
        map.put("code", "0");

        // JobDetailTools.addJobDeteil(TimedTask.class, new JobDataMap(), "绩效数据查询更新" + String.valueOf(System.currentTimeMillis()), "统计组", "0 02 20 * * ? ", 0);

        return map;
    }



    public void kpiCount() {

        // 默认统计近一个月的数据
        // 用户

        List<CcmKpiCount> list = ccmKpiCountService.findList(new CcmKpiCount());

        if (!list.isEmpty() || list.size() != 0) {
            for (CcmKpiCount count : list) {
                ccmKpiCountService.delete(count);
            }
        }

        List<User> userList = userDao.findList(new User());
        for (User user : userList) {
            CcmKpiCount count = new CcmKpiCount();
            count.setRemarks(user.getId());
            count.setUserName(user.getName());
            count.setCreateDate(new Date());
            count.setUpdateDate(new Date());
            count.setTailTimes("0");
            count.setPeopleNumber("0");
            count.setTenantNumber("0");
            count.setOrgNumber("0");
            count.setComponentsNumber("0");
            count.setReportsTimes("0");
            count.setReliefTimes("0");
            count.setPatrolTimes("0");
            count.setPoliceTaskTimes("0");
            ccmKpiCountService.save(count);
        }

        // 重点人员帮教次数
        List<Count> tailCount = ccmLogTailDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : tailCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setTailTimes(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }

        // 新增重点人员数量
        List<Count> peopleCount = ccmPeopleDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : peopleCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setPeopleNumber(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }

        // 新增的房东和租住人数量
        List<Count> tenantCount = ccmPopTenantDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : tenantCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setTenantNumber(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }
        // 重点机构
        List<Count> orgCount = ccmOrgNpseDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : orgCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setOrgNumber(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }

        // 消防设施
        List<Count> cityCount = ccmCityComponentsDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : cityCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setComponentsNumber(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }

        // 工作日志
        List<Count> reportCount = ccmWorkReportDao.getByCount();
        for (CcmKpiCount kpiCount : list) {
            for (Count count : reportCount) {
                if (kpiCount.getRemarks().equals(count.getUserId())) {
                    kpiCount.setReportsTimes(count.getCounts());
                    ccmKpiCountService.save(kpiCount);
                }
            }
        }

        // 巡逻次数
        List<Count> patrolCount = ccmPatrolUnitDao.getByCount();
        Map<String, Integer> map = getMap(patrolCount);

        for (CcmKpiCount kpiCount : list) {
            if (map.containsKey(kpiCount.getRemarks())) {
                kpiCount.setPatrolTimes(map.get(kpiCount.getRemarks()).toString());
                ccmKpiCountService.save(kpiCount);
            }

        }

        // 备勤次数

        List<Count> reliefCount = ccmReliefUnitDao.getByCount();
        Map<String, Integer> map1 = getMap(reliefCount);

        for (CcmKpiCount kpiCount : list) {
            if (map1.containsKey(kpiCount.getRemarks())) {
                kpiCount.setReliefTimes(map1.get(kpiCount.getRemarks()).toString());
                ccmKpiCountService.save(kpiCount);
            }

        }

        // 警卫任务次数

        List<Count> guardCount = ccmGuardUnitDao.getByCount();
        Map<String, Integer> map2 = getMap(guardCount);

        for (CcmKpiCount kpiCount : list) {
            if (map2.containsKey(kpiCount.getRemarks())) {
                kpiCount.setPoliceTaskTimes(map2.get(kpiCount.getRemarks()).toString());
                ccmKpiCountService.save(kpiCount);
            }

        }


    }

    private Map<String, Integer> getMap(List<Count> list) {

        Map<String, Integer> map = Maps.newHashMap();

        for (Count count : list) {
            if (count.getUserId().contains(",")) {
                String[] split = count.getUserId().split(",");
                for (String s : split) {
                    if (map.containsKey(s)) {
                        map.put(s, map.get(s) + Integer.valueOf(count.getCounts()));
                    } else {
                        map.put(s, Integer.valueOf(count.getCounts()));
                    }
                }

            } else {
                if (map.containsKey(count.getUserId())) {
                    map.put(count.getUserId(), map.get(count.getUserId()) + Integer.valueOf(count.getCounts()));
                } else {
                    map.put(count.getUserId(), Integer.valueOf(count.getCounts()));
                }
            }

        }

        return map;
    }

}
