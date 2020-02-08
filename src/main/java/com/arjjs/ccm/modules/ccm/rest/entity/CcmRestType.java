package com.arjjs.ccm.modules.ccm.rest.entity;

/**
 * 类型静态类
 * 
 * @author pengjianqiang
 * @version 2018-02-02
 */
public class CcmRestType {

    public static final int OK = 0;					        //成功
    public static final int ERROR_PARAM = -1;				    //参数错误
    public static final int ERROR_DB_CONN = -2;				//数据库连接错误
    public static final int ERROR_DB_EXCEPTION = -3;		    //数据库异常
    public static final int ERROR_DB_EXIST = -4;			    //数据已存在
    public static final int ERROR_USER_NOT_EXIST = -5;		//用户不存在，或者已更改
    public static final int ERROR_PASSWORD = -6;			    //密码错误
    public static final int ERROR_SAME_USER_LOGON = -7;	    //同名用户登录
    public static final int ERROR_USER_INACTIVE = -8;		//用户未激活或者已过期
    public static final int ERROR_NO_PERSSION = -9;		    //用户没有权限
    public static final int ERROR_ERR_LOCKED = -10;			//资源已锁定
    public static final int ERROR_RES_BUSY = -11;			//资源忙
    public static final int ERROR_RES_NOT_EXIST = -12;		//该设备不存在，或者已更改
    public static final int ERROR_DB_NOT_EXIST = -13;		//数据不存在
    public static final int ERROR_NO_LOGINNAME = -14;				    //登录名为空
    public static final int ERROR_NO_PASSWORD = -15;				    //登录密码为空
    public static final int ERROR_NO_USER = -16;                        //无该用户
	public static final int ERROR_NO_MODIFY_PERMISSIONS = -17;    ////无修改权限
	public static final int ERROR_NO_EXAMINATION_REPORT = -19;    ////无审核意见
	public static final int CASEDEAL_SAVE_SUCCEED = -20;    ////保存事件处理成功
	public static final int SUBMIT_REPEAT = -21;    ////重复提交

	
	public static final String REAL_MONITOR_UUID = "ccm-4232342-activemq-topic-id";

	
	
}
