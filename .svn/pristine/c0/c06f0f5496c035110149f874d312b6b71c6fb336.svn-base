package com.arjjs.ccm.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: ParameterUtils
 * @Description: 参数转换
 *
 */
@Component
public class ParameterUtils {

	private static ParameterUtils parameterUtils;

	public void setParameterUtils(ParameterUtils parameterUtils) {
		this.parameterUtils = parameterUtils;
	}

	@PostConstruct
	public void init() {
		parameterUtils = this;
	}

	/**
	 * 内部方法实现Json对象转换
	 * 
	 * @param inputParm
	 * @param classname
	 * @return
	 */
	public static Object getParmObjByJsonStr(String inputParm, String classname) {
		Object retObj = null;
		boolean continueflag = true;
		Class clazz = null;
		/* ***********************************/
		// check parameter
		/* ***********************************/
		if (continueflag && inputParm != null && classname != null) {
			try {
				clazz = Class.forName(classname);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				continueflag = false;
			}
		} else {
			continueflag = false;
		}
		/* ***********************************/
		// do method work
		/* ***********************************/
		if (continueflag) {
			retObj = JSON.parseObject(inputParm, clazz);
		}

		/* ***********************************/
		// return result
		/* ***********************************/
		return retObj;
	}

	/**
	 * 获取指定KEY的value值最大值
	 * 
	 * @param list
	 * @param key
	 * @return: BigDecimal
	 * @author: zhangshanshan
	 */
	public static BigDecimal getMaxValue(List<Map<String, Object>> list, String key) {
		BigDecimal maxValue = new BigDecimal(0);

		List<BigDecimal> valueList = new ArrayList<BigDecimal>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0 && key != null && !"".equals(key)) {
			// do method work
			for (Map<String, Object> map : list) {
				if (map.get(key) != null) {
					valueList.add((BigDecimal) map.get(key));
				}
			}
		}

		// return result
		if (valueList != null && !"".equals(valueList) && valueList.size() > 0) {
			maxValue = Collections.max(valueList);
		}

