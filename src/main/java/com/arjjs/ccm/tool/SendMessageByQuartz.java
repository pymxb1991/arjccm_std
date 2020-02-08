package com.arjjs.ccm.tool;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.oa.service.OaNotifyService;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.Map;

/**
 * 定时发送消息
 *
 * @author: li jiupeng
 * @create: 2019-07-11 16:16
 */
public class SendMessageByQuartz implements Job {

       private Logger log = Logger.getLogger(this.getClass());
    /**
     * 任务类型标识 自定义
     */
    private final String TYPE="guard_time_task";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取map 数据
        JobDataMap jobDataMap=jobExecutionContext.getMergedJobDataMap();

        OaNotifyService oaNotifyService= (OaNotifyService) jobDataMap.get("oaNotifyService");
        OfficeService officeService= (OfficeService) jobDataMap.get("officeService");
        String ids= (String) jobDataMap.get("ids");
        String userIds= (String) jobDataMap.get("userIds");
        String title= (String) jobDataMap.get("title");
        String content= (String) jobDataMap.get("content");
        String status= (String) jobDataMap.get("status");
        String taskUrl= (String) jobDataMap.get("taskUrl");
        String thisId= (String) jobDataMap.get("thisId");

        if(StringUtils.isNotBlank(userIds)){
            String[] split = userIds.split(",");
            for (int i = 0; i < split.length; i++) {
                //通知到手机APP
                Map<String, String> map = Maps.newHashMap();
                map.put("type",TYPE);
                map.put("id", thisId);
                map.put("name", title);
                if(MessageTools.sendAppMsgByUserId(split[i], map)){
                    log.info("定时通知app执行完毕url:"+taskUrl);
                }else{
                    log.error("定时通知app执行失败url:"+taskUrl);
                }
            }
        }

        if(StringUtils.isNotBlank(ids)){
            if(MessageTools.sendMessageByOfficePersonInCharge(oaNotifyService,officeService,ids,title,content,status,taskUrl)){

                log.info("定时通知执行完毕url:"+taskUrl);
                Scheduler scheduler=jobExecutionContext.getScheduler();
                try {
                    scheduler.shutdown();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }else{
                log.error("定时通知执行失败url:"+taskUrl);
            }
        }

    }
}
