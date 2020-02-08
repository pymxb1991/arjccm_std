package com.arjjs.ccm.modules.flat.export.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.export.service.ExportWordService;

/**
 * 
 * 创建word文档 步骤:
 * 
 * 1,建立文档
 * 
 * 2,创建一个书写器
 * 
 * 3,打开文档
 * 
 * 4,向文档中写入数据
 * 
 * 5,关闭文档
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/export/exportWord")
public class ExportWordController extends BaseController{

	@Autowired
	private ExportWordService exportWordService;
	
	/**
	 * 方法说明：警情生成word并下载音视频压缩成.zip文件
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "alarmExportWord")
	public void queryAlarmAllData(HttpServletRequest request, HttpServletResponse response,String id,String place) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String downloadName = place + dateFormat.format(new Date()) + ".zip";//压缩文件名称(案发地址+当前时间命名)
		OutputStream out = response.getOutputStream();
		byte[] data = exportWordService.queryAlarmAllData(id);
		response.reset();
		//处理文件名有中文问题    
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			downloadName = URLEncoder.encode(downloadName,"UTF-8");
		} else {
			downloadName = new String(downloadName.getBytes(),"ISO-8859-1");
		}
        response.setHeader("Content-Disposition","attachment;fileName=" + downloadName);
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
		IOUtils.write(data, out);
		out.flush();
		out.close();
	}
}