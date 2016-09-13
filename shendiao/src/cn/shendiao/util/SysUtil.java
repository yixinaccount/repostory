package cn.shendiao.util;



import java.beans.PropertyDescriptor;
import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;




import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;




public class SysUtil {
	
	protected static Logger log = Logger.getLogger(SysUtil.class);
	
	/**
	 * 系统常量
	 */
	public final static String ENCODE = "UTF-8";	// 系统编码
	
	public static final String ADMIN = "1";		// 超级管理员
	
	public final static String DEFAULT = "0";		// 默认用户类型
	
	public final static String USE = "0";		// 启用
	
	public final static String UNUSE = "1";		// 停用
	
	public final static String DEL = "2";		// 删除
	
	/**
	 * 文件路径
	 */
	public static final String FILE_PATH = "upload";
	
	/**
	 * session登陆的名称
	 */
	public final static String SESSION_USER = "loginUser";
	
	/**
	 * 敏感字符的名称
	 */
	public final static String SENSITIVE = "SENSITIVE";
	
	/**
	 * 分页个数
	 */
	public final static int ROWS = 10;
	
	/**
	 * 前台分页个数
	 */
	public final static int ROWS_HOME = 50;
	
	/**
	 * 前台分页个数
	 */
	public final static int ROWS_SEARCH = 10;
	
	/**
	 * 系统代码
	 */
	public final static int SUCCESS = 0;		// 成功
	
	public final static int FAILURE = -1;		// 失败
	
	/**
	 * 文件大小单位
	 */
	public final static String B = "B";		// byte
	
	public final static String K = "KB";		// kb
	
	public final static String M = "MB";		// mb
	
	public final static String G = "GB";		// gb
	
	public final static int UNIT = 1024;		// 换算单位
	
	/**
	 * 通过递归获取目录下所有文件的大小
	 * @param path
	 * @return
	 */
	public static long getLength(File path) {
		long length = 0;
		if (path.isDirectory()) {
			File temp[] = path.listFiles();
			for (File file : temp) {
				length += getLength(file);
			}
		} else {
			length += path.length();
		}
		return length;
	}
	
	/**
	 * 获取文件长度所代表的单位
	 * @param length 文件长度
	 * @return
	 */
	public static String getLengthStr(Long length) {
		String value = "";
		if (length < 900 && length >= 0) {
			value = length + " " + B;
			return value;
		}
		double temp = Double.parseDouble(length + "") / UNIT;
		if (temp >= 0.9 && temp < 900) {
			return String.format("%.2f", temp) + " " + K;
		}
		temp = Double.parseDouble(length + "") / UNIT / UNIT;
		if (temp >= 0.9 && temp < 900) {
			return String.format("%.2f", temp) + " " + M;
		}
		temp = Double.parseDouble(length + "") / UNIT / UNIT / UNIT;
		if (temp >= 0.9 && temp < 900) {
			return String.format("%.2f", temp) + " " + G;
		}
		return length + " " + B;
	}
	
	/**
	 * 验证字符串是否为空
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		return (val == null || val.equals("")) ? true : false;
	}
	
	/**
	 * 判断对象是否为空
	 * @param o
	 * @return
	 */
	public static boolean isNull(Object o) {
		if (o instanceof String) {
			return (o == null || isNull(o.toString())) ? true : false;
		} else {
			return o == null ? true : false;
		}
	}
	
	/**
	 * 对象o的多判断多返回
	 * 
	 * @param o 要判断的对象
	 * @param code 不定量参数，除默认值外均不能为空，参数个数为双数时，默认返回null，个数为单数时，默认返回最后一个参数值
	 * @return
	 */
	public static Object decode(Object o, Object... code) {
		Object value = null;
		if (o == null) {
			throw new NullPointerException("要判断的参数不能为空");
		}
		for (int i = 0; i < (code.length / 2); i++) {
			if (code[i * 2 + 1] == null || code[i * 2] == null) {
				throw new NullPointerException("第 " + (i + 2) + " 个参数不能为空");
			}
			if (o.equals(code[i * 2])) {
				value = code[i * 2 + 1];
				break;
			}
		}
		if (value == null) {
			if (code.length % 2 == 1 ) {
				value = code[code.length - 1];
			}
		}
		return value;
	}
	
	/**
	 * 如果o的默认值是null，则初始化为v，否则直接返回o
	 * @param o 要初始化的对象
	 * @param v 默认输出
	 * @return
	 */
	public static Object nvl(String o, String v) {
		return isNull(o) ? v : o;
	}
	
	/**
	 * 把时间格式转换为字符
	 * @param date 要转换的时间
	 * @param p 格式
	 * @return
	 */
	public static String toChar(Date date, String p) {
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.format(date);
	}
	
	/**
	 * 当前时间增加天数
	 * @param days
	 * @return
	 */
	public static Date nowTimeAddDay(Integer days){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
       
        c.add(Calendar.DAY_OF_MONTH, days);
        
        return c.getTime();
		
	}
	
	/**
	 * 把时间格式转换为字符
	 * @param date 要转换的时间
	 * @param p 格式
	 * @return
	 */
	public static String toChar(long date, String p) {
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.format(date);
	}
	
