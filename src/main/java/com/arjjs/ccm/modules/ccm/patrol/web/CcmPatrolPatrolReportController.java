package com.arjjs.ccm.modules.ccm.patrol.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUnit;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolMissionsService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUnitService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.DictService;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.tool.DateTools;
import com.arjjs.ccm.tool.JsonResult;
import com.arjjs.ccm.tool.LjpTools;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import word.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 巡逻报告 Controller
 *
 * @author: li jiupeng
 * @create: 2019-07-12 11:19
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/patrolReport")
public class CcmPatrolPatrolReportController {

    @Autowired
    private CcmPatrolMissionsService ccmPatrolMissionsService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private DictService dictService;
    @Autowired
    private SystemService systemService;

    @Autowired
    private CcmPatrolUnitService ccmPatrolUnitService;


    private final int ONE_DAY=86400000;

    @RequestMapping(value = "/")
    public String index(){

        return "ccm/patrol/ccmPeoplePatrolReport";
    }

    @RequestMapping(value = "/getChartData")
    @ResponseBody
    public JsonResult getChartData(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){

        if(startDate==null || endDate==null){
            return  JsonResult.error("时间为空");
        }

        //开始时间
        Date date1 = DateTools.strToDate(startDate);
        //结束时间
        Date date = DateTools.strToDate(endDate);
        //结束时间time
        long time1 = date.getTime();
        //开始时间time
        long time2 = date1.getTime();
        int day= (int) (time1 - time2) / ONE_DAY;
        Set<String> officeSet=new HashSet<>();
        List<CcmPatrolMissions> listByDate = ccmPatrolMissionsService.findListByDate(date1, date);
        Map<String,Map> map=new HashMap<>();
        List<String> key= Lists.newArrayList();
        Integer [] taskNum=new Integer[day];
        for (int i = 1; i <= day; i++) {
            long time = ONE_DAY * i;
            long stemp=time2+time;
            long etemp=time2+time+ONE_DAY;
            //每个部门下的任务数量
            int z=0;
            Map<String,Integer> stringIntegerMap=new HashMap<>();
            for (CcmPatrolMissions item : listByDate) {
                if(item.getCreateDate().getTime()>stemp && item.getCreateDate().getTime()<etemp){
                    z++;
                    item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
                            idToOfficeName(item.getOffice().split(",")) :
                            item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :"");
                    //判断是否 数组
                    if(item.getOfficeName().indexOf(",")!=-1){
                        String[] split = item.getOfficeName().split(",");
                        //循环加入
                        for (int i1 = 0; i1 < split.length; i1++) {
                            //判断原先有值吗
                            if(stringIntegerMap.get(split[i1])==null && split[i1]!=""){
                                stringIntegerMap.put(split[i1],1);
                            }else if(split[i1]!=""){
                                stringIntegerMap.put(split[i1],stringIntegerMap.get(split[i1])+1);
                            }
                            officeSet.add(split[i1]);
                        }
                    }else{
                        officeSet.add(item.getOfficeName());
                        //判断原先有值吗
                        if(stringIntegerMap.get(item.getOfficeName())==null && item.getOfficeName()!=null){
                            stringIntegerMap.put(item.getOfficeName(),1);
                        }else if(item.getOfficeName()!=""){
                            stringIntegerMap.put(item.getOfficeName(),stringIntegerMap.get(item.getOfficeName())+1);
                        }
                    }

//                    list.add(item);
                }
            }
            //天数对应的任务数量
            taskNum[i-1]=z;

            try {
                String toStr = DateTools.dateToStr(new Date(stemp));
                key.add(toStr);
                map.put(toStr,stringIntegerMap);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        List<String> stringObjectMap=Lists.newArrayList();
        //列表数据转换格式
        for (int i = 0; i < key.size(); i++) {
            String temp=key.get(i);
            Map<String,Integer>  map1 = map.get(temp);
            StringBuilder stringBuilder=new StringBuilder();
            AtomicInteger intTem= new AtomicInteger();
            map1.forEach((item,inte)->{
                if(StringUtils.isBlank(stringBuilder.toString()) ){
                    stringBuilder.append(item);
                    intTem.addAndGet(inte);
                }else{
                    stringBuilder.append(",");
                    stringBuilder.append(item);
                    intTem.addAndGet(inte);
                }


            });
            stringObjectMap.add(temp+"|"+stringBuilder.toString()+"|"+taskNum[i]);
        }


        Map<String,Integer[]> stringIntegerMap=new HashMap<>();
        //统计图数据格式
        for (String s : officeSet) {
            Integer [] intArr=new Integer[key.size()];
            for (int i = 0; i < key.size(); i++) {
                String temp=key.get(i);
                Map<String,Integer>  map1 = map.get(temp);
                if(map1.get(s)!=null){
                    intArr[i]=map1.get(s);
                }else{
                    intArr[i]=0;
                }

            }
            stringIntegerMap.put(s,intArr);

        }

        Map<String,Object> data=new HashMap<>();
        data.put("intArr",stringIntegerMap);
        data.put("key",key);
        data.put("officeSet",officeSet);
        data.put("stringList",stringObjectMap);
        return JsonResult.ok(data);
    }

    /**
     *如果为null 则没有权限查询
     */
    private String idToOfficeName(String [] strings){
        StringBuilder name=new StringBuilder();
        for (int i = 0; i < strings.length; i++) {

            Office office = officeService.get(strings[i]);
            if(office==null){
                return null;
            }
            if(i==0){
                name.append(office.getName());
            }else{
                name.append(",");
                name.append(office.getName());
            }
        }
        return name.toString();
    }

    @RequestMapping(value = "ListByDate")
    public String ListByDate(String date, Model model){
        if(StringUtils.isBlank(date)){
            return "/error/404";
        }

        Date strToDate = DateTools.strToDate(date);
        Date endDate=new Date(strToDate.getTime()+ONE_DAY);
        List<CcmPatrolMissions> listByDate = ccmPatrolMissionsService.findListByDate(strToDate, endDate);
        listByDate.forEach(item->item.setOfficeName(item.getOffice().indexOf(",")!=-1 ?
                idToOfficeName(item.getOffice().split(",")) :
                item.getOffice().length()>0 ? officeService.get(item.getOffice()).getName() :""
        ));

        model.addAttribute("list",listByDate);
        return "ccm/patrol/ccmPatrolMissionsListByDate";
    }


    @RequestMapping(value = "downloadImg")
    @ResponseBody
    public JsonResult downloadImg(String baseStr){
        baseStr=baseStr.split(",")[1];
        BASE64Decoder base64Decoder=new BASE64Decoder();
        try {
            byte[] bytes = base64Decoder.decodeBuffer(baseStr);
            // 处理数据
            OutputStream outputStream=new FileOutputStream("C:\\Users\\dongqikai\\Videos\\a\\1.jpeg");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.ok(null);
    }

    /**
     *如果为null 则没有权限查询
     */
    private String idToUserName(String [] strings){
        StringBuffer name=new StringBuffer();
        for (int i = 0; i < strings.length; i++) {

            User user = systemService.getUser(strings[i]);
            if(user==null){
                continue;
            }
            if(name.length()>0){
                name.append(",");
            }
            name.append(user.getName());
        }
        return name.toString();
    }


    /**
     * java2word 方式导出
     * @param dateStr
     * @param baseStr
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "exportWord")
    @ResponseBody
    public JsonResult exportWord(String dateStr,String baseStr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String outPath = Global.getConfig("FILE_DOWNLOAD_PATH");

        File path=new File(outPath);
        if(!path.exists()){
            path.mkdir();
        }


        String fileName = outPath + dateStr + ".doc";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            return JsonResult.ok(dateStr);
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", dateStr);
        dataMap.put("img", baseStr);

        //查询当天的数据
        Date strToDate = DateTools.strToDate(dateStr);
        Date endDate=new Date(strToDate.getTime()+ONE_DAY);
        List<CcmPatrolMissions> listByDate = ccmPatrolMissionsService.findListByDate(strToDate, endDate);
        //数据字典
        Dict dict = new Dict();
        dict.setLabel("");
        //总数据
        List<Object> detail = new ArrayList<>();
        //添加每行
        listByDate.forEach(item->{
            //当前行
            Map<String, String> map1 = new HashMap<>();
            //查询状态
            dict.setValue(item.getStatus());
            List<Dict> all = dictService.findAll(dict);
            if(all.size()>0){
                map1.put("status", all.get(0).getName());
            }
            //查询人员
            CcmPatrolUnit ccmPatrolUnit = new CcmPatrolUnit();
            ccmPatrolUnit.setMissions(item);
            List<CcmPatrolUnit> list = ccmPatrolUnitService.findList(ccmPatrolUnit);
            if(list.size()>0){
                map1.put("people",  LjpTools.IfNull(list.get(0).getUserName(),""));
                //巡逻车辆
                map1.put("car", LjpTools.IfNull(list.get(0).getPatrolVehicles(),""));
                //车载设备
                map1.put("device", LjpTools.IfNull(list.get(0).getVehicleEquipment(),"") );
                //单兵装备
                map1.put("equipment", LjpTools.IfNull(list.get(0).getIndividualEquipment(),""));
            }else{
                map1.put("people","" );
                //巡逻车辆
                map1.put("car", "");
                //车载设备
                map1.put("device", "");
                //单兵装备
                map1.put("equipment", "");
            }
            //巡逻任务
            map1.put("task", item.getPatrolContent());
            detail.add(map1);
        });


        dataMap.put("detail", detail);

        //Configuration用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

        String rootPath = request.getSession().getServletContext().getRealPath("/");
        configuration.setDirectoryForTemplateLoading(new File(rootPath + "static\\template\\word"));

        //以utf-8的编码读取ftl文件
        Template t =  configuration.getTemplate("template.ftl","utf-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),1024);
        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        out.close();
        return JsonResult.ok(dateStr);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/download/{filename:.+}/{downloadName}")
    public void getDownload(@PathVariable String filename, @PathVariable("downloadName") String downloadName, HttpServletRequest request,
                            HttpServletResponse response) throws FileNotFoundException {
        String rootPath = Global.getConfig("FILE_DOWNLOAD_PATH");

        File file=new File(rootPath + filename + ".doc");
        if(!file.exists()){
            throw new FileNotFoundException("没有找到文件:"+filename + ".doc");
        }

        // 读到流中
        InputStream inStream = null;// 文件的存放路径
        try {
            inStream = new FileInputStream(rootPath + filename + ".doc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        //中文支持
        try {
            String newDownloadName = new String(downloadName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName=" + newDownloadName);
        } catch (Exception e) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);
        }
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
    }
}
