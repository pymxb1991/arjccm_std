package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.book.entity.CcmDatabaseBook;
import com.arjjs.ccm.modules.ccm.book.service.CcmDatabaseBookService;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmDatabaseCollection;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmRestCollection;
import com.arjjs.ccm.modules.ccm.collection.service.CcmDatabaseCollectionService;
import com.arjjs.ccm.modules.ccm.proposal.entity.CcmDatabaseProposal;
import com.arjjs.ccm.modules.ccm.proposal.service.CcmDatabaseProposalService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestCatalog;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestCollectionInfo;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestDatabaseBook;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestProposal;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.cms.entity.Comment;
import com.arjjs.ccm.modules.pbs.person.service.UserService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.RabbitMQTools;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 一村一警资料库app
 * @author jiaopanyu
 * @version 2019-10-10 文件传建以及app资料，目录，文章以及收藏夹的查询，添加和取消
 * @author jiaopanyu
 * @version 2019-10-11 一村一警公告建议信息的添加和查询
 */
@Controller
@RequestMapping(value = "${appPath}/rest/book")
@Api(description = "一村一警资料库app接口类")
public class CcmRestBook extends BaseController {
	
	@Autowired
	private CcmDatabaseBookService ccmDatabaseBookService;
	
	@Autowired
	private CcmDatabaseCollectionService ccmDatabaseCollectionService;
	
	@Autowired
	private CcmDatabaseProposalService ccmDatabaseProposalService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询一村一警资料库资料信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getBookInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查询一村一警资料库资料信息")
	public CcmRestResult getBookInfo(CcmDatabaseBook ccmDatabaseBook,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		ccmDatabaseBook.setType("1");
		List<CcmDatabaseBook> list = ccmDatabaseBookService.findList(ccmDatabaseBook);
		List<CcmRestDatabaseBook> resultList = Lists.newArrayList();
		for (CcmDatabaseBook bookInfo : list) {
			CcmRestDatabaseBook resultBookInfo = new CcmRestDatabaseBook();
			resultBookInfo.setBookId(bookInfo.getId());
			resultBookInfo.setCode(bookInfo.getCode());
			resultBookInfo.setName(bookInfo.getName());
			resultBookInfo.setContent(bookInfo.getContent());
			resultBookInfo.setImageurl(bookInfo.getImageurl());
			resultList.add(resultBookInfo);
		}
		result.setCode(CcmRestType.OK);
		result.setResult(resultList);
		return result;
	}
	
