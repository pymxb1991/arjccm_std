package com.arjjs.ccm.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CretaCode {

	/** 订单类别头 */
    private static final String POLICE_CODE = "CJ";
	//我要获取当前的日期
    private static Date date = new Date();
    //设置要获取到什么样的时间
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    //获取String类型的时间
    private static String createdate = sdf.format(date);
    /**
     * 生成订单单号编码
     * @param userId
     */
    public static String getCreateCode(String longinName){
        return POLICE_CODE + longinName+createdate;
    }
}
