package com.arjjs.ccm.modules.flat.analyst.web;

import java.text.SimpleDateFormat;
import java.util.*;

import com.arjjs.ccm.common.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.ccm.syslog.dao.SysLogDao;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.flat.deviceuse.service.CcmDeviceUseService;
import com.arjjs.ccm.modules.sys.dao.UserDao;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Author: Li
 * @CreateTime: 2019-07-22 10:28
 * @Description: 统计控制层
 */
@Controller
@RequestMapping(value = "${adminPath}/analyst")
public class PersonalAnalystController {

    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private CcmDeviceUseService ccmDeviceUseService;
    @Autowired
    private UserDao userDao;

    private final int cacheSeconds=60*60*24;


    @RequestMapping(value = "personal")
    public String toPersonal() {
        return "/flat/analyst/personal";
    }

    @RequestMapping(value = "department")
    public String toDept() {
        return "/flat/analyst/department";
    }

    @RequestMapping(value = "getDataByDept", method = RequestMethod.GET)
    @ResponseBody
    public Object getDataForDept() {

//        Map<String, Object> getDataByDept = JedisUtils.getObjectMap("getDataByDept");
//
//        if(getDataByDept!=null){
//            return getDataByDept;
//        }

        List<User> findList = userDao.findUserByOfficeId(UserUtils.getUser());
        List<String> nameList = Lists.newArrayList();

        String[] ids = new String[findList.size()];

        for (int i = 0; i < findList.size(); i++) {
            ids[i] = findList.get(i).getId();
            nameList.add(findList.get(i).getName());
        }
        // 初始化数据容器
        List<Count> counts = Lists.newArrayList();
        for (String id : ids) {
            Count count = new Count();
            count.setUserId(id);
            counts.add(count);
        }

        // 部门成员查询记录数
        List<Count> query = sysLogDao.getDateForDept(ids);
        // 部门成员app登录次数与使用时长
        List<Count> times = ccmDeviceUseService.getDataByDept(ids);

        for (int i = 0; i < counts.size(); i++) {

            for (int j = 0; j < query.size(); j++) {
                if (counts.get(i).getUserId().equals(query.get(j).getUserId())) {
                    counts.get(i).setCounts(query.get(j).getCounts());
                    break;
                } else {
                    counts.get(i).setCounts("0");
                }
            }

            for (int j = 0; j < times.size(); j++) {
                if (counts.get(i).getUserId().equals(times.get(j).getUserId())) {
                    counts.get(i).setDays(times.get(j).getCounts());
                    counts.get(i).setTimes(times.get(j).getTimes());
                    break;
                } else {
                    counts.get(i).setDays("0");
                    counts.get(i).setTimes("0");
                }
            }

        }

        List<String> queryList = Lists.newArrayList();
        List<String> timesList = Lists.newArrayList();
        List<String> loginList = Lists.newArrayList();

        for (Count count : counts) {
            queryList.add(count.getCounts());
            loginList.add(count.getDays());
            timesList.add(count.getTimes());
        }

        HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(4);

        map.put("queryList", queryList);
        map.put("timesList", timesList);
        map.put("nameList", nameList);
        map.put("loginList", loginList);

//        JedisUtils.setObjectMap("getDataByDept",map,cacheSeconds);

        return map;

    }

    // 统计当前用户查询记录
    @RequestMapping(value = "getDataForPeople", method = RequestMethod.GET)
    @ResponseBody
    public Object getDateForPersonal() {

        Map<String, Object> getDataForPeople = JedisUtils.getObjectMap("getDataForPeople");
        if(getDataForPeople!=null){
            return getDataForPeople;
        }

        User user = UserUtils.getUser();

        List<String> dates = getDate();
        List<Count> counts = Lists.newArrayList();
        List<Count> days = sysLogDao.getLogByDays(user.getId(), dates.get(0), getNowDate());

        for (String date : dates) {
            Count count = new Count();
            count.setDays(date);
            counts.add(count);
        }

        for (int i = 0; i < counts.size(); i++) {

            for (int j = 0; j < days.size(); j++) {
                if (counts.get(i).getDays().equals(days.get(j).getDays())) {
                    counts.get(i).setCounts(days.get(j).getCounts());
                    break;
                } else {
                    counts.get(i).setCounts("0");
                }
            }

        }

        List<String> dateList = Lists.newArrayList();
        List<String> timesList = Lists.newArrayList();

        for (Count count : counts) {
            dateList.add(count.getDays());
            timesList.add(count.getCounts());
        }

        HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(3);
        map.put("dateList", dateList);
        map.put("timesList", timesList);
        map.put("user", user.getName());
        JedisUtils.setObjectMap("getDataForPeople",map,cacheSeconds);
        return map;
    }

    // 统计当前用户APP登录次数与使用时长
    @RequestMapping(value = "countUseTime", method = RequestMethod.GET)
    @ResponseBody
    public Object getForUse() {
        User user = UserUtils.getUser();

        List<String> dates = getDate();
        List<Count> counts = Lists.newArrayList();

        for (String date : dates) {
            Count count = new Count();
            count.setDays(date);
            counts.add(count);
        }

        List<Count> days = ccmDeviceUseService.countUseTime(user.getId(), dates.get(0), getNowDate());

        for (int i = 0; i < counts.size(); i++) {

            for (int j = 0; j < days.size(); j++) {
                if (counts.get(i).getDays().equals(days.get(j).getDays())) {
                    counts.get(i).setCounts(days.get(j).getCounts());
                    counts.get(i).setTimes(days.get(j).getTimes());
                    break;
                } else {
                    counts.get(i).setCounts("0");
                    counts.get(i).setTimes("0");
                }
            }

        }

        List<String> dateList = Lists.newArrayList();
        List<String> timeList = Lists.newArrayList();
        List<String> timesList = Lists.newArrayList();

        for (Count count : counts) {
            dateList.add(count.getDays());
            timeList.add(count.getCounts());
            timesList.add(count.getTimes());
        }

        HashMap<Object, Object> map = Maps.newHashMapWithExpectedSize(3);

        map.put("dateList", dateList);
        map.put("timeList", timeList);
        map.put("timesList", timesList);

        return map;
    }

    private List<String> getDate() {

        List<String> dateList = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 7; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - i);
            Date date = calendar.getTime();
            dateList.add(sdf.format(date));
        }

        return dateList;
    }

    private String getNowDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return sdf.format(calendar.getTime());
    }
}
