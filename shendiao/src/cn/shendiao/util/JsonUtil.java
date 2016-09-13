package cn.shendiao.util;



import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {

	private static Logger log = Logger.getLogger(JsonUtil.class);
	
	/**
	 * json返回
	 */
	public final static String CODE = "code";		// 系统返回代码
	
	public final static String MSG = "msg";		// 系统返回信息
	
	public static JsonConfig getConfig() {
		JsonConfig conf = new JsonConfig();
		conf.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		conf.setIgnoreDefaultExcludes(false);
		conf.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		conf.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				// TODO Auto-generated method stub
				return process(value);
			}
			
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				// TODO Auto-generated method stub
				return process(value);
			}
			
			private Object process(Object value ) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (value instanceof Timestamp) {
					return sdf.format((Timestamp)value);
				} else if (value instanceof Date) {
					return sdf.format((Date)value);
				} else if (value == null) {
					return "";
				} else {
					return value.toString();
				}
			}
		});
		return conf;
	}
	
	/**
	 * 输出json信息
	 * @param json
	 * @param response
	 */
	public static void writeJson(JSONObject json, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html;charset=" + SysUtil.ENCODE);
		response.setCharacterEncoding(SysUtil.ENCODE);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("系统错误", e);
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "系统错误：" + e.getMessage());
		} finally {
			if (out != null) {
				out.write(json.toString());
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * 输出json信息
	 * @param json
	 * @param response
	 */
	public static void writeStr(String str, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html;charset=" + SysUtil.ENCODE);
		response.setCharacterEncoding(SysUtil.ENCODE);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("系统错误", e);
		} finally {
			if (out != null) {
				out.write(str);
				out.flush();
				out.close();
				out = null;
			}
		}
	}
}
