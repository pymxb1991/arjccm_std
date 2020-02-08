package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.ccm.service.entity.CcmCommunityWork;
import com.arjjs.ccm.modules.ccm.service.service.CcmCommunityWorkService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.CommUtilRest;

/**
 * 社区服务
 * 
 * @author arj
 * @version 2018-03-12
 */
@Controller
@RequestMapping(value = "${appPath}/rest/service/ccmCommunityWork")
public class CcmRestCommunityWork extends BaseController {

	@Autowired
	private CcmCommunityWorkService ccmCommunityWorkService;

	/**
	 * @see 获取单个社区服务
	 * @param id
	 *            社区服务ID
	 * @return
	 * @author arj
	 * @version 2018-03-12
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public CcmRestResult get(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 当前 是否为空
		CcmCommunityWork ccmCommunityWork = ccmCommunityWorkService.get(id);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmCommunityWork);

		return result;
	}

	/**
	 * @see 查询服务信息
	 * @param pageNo
	 *            页码
	 * @param pageSiz
	 *            分页大小
	 * @return
	 * @author arj
	 * @version 2018-03-12
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public CcmRestResult query(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmCommunityWork ccmCommunityWork) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		Page<CcmCommunityWork> page = ccmCommunityWorkService.findPage(new Page<CcmCommunityWork>(req, resp),
				(null == ccmCommunityWork) ? new CcmCommunityWork() : ccmCommunityWork);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * @see 修改 社区服务
	 * @param ccmCommunityWork
	 *            社区服务对象
	 * @author arj
	 * @version 2018-02-03
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CcmRestResult modify(String userId, CcmCommunityWork ccmCommunityWork, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 如果创建者为空
		if (null == ccmCommunityWork.getCreateBy()) {
			ccmCommunityWork.setCreateBy(new User(userId));
		}
		ccmCommunityWork.setUpdateBy(new User(userId));
		ccmCommunityWorkService.save(ccmCommunityWork);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "selectType", method = RequestMethod.GET)
	public List<Dict> selectType(@RequestParam(required = false) String type,
			@RequestParam(required = false) String type2, Model model) {
		return DictUtils.getDictList(type);
	}

}