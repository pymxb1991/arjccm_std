package com.arjjs.ccm.tool.freemaker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
/**
 *pdf  freemark����
 * @ClassName: FreemarkerConfiguration
 * @Description: freemark����
 * 
 */
public class FreemarkerConfiguration {

	private static Configuration config = null;

	/**
	 * ��ȡ FreemarkerConfiguration
	 * 
	 * @Title: getConfiguation
	 * @Description:
	 * @return
	 * 
	 */
	public static synchronized Configuration getConfiguation(String path) {
		if (config == null) {
			setConfiguation( path);
		}
		return config;
	}
	/**
	 * ���� ����
	 * @Title: setConfiguation
	 * @Description: 
	 *
	 */
	private static void setConfiguation( String path) {
		config = new Configuration();
		//String path = ResourceLoader.getPath("");
	    
		try {
		
			config.setDirectoryForTemplateLoading(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}