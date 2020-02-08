package com.arjjs.ccm.tool;

import com.arjjs.ccm.common.utils.StringUtils;

/**
 * string 工具类
 *
 * @author: li jiupeng
 * @create: 2019-08-12 14:47
 */
public class StringTool {


    /**
     * 判断多个字符串是否为空 其中不能有空字符串空格 其中有一个为空就返回true 反之false
     * @param strings
     * @return
     */
    public static boolean isAllBlank(String... strings){

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
    public static boolean isNotAllBlank(String... strings){
        return !isAllBlank(strings);
    }

    /**
     * 全部不为空返回true
     * @param strings
     * @return
     */
    public static boolean isNotAllEmpty(String... strings){
        return !isAllEmpty(strings);
    }

    /**
     * 判断多个字符串是否为空 其中有一个为空就返回true 反之false
     * @param strings
     * @return
     */
    public static boolean isAllEmpty(String... strings){

        for (String string : strings) {
            if(StringUtils.isEmpty(string)){
                return true;
            }
        }

        return false;
    }


}
