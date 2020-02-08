package com.arjjs.ccm.test;

import java.util.HashMap;
import java.util.Map;

import com.arjjs.ccm.tool.geoJson.Features;
import com.arjjs.ccm.tool.geoJson.Properties;

public class Test {

	public static void main(String[] args) {
		
		
		Features featureDto = new Features();
		Properties properties = new Properties();
		// 1 type 默认不填
		// 2 id 添加
//		featureDto.setId(Buildmanage.getId());
		// 3 properties 展示属性信息
//		properties.setName(Buildmanage.getName());
//		properties.setIcon(Buildmanage.getImage());
		Map<String, String> map = new HashMap<String, String>();
		// 创建附属信息
		map.put("楼栋名称","名称");
		map.put("电话", "电话");
		map.put("生日", "生日");
		//properties.addInfo(map);
		featureDto.setProperties(properties);
		System.out.println(featureDto);
	}
}
