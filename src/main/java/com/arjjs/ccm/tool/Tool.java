package com.arjjs.ccm.tool;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;

import net.sf.json.JSONObject;

public class Tool {

  	/**
  	 * 方法说明：http get 数据获取
  	 *
  	 * @param Url
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-9
  	 * @修改人及日期：pengjianqiang    2018-5-9
  	 * @修改描述：
  	 * @其他：
  	 */
	public static String getRestReturn(String Url){
		StringBuffer strBuffer = new StringBuffer();
		BufferedReader brd =null;
		HttpURLConnection connet = null;
        try {
			URL url = new URL(Url);
			  //实例一个HTTP CONNECT
			  connet = (HttpURLConnection) url.openConnection();
			  connet.setRequestMethod("GET");
			  connet.setDoOutput(true);
			  connet.setDoInput(true);
			  connet.setUseCaches(false);
			  connet.setConnectTimeout(300000);
			  connet.setReadTimeout(300000);
			  connet.connect();
			  if(connet.getResponseCode() != 200){
			      throw new IOException(connet.getResponseMessage());
			  }
			  //将返回的值存入到String中
			  brd = new BufferedReader(new InputStreamReader(connet.getInputStream(),"utf8"));
			  String line=brd.readLine();
			  while(line != null){
				  line = java.net.URLDecoder.decode(line, "UTF-8");
				  strBuffer.append(line);
				  line = brd.readLine();
			  }
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			if(brd !=null){
				 try {
					 brd.close();
				 } catch (IOException e) {
					 
				 }
			}
			if(connet!=null){
				connet.disconnect();
			}
		}
        return strBuffer.toString();
    }
	

