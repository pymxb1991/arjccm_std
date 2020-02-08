package com.arjjs.ccm.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	public static final String [] EVENT_REPORT_TYPE = new String []{
			"手动录入","APP录入","APP录入","热线录入","网站录入","机顶盒录入"
	};
	public static final String [] TYPE_IMAGE = new String []{"bmp","gif","jpeg","jpg","png","tiff","psd","svg","pcx","dxf","wmf"};
	public static final String [] TYPE_AUDIO = new String []{"wav","flac","ape","alac","mp3","aac","ogg","vorbis","opus"};
	public static final String [] TYPE_VIDEO = new String []{"mp4","wmv","asf","asx","rm","rmvb","3gp","mov","m4v","avi","dat","mkv","flv","vob"};

	public static final String MAP_CONFIG_ID = "map_info_config";// 地图配置ID

	public final static Map<String, String> DEVICE_CAMERA_TYPE = new HashMap<String, String>(); //  摄像机类型

	static {
		DEVICE_CAMERA_TYPE.put("0", "枪机");
		DEVICE_CAMERA_TYPE.put("1", "球机");
		DEVICE_CAMERA_TYPE.put("2", "半球");
	}
	/**
	 * 获取两个list 中不同结果
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> getDiffrent(List<String> list1, List<String> list2) {
		long st = System.nanoTime();
		Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
		List<String> diff = new ArrayList<String>();
		for (String string : list1) {
			map.put(string, 1);
		}
		for (String string : list2) {
			Integer cc = map.get(string);
			if(cc!=null){
				map.put(string, ++cc);
				continue;
			}
			map.put(string, 1);
		}
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			if(entry.getValue()==1){
				diff.add(entry.getKey());
			}
		}
		return diff;
	}
}
