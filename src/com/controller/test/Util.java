package com.controller.test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@SuppressWarnings("all")
public class Util {
	public final static Logger log = Logger.getLogger(Util.class);
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz").toCharArray();
	private static char[] CNChars= {'一','二','三','四','五','六','七','八','九','十'};
	private static Random strGen = new Random();
	
	private final static String[] cityArr = {"北京","上海","广州","深圳","合肥","郑州","武汉","南京","重庆","成都","青岛","苏州","石家庄","长春","西安"};
	private final static String[] telFirstArr={"134","135","136","137","138","139","150","151","152","157","158","159","176","182","170"};
	
	public static String getYYYYMM() {
		return new SimpleDateFormat("yyyyMM").format(new Date());
	}
	public static String getYYYYQuarter(){
		return getYYYYQuarter(new Date());
	}
	public static String getYYYYQuarter(Date date){
		if(date == null)
			date = new Date();
		String year = getDateYYYYMM(date), month = year.substring(4,6), quarter = "";
		if(month.matches("01|02|03")){
			quarter = "01";
		}else if(month.matches("04|05|06")){
			quarter = "02";
		}else if(month.matches("07|08|09")){
			quarter = "03";
		}else if(month.matches("10|11|12")){
			quarter = "04";
		}
		return year.substring(0, 4)+quarter;
	}
	
	public static String getFileExt(String fileName){
		if(fileName == null)
			return null;
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(fileName.indexOf("?") != -1){
			return fileName.substring(0, fileName.indexOf("?"));
		}else{
			return fileName;
		}
	}
	
	public static boolean ltFileSize(long size, int amount){
		if(size > 1024 * 1024 * amount){
			return false;
		}else{
			return true;
		}
	}
	
	public static String genNewFileNameByDate(String fileExt){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
	}
	
	
	public static String getBcolor(int min, int max){
		if(min < 0) min =0; if(min > 255) min = 255;
		if(max < 1) max = 1; if(max > 255) max = 255;
		Random rd = new Random();
		String str = "";
		for(int i = 0; i < 3; i++){
			int val = rd.nextInt(max - min + 1) + min;
			String hex = Integer.toHexString(val);
			if(hex.length() == 1)
				hex = "0"+hex;
			str += hex;
		}
		return "#"+str;
	}
	
	public static String getBcolor(int rmin, int rmax, int gmin, int gmax, int bmin, int bmax){
		if(rmin < 0) rmin =0; if(rmin > 255) rmin = 255;
		if(rmax < 0) rmax = 0; if(rmax > 255) rmax = 255;
		if(gmin < 0) gmin =0; if(gmin > 255) gmin = 255;
		if(gmax < 0) gmax = 0; if(gmax > 255) gmax = 255;
		if(bmin < 0) bmin =0; if(bmin > 255) bmin = 255;
		if(bmax < 0) bmax = 0; if(bmax > 255) bmax = 255;
		Random rd = new Random();
		String str = "#";
		int r = rd.nextInt(rmax - rmin + 1) + rmin;
		int g = rd.nextInt(gmax - gmin + 1) + gmin;
		int b = rd.nextInt(bmax - bmin + 1) + bmin;
		for(int i : new int[]{r,g,b}){
			String h = Integer.toHexString(i);
			if(h.length() == 1)
				h = "0"+h;
			str += h;
		}
		return str;
	}
	
	public static boolean parseBoolean(Object obj){
		return parseBoolean(obj,false);
	}
	public static boolean parseBoolean(Object obj,boolean def){
		if(null == obj){
			return def;
		}
		if(obj instanceof Boolean){
			return (Boolean)obj;
		}else{
			try{
				return Boolean.valueOf(obj.toString().trim());
			}catch (Exception e) {
				return def;
			}
		}
	}
	
	public static Integer parseInt(Object obj){
		return parseInt(obj,0);
	}
	
	public static Integer parseInt(Object obj, Integer id){
		if(obj == null || obj == "null" || "".equals(obj.toString().trim()))
			return id;
		try{
			id = Integer.parseInt(obj.toString().trim());
		}catch (Exception e) {
//			e.printStackTrace();
		}
		return id;
	}
	
	public static Long parseLong(Object obj){
		return parseLong(obj,0l);
	}
	