  	/**
  	 * 方法说明：http post 
  	 *
  	 * @param Url
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-12
  	 * @修改人及日期：pengjianqiang    2018-5-12
  	 * @修改描述：
  	 * @其他：
  	 */
	public static String postRestReturn(String Url) throws IOException {
		URL url1 = new URL(Url);
		HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(60000);
		InputStream in1 = urlConnection.getInputStream();
		BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in1, "utf8"));
		StringBuffer temp1 = new StringBuffer();
		String line1 = bufferedReader1.readLine();
		while (line1 != null) {
			line1 = java.net.URLDecoder.decode(line1, "UTF-8");
			temp1.append(line1);
			line1 = bufferedReader1.readLine();
		}
		bufferedReader1.close();
		return temp1.toString();
	}
	
  	/**
  	 * 方法说明： 获取一个月的最后一天
  	 *
  	 * @param date
  	 * @return 
  	 * @作者及日期：pengjianqiang    2018-5-9
  	 * @修改人及日期：pengjianqiang    2018-5-9
  	 * @修改描述：
  	 * @其他：
  	 */
  	public static int  getLastDay(final Date date){ Calendar calendar = Calendar.getInstance();
  		calendar.setTime(date);
  		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  		return endDay;
  		}  
  	
  	public static void main(String args[]) throws IOException {
  		String urllogin = "http://192.168.1.243:8080/arjccm/app/rest/login/login?loginName=admin&password=admin";
  		String loginReturn = postRestReturn(urllogin);
		System.out.println(loginReturn);
		String userId = "";
		if (loginReturn != null && !"".equals(loginReturn)) {
			JSONObject jsonLogin = JSONObject.fromObject(loginReturn);
			int code = jsonLogin.getInt("code");
			if (code != CcmRestType.OK) {
				return;
			}
			String resultContent = jsonLogin.getString("result");
			JSONObject jsonLoginUser = JSONObject.fromObject(resultContent);
			userId = jsonLoginUser.getString("id");
		}
  		
//  		String urlRest = "http://192.168.1.243:8080/arjccm/app/rest/building/saveSyn?userId=1&id=8dfb8151c58c4beb849efaeded854649&name=长征里小区&buildname=楼12&areaId=1ac94bc554e241e9abeedef982000107&floorArea=332.0&pilesNum=6&elemNum=44&buildNum=332&buildPeo=555&buildPname=沈先生&sex=0&nation=01&content=01&birthday=Wed May 12 00:00:00 CST 1954&education=&tel=13877654326&phone=&residence=&residencedetail=&areaMap=&areaPoint=&image=&images=&remarks=&isNewRecord=true";
  		String urlRest = "http://192.168.1.243:8080/arjccm/app/rest/building/saveSyn?userId=1&id=8dfb8151c58c4beb849efaeded854649&name=长征里小区&buildname=楼12&areaId=1ac94bc554e241e9abeedef982000107&floorArea=332.0&pilesNum=6&elemNum=44&buildNum=332&buildPeo=555&buildPname=沈先生&sex=0&nation=01&content=01&education=&tel=13877654326&phone=&residence=&residencedetail=&areaMap=&areaPoint=&image=&images=&remarks=&isNewRecord=true";
  		String restReturn = Tool.postRestReturn(urlRest);
		if (restReturn != null && !"".equals(restReturn)) {
			JSONObject jsonLogin = JSONObject.fromObject(restReturn);
			int code = jsonLogin.getInt("code");
			if (code == CcmRestType.OK) {
			}
		}
  		
  	}
  	private static double EARTH_RADIUS = 6378137.0;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 方法说明：通过经纬度获取距离(单位：米)
	 *
	 * @param
	 * @return
	 * @作者及日期：wangyikai 2018-08-17 @修改人及日期： @修改描述： @其他：
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		return s;
	}

	/**
	 * 方法说明：判断一个点是否在圆形区域内
	 *
	 * @param
	 * @return
	 * @作者及日期：wangyikai 2018-08-17 @修改人及日期： @修改描述： @其他：
	 */
	public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, String radius) {
		double distance = getDistance(lat1, lng1, lat2, lng2);
		double r = Double.parseDouble(radius);
		if (distance > r) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * POST方式向指定URL发送数据方法
	 * 
	 * @param url   指定的接收数据的地址(最后不加?)
	 * @param param 发送的数据(不带有?,直接就是key1=value1&key2=value2格式)
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			// 打开和URL之间的连接
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST"); // POST方法
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			if (conn.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			return null;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取最近12个月，经常用于统计图表的X轴
	 */
	public static String[] getLast12Months() {
		String[] last12Months = new String[12];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); // 要先+1,才能把本月的算进去</span>
		for (int i = 0; i < 12; i++) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
			last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + fillZero((cal.get(Calendar.MONTH) + 1), 2);
		}
		return last12Months;
	}
	
	
	/**
	 * 方法说明：判断是否在多边形区域内
	 *
	 * @param pointLon
	 *            要判断的点的纵坐标
	 * @param pointLat
	 *            要判断的点的横坐标
	 * @param lon
	 *            区域各顶点的纵坐标数组
	 * @param lat
	 *            区域各顶点的横坐标数组
	 * @return
	 * @作者及日期：wangyikai 2018-08-17
	 * @修改人及日期：
	 * @修改描述： 
	 * @其他：
	 */
	public static boolean isInPolygon(double pointLon, double pointLat, double[] lon, double[] lat) {
		// 将要判断的横纵坐标组成一个点
		Point2D.Double point = new Point2D.Double(pointLon, pointLat);
		// 将区域各顶点的横纵坐标放到一个点集合里面
		List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
		double polygonPoint_x = 0.0, polygonPoint_y = 0.0;
		for (int i = 0; i < lon.length; i++) {
			polygonPoint_x = lon[i];
			polygonPoint_y = lat[i];
			Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
			pointList.add(polygonPoint);
		}
		return check(point, pointList);
	}
	
	/**
	 * 方法说明：一个点是否在多边形内
	 *
	 * @param point
	 *            要判断的点的横纵坐标
	 * @param polygon
	 *            组成的顶点坐标集合
	 * @return
	 * @作者及日期：wangyikai 2018-08-17
	 * @修改人及日期：
	 * @修改描述： 
	 * @其他：
	 */
	private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
		java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();
		Point2D.Double first = polygon.get(0);
		// 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
		peneralPath.moveTo(first.x, first.y);
		polygon.remove(0);
		for (Point2D.Double d : polygon) {
			// 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
			peneralPath.lineTo(d.x, d.y);
		}
		// 将几何多边形封闭
		peneralPath.lineTo(first.x, first.y);
		peneralPath.closePath();
		// 测试指定的 Point2D 是否在 Shape 的边界内。
		return peneralPath.contains(point);
	}

	/** * 装位数不够totalBit的补0 */
	public static String fillZero(int value, int totalBit) {
		return fillZero(String.valueOf(value), totalBit);
	}

	public static String fillZero(String value, int totalBit) {
		int length = value.length();
		int fillLength = totalBit - length;
		if (fillLength < 0) {
			return value;
		}
		String returnValue = "";
		for (int i = 0; i < fillLength; i++) {
			returnValue += "0";
		}
		returnValue += value;
		return returnValue;
	}
	
	/**
	 * 方法描述：截取含有"[""]"的string字符串
	 * @param value
	 * @return
	 */
	public static String getStringValue(String value) {
		value = value.replace("[", "");
		value = value.replace("]", "");
		return value;
	}
	
	/**
	 * 字符串乱码转成utf-8格式
	 * @param str
	 * @return
	 */
	public static String toUTF8(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		try {
			if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
				str = new String(str.getBytes("GB2312"), "utf-8");
				return str;
			}
		} catch (Exception exception) {
		}
		try {
			if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				str = new String(str.getBytes("ISO-8859-1"), "utf-8");
				return str;
			}
		} catch (Exception exception1) {
		}
		try {
			if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
				str = new String(str.getBytes("GBK"), "utf-8");
				return str;
			}
		} catch (Exception exception3) {
		}
		return str;
	}


	/**
	 * 判断多个字符串是否为空 其中不能有空字符串空格 其中有一个为空就返回true 反之false
	 * @param strings
	 * @return
	 */
	public static boolean isExistBlank(String... strings){

		for (String string : strings) {
			if(StringUtils.isBlank(string)){
				return true;
			}
		}

		return false;
	}
	
}
