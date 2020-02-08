/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.risk.report.entity.RiskMassesOpinion;
import com.arjjs.ccm.modules.risk.report.service.RiskMassesOpinionService;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.ImportExecl;
import com.arjjs.ccm.tool.ImportExeclMap;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 社情民意调查Controller
 * @author liang
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/report/riskMassesOpinion")
public class RiskMassesOpinionController extends BaseController {

	@Autowired
	private RiskMassesOpinionService riskMassesOpinionService;
	
	@ModelAttribute
	public RiskMassesOpinion get(@RequestParam(required=false) String id) {
		RiskMassesOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskMassesOpinionService.get(id);
		}
		if (entity == null){
			entity = new RiskMassesOpinion();
		}
		return entity;
	}
	//社情民意分析列表
	@RequiresPermissions("report:riskMassesOpinion:view")
	@RequestMapping(value = "listMap")
	public String listMap(RiskMassesOpinion riskMassesOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskMassesOpinion> page = riskMassesOpinionService.findPage(new Page<RiskMassesOpinion>(request, response), riskMassesOpinion); 
		model.addAttribute("page", page);
		return "risk/report/riskMassesOpinionListMap";
	}
	//社情民意分析统计图
	@RequiresPermissions("report:riskMassesOpinion:view")
	@RequestMapping(value = "formMap")
	public String formMap(RiskMassesOpinion riskMassesOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		int popNum = 0;//参与人数
		
		ImportExecl poi = new ImportExecl();//读取文件
	 	String url = request.getSession().getServletContext().getRealPath("/");//获取文件路径/java1/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/arjccmNew/
	 	url = url.replaceAll("\\\\","/");
	 	String url2 = riskMassesOpinion.getFile();//获取文件路径|/arjccm/userfiles/1/files/report/riskMassesOpinion/2018/04/%E6%B5%8B%E8%AF%95%E7%B1%BB%E5%9E%8B.xls
	 	url2 = StringUtils.substringAfter(url2,"/");
	 	url2 = StringUtils.substringAfter(url2,"/");
	 	url2 = StringUtils.substringBeforeLast(url2,"/");
	 	url += url2+"/";
	 	String url3 = riskMassesOpinion.getFileName();//获取文件名：测试类型.xls
	 	url += url3;
	 	System.out.println(url);
	 	
	 	JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
	 	List<ImportExeclMap> importExeclMapList = new ArrayList<ImportExeclMap>();//封装list
	 	String title = "";//标题
        String type = "";//类型：单选-多选
        
        List<List<String>> list = poi.read(url);//获取文档内容
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	System.out.print("第" + (i) + "行");
                popNum = i;//参与人数
                List<String> cellList = list.get(i);//第i行
                for (int j = 0; j < cellList.size(); j++) {
                	System.out.print("    第" + (j + 1) + "列值：");
                    System.out.print("    " + cellList.get(j));
                    String str1 = cellList.get(j);//第j个单元格
                    
                    if(i==0){
                    	title = StringUtils.substringBeforeLast(str1,"[");//封装-title
                    	type = StringUtils.substringAfter(str1,"[");
                    	type = StringUtils.substringBeforeLast(type,"]");//封装-type
                    	
                    	if("单选题".equals(type)){
                    	 	List<EchartType> echartTypeList = new ArrayList<EchartType>();
                    		for (int k = 1; k < list.size(); k++) {
                    			List<String> cellList2 = list.get(k);
                    			String t1 = cellList2.get(j);//获取相对应的第j列内容
                    			int count = 0;
                    			for (int l = 1; l < list.size(); l++) {
                        			List<String> cellList3 = list.get(l);
                        			String t2 = cellList3.get(j);//获取相对应的第j列内容
                        			if(t1.equals(t2)){
                        				count += 1;//分类+1
                        			}
                        			
                    			}
                    			EchartType echartType = new EchartType();
                    			echartType.setType(t1);
                    			echartType.setValue(count+"");
                    			if(echartTypeList.size()==0){
                    				echartTypeList.add(echartType);//存入
                    			}else{
	                    			int n = 0;
	                    			for(int m=0;m<echartTypeList.size();m++){
	                    				if(echartTypeList.get(m).getType().equals(echartType.getType())){
	                    					n+=1;
	                    				}
	                    			}
	                    			if(n==0){
	                    				echartTypeList.add(echartType);//存入
	                    			}
                    			}
                    		}
                    		ImportExeclMap importExeclMap = new ImportExeclMap();
                    		String echartTypeListAll = JSONArray.fromObject(echartTypeList,config).toString(); //Json串
                    		importExeclMap.setId(IdGen.uuid());
                    		importExeclMap.setTitle(title);
                            importExeclMap.setType(type);
                            importExeclMap.setListJson(echartTypeListAll);
                            importExeclMapList.add(importExeclMap);//存入
                    		
                    	}else if("多选题".equals(type)){
                    		String title3 = "";//标题-多选题
                            List<EchartType> echartTypeList2 = new ArrayList<EchartType>();//多选type和value
                    		
                    		for (int m = 0; m < list.size(); m++) {
                    			List<String> cellList2 = list.get(m);
                    			for (int n = 0; n < cellList2.size(); n++) {
                    				String str2 = cellList.get(n);//获取元素
                    				if(m==0){
                                    	title3 = StringUtils.substringBeforeLast(str2,"[");//获取每列的title
                    				}
                    				
                    				if(title.equals(title3)){
                    					String t3 = StringUtils.substringAfter(str2,"－");//获取每列的类型type
                    					int count2 = 0;//多选value
                    					for (int k = 1; k < list.size(); k++) {
                                			List<String> cellList4 = list.get(k);
                                			String countString = cellList4.get(n);
                                			count2 += Integer.parseInt(countString);//获取每列的值value
                                		}
                    					
                    					EchartType echartType = new EchartType();
                            			echartType.setType(t3);
                            			echartType.setTypeO(title3);
                            			echartType.setValue(count2+"");
                            			echartTypeList2.add(echartType);//存入
                    				}
                        		}
                    		}
                    		ImportExeclMap importExeclMap = new ImportExeclMap();
                    		String echartTypeListAll = JSONArray.fromObject(echartTypeList2,config).toString(); //Json串
                    		importExeclMap.setId(IdGen.uuid());
                    		importExeclMap.setTitle(title);
                            importExeclMap.setType(type);
                            importExeclMap.setListJson(echartTypeListAll);
                            if(importExeclMapList.size()==0){
                            	importExeclMapList.add(importExeclMap);//存入
                            }else{
                            	int n = 0;
                            	for(int o=0;o<importExeclMapList.size();o++){
                            		if(importExeclMapList.get(o).getTitle().equals(title)){
                            			n +=1;
                            		}
                                }
                            	if(n==0){
                            		importExeclMapList.add(importExeclMap);//存入
                    			}
                            }
                    	}
                    	
                    }
                    
                }
                System.out.println();
            }

        }
		
		model.addAttribute("riskMassesOpinion", riskMassesOpinion);
		model.addAttribute("popNum", popNum);//参与人数
		model.addAttribute("importExeclMapList", importExeclMapList);//封装list
		return "risk/report/riskMassesOpinionFormMap";
	}
	/**
	 * 导出模板数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws FileNotFoundException 
	 */
	
	@RequiresPermissions("report:riskMassesOpinion:view")
	@RequestMapping(value = "export")
	public void exportFile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws FileNotFoundException {

		String fileName = "template.xls".toString();
		String url = request.getSession().getServletContext().getRealPath("/");
	 	url = url.replaceAll("\\\\","/");//获取文件路径/java1/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/arjccmNew/
	 	url = StringUtils.substringBefore(url,".metadata/");
		//InputStream inStream = new FileInputStream(url+"arjccm/WebContent/static/risk/file/template.xls");// 文件的存放路径
		InputStream inStream = new FileInputStream(url+"static/risk/file/template.xls");// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
						
		//return "redirect:"+Global.getAdminPath()+"/report/riskMassesOpinion/?repage";
	}
	
	
	
	
	
	
	
	//
	@RequiresPermissions("report:riskMassesOpinion:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskMassesOpinion riskMassesOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskMassesOpinion> page = riskMassesOpinionService.findPage(new Page<RiskMassesOpinion>(request, response), riskMassesOpinion); 
		model.addAttribute("page", page);
		return "risk/report/riskMassesOpinionList";
	}

	@RequiresPermissions("report:riskMassesOpinion:view")
	@RequestMapping(value = "form")
	public String form(RiskMassesOpinion riskMassesOpinion, Model model) {
		model.addAttribute("riskMassesOpinion", riskMassesOpinion);
		return "risk/report/riskMassesOpinionForm";
	}

	@RequiresPermissions("report:riskMassesOpinion:edit")
	@RequestMapping(value = "save")
	public String save(RiskMassesOpinion riskMassesOpinion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskMassesOpinion)){
			return form(riskMassesOpinion, model);
		}
		riskMassesOpinionService.save(riskMassesOpinion);
		addMessage(redirectAttributes, "保存社情民意调查成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskMassesOpinion/?repage";
	}
	
	@RequiresPermissions("report:riskMassesOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskMassesOpinion riskMassesOpinion, RedirectAttributes redirectAttributes) {
		riskMassesOpinionService.delete(riskMassesOpinion);
		addMessage(redirectAttributes, "删除社情民意调查成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskMassesOpinion/?repage";
	}

}