	public static Long parseLong(Object obj, Long id){
		if(obj == null ||"null".equals(obj) || "".equals(obj.toString().trim()))
			return id;
		try{
			id = Long.parseLong(obj.toString().trim());
		}catch (Exception e) {
		}
		return id;
	}
	
	
	public static Float parseFloat(Object obj) {
		return parseFloat(obj, 0.0f);
	}
	public static Float parseFloat(Object obj, Float f) {
		if(obj == null || "null".equals(obj) || "".equals(obj.toString().trim()))
			return f;
		try{
			return Float.parseFloat(obj.toString().trim());
		}catch (Exception e) {
		}
		return f;
	}
	
	
	public static Double parseDouble(Object obj) {
		return parseDouble(obj, 0.0d);
	}
	public static Double parseDouble(Object obj, Double d) {
		if(obj == null || "null".equals(obj) || "".equals(obj.toString().trim()))
			return d;
		try{
			return Double.parseDouble(obj.toString().trim());
		}catch (Exception e) {
		}
		return d;
	}
	
	public static Date parseDateYMD(String str){
		return parseDate(str, null);
	}
	
	public static Date parseDateYMD_HMS(String str){
		return parseDate(str, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date parseDate(String str,String format){
		if(str == null || "".equals(str.trim()))
			return null;
		if(format == null || "".equals(format.trim()))
				format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.parse(str.trim());
		}catch (Exception e) {
			return null;
		}
	}
	/**
	 * 日期的加减
	 * @param date
	 * @param dateField
	 * @param incr
	 * @return
	 */
	public static Date parseDate(Date date, String dateField, int incr){
		if(date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if("y".equals(dateField)){
			c.add(Calendar.YEAR, incr);
		}else if("M".equals(dateField)){
			c.add(Calendar.MONTH, incr);
		}else if("d".equals(dateField)){
			c.add(Calendar.DAY_OF_MONTH, incr);
		}else if("H".equals(dateField)){
			c.add(Calendar.HOUR_OF_DAY, incr);
		}else if("m".equals(dateField)){
			c.add(Calendar.MINUTE, incr);
		}else if("s".equals(dateField)){
			c.add(Calendar.SECOND, incr);
		}
		return c.getTime();
	}
	
	public static String formatDateYMD(Date date){
		return formatDate(date, null);
	}
	
	public static String formatDateYMD_HMS(Date date){
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formatDate(Date date,String format){
		if(date == null)
			return null;
		if(format == null || "".equals(format.trim()))
			format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.format(date);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static Date getDate(){
		return getDate(new Date(), 0, 0);
	}
	
	
	public static Date getDate(Date date, int type, int offset){
		if(date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int n = 0;
		if(type == 1)
			n = Calendar.YEAR;
		else if(type == 2)
			n = Calendar.MONTH;
		else if(type == 3)
			n = Calendar.DAY_OF_MONTH;
		else if(type == 4)
			n = Calendar.HOUR;
		else if(type == 5)
			n = Calendar.MINUTE;
		else if(type == 6)
			n = Calendar.SECOND;
		
		if( n != 0)
			c.add(n, offset);
		
		return c.getTime();
	}
	
	public static Date getDate(long time) {
		Date date = new Date();
		if(time > 0){
			date.setTime(time);
		}
		return date;
	}
	
	public static String getString(Object obj){
		return getString(obj, null);
	}
	
	
	public static String getString(Object obj, String def){
		if(obj == null)
			return def;
		try{
			if(obj instanceof String){
				String ss = (String) obj;
				if("".equals(ss.trim()))
					return def;
			}
			return String.valueOf(obj);
		}catch (Exception e) {
			return def;
		}
	}
	
	public static String parseString(Object obj) {
		return parseString(obj, null);
	}
	
	public static String parseString(Object obj, String def) {
		if(obj == null){
			return def;
		}
		if("".equals(obj.toString().trim()))
			return def;
		else
			return obj.toString();
	}
	
	public static String trim(Object obj){
		if(obj == null)
			return null;
		if(obj instanceof String){
			return obj.toString().trim();
		}
		return null;
	}
	
	public static String[] getArrayByStr(String str, String regex){
		return getArrayByStrObj(str, regex);
	}
	public static String[] getArrayByStrObj(Object obj , String regex){
		String str = (String) obj;
		if(str == null || str.trim().length() ==0)
			return new String[]{};
		return str.split(regex);
	}
	
	
	public static String getArrayValByIdx(String[] arr, int idx){
		try{
			return arr[idx];
		}catch (Exception e) {
			return "";
		}
	}
	
	
	public static String getStrByArray(Object[] obj){
		String str = "";
		if(obj != null){
			for(Object o : obj){
				if(o == null || (o+"").length() == 0)
					continue;
				if(str.length() > 0)
					str += ",";
				str += o;
			}
		}
		return str;
	}
	
	
	public static String getUriTail(String uri){
		if(uri == null)
			return null;
		if(uri.lastIndexOf("/") != -1)
			uri = uri.substring(uri.lastIndexOf("/")+1);
		if(uri.indexOf(".") != -1)
			uri = uri.substring(0,uri.indexOf("."));
		
		return uri;
	}
	
	
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getDateYYYYMM(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(date);
	}
	
	public static String getDateHHmmss(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	
	
	public static String isoToUtf8(String str){
		return encoding(str, null, null);
	}
	
	
	public static String encoding(String str, String srcCharset, String destCharset){
		if(str == null)
			return null;
		if(srcCharset == null || srcCharset.trim().length() == 0)
			srcCharset = "iso-8859-1";
		if(destCharset == null || destCharset.trim().length() == 0)
			destCharset = "utf-8";
		try{
			return new String(str.getBytes(srcCharset), destCharset);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static boolean nullOrEmptyString(String string) {
		return string == null || string.length() == 0;
	}

	public static boolean nullOrEmptyOrBlankString(String string) {
		int length = string == null ? 0 : string.length();
		if (length > 0) {
			for (int i = 0; i < length; i++)
				if (!Character.isWhitespace(string.charAt(i)))
					return false;

		}
		return true;
	}
	
	public static boolean isEmpty(String str){
		if (str == null)
			return true;
		return "".equals(str.trim());
	}
	
	/**
	 * 忽略大小写的部分匹配
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean matche(String regex, String str) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(str).find();
	}
	/**
	 * 不忽略大小写的全部匹配
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean matches(String regex, String str) {
		return Pattern.matches(regex, str);
	}
	
	/**
	 * 获取字符串中的中文
	 * @param str
	 * @return
	 */
	public static String getZh(String str) {
		return getZh(str, 1, "");
	}
	/**
	 * 获取字符串中的中文
	 * @param str
	 * @return
	 */
	public static String getZh(String str, String defStr) {
		return getZh(str, 1, defStr);
	}
	/**
	 * 获取字符串中的中文
	 * 
	 * @param str 字符串
	 * @param index 指定匹配组索引
	 * @param defStr 没有找到时，默认返回的值
	 * @return
	 */
	public static String getZh(String str, int index, String defStr) {
		Pattern p = Pattern.compile("([[\u4E00-\u9FA5]]+)");
		Matcher m = p.matcher(str);
		if (m.find()) {
			defStr = m.group(1);
		}
		return defStr;
	}
	/**
	 * 获取 当前时间并转换成秒
	 * @return 返回 精确到 秒数(长度 10位)
	 */
	public static long getLongDateS(){
		return getLongDateS(new Date());
	}
	
	public static long getNextYearD(){
		Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        return date.getTime();
	}
	
	/**
	 * 根据时间获取下一年的时间戳
	 * @param s
	 * @return
	 */
	public static long getNextYearByDateTime(long s){
		Calendar calendar = Calendar.getInstance();
		if(s == 0)
			s = System.currentTimeMillis();
		Date date = new Date(s);
		calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        return date.getTime();
	}
	
	/**
	 * 获取时间 + N 天的时间戳
	 * @param s
	 * @return
	 */
	public static long getNextDayByDay(long s, int day){
		Calendar calendar = Calendar.getInstance();
		if(s == 0)
			s = System.currentTimeMillis();
		Date date = new Date(s);
		calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        date = calendar.getTime();
        return date.getTime();
	}
	
	/**
	 * 获取 当前时间并转换成秒
	 * @return 返回 精确到 秒数(长度 10位)
	 */
	public static long getLongDateS(Date date){
		return getLong(date, 1);
	}
	
	/**
	 * 获取日期为 long型值
	 * @param type 类型。0：毫秒，1： 秒，2：分， 3：时， 4：天
	 * @return
	 */
	public static long getLong(Date date, int type){
		if(date == null)
			return -1;
		if(type == 0)
			return date.getTime();
		else if(type == 1)
			return date.getTime()/1000;
		else if(type == 2)
			return date.getTime()/1000/60;
		else if(type == 3)
			return date.getTime()/1000/60/60;
		else if(type == 4)
			return date.getTime()/1000/60/60/24;
		else
			return -1;
	}
	
	public static Map<String, Object> getMap(Object... objects) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (objects != null) {
			if (objects.length % 2 == 0) {
				for (int i = 0; i < objects.length; i += 2)
					result.put(objects[i].toString(), objects[i + 1]);
			} else {
				throw new RuntimeException("传递的参数个数不正确！");
			}
		}
		return result;
	}
	public static Map<String, String> getMap2(String... strs) {
		Map<String, String> result = new HashMap<String, String>();
		if (strs != null) {
			if (strs.length % 2 == 0) {
				for (int i = 0; i < strs.length; i += 2)
					result.put(strs[i].toString(), strs[i + 1]);
			} else {
				throw new RuntimeException("传递的参数个数不正确！");
			}
		}
		return result;
	}
	
	public static String getMatchStr(String reg, String text, int group){
		String v = "";
		if(group < 1) group = 1;
		Matcher m = Pattern.compile(reg).matcher(text);
		if (m.find()) {
			v = m.group(group);
		}
		return v;
	}
	
	/**
	 * 字符串长度超过一定值会截取
	 * @param obj
	 * @param length
	 * @return
	 */
	public static String getSubStr(String obj, int length){
		if(length>0){
			if(obj.length()>length)
				obj = obj.substring(0,length)+"...";
		}
		return obj;
	}

	/**
	 * 校验邮箱
	 * @author lyb
	 * @time 2017-8-28 - 上午10:38:52	
	 * @param args
	 */
	public static boolean checkEmail(String str){
		boolean flag = false;
		  try{
		   String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		   Pattern regex = Pattern.compile(check);
		   Matcher matcher = regex.matcher(str);
		   flag = matcher.matches();
		  }catch(Exception e){
			  flag = false;
		  }
		  return flag;
	}
	/**
	 * 校验手机
	 * @author lyb
	 * @time 2017-8-28 - 上午10:38:52	
	 * @param args
	 */
	public static boolean checkMobile(String str){
		boolean flag = false;
		  try{
		   String check = "^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$";
		   Pattern regex = Pattern.compile(check);
		   Matcher matcher = regex.matcher(str);
		   flag = matcher.matches();
		  }catch(Exception e){
			  flag = false;
		  }
		  return flag;
	}
	
	/**
	 * 秒数转为 日期 YYYY-MM-DD
	 * @param s
	 * @return
	 */
	public static String getDateYYYYMMDD(String s){
		return getDate(s, "yyyy-MM-dd");
	}
	/**
	 * 秒数转为 日期 "yyyy-MM-dd HH:mm:ss"
	 * @param s
	 * @return
	 */
	public static String getDateTime(String s){
		return getDate(s, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 秒数转为 日期
	 * @param s
	 * @return
	 */
	public static String getDate(String s, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long lt = new Long(Long.parseLong(s)*1000);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
	}
	
	public static String getDateYmdStr(Date dt){
		return new SimpleDateFormat("yyyyMMdd").format(dt);
	}
	
	public static String getDateYmdStr(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static Integer getDateYmd(){
		return Integer.parseInt(Util.getDateYmdStr());
	}
	
	public static Integer getDateYmd(Date dt){
		return Integer.parseInt(Util.getDateYmdStr(dt));
	}
	
	/**
	 * 获取某一天0点的时间戳
	 * @param date : yyyy-MM-d
	 * @return
	 */
	public static long getZeroTime(String date){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(date + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return d.getTime();
	}
	
	/**
	 * 获取本周一0点的时间
	 * @return
	 */
	public static long getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
				cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime().getTime();
	}
	/**
	 * 获得本月第一天0点时间
	 * @return
	 */
	public static long getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
				cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime().getTime();
	}
	/**
	 * 获取当前0点时间戳
	 * @return
	 */
	public static long getZeroTime(){
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime(); 
	}

	public static long ip2Long(String ip) {
		Long ips = 0L; 
		String[] numbers = ip.split("\\.");
		//等价上面
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(numbers[i]);
		}
		return ips;
	}
	
	public static String long2Ip(long number) {
		//等价上面
		String ip = "";
		for (int i = 3; i >= 0; i--) {
			ip  += String.valueOf((number & 0xff));
			if(i != 0){
				ip += ".";
			}
			number = number >> 8;
		}
		return ip;	    
	}
	
	/**
	 * 拼接图片地址
	 * @param path
	 * @return
	 */
	public static String getImagePath(String prefixUrl, String path){
		if(Util.isEmpty(path)){
			return "";
		}
		String pathTemp = path.toLowerCase();
		if(pathTemp.startsWith("http://") || pathTemp.startsWith("https://") || pathTemp.startsWith("fxweb://")){
			return path;
		}
		boolean endExist = false;
		if(prefixUrl.lastIndexOf("/") != -1){
			endExist = true;
		}
		if(endExist){
			if(path.startsWith("/")){
				prefixUrl = prefixUrl.substring(0, prefixUrl.length() - 1);
			}
		}else{
			if(!path.startsWith("/")){
				prefixUrl = prefixUrl+"/";
			}
		}
		return prefixUrl + path;
	}
	
	public static String getImagePathR(String path){
		if(Util.isEmpty(path)){
			return "";
		}
		String pathTemp = path.toLowerCase();
		if(pathTemp.startsWith("http://") || pathTemp.startsWith("https://") || pathTemp.startsWith("/")){
			return path;
		}else{
			return "/"+path;
		}
	}
	
	public static String objectToString(Object obj){
		return obj == null?"":String.valueOf(obj);
	}
	public static String objectToDoubleString(Object obj){
		return obj == null?"0.0":String.valueOf(obj);
	}
	
	public static String filterEmoji(String source) {
		if(isEmpty(source))return "";
        return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
	}
	
	/**
	 * 50.0返回50    50.10返回50.1
	 * @param s
	 * @return
	 */
	public static String rvZeroAndDot(String s){  
		if (Util.isEmpty(s)) {
			return null;
		}
		if(s.indexOf(".") > 0){  
			s = s.replaceAll("0+?$", "");//去掉多余的0  
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
		}  
	    return s;  
	}
	
	/**
	 * 50.0返回50    50.10返回50
	 * @param s
	 * @return
	 */
	public static String rvInt(String s){  
		if (Util.isEmpty(s)) {
			return null;
		}
		if(s.indexOf(".") > 0){  
			s = s.substring(0, s.lastIndexOf("."));
		}
	    return s;  
	}
	
	/**
	 * 100以内阿拉伯数字转中文数字：1 --> 一
	 * @param number
	 * @return
	 */
	public static String number2CN(int number){
		if(number < 11 && number > 0){
			return CNChars[number-1] + "";
		}else if(number > 10 & number < 20){
			return "十" + CNChars[number%10-1];
		}else if(number > 19 && number < 100){
			return CNChars[number/10-1] + "十" + (number%10-1 < 0?"":""+CNChars[number%10-1]);
		}
		return "";
		
	}
	
	public static String getCityArr(int number){
		return Util.cityArr[number];
	}
	
	public static String getTelFirstArr(int number){
		return Util.telFirstArr[number];
	}
	
	public static int getLength(String s){  
        s = s.replaceAll("[^\\x00-\\xff]", "**");  
        int length = s.length();  
        return length;  
    }
	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        // 保留num的位数
        // 0 代表前面补充0     
        // num 代表长度为4     
        // d 代表参数为正数型 
        return String.format("%0" + num + "d", Integer.parseInt(code) + 1);
    }
    
    public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
    
    //过滤img标签的样式
  	public static String filterImgStyle(String htmlStr) {
  		StringBuffer reStr = new StringBuffer();
  		String regEx_img = "(i?)<img.*? src=\"?(.*?\\.(jpg|gif|bmp|bnp|png))\".*?>";
  		Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
  		Matcher m_image = p_image.matcher(htmlStr);
  		int s = 0;
  		while (m_image.find()) {
  			int m = m_image.start();
  			int n = m_image.end();
  			reStr.append(htmlStr.substring(s, m));
  			String img = m_image.group();
  			// 匹配<img>中的src数据  
              Matcher m_str = Pattern.compile(" src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
              if(m_str.find()){
              	String srcTemp = m_str.group(1);
              	if(!srcTemp.startsWith("/") && !srcTemp.startsWith("http")){
              		srcTemp = "/"+srcTemp;
              	}
              	reStr.append("<img class=\"con_img\" src=\""+srcTemp+"\"/>");
              }else{
              	reStr.append("");
              }
  			s = n;
  		}
  		reStr.append(htmlStr.substring(s));
  		return reStr.toString();
  	}
  	
  	//过滤P标签样式
  	public static String filterPStyle(String htmlStr) {
  		StringBuffer reStr = new StringBuffer();
  		String regEx = "(i?)<p.*?>";
  		Pattern pt = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
  		Matcher m_p = pt.matcher(htmlStr);
  		int s = 0;
  		while (m_p.find()) {
  			int m = m_p.start();
  			int n = m_p.end();
  			reStr.append(htmlStr.substring(s, m));
  			reStr.append("<p class=\"con_word\">");
  			s = n;
  		}
  		reStr.append(htmlStr.substring(s));
  		return reStr.toString();
  	}
  	
  	public static String strFilter (String str){
  		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
  		Pattern p = Pattern.compile(regEx); 
  		Matcher m = p.matcher(str); 
  		return m.replaceAll("").trim(); 
  	}
  	
  	private static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {    
  		if (map == null)  
  			return null;    
  		Object obj = beanClass.newInstance();  
  		Field[] fields = obj.getClass().getDeclaredFields();
  		List<Method> objectSetMethods = getObjectSetMethods(beanClass);
  		Map<String,Method> methodMap = new HashMap<>();
		for(Method method:objectSetMethods){
			methodMap.put(method.getName().toLowerCase(), method);
		}
  		for (Field field : fields) {    
  			int mod = field.getModifiers();    
  			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
  			}    
  			field.setAccessible(true);  
  			Method method = methodMap.get("set"+field.getName().toLowerCase());
  			if(method == null){
				continue;
			}
  			Class<?> cc=method.getParameterTypes()[0];
			String parameterType=cc.getSimpleName();
			String value = map.get(field.getName());
			if(Util.isEmpty(value) || "null".equals(value)){
				if("String".equals(parameterType)){
					field.set(obj, "");
				}else{
					field.set(obj, null);
				}
			} else if ("int".equals(parameterType) || "Integer".equals(parameterType)) {
				int v = Integer.parseInt(value);
				field.set(obj, v);
			} else if ("float".equals(parameterType) || "Float".equals(parameterType)) {
				float v = Float.parseFloat(value);
				field.set(obj, v);
			} else if ("double".equals(parameterType) || "Double".equals(parameterType)) {
				double v = Double.parseDouble(value);
				field.set(obj, v);
			} else if("short".equals(parameterType) || "Short".equals(parameterType)){
				short v = Short.parseShort(value);
				field.set(obj, v);
			} else if("long".equals(parameterType) || "Long".equals(parameterType)){
				long v = Long.parseLong(value);
				field.set(obj, v);
			}  else {
				field.set(obj, value);
			}
  		 }   
  		return obj;    
  	} 
  	
  	private static <T> List<Method> getObjectSetMethods(Class<T> cls){
		List<Method> setMethods = new ArrayList<Method>();
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				setMethods.add(method);
			}
		}
		return setMethods;
	}
  	
  	private static Map<String, String> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
        Map<String, String> map = new HashMap<String, String>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            Object oo = field.get(obj);
            map.put(field.getName(), oo==null?"":oo.toString());  
        }    
        return map;  
    }
  	
	public static void main(String[] args) {
//		System.out.println(Util.stringFilter(""));
	}
	
}
