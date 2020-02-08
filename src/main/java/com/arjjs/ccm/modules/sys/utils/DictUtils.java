/**
 * Copyright &copy; 2012-2016 <a href="http://www.arjjs.com">arjjs</a> All rights reserved.
 */
package com.arjjs.ccm.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.arjjs.ccm.common.mapper.JsonMapper;
import com.arjjs.ccm.common.utils.CacheUtils;
import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.modules.sys.dao.DictDao;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典工具类
 * @author admin001
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	
	// @param type  type或parentId均可
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
				dictList = dictMap.get(dict.getParentId());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getParentId(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	/**
	 * 获取字典列表,排除多个value
	 * @param type 父类型
	 * @param extValues 排除的value
	 * @return
	 */
	public static List<Dict> getDictListExt(String extValues, String type){
		List<Dict> dictList = getDictList(type);
		List<Dict> dictListCopy = new ArrayList<>();
		dictListCopy.addAll(dictList);
		for (Dict dict : dictList) {
			for (String extValue : StringUtils.split(extValues, ",")){
				if(extValue!=null && extValue.equals(dict.getValue())){
					dictListCopy.remove(dict);
				}
			}
		}
		return dictListCopy;		
	}
	/**
	 * 获取子字典列表
	 * @param type 父类型
	 * @param value 父value
	 * @return
	 */
	public static List<Dict> getDictChildList(String value, String type){
		String parentId = "";
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					parentId = dict.getId();
				}
			}
		}
		List<Dict> dictList = getDictList(parentId);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;		
	}	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
