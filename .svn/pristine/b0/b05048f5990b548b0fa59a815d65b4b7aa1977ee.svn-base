/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arjjs.ccm.tool.pdf;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arjjs.ccm.common.utils.DateUtils;

/**
 * pdf生成  资源加载类
 * @ClassName: Loader
 * @Description: 资源加载类
 * 
 */

public class ResourceUitle  { 
	public static String CLASS_PATH_PREFIX ="classpath:";
	
	
  /**
   * classpath中获取资源
   * @Title: getResource
   * @Description: classpath中获取资源
   * @param resource
   * @return
   * 
   */
   public static URL getResource(String resource) {
    ClassLoader classLoader = null;
    classLoader = Thread.currentThread().getContextClassLoader();
    
    return classLoader.getResource(resource);
  } 
  
  /**
   *  classpath 中搜索路径
   * @Title: getPath
   * @Description: 
   * @param resource
   * @return
   *
   */
  public static String getPath(String resource){
	  if(resource!=null){
		  if(resource.startsWith(CLASS_PATH_PREFIX)){
			  resource = getPath("")+resource.replaceAll(CLASS_PATH_PREFIX, "");
		  }
	  }
	  
	  URL url = getResource(resource);
	  if(url==null)
		  return null;
	  return url.getPath().replaceAll("%20", " ");
  }
  /**
   * 
   * @Title: getPath
   * @Description: 
   * @param resource
   * @param clazz
   * @return
   * 
   */
  @SuppressWarnings("rawtypes")
public static String getPath(String resource,Class clazz){
	  URL url = getResource(resource, clazz);
	  if(url==null)
		  return null;
	  return url.getPath().replaceAll("%20", " ");
  }
  
  /**
   * 指定class中获取资源
   * @Title: getResource
   * @Description: 指定class中获取资源
   * @param resource
   * @param clazz
   * @return
   * 
   */
  @SuppressWarnings("rawtypes")
public static URL getResource(String resource,Class clazz){
	  return clazz.getResource(resource);
  }
  
	/**
	 * If running under JDK 1.2 load the specified class using the
	 * <code>Thread</code> <code>contextClassLoader</code> if that fails try
	 * Class.forname. Under JDK 1.1 only Class.forName is used.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static Class loadClass(String clazz) throws ClassNotFoundException {
		return Class.forName(clazz);
	}
	
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
		public static Map<String, Object> transBean2Map(Object obj) {
			if (obj == null) {
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String key = property.getName();
					// 过滤class属性
					if (!key.equals("class")) {
						// 得到property对应的getter方法
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj);
	                     if(value instanceof Date) {
	                    	 Date date=(Date) value;
	                    	 value= DateUtils.formatDate(date,"yyyy-MM-dd HH:mm:ss");                    	 
	                     }
						map.put(key, value);
					}
	 
				}
			} catch (Exception e) {
			
			}
			return map;
	 
		}
		
		
}
