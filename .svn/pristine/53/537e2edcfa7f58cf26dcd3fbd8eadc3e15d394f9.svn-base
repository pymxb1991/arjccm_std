package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmEntityFileUpload;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmEntityProgress;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;

/**
 * 文件接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-27
 */
@Controller
@RequestMapping(value = "${appPath}/rest/file")
public class CcmRestFile extends BaseController {

	/**
	 * @see  上传信息
	 * @param 
	 * @return 
	 * @author pengjianqiang
	 * @version 2018-02-27
	 */
	@ResponseBody
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST)
	public CcmRestResult fileUpload(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		CcmRestResult result = new CcmRestResult();
		/*long fileSize = 0L;
        // 文件上传解析之前 需要先判断 该表单是否为文件上传表单 enctype="multipart/form-data"
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {// 如果没有使用则返回.
            result.setCode(1);
            return result;
        }
        FileItemFactory factory = new DiskFileItemFactory();// 创建FileItem对象的工厂
        ServletFileUpload upload = new ServletFileUpload(factory);// 创建一个文件上传处理器的实例upload ,用于处理文件上传
        List<FileItem> items = null;
        try {
            // 使用处理器对请求进行解析，它会把消息体中的每一块（部分）解析成一个FileItem对象。
            items = upload.parseRequest(req);// 处理文件上传
        } catch (FileUploadException e) {
            result.setCode(2);
            e.printStackTrace();
            return result;
        }
        if (items == null) { // items为空，则没有文件需要上传
            result.setCode(3);
            return result;
        }
        String fileName = "";
        InputStream inputStream = null;
        for (FileItem fileItem : items) {// 处理第一个FileItem
            if (!fileItem.isFormField()) {// 如果文件项不是一个简单表单域，他就是一个上传文件
                String fileType = fileItem.getName().split("\\.")[fileItem.getName().split("\\.").length - 1];
                fileName = UUID.randomUUID().toString();
		 		fileName += ".";
		 		fileName += fileType;
                inputStream = fileItem.getInputStream();
                fileSize=fileItem.getSize();
            }
        }
        if (null == inputStream) {
            result.setCode(4);
            return result;
        }
        if ("".equals(fileName)) {
            result.setCode(5);
            result.setCode(5);
            return result;
        }
        CcmEntityProgress progress = new CcmEntityProgress();
        if (req.getParameter("progressId") != null && !"".equals(req.getParameter("progressId"))) {
        	progress.setUuid(req.getParameter("progressId"));
        } else {
        	progress.setUuid("");
        }
        progress.setTotalBytes(fileSize);
        
        String path = Global.getConfig("FILE_UPLOAD_PATH");//文件上传存储路径
        
        int fileUploadR = uploadFile(inputStream, path, fileName, progress);
        if (fileUploadR != 0) {
            result.setCode(6);
            return result;
        }
        CcmEntityFileUpload retVo = new CcmEntityFileUpload();
        retVo.setData(UUID.randomUUID().toString());
        retVo.setName(fileName);
        retVo.setSrc(path + fileName.replace("\\", "/"));
        */
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
//		String realPath = request.getSession().getServletContext().getRealPath("/upload") + "/";
		long fileSize = 0L;
		CcmEntityFileUpload retVo = null;

		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					InputStream inputStream = file.getInputStream();
					fileSize=file.getSize();
					// 客户端提交的参数名
//					String clientFileName = file.getName();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println("上传文件名=>>" + myFileName);
						// 重命名上传后的文件类型
						String ext = myFileName.substring(myFileName.lastIndexOf(".") + 1,
								myFileName.length());
						// 重命名上传后的文件名
						String fileName = UUID.randomUUID().toString().replace("-", "_") + "." + ext;
						// 定义上传路径
						/*String path;
						path = realPath + "thumb/" + fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
						String outing = new CompressPicDemo().compressPic(realPath + "thumb/",
								realPath + "thumb/", fileName, 480, 480, true);
						// System.out.println("outing:"+outing);
						if (outing != null)
							thumb = outing;*/
						CcmEntityProgress progress = new CcmEntityProgress();
				        if (request.getParameter("progressId") != null && !"".equals(request.getParameter("progressId"))) {
				        	progress.setUuid(request.getParameter("progressId"));
				        } else {
				        	progress.setUuid("");
				        }
				        progress.setTotalBytes(fileSize);
						UserUtils.getUser().getId();

						String path = Global.getConfig("FILE_UPLOAD_PATH");//文件上传存储路径
						//String path2=Global.getConfig("FILE_DOWNLOAD_PATH");
						int fileUploadR = uploadFile(inputStream, path, fileName, progress);
				        if (fileUploadR != 0) {
				            result.setCode(6);
				            return result;
				        }
				        String fileUrl = Global.getConfig("FILE_UPLOAD_URL");//文件上传存储路径
				        String pathr = path.substring(path.indexOf(":")+2);
						retVo = new CcmEntityFileUpload();	
				        retVo.setData(UUID.randomUUID().toString());	
				        retVo.setName(pathr.replace("\\", "/") + fileName.replace("\\", "/"));	//数据库存储路径
				        retVo.setSrc(fileUrl+pathr.replace("\\", "/") + fileName.replace("\\", "/"));	//全路径
				        retVo.setType(ext);	//文件类型
				        retVo.setFileName(myFileName);	//上传文件名
					}
				}
			}
		}
		
		result.setResult(retVo);
        result.setCode(CcmRestType.OK);
        return result;
		
	}

	//文件上传进度Map uuid和当前大小
	public static Map<String, CcmEntityProgress> map = new HashMap<String, CcmEntityProgress>();
	
	/**
	  * 上传文件
	  * @param inputStream  文件流数据
	  * @param path         保存路径
	  * @param fileName     文件名称
	  * @param progress     上传进度
	  * @return
	  * @author pengjianqiang
	  * @version 2018-02-27
	  */
	public int uploadFile(InputStream inputStream, String path, String fileName, CcmEntityProgress progress) {
		int flag = -1;
		FileOutputStream fileOut = null;
		BufferedInputStream bf = null;
		try {
		    fileName = fileName.replace("\\", "/");
		    File realFilePath = new File(path);
    		if (!realFilePath.exists()) {
    			realFilePath.mkdirs();//创建目录
    		}
		    fileOut = new FileOutputStream(path + fileName, false);
			bf = new BufferedInputStream(inputStream);
			byte[] bt = new byte[8192];
			int n = bf.read(bt);
			long readUpload = 0;
			
			while (n != -1) {
				if (!"".equals(progress.getUuid())) { //有progressid，则进行进度处理
					readUpload+=8192;
					progress.setUploadedBytes(readUpload);
					CcmRestFile.map.put(progress.getUuid(),progress);	
//					System.out.println("MAP:"+this.map.get(progress.getUuid()).toString());
				}
				
				fileOut.write(bt, 0, n);
				fileOut.flush();
				n = bf.read(bt);
			}
			
			flag = 0;
//			log.debug("文件传输结束...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fileOut)
					fileOut.close();
				if (null != bf)
					inputStream.close();
				bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}
	 

}