package com.arjjs.ccm.tool;


import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字工具包
 *
 * @author: li jiupeng
 * @create: 2019-07-03 10:49
 */
public class NumberTools {


    @Test
    public void test(){
        System.out.println(isPhone("11610336925"));
    }

    /**
     * 是否是数字
     * @return boolean
     */
    public static boolean isNumber(String string){

        if(string !=null && string.length()>0) {
            for (int i = string.length(); --i >= 0; ) {
                int chr = string.charAt(i);
                if (chr < 48 || chr > 57)
                    return false;
            }
        }else{
            return false;
        }

        return true;
    }

    /**
     * 验证是否为手机号
     * @return boolean
     */
    public static boolean isPhone(String str){

        if(NumberTools.isNumber(str) && str.length()==11){
            String regex="^1(3|4|5|6|7|8|9)\\d{9}$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(str);
            if(matcher.matches()){
                return true;
            }
        }

        return false;
    }


}
