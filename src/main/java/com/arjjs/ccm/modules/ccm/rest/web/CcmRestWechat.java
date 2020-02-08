package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.dao.wechat.CcmWechatEventAttachmentDao;
import com.arjjs.ccm.modules.ccm.event.dao.wechat.CcmWechatEventDao;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEvent;
import com.arjjs.ccm.modules.ccm.event.entity.wechat.CcmWechatEventAttachment;
import com.arjjs.ccm.modules.ccm.event.service.wechat.CcmWechatEventAttachmentService;
import com.arjjs.ccm.modules.ccm.event.service.wechat.CcmWechatEventService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmEntityFileUpload;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmEntityProgress;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtilRest;

/**
 * 楼栋接口类
 * 
 * @author pengjianqiang
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${appPath}/rest/wechat")
public class CcmRestWechat extends BaseController {

	@Autowired
	private CcmWechatEventService ccmWechatEventService;
	@Autowired
	private CcmWechatEventDao ccmWechatEventDao;
	@Autowired
	private CcmWechatEventAttachmentService ccmWechatEventAttachmentService;


	/**
	 * @see 获取单个微信上报登记
	 * @param id
	 *           微信上报ID
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-15
	 */
	@ResponseBody
	@RequestMapping(value = "/getWechatEvent", method = RequestMethod.GET)
	public CcmRestResult getRequest(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			result.setCode(CcmRestType.ERROR_PARAM);
			return result;
		}
		// 当前 是否为空
		CcmWechatEvent ccmWechatEvent = ccmWechatEventService.get(id);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmWechatEvent);

		return result;
	}

	/**
	 * @see 查询微信上报信息
	 * @param pageNo
	 *            页码
	 * @param pageSiz
	 *            分页大小
	 * @return
	 * @author fuxinshuang
	 * @version 2018-03-15
	 */
	@ResponseBody
	@RequestMapping(value = "/queryWechatEvent", method = RequestMethod.GET)
	public CcmRestResult queryRequest(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmWechatEvent ccmWechatEvent) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 设置当前的 微信上报不为空
		ccmWechatEvent = (null == ccmWechatEvent) ? new CcmWechatEvent() : ccmWechatEvent;
		// 获取userId
		User userEntity = new User();
		userEntity.setId(userId);
		ccmWechatEvent.setCreateBy(userEntity);
		Page<CcmWechatEvent> page = ccmWechatEventService.findPage(new Page<CcmWechatEvent>(req, resp),
				(null == ccmWechatEvent) ? new CcmWechatEvent() : ccmWechatEvent);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * @see 填加微信上报登记
	 *            微信上报对象
	 * @author fuxinshuang
	 * @version 2018-03-13
	 */
	@ResponseBody
	@RequestMapping(value = "/saveWechatEvent", method = RequestMethod.POST)
	public CcmRestResult saveRequest(CcmWechatEvent ccmWechatEvent, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = new CcmRestResult();
		// 如果当前的 flag 为返回
		
		ccmWechatEvent.setCreateBy(new User("0"));
		ccmWechatEvent.setCreateDate(new Date());
		ccmWechatEvent.setReportTime(new Date());
		ccmWechatEvent.setUpdateBy(new User("0"));
		ccmWechatEvent.setUpdateDate(new Date());
		ccmWechatEvent.setStatus("01");
		ccmWechatEventDao.insert(ccmWechatEvent);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}
	/**
	 * @see 填加微信上报附件添加
	 *            微信上报对象
	 * @author fuxinshuang
	 * @version 2018-03-13
	 */
	@ResponseBody
	@RequestMapping(value = "/saveWechatEventAtta", method = RequestMethod.POST)
	public CcmRestResult saveWechatEventAtta(CcmWechatEventAttachment ccmWechatEventAttachment, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = new CcmRestResult();
		// 如果创建者为空
		ccmWechatEventAttachment.setCreateBy(new User("0"));
		ccmWechatEventAttachment.setUpdateBy(new User("0"));
		ccmWechatEventAttachmentService.save(ccmWechatEventAttachment);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}

	/**
	 * @see 填加微信上报附件添加
	 *            微信上报对象
	 * @author fuxinshuang
	 * @version 2018-03-13
	 */
	/*@ResponseBody
	@RequestMapping(value = "/saveWechatEventAtta", method = RequestMethod.POST)
	public CcmRestResult saveWechatEventAtta(CcmWechatEventAttachment ccmWechatEventAttachment, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CcmRestResult result = new CcmRestResult();
	//===================获取附件===========
		
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
		
						CcmEntityProgress progress = new CcmEntityProgress();
				        if (request.getParameter("progressId") != null && !"".equals(request.getParameter("progressId"))) {
				        	progress.setUuid(request.getParameter("progressId"));
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
		//===================获取附件结束===========
		//拿到附件路径 设置为path
		// retVo.getName();
//		ccmWechatEventAttachment.设置path为附件获取的图片路径 retVo.getName()
		System.out.println("地址-------------"+retVo.getName());
		ccmWechatEventAttachment.setPath(retVo.getName());
		ccmWechatEventAttachment.setFileType(retVo.getType());
		ccmWechatEventAttachment.setFileName(retVo.getFileName());
		// 如果创建者为空
		ccmWechatEventAttachment.setCreateBy(new User("0"));
		ccmWechatEventAttachment.setUpdateBy(new User("0"));
		System.out.println("存储对象-------------"+ccmWechatEventAttachment);
		ccmWechatEventAttachmentService.save(ccmWechatEventAttachment);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}*/
	
	
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
	/*public int uploadFile(InputStream inputStream, String path, String fileName, CcmEntityProgress progress) {
		int flag = -1;
		FileOutputStream fileOut = null;
		BufferedInputStream bf = null;
		try {
		    fileName = fileName.replace("\\", "/");
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
	}*/
}