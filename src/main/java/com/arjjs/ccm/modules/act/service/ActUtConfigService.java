/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.act.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.dao.ActUtConfigDao;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.act.entity.ActUtConfig;
import com.arjjs.ccm.modules.gen.dao.GenTableColumnDao;
import com.arjjs.ccm.modules.gen.entity.GenTable;
import com.arjjs.ccm.modules.gen.entity.GenTableColumn;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;
import com.arjjs.ccm.tool.Select2Type;
import com.google.common.collect.Maps;

/**
 * 流程配置Service
 * 
 * @author dongqikai
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class ActUtConfigService<T> extends CrudService<ActUtConfigDao, ActUtConfig> {

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private GenTableColumnDao genTableColumnDao;

	public ActUtConfig get(String id) {
		return super.get(id);
	}

	public List<ActUtConfig> findList(ActUtConfig actUtConfig) {
		return super.findList(actUtConfig);
	}

	public Page<ActUtConfig> findPage(Page<ActUtConfig> page, ActUtConfig actUtConfig) {
		return super.findPage(page, actUtConfig);
	}

	@Transactional(readOnly = false)
	public void save(ActUtConfig actUtConfig) {
		actUtConfig.configs2config();
		super.save(actUtConfig);
	}

	@Transactional(readOnly = false)
	public void delete(ActUtConfig actUtConfig) {
		super.delete(actUtConfig);
	}

	/**
	 * 根据分类获取信息
	 * 
	 * @param category
	 * @return
	 */
	public List<ActUtConfig> getKeyByCategory(String category) {
		ActUtConfig actUtConfig = new ActUtConfig();
		actUtConfig.setProcessType(category);
		return findList(actUtConfig);
	}

	/**
	 * 获取流程id
	 * 
	 * @param configId
	 * @param t
	 * @param formId
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> getProcInsId(String configId, T t, String formId) {
		Map<String, Object> vars = Maps.newHashMap();
		return getProcInsId(configId, t, formId, vars);
	}

	@Transactional(readOnly = false)
	public Map<String, String> getProcInsId(String configId, T t, String formId, Map<String, Object> vars) {
		Map<String, String> returnMap = new HashMap<String, String>();
		ActUtConfig actUtConfig = getConfig(configId);// 根据id获取流程配置信息
		if (actUtConfig == null) {
			return returnMap;
		}
		String title = getTitle(actUtConfig, t);
		if (StringUtils.isBlank(title)) {
			title = actUtConfig.getTitle();
		}
		// 开始流程并返回对应流程id
		String procInsId = actTaskService.startProcess(actUtConfig.getProcessId(), actUtConfig.getTable(), formId,
				title, vars, UserUtils.getUser().getLoginName());
		returnMap.put("title", title);
		returnMap.put("procInsId", procInsId);
		return returnMap;
	}

	/**
	 * 获取标题
	 * 
	 * @param actUtConfig
	 * @param t
	 * @return
	 */
	public String getTitle(ActUtConfig actUtConfig, T t) {
		String title = "[" + actUtConfig.getProcessName() + "]";
		actUtConfig.config2configs();// 将拼接字符串转换为数组
		String[] configs = actUtConfig.getTitleConfigs();
		if (configs == null || configs.length <= 0)
			return "";
		Class<?> entityClass = t.getClass();// 获取类对象
		// 遍历标题配置的字段
		for (int i = 0; i < configs.length; i++) {
			try {
				Method method = entityClass.getMethod("get" + upperCase(configs[i]));// 获取对应配置字段的方法
				if (i != 0) {
					title += ":";
				}
				// 判断方法返回类型并获取执行结果对象，然后取得名称
				if (PlmTypes.CLASS_TYPE.contains(method.getReturnType())) {
					if (User.class.equals(method.getReturnType())) {
						User u = (User) method.invoke(t);
						title += u.getName();
					} else if (Office.class.equals(method.getReturnType())) {
						Office o = (Office) method.invoke(t);
						title += o.getName();
					} else if (Date.class.equals(method.getReturnType())) {
						Date d = (Date) method.invoke(t);
						title += DateUtils.formatDate(d);
					} /*
						 * else if (PlmEquipment.class.equals(method.getReturnType())) { PlmEquipment p
						 * = (PlmEquipment) method.invoke(t); title += p.getName(); }
						 */ else {
						continue;
					}
				} else {
					title += method.invoke(t).toString();// 直接返回执行结果字符串
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return title;
	}

	/**
	 * 根据ID获取对应配置信息
	 * 
	 * @param id
	 * @return
	 */
	public ActUtConfig getConfig(String id) {
		return get(id);
	}

	/**
	 * 获取实体字段
	 * 
	 * @param tableId
	 * @return
	 */
	public List<Select2Type> getSelectList(String tableId) {
		List<Select2Type> list = new ArrayList<>();
		Select2Type typeFirst = new Select2Type();
		typeFirst.setId("");
		typeFirst.setText("请选择配置项...");
		list.add(typeFirst);
		GenTableColumn tableColumn = new GenTableColumn(new GenTable());
		tableColumn.getGenTable().setName(tableId);
		List<GenTableColumn> columns = genTableColumnDao.findList(tableColumn);
		if (columns == null || columns.isEmpty())
			return list;
		for (GenTableColumn genTableColumn : columns) {
			String colName = genTableColumn.getJavaField();
			// 去除.符号后的内容
			if (colName.indexOf(".") > 0) {
				colName = colName.substring(0, colName.indexOf("."));
			}
			if (PlmTypes.PUBLIC_COLUMNS.contains(colName))
				continue;// 过滤公共字段
			Select2Type type = new Select2Type();
			type.setId(colName);
			type.setText(genTableColumn.getComments());
			list.add(type);
		}
		return list;
	}

	/**
	 * 首字母大写转换
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 流程退回后处理
	 * 
	 * @param act
	 */
	@Transactional(readOnly = false)
	public void complete(Act act) {
		act.setComment(("yes".equals(act.getFlag()) ? "[重申] " : "[销毁] ") + act.getComment());
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(act.getFlag()) ? "1" : "0");
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), "", vars);
	}

}