		return maxValue;
	}

	/**
	 * 获取指定KEY的value值最大值
	 * 
	 * @param list
	 * @param key
	 * @return: String
	 * @author: zhangshanshan
	 * @date: 2018年7月13日 下午7:20:57
	 */
	public static String getMaxValueString(List<Map<String, Object>> list, String key) {
		String maxValue = "";
		List<String> valueList = new ArrayList<String>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0 && key != null && !"".equals(key)) {
			// do method work
			for (Map<String, Object> map : list) {
				if (map.get(key) != null) {
					valueList.add(map.get(key).toString());
				}
			}
		}
		// return result
		if (valueList != null && !"".equals(valueList) && valueList.size() > 0) {
			maxValue = Collections.max(valueList);
		}
		return maxValue;
	}

	/**
	 * 获取指定KEY的value值最小值
	 * 
	 * @param list
	 * @param key
	 * @return: BigDecimal
	 * @author: zhangshanshan
	 * @date: 2018年9月6日 上午9:20:34
	 */
	public static BigDecimal getMinValue(List<Map<String, Object>> list, String key) {
		BigDecimal maxValue = new BigDecimal(0);

		List<BigDecimal> valueList = new ArrayList<BigDecimal>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0 && key != null && !"".equals(key)) {
			// do method work
			for (Map<String, Object> map : list) {
				if (map.get(key) != null) {
					valueList.add((BigDecimal) map.get(key));
				}
			}
		}

		// return result
		if (valueList != null && !"".equals(valueList) && valueList.size() > 0) {
			maxValue = Collections.min(valueList);
		}

		return maxValue;
	}

	/**
	 * 获取指定KEY的value值最小值
	 * 
	 * @param list
	 * @param key
	 * @return: String
	 * @author: zhangshanshan
	 * @date: 2018年9月6日 上午9:20:42
	 */
	public static String getMinValueString(List<Map<String, Object>> list, String key) {
		String maxValue = "";
		List<String> valueList = new ArrayList<String>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0 && key != null && !"".equals(key)) {
			// do method work
			for (Map<String, Object> map : list) {
				if (map.get(key) != null) {
					valueList.add(map.get(key).toString());
				}
			}
		}
		// return result
		if (valueList != null && !"".equals(valueList) && valueList.size() > 0) {
			maxValue = Collections.min(valueList);
		}
		return maxValue;
	}

	/**
	 * 将list中的map的key变为小写
	 * 
	 * @param list
	 * @return: List<Map<String,Object>>
	 * @author: zhangshanshan
	 * @date: 2018年7月12日 下午3:58:00
	 */
	public static List<Map<String, Object>> transformListLowerCase(List<Map<String, Object>> list) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0) {
			// do method work
			for (Map<String, Object> map : list) {
				Map<String, Object> resultMap = new HashMap<>();
				Set<String> keySet = map.keySet();
				for (String key : keySet) {
					String newKey = key.toLowerCase();
					if (map.get(key) == null) {
						resultMap.put(newKey, "");
					} else {
						resultMap.put(newKey, map.get(key));
					}
				}
				returnList.add(resultMap);
			}
		}
		// return result
		return returnList;
	}

	/**
	 * 将map的key变为小写
	 * 
	 * @param map
	 * @return: Map<String,Object>
	 * @author: zhangshanshan
	 * @date: 2018年11月22日 下午9:24:08
	 */
	public static HashMap<String, Object> transformMapLowerCase(Map<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// check parameter
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String newKey = key.toLowerCase();
			if (map.get(key) == null) {
				resultMap.put(newKey, "");
			} else {
				resultMap.put(newKey, map.get(key));
			}
		}
		// return result
		return resultMap;
	}

	/**
	 * 将map的key变为小写
	 * 
	 * @param list
	 * @return: List<Map<String,Object>>
	 * @author: zhangshanshan
	 * @date: 2018年7月12日 下午3:58:42
	 */
	public static List<Map<String, Object>> transformLowerCaseTwo(List<Map<String, Object>> list) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0) {
			// do method work
			for (Map<String, Object> map : list) {
				Iterator conditionIter = map.entrySet().iterator();
				Map<String, Object> resultMap = new HashMap<>();
				while (conditionIter.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) conditionIter.next();
					String tmpKey = entry.getKey().toLowerCase();
					Object tmpValue = entry.getValue();

					resultMap.put(tmpKey, tmpValue);
				}
				returnList.add(resultMap);
			}
		}
		// return result
		return returnList;
	}

	/**
	 * 获取指定KEY的value，去重拼接为字符串
	 * 
	 * @param list
	 * @param key
	 * @return: String
	 * @author: zhangshanshan
	 * @date: 2018年7月12日 下午3:58:50
	 */
	public static String getValueByKey(List<Map<String, Object>> list, String key) {
		String keyStr = "";
		// check parameter
		if (list != null && !"".equals(list) && list.size() > 0 && key != null && !"".equals(key)) {
			// do method work
			// 获取指定key的value值
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && !"".equals(list.get(i)) && list.get(i).get(key) != null
						&& !"".equals(list.get(i).get(key))
						&& !keyStr.contains("'" + list.get(i).get(key).toString() + "'")) {
					keyStr = keyStr + "'" + list.get(i).get(key).toString() + "'";
					if (i < list.size() - 1) {
						keyStr = keyStr + ",";
					}
				}
			}

			// return result
			// 去掉后面的逗号，至最后一个不是逗号为止
			for (int i = 0; i < list.size(); i++) {
				if (keyStr.endsWith(",")) {
					int indx = keyStr.lastIndexOf(",");
					keyStr = keyStr.substring(0, indx) + keyStr.substring(indx + 1, keyStr.length());
				} else {
					break;
				}
			}
		}
		return keyStr;
	}

	/**
	 * 获取JSONArray的值，去重拼接为字符串
	 * 
	 * @param array
	 * @return: String
	 * @author: zhangshanshan
	 * @date: 2018年7月12日 下午4:54:40
	 */
	public static String getJsonValue(JSONArray array) {
		String jsonStr = "";

		// check parameter
		if (array != null && !"".equals(array) && array.size() > 0) {
			// do method work
			// 获取指定json的value值
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) != null && !"".equals(array.get(i)) && !jsonStr.contains("'" + array.get(i) + "'")) {
					jsonStr = jsonStr + "'" + array.get(i) + "'";
					if (i < array.size() - 1) {
						jsonStr = jsonStr + ",";
					}
				}
			}

			// return result
			// 去掉后面的逗号，至最后一个不是逗号为止
			for (int i = 0; i < array.size(); i++) {
				if (jsonStr.endsWith(",")) {
					int indx = jsonStr.lastIndexOf(",");
					jsonStr = jsonStr.substring(0, indx) + jsonStr.substring(indx + 1, jsonStr.length());
				} else {
					break;
				}
			}
		}
		return jsonStr;
	}

	/**
	 * 将数据保留两位小数
	 * 
	 * @param num
	 * @return
	 * @return: double
	 * @author: zhangshanshan
	 * @date: 2018年7月23日 下午5:29:36
	 */
	public static double getTwoDecimal(double num) {
		DecimalFormat dFormat = new DecimalFormat("#.00");
		String str = dFormat.format(num);
		Double temp = Double.valueOf(str);
		return temp;
	}

	/**
	 * 按指定的key给List<Map<String, Object>>排序，key的value值为数字
	 * 
	 * @param list
	 * @param key
	 * @return: void
	 * @author: zhangshanshan
	 * @date: 2018年7月23日 下午8:04:15
	 */
	public static void listMapSort(List<Map<String, Object>> list, final String key) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Double name1 = Double.parseDouble(o1.get(key).toString());// name1是从你list里面拿出来的一个
				Double name2 = Double.parseDouble(o2.get(key).toString()); // name1是从你list里面拿出来的第二个
				// return name1.compareTo(name2);//顺序
				return name2.compareTo(name1);// 降序
			}
		});
	}

	/**
	 * List<Map<String, Object>>去重
	 * 
	 * @param list
	 * @return: void
	 * @author: zhangshanshan
	 * @date: 2018年7月25日 下午7:52:59
	 */
	public static void removeDuplicate(List<Map<String, Object>> list) {
		if (list != null && !"".equals(list) && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> m1 = list.get(i);
				for (int j = i + 1; j < list.size(); j++) {
					Map<String, Object> m2 = list.get(j);
					if (m1.equals(m2)) {
						list.remove(j);
						j--;
						continue;
					}
				}
			}
		}
	}

	/**
	 * 字符串去重
	 * 
	 * @param para  截取的字符串
	 * @param split 截取的标识
	 * @return: List<String>
	 * @author: zhangshanshan
	 * @date: 2018年8月1日 上午9:14:38
	 */
	public static List<String> removeDuplicate(String para, String split) {
		List<String> list = new ArrayList<String>();
		// check parameter
		if (para != null && !"".equals(para) && split != null && !"".equals(split)) {
			// do method work
			String[] str = para.split(split);
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null && !"".equals(str[i]) && !list.contains(str[i])) {
					list.add(str[i]);
				}
			}
		}
		// return result
		return list;
	}

	/**
	 * 字符串去重返回字符串
	 * 
	 * @param para
	 * @param split
	 * @return: String
	 * @author: zhangshanshan
	 * @date: 2018年9月12日 下午5:23:20
	 */
	public static String removeDuplicateString(String para, String split) {
		List<String> list = new ArrayList<String>();
		// check parameter
		if (para != null && !"".equals(para) && split != null && !"".equals(split)) {
			// do method work
			String[] str = para.split(split);
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null && !"".equals(str[i]) && !list.contains(str[i])) {
					list.add(str[i]);
				}
			}
		}
		String content = "";
		for (int i = 0; i < list.size(); i++) {
			if (content != null && !"".equals(content)) {
				content = content + "," + list.get(i);
			} else {
				content = list.get(i);
			}
		}
		// return result
		return content;
	}

	/**
	 * 在指定范围内生成指定数量的随机数
	 * 
	 * @param min
	 * @param max
	 * @param n
	 * @return
	 * @return: JSONArray
	 * @author: zhangshanshan
	 * @date: 2018年10月15日 下午8:34:49
	 */
	public static JSONArray randomNumberArray(int min, int max, int n) {
		JSONArray array = new JSONArray();
		if (n < (max - min) && max > min) {
			int count = 0;
			while (count < n) {
				int num = (int) (Math.random() * (max - min)) + min;
				boolean flag = true;
				for (int i = 0; i < array.size(); i++) {
					if (num == Integer.parseInt(array.get(i).toString())) {
						flag = false;
						break;
					}
				}
				if (flag) {
					array.add(num);
					count++;
				}
			}
		}
		return array;
	}

	/**
	 * 读取文件，修改文件内容，另存
	 * 
	 * @param filePath
	 * @param outPath
	 * @param oldContent
	 * @param newContent
	 * @return: void
	 * @author: zhangshanshan
	 * @date: 2018年10月18日 上午9:39:43
	 */
	public static void autoReplace(String filePath, String outPath, String oldContent, String newContent) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				Long fileLength = file.length();
				byte[] fileContext = new byte[fileLength.intValue()];
				FileInputStream in = new FileInputStream(filePath);
				in.read(fileContext);
				in.close();
				String str = new String(fileContext);
				str = str.replace(oldContent, newContent);

				PrintWriter out = new PrintWriter(outPath);
				out.write(str.toCharArray());
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double getDouble(String str) {
		double minlat = 0;
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		if(str != null && !"".equals(str) && pattern.matcher(str).matches()) {
			BigDecimal maxlng = new BigDecimal(str);
			minlat = maxlng.doubleValue();
		}
		return minlat;
	}
	
    
	/**
	 * 判断是否为数字
	 * @param numStr
	 * @return: boolean
	 * @author: zhangshanshan 
	 * @date: 2018年11月9日 下午3:38:06
	 */
	public static boolean isNumber(String numStr){
		boolean flag = false;
		try{
			Pattern pattern = Pattern.compile("^[\\d]*$");
			if(numStr != null && !"".equals(numStr.trim()) && pattern.matcher(numStr).matches()){
				flag = true;
			}
		}catch (Exception e) {
		}
		return flag;
	}
	
	
	public static void main(String[] args) throws IOException {
//		System.out.println("{\"licenseparm\":{\"loginName\":\"chenmingyu\",\"token\":\"COkZFcxL\"},\"operateparm\":{\"alarmHandleId\":\"\", \"type\":\"0\"}}");
//		System.out.println(JSON.parseObject("{'licenseparm':{'loginName':'chenmingyu','token':'COkZFcxL'},'operateparm':{'alarmHandleId':'', 'type':'0'}}").toJSONString());
	}

}
