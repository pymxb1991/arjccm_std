package com.arjjs.ccm.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author maoxiaobin 2018.8.26
 */
public class ShareUtil {
	

	/**
	 * byte转inputstream
	 * 
	 * @param buffer
	 * @return
	 */
	public static final InputStream byte2Input(byte[] buffer) {
		return new ByteArrayInputStream(buffer);
	}

	/**
	 * inputstream转outputstream
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static final OutputStream input2output(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		return swapStream;
	}

	/**
	 * inputstream转byte
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static final byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}


	public static String getCurrentPreciseTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HH:mm:ss:SSS");

		String formatStr =formatter.format(new Date());
		return formatStr;
	}

	/**
	 * 获取当前日期 精确到分
	 * 
	 */
	public static String getCurrentMinute(String time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String minuteTime = "";

		if (time != null && !"".equals(time)) {
			Date parse;
			try {
				parse = sdf.parse(time);
				minuteTime = sdf.format(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return minuteTime;
	}
	/**
	 * 获取当前日期 精确到分
	 * 
	 */
	public static String getCurrentMinute2(String time) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String minuteTime = "";
		
		if (time != null && !"".equals(time)) {
			Date parse;
			try {
				parse = sdf.parse(time);
				minuteTime = sdf.format(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return minuteTime;
	}
	/**
	 * 获取当前日期 精确到秒
	 * 
	 */
	public static String getTimeSecond(String time) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String minuteTime = "";
		
		if (time != null && !"".equals(time)) {
			Date parse;
			try {
				parse = sdf.parse(time);
				minuteTime = sdf.format(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return minuteTime;
	}
	/**
	 * 获取当前日期 精确到年月日
	 * 
	 */
	public static String getCurrentDate(String time) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String minuteTime = "";

		if (time != null && !"".equals(time)) {
			Date parse;
			try {
				parse = sdf.parse(time);
				minuteTime = sdf.format(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return minuteTime;
	}
	/**
	 * 获取当前日期
	 *
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String formatStr =formatter.format(new Date());
		return  formatStr;
	}
	/**
	 * 获取MD5
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str);
	}

	/**
	 * 获取ID
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").trim();
	}

	/**
	 * 判断是否为允许的上传文件类型,true表示允许
	 * 
	 * @param fileName
	 *            文件名
	 * @param suffix
	 *            后辍名
	 */
	public static boolean checkFileType(String fileName, String suffix) {
		if (suffix.contains(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()))) {
			return true;
		}
		return false;
	}

	/**
	 * 判断list是否为null
	 * 
	 * @param list
	 *            集合
	 */
	public static <T> boolean listIsEmpty(List<T> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 解析html
	 * 
	 * String html
	 *            字符串
	 */
	public static List<String> parseXml(String html){
		List<String> resultList = new ArrayList<String>();
	    Pattern p = Pattern.compile(">([^</]+)</");//正则表达式 commend by danielinbiti
	    Matcher m = p.matcher(html);//
	    while (m.find()) {
	         resultList.add(m.group(1));
	    }
	    return resultList;
	}
	/**
	 * 解析html
	 * 
	 * String html
	 *            字符串
	 */
	public static String delHTMLTag(String html){
		html = html.replace("&nbsp;", "");
		List<String> list = parseXml(html);
		String htmlStr = "";
		for (String str : list) {
			htmlStr = htmlStr + str;
		}
		return htmlStr;
	}
	
	public static String getQueryLike(String content) {
		
		return "%"+content+"%";
	}
	/**
	 * 
	 * @Title: getHandleTime
	 * @Description: 获取办理时长
	 * @param data1 接收时间
	 * @param data2 审批时间
	 * @return: String 
	 */
	public static String getHandleTime(String receiveTime,String operateTime) {
	    StringBuffer t1 = new StringBuffer();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    try {
			Date parse1 = sdf.parse(receiveTime);
			Date parse2 = sdf.parse(operateTime);
			long diff = parse2.getTime() - parse1.getTime();
			long days = diff/(1000*60*60*24);
			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
			if (days == 0) {
				t1.append("办理时长");
				t1.append(hours);
				t1.append("小时");
				t1.append(minutes);
				t1.append("分");
			}else {
				t1.append("办理时长");
				t1.append(hours);
				t1.append("小时");
				t1.append(minutes);
				t1.append("分");
			}
			  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new String(t1);
	}
	
	public static String getReceiveTime(String receiveTime,String currentTime) {
	    StringBuffer t1 = new StringBuffer();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    try {

			Date parse1 = sdf.parse(receiveTime);
			Date parse2 = sdf.parse(currentTime);
			long diff = parse2.getTime() - parse1.getTime();
			long days = diff/(1000*60*60*24);
			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
			
			
			if (days == 0 &&  hours == 0 ) {
				if (minutes == 0) {
					t1.append("刚刚");
				}else {
					t1.append(minutes);
					t1.append("分钟前");
				}
			}else if (days == 0 &&  hours != 0 ) {
				
				t1.append(hours);
				t1.append("小时前");
			}else if (days < 7  ) {
				
				t1.append(days);
				t1.append("天前");
			}else {
				
				t1.append(getCurrentMinute2(receiveTime));
				
			}
			  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new String(t1);
	}
	//注：这是Java正则表达式去除html标签方法。
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签
    /**
     * 
     * @Title: formatDuring
     * @Description: 转换办理时长
     * @param mss
     * @return
     * @return: String
     */
    public static String formatDuring(long mss) {  
	    long days = mss / (1000 * 60 * 60 * 24);  
	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
	   // long seconds = (mss % (1000 * 60)) / 1000;  
	    if(days!=0) {
	    	return "办理时长"+ days + "天" + hours + "小时" + minutes + "分 " ; 
	    }else {
	    	return "办理时长"+ hours + "小时 " + minutes + "分" ; 
	    }
	}
/**
     * @param htmlStr
     * @return 删除Html标签
     */
    public static String parseXmlToString(String htmlStr) {
    	htmlStr = htmlStr.replace("&nbsp;", "");
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签
 
 
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签
 
 
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
 
 
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
 
 
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
 
 
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤 
        return htmlStr.trim(); // 返回文本字符串
    }
    

	public static String toJSON(Object obj) {
	    ObjectMapper om = new ObjectMapper();
	    try {
	        String json = om.writeValueAsString(obj);
	        return json;
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * 
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * @param date1
	 * @param date2
	 * @return 
	 * @throws Exception 
	 **/
	public static Long differentDaysByMillisecond(String strDate1, String strDate2) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		Date date1 =format.parse(strDate1);
		
		Date date2 = format.parse(strDate2);
		
		Long days =  (date2.getTime() - date1.getTime());
		return days;
	}
	/**
	 * 
	 * 
	 * @param date1
	 * @param date2
	 * @return 
	 * @throws Exception 
	 **/
	public static String transContractAmount(String strDate) throws Exception {
		if (StringUtils.isBlank(strDate))
			strDate = "0";
		BigDecimal contractAmount = new BigDecimal(strDate);
		BigDecimal contractAmount2 = contractAmount.multiply( new BigDecimal(1000000));
		BigDecimal setScale = contractAmount2.setScale(0, BigDecimal.ROUND_UP);
		return setScale.toString();
	}
	
	public static Date transDateTime(String strDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null; //初始化date
		try {
			date = sdf.parse(strDate); //Mon Jan 14 00:00:00 CST 2013
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	 public static int compare_date(String DATE1, String DATE2) {
	        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
               // System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
               // System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}