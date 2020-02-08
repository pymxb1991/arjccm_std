package com.arjjs.ccm.tool;

import com.arjjs.ccm.modules.ccm.sys.dao.SysDictsDao;
import com.arjjs.ccm.modules.flat.analyst.web.KpiCountController;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: Li
 * @CreateTime: 2019-07-30 17:32
 * @Description: 绩效统计定时数据更新
 */
public class TimedTask implements Job {

    @Autowired
    private KpiCountController kpiCountController;
    private Logger log = Logger.getLogger(this.getClass());


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println(kpiCountController);
        System.out.println("-------------------------------------------");
    }
}
