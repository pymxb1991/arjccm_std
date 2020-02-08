package com.arjjs.ccm.tool;

import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.oa.entity.OaNotify;
import com.arjjs.ccm.modules.oa.entity.OaNotifyRecord;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 *
 * @author: li jiupeng
 * @create: 2019-07-11 15:09
 */
public class MessageTools {


    /**
     * 根据部门 负责人发送消息
     * @param oaNotifyService 消息通知service not null
     * @param officeService 部门service not null
     * @param officeIds 部门ids 列如:'1234asdasd,123132aasd' or '123asd'
     * @param cotent 发送消息的内容
     * @param title 发送消息的标题
     * @param status 0草稿 1 发布
     * @param taskUrl 阅览链接 到controller的链接
     * @return boolean 类型是否发送成功
     *
     */
    public static boolean sendMessageByOfficePersonInCharge(
            OaNotifyService oaNotifyService, OfficeService officeService, String officeIds, String title, String cotent, String status, String taskUrl) {
        if(oaNotifyService==null || officeService==null || StringUtils.isBlank(officeIds)){
            return false;
        }
        //判断是单个部门还是多个部门
        String[] split = officeIds.split(",");
        List<OaNotifyRecord> oaNotifyRecords = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            Office office = officeService.get(split[i]);
            if (office != null) {
                if (office.getPrimaryPerson() != null) {
                    String id = office.getPrimaryPerson().getId();
                    OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
                    oaNotifyRecord.setUser(new User(id));
                    oaNotifyRecord.setReadFlag("0");
                    oaNotifyRecords.add(oaNotifyRecord);
                }
            }
        }
        //确认有通知数据
        if (oaNotifyRecords.size() > 0) {
            OaNotify oaNotify = new OaNotify();
            oaNotify.setTitle(title);
            oaNotify.setContent(cotent);
            oaNotify.setStatus("1");
            oaNotify.setTaskUrl(taskUrl);
            oaNotify.setOaNotifyRecordList(oaNotifyRecords);
            oaNotifyService.save1(oaNotify);
            return true;
        }
        return false;
    }

    /**
     * 根据用户 负责人发送消息
     * @param oaNotifyService 消息通知service not null
     * @param officeService 部门service not null
     * @param officeIds 部门ids 列如: '123asd'
     * @param cotent 发送消息的内容
     * @param title 发送消息的标题
     * @param status 0草稿 1 发布
     * @param taskUrl 阅览链接 到controller的链接
     * @return boolean 类型是否发送成功
     *
     */
    public static boolean sendMessageByUser(
            OaNotifyService oaNotifyService, OfficeService officeService, String userId, String title, String cotent, String taskUrl) {
        if(oaNotifyService==null || officeService==null || StringUtils.isBlank(userId)){
            return false;
        }
        //判断是单个部门还是多个部门
        List<OaNotifyRecord> oaNotifyRecords = new ArrayList<>();
        User user = UserUtils.get(userId);
        if (user != null) {
            String id = user.getId();
            OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
            oaNotifyRecord.setUser(user);
            oaNotifyRecord.setReadFlag("0");
            oaNotifyRecord.setCreateBy(user);
            oaNotifyRecord.setUpdateBy(user);
            oaNotifyRecords.add(oaNotifyRecord);
        }
        //确认有通知数据
        if (oaNotifyRecords.size() > 0) {
            OaNotify oaNotify = new OaNotify();
            oaNotify.setTitle(title);
            oaNotify.setContent(cotent);
            oaNotify.setStatus("1");
            oaNotify.setTaskUrl(taskUrl);
            oaNotify.setOaNotifyRecordList(oaNotifyRecords);
            oaNotify.setCreateBy(user);
            oaNotify.setUpdateBy(user);
            oaNotifyService.save1(oaNotify);
            return true;
        }
        return false;
    }

    /**
     * 向单位负责人发消息
     * @param officeService
     * @param ccmReliefTask
     * @param map
     * @return
     */
    public static boolean sendAppMsgByOfficeId(OfficeService officeService, String officeIds, Map<String, Object> map) {
        if (officeService == null || officeIds == null || "".equals(officeIds)) {
            return false;
        }

        String[] officeIdArr = officeIds.split(",");//获得单位数组

        for (int i = 0; i < officeIdArr.length; i++) {
            Office office = officeService.get(officeIdArr[i]);
            if (office != null) {
                if (!EntityTools.isEmpty(office.getPrimaryPerson())) {
                    String message = JSONObject.toJSONString(map);
                    //用人员的数据库主键作为队列的名字
                    RabbitMQServerTools.sendSampleMessage(office.getPrimaryPerson().getId(), message);
                }
            }
        }
        return true;
    }

    /**
     * 负责人发送给民警
     * @return
     */
    public static boolean sendAppMsgByUserId(String userId, Map<String, String> map) {
        if ("".equals(userId) || userId == null) {
            return false;
        }
        String message = JSONObject.toJSONString(map);
        RabbitMQServerTools.sendSampleMessage(userId, message);
        return true;
    }
}
