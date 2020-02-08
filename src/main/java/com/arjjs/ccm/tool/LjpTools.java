package com.arjjs.ccm.tool;

import com.arjjs.ccm.common.utils.StringUtils;

import java.util.Date;

/**
 * 工具类
 *
 * @author: li jiupeng
 * @create: 2019-08-19 11:14
 */
public class LjpTools {

    /**
     * 如果是null返回后面的参数值 （相同类型）
     * @param t 比对对象
     * @param val 返回值
     * @param <T>
     * @return
     */
    public static  <T> T IfNull(T t, T val){
        if(t==null){
            return val;
        }else{
            return t;
        }
    }


    /**
     * 判断多个字符串是否为空 其中不能有空字符串空格 其中有一个为空就返回true 反之false
     * @param strings
     * @return
     */
    public static boolean isExistBlank(String... strings){

        for (String string : strings) {
            if(StringUtils.isBlank(string)){
                return true;
            }
        }

        return false;
    }

    /**
     * 全部不为空返回true
     * @param strings
     * @return
     */
    public static boolean isNotExistBlank(String... strings){
        return !isExistBlank(strings);
    }

    /**
     * 全部不为空返回true
     * @param strings
     * @return
     */
    public static boolean isNotExistEmpty(String... strings){
        return !isExistEmpty(strings);
    }

    /**
     * 判断多个字符串是否为空 其中有一个为空就返回true 反之false
     * @param strings
     * @return
     */
    public static boolean isExistEmpty(String... strings){

        for (String string : strings) {
            if(StringUtils.isEmpty(string)){
                return true;
            }
        }

        return false;
    }

    /**
     * 是否有null
     * @param integers
     * @return
     */
    public static boolean isExistNull(Integer... integers){
        for (Integer integer : integers) {
            if(integer==null){
                return true;
            }
        }
        return false;
    }

    /**
     * 是否有null 和 0
     * @param integers
     * @return
     */
    public static boolean isExistNullOrZero(Integer... integers){
        for (Integer integer : integers) {
            if(integer==null || integer==0){
                return true;
            }
        }
        return false;
    }
    /**
     * 是否有null 和 0
     * @param integers
     * @return
     */
    public static boolean isExist(Object... objects){

        for (Object object : objects) {
            if (object instanceof Integer) {
            } else if (object instanceof String) {
            } else if (object instanceof Double) {
            } else if (object instanceof Float) {
            } else if (object instanceof Long) {
            } else if (object instanceof Boolean) {
            } else if (object instanceof Date) {
            }
        }

        return false;
    }

}
