package com.arjjs.ccm.tool;

import org.quartz.*;

/**
 * 定时执行工具类
 *
 * @author: li jiupeng
 * @create: 2019-07-11 18:23
 */
public class JobDetailTools {


    /**
     * 添加一个 定时任务
     *
     * @param c 写好的 预执行类
     * @param jobDataMap 需要传给 预执行类的 mao
     * @param name 执行任务名称
     * @param group 执行任务组
     * @param time 需要执行的 时间差  毫秒为单位
     * @param repeatCount 执行的次数
     * @return
     */
    public static boolean addJobDeteil(Class c ,JobDataMap jobDataMap,String name,String group,String time,int repeatCount){

        try {

            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

            Scheduler scheduler = schedFact.getScheduler();


            //设定执行 类
            JobDetail jobDetail= JobBuilder.newJob(c)
                    .withIdentity(name,group)
                    .setJobData(jobDataMap)
                    .build();

            //设定 执行条件
            Trigger trigger=TriggerBuilder.newTrigger()
                    .withIdentity(name,group)
                    .startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(time))
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(10)
//                            .withRepeatCount(repeatCount))
                    .build();

            //把执行类和执行条件 加入到定时任务里
            scheduler.scheduleJob(jobDetail,trigger);
            if(!scheduler.isShutdown()){
                //开启任务
                scheduler.start();
            }


        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
        return  true;

    }
}
