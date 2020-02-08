package com.arjjs.ccm.modules.plm.jump;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.arjjs.ccm.modules.sys.entity.Dict;

/**
 * 流程跳转
 * 
 * @author UserLi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/jump")
public class JumpController {

	// 信息查阅
	@RequestMapping(value = { "index" })
	public String index(Dict dict, Model model) {
		return "plm/act/actTaskApplyIndex";
	}

	// 我的请求
	@RequestMapping(value = { "indexList" })
	public String indexList(Dict dict, Model model) {
		return "plm/act/plmActIndex";
	}

	// 待办事宜
	@RequestMapping(value = { "actIndex" })
	public String actIndex(Dict dict, Model model) {
		return "plm/act/actTaskTodoIndex";
	}

	// 已办流程
	@RequestMapping(value = { "historicIndex" })
	public String historicIndex(Dict dict, Model model) {
		return "plm/act/actTaskHistoricIndex";
	}

	// 流程归档
	@RequestMapping(value = { "indexFile" })
	public String indexFile(Dict dict, Model model) {
		return "plm/act/plmActIndexFile";
	}

	// 流程督办
	@RequestMapping(value = { "indexSupTodo" })
	public String indexSupTodo(Dict dict, Model model) {
		return "plm/act/plmActSupTodoIndex";
	}

}
