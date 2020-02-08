package com.arjjs.ccm.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    public static List<String> getImgStr(String htmlStr) {
        List<String> list = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                list.add(m.group(1));
            }
        }
        return list;
    }


    public static String getUrlAndPort1(String Str) {
        //切分
        String regex = "/";
        String[] strings = Str.split(regex);
        //输出结果
        System.out.println(strings[2]);
        return strings[2];
    }

    public static String getStr(String Str) {
        String newStr = Str.replace("http://", "");
        String string = newStr.substring(newStr.indexOf("/"),newStr.length());
        System.out.println(string);
        return string;
    }


    public static String getUrlAndPort3(String Str) {
        Pattern pattern = Pattern.compile("[^http://]*?.com");
        Matcher matcher = pattern.matcher(Str);
        while(matcher.find()){
            String group = matcher.group();
            System.out.println(group);
        }
        return null;
    }
}