	/**
	 * 查询一村一警资料库资料目录信息
	 * @param ccmDatabaseBook
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getCatalogInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查询一村一警资料库资料目录信息")
	public CcmRestResult getCatalogInfo(CcmDatabaseBook ccmDatabaseBook,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String bookId = ccmDatabaseBook.getBookId();
		if(StringUtils.isBlank(bookId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("资料库所属资料的节点不可以为空！");
			return result;
		}
		List<CcmDatabaseBook> list = ccmDatabaseBookService.findList(ccmDatabaseBook);
		List<CcmRestCatalog> resultList = Lists.newArrayList();
		this.sortList(resultList, list, bookId, true);
		result.setCode(CcmRestType.OK);
		result.setResult(resultList);
		return result;
	}
	
	public void sortList(List<CcmRestCatalog> list, List<CcmDatabaseBook> sourcelist, String parentId, boolean cascade) {
		for(int i = 0; i < sourcelist.size(); ++i) {
			CcmDatabaseBook e = (CcmDatabaseBook)sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
				CcmRestCatalog resultBookInfo = new CcmRestCatalog();
				resultBookInfo.setBookId(e.getId());
				resultBookInfo.setCode(e.getCode());
				resultBookInfo.setName(e.getName());
				resultBookInfo.setContent(e.getContent());
				resultBookInfo.setImageurl(e.getImageurl());
				List<CcmRestCatalog> childrenList = Lists.newArrayList();
				resultBookInfo.setChildrenList(childrenList);
				list.add(resultBookInfo);
				if (cascade) {
					for(int j = 0; j < sourcelist.size(); ++j) {
						CcmDatabaseBook child = (CcmDatabaseBook)sourcelist.get(j);
						if (child.getParent() != null && child.getParent().getId() != null && child.getParent().getId().equals(e.getId())) {
							sortList(childrenList, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}

	}
	
	/**
	 * 查询一村一警资料库资料的文章信息
	 * @param ccmDatabaseBook
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查询一村一警资料库资料的文章信息")
	public CcmRestResult getArticleInfo(CcmDatabaseBook ccmDatabaseBook,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String bookId = ccmDatabaseBook.getId();
		if(StringUtils.isBlank(bookId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("资料库节点id不可以为空！");
			return result;
		}
		CcmDatabaseBook bookInfo = ccmDatabaseBookService.getArticleInfo(ccmDatabaseBook);
		CcmRestDatabaseBook resultBookInfo = new CcmRestDatabaseBook();
		if(bookInfo != null) {			
			resultBookInfo.setBookId(bookInfo.getId());
			resultBookInfo.setCode(bookInfo.getCode());
			resultBookInfo.setName(bookInfo.getName());
			resultBookInfo.setContent(bookInfo.getContent());
			resultBookInfo.setImageurl(bookInfo.getImageurl());
			resultBookInfo.setIsCollection(bookInfo.getIsCollection());
		}
		result.setCode(CcmRestType.OK);
		result.setResult(resultBookInfo);
		return result;
	}
	
	/**
	 * 根据用户id获取用户收藏的资料信息
	 * @param ccmRestCollectionInfo
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/getCollectionInfo", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户id获取用户收藏的资料信息")
	public CcmRestResult getCollectionInfo(CcmRestCollectionInfo ccmRestCollectionInfo,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String userId = ccmRestCollectionInfo.getUserId();
		if(StringUtils.isBlank(userId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("用户信息不可以为空！");
			return result;
		}
		CcmDatabaseCollection ccmDatabaseCollection = new CcmDatabaseCollection();
		User user = new User();
		user.setId(userId);
		ccmDatabaseCollection.setUser(user);
		List<CcmRestCollection> list = ccmDatabaseCollectionService.getCollectionInfo(ccmDatabaseCollection);
		result.setCode(CcmRestType.OK);
		result.setResult(list);
		return result;
	}
	
	/**
	 * 用户添加收藏信息
	 * @param ccmDatabaseCollection
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/addCollectionInfo", method = RequestMethod.GET)
	@ApiOperation(value = "用户添加收藏信息")
	public CcmRestResult addCollectionInfo(CcmRestCollectionInfo ccmRestCollectionInfo,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String userId = ccmRestCollectionInfo.getUserId();
		if(StringUtils.isBlank(userId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("用户信息不可以为空！");
			return result;
		}
		String bookId = ccmRestCollectionInfo.getBookId();
		if(StringUtils.isBlank(bookId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("资料信息不可以为空！");
			return result;
		}
		CcmDatabaseCollection ccmDatabaseCollection = new CcmDatabaseCollection();
		ccmDatabaseCollection.setBookId(bookId);
		User user = new User();
		user.setId(userId);
		ccmDatabaseCollection.setUser(user);
		ccmDatabaseCollection.setCreateDate(new Date());
		ccmDatabaseCollection.setDelFlag(Comment.DEL_FLAG_NORMAL);
		CcmDatabaseCollection collection = ccmDatabaseCollectionService.getCollectionData(ccmDatabaseCollection);
		if(collection == null) {			
			ccmDatabaseCollection.setId(UUID.randomUUID().toString());
			ccmDatabaseCollectionService.addCollectionInfo(ccmDatabaseCollection);
		}else {
			ccmDatabaseCollection.setId(collection.getId());
			ccmDatabaseCollectionService.updateCollectionInfo(ccmDatabaseCollection);
		}
		result.setCode(CcmRestType.OK);
		result.setMsg("收藏资料成功");
		return result;
	}
	
	/**
	 * 用户取消收藏信息
	 * @param ccmDatabaseCollection
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-10
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelCollectionInfo", method = RequestMethod.GET)
	@ApiOperation(value = "用户取消收藏信息")
	public CcmRestResult cancelCollectionInfo(CcmRestCollectionInfo ccmRestCollectionInfo,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String userId = ccmRestCollectionInfo.getUserId();
		if(StringUtils.isBlank(userId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("用户信息不可以为空！");
			return result;
		}
		String bookId = ccmRestCollectionInfo.getBookId();
		if(StringUtils.isBlank(bookId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("资料信息不可以为空！");
			return result;
		}
		CcmDatabaseCollection ccmDatabaseCollection = new CcmDatabaseCollection();
		ccmDatabaseCollection.setBookId(bookId);
		User user = new User();
		user.setId(userId);
		ccmDatabaseCollection.setUser(user);
		ccmDatabaseCollectionService.cancelCollectionInfo(ccmDatabaseCollection);
		result.setCode(CcmRestType.OK);
		result.setMsg("取消收藏资料成功");
		return result;
	}
	
	/**
	 * 得到RabbitMq配置信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-11
	 */
	@ResponseBody
	@RequestMapping(value = "/getRabbitMqInfo", method = RequestMethod.GET)
	@ApiOperation(value = "得到RabbitMq配置信息")
	public CcmRestResult getRabbitMqInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		Map<String, String> rabbitMap = new HashMap<String, String>();
		rabbitMap.put("rabbitMqIp", Global.getConfig("RABBIT_MQ_HOST"));
		rabbitMap.put("rabbitMqPort", Global.getConfig("RABBIT_MQ_PORT"));
		rabbitMap.put("rabbitMqUserName", Global.getConfig("RABBIT_MQ_USERNAME"));
		rabbitMap.put("rabbitMqPassWord", Global.getConfig("RABBIT_MQ_PASSWORD"));
		result.setResult(rabbitMap);
		result.setCode(CcmRestType.OK);
		return result;
	}
	
	/**
	 * 添加一村一警公告建议
	 * @param ccmDatabaseProposal
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-11
	 */
	@ResponseBody
	@RequestMapping(value = "/insertProposalInfo", method = RequestMethod.POST)
	@ApiOperation(value = "添加一村一警公告建议")
	public CcmRestResult insertProposalInfo(@RequestBody CcmDatabaseProposal ccmDatabaseProposal,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		String content = ccmDatabaseProposal.getContent();
		if(StringUtils.isBlank(content)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("公告或建议信息不可以为空！");
			return result;
		}
		String userId = ccmDatabaseProposal.getUserId();
		if(StringUtils.isBlank(userId)) {
			result.setCode(CcmRestType.ERROR_PARAM);
			result.setMsg("用户信息不可以为空！");
			return result;
		}
		User user = userService.get(userId);
		if(user == null) {
			result.setCode(CcmRestType.ERROR_USER_NOT_EXIST);
			result.setMsg("用户不存在，或者已更改！");
			return result;
		}
		ccmDatabaseProposal.setCreateBy(user);
		ccmDatabaseProposal.setUpdateBy(user);
		ccmDatabaseProposal.setCreateDate(new Date());
		ccmDatabaseProposal.setUpdateDate(new Date());
		ccmDatabaseProposal.setDelFlag(Comment.DEL_FLAG_NORMAL);
		String id = UUID.randomUUID().toString();
		ccmDatabaseProposal.setId(id);
		ccmDatabaseProposalService.addProposal(ccmDatabaseProposal);
		Map<String, String> rabbitMap = new HashMap<String, String>();
		rabbitMap.put("proposalId", id);
		rabbitMap.put("proposalContent", content);
		rabbitMap.put("proposalTitle", ccmDatabaseProposal.getTitle());
		rabbitMap.put("userId", userId);
		rabbitMap.put("userName", user.getName());
		rabbitMap.put("userPhoto", user.getPhoto());
		RabbitMQTools.sendMessage(Global.getConfig("DATABASE_BOOK_QUEUE_NAME"),JSONObject.toJSONString(rabbitMap));
		result.setCode(CcmRestType.OK);
		result.setResult(id);
		result.setMsg("添加公告或建议成功");
		return result;
	}
	
	/**
	 * 获取一村一警公告建议信息
	 * @param ccmDatabaseProposal
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 * @author jiaopanyu
	 * @version 2019-10-11
	 */
	@ResponseBody
	@RequestMapping(value = "/getProposalInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取一村一警公告建议信息")
	public CcmRestResult getProposalInfo(CcmDatabaseProposal ccmDatabaseProposal,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CcmRestResult result = new CcmRestResult();
		Date createDate = ccmDatabaseProposal.getCreateDate();
		if(createDate == null) {
			createDate = new Date();
			ccmDatabaseProposal.setCreateDate(createDate);
		}
		int pageSize = ccmDatabaseProposal.getPageSize();
		if(pageSize <= 0) {
			pageSize = 10;
			ccmDatabaseProposal.setPageSize(pageSize);
		}
		int pageCurrent = ccmDatabaseProposal.getPageCurrent();
		if(pageCurrent <= 0) {
			pageCurrent = 1;
			ccmDatabaseProposal.setPageCurrent(pageCurrent);
		}
		int pageNo =  (pageCurrent - 1) * pageSize;
		ccmDatabaseProposal.setPageNo(pageNo);
		List<CcmRestProposal> list = ccmDatabaseProposalService.getProposalInfo(ccmDatabaseProposal);
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("pageCurrent", String.valueOf(pageCurrent));
		listMap.put("pageSize", String.valueOf(pageSize));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(createDate);
		listMap.put("createDate", dateString);
		listMap.put("data", list);
		result.setResult(listMap);
		result.setCode(CcmRestType.OK);
		return result;
	}
}