	/**
	 * 把时间格式的字符转换成Date对象
	 * @param date 要转换的时间
	 * @param p 格式
	 * @return
	 */
	public static Date toDate(String date, String p) {
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("工具类异常", e);
			return null;
		}
	}
	
	/**
	 * 让字符串的首字母大写
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		String temp = str.substring(0, 1);
		str = temp.toUpperCase() + str.substring(1, str.length());
		return str;
	}
	
	/**
	 * 验证map中的个别键是否为空
	 * @param param 要验证的map
	 * @param keys 
	 * @return
	 */
	public static boolean verifyMapNotNull(Map<String, String> param, String... keys) {
		boolean bool = Boolean.TRUE;
		if (param == null) {
			return false;
		}
		for (String k : keys) {
			if (isNull(param.get(k))) {
				bool = false;
				break;
			}
		}
		return bool;
	}
	
	
	
	
	/**
	 * 获得用户ip
	 * @param 
	 * @param 
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
	    if (request.getHeader("x-forwarded-for") == null) {
	    	
		   return request.getRemoteAddr();
		  }
		  return request.getHeader("x-forwarded-for");
	    }
	
	/**
	 * 判断是否request里面有某项
	 */
	public static boolean getIs(HttpServletRequest request,String name){
		boolean bool = Boolean.TRUE;
		String getcontent =null;
		getcontent = (String)request.getAttribute(name);
		if(getcontent!=null){
			request.removeAttribute(name);
			
		}
			
		return bool;
	}
	/**
	 * 生产随机数
	 * @param length 长度
	 * @return
	 */
	public static String RandomUtils(int length){
		 String allChar = "0123456789abcdefghijklmnopqrstuvwxyz";
		 
		 StringBuffer sb = new StringBuffer();
		 Random random = new Random();
		 for (int i = 0; i < length; i++) {
			 sb.append(allChar.charAt(random.nextInt(allChar.length())));
		 }
		 return sb.toString();
	}
	
	/**
	 * 实体转map
	 * @param obj
	 * @return
	 */
	  public static Map<String, Object> beanToMap(Object obj) { 
          Map<String, Object> params = new HashMap<String, Object>(0); 
          try { 
              PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
              PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
              for (int i = 0; i < descriptors.length; i++) { 
                  String name = descriptors[i].getName(); 
                  if (!"class".equals(name)) { 
                      params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                  } 
              } 
          } catch (Exception e) { 
              e.printStackTrace(); 
          } 
          return params; 
  }
	  
	  
	  /**
	   * md5加密
	   * @param inputText
	   * @return
	   * @throws Exception
	   */
	  public static String md5(String inputText) throws Exception {
		  MessageDigest m = MessageDigest.getInstance("md5");
		  m.update(inputText.getBytes());
		  return hex(m.digest());
	  }
	  
	  /**
	   * md5加密
	   * @param bytes
	   * @return
	   * @throws Exception
	   */
	  public static String md5String(byte[] bytes) throws Exception {
			MessageDigest m = MessageDigest.getInstance("md5");
			m.update(bytes);
			return hex(m.digest());
			
		}
			
	  private static String hex(byte[] arr) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; ++i) {
				sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}	
	  
	  
	  
	  
	  //产生随机数
	  
	  	private final static Random RANDOM = new Random();
		private final static char[] abcChars = new char[52];

		static {
			for (int i = 0; i < abcChars.length; i++) {
				int index = i + 65;
				if (index > 90) {
					index = index + 6;
				}
				abcChars[i] = (char) index;
			}
		}

		public static int getInt(int max) {
			return RANDOM.nextInt(max);
		}

		/**
		 * 产生随机数
		 * 
		 * @return
		 */
		public static String getRanText(int len) {
			StringBuffer text = new StringBuffer();
			for (int i = 0; i < len; i++) {
				text.append(RANDOM.nextInt(10));
			}
			return text.toString();
		}

		/**
		 * 产生随机小写英文字符
		 * 
		 * @return
		 */
		public static String getAbcLowerText(int len) {
			return getABCText(len).toLowerCase();
		}

		/**
		 * 产生随机大写英文字符
		 * 
		 * @return
		 */
		public static String getAbcUpperText(int len) {
			return getABCText(len).toUpperCase();
		}

		/**
		 * 产生随机英文字符
		 * 
		 * @return
		 */
		public static String getABCText(int len) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < len; i++) {
				sb.append(abcChars[RANDOM.nextInt(abcChars.length)]);
			}
			return sb.toString();
		}

		public static <T> T getRandomItem(List<T> list) {
			T item = list.get(RANDOM.nextInt(list.size()));
			list.remove(item);
			return item;
		}
	  
		
		/**
		 * 判断两个时间相差天数
		 * @param date1
		 * @param date2
		 * @return
		 */
	 public static int differDays(Date date1,Date date2)
		    {
		        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		        return days;
		    }
	  
	 /**
		 * 判断两个时间相差分钟数
		 * @param date1
		 * @param date2
		 * @return
		 */
	 public static int differMinute(Date date1,Date date2)
		    {
		        int minute = (int) ((date2.getTime() - date1.getTime()) / (1000*60));
		        return minute;
		    }
			
	
}
