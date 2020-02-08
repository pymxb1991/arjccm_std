package com.arjjs.ccm.modules.ccm.background;

import com.arjjs.ccm.common.utils.MacUtils;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("${adminPath}/background/")
public class Background {

    @RequestMapping("index")
    public String index() {

        return "background/background";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(MultipartFile file, Integer num) {

        Map map = Maps.newHashMap();

        if (file == null) {
            map.put("code", "0");
            map.put("msg", "图片为空");
            return map;
        }

        // 背景图片存放路径
        String targetPath = getXmlPath().substring(0, getXmlPath().length() - 8) + "static" + File.separator + "images" + File.separator + "login_bg";

        saveUploadFiles(targetPath, file, num);

        map.put("code", "200");
        map.put("msg", "上传成功");

        return map;

    }


    public static void saveUploadFiles(String targetPath, MultipartFile multipartFile, Integer num) {

        String uploadFileName = multipartFile.getOriginalFilename();
        // 获取文件类型
        String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        // 文件名
        // String fileName = UUID.randomUUID().toString().replace("-", "") + fileType;
        String fileName = "login_bg" + num + fileType;
        // 路径
        File targetFile = new File(targetPath + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static String getXmlPath() {
        // file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/
        // file:/usr/tomcat/tomcat8/webapps/arjccm/WEB-INF/classes/
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();

        //  System.out.println("license file path:" + path);
        if (MacUtils.getOSName().startsWith("windows")) {//Windows的文件路径
            path = path.replace('/', '\\'); // 将/换成\
            path = path.replace("file:", ""); // 去掉file:
            path = path.replace("classes\\", ""); // 去掉class\
            path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
        } else {//linux的文件路径
            path = path.replace("file:", ""); // 去掉file:
            path = path.replace("classes/", ""); // 去掉class\
        }

        return path;
    }

}
