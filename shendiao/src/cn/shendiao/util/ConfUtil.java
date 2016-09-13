package cn.shendiao.util;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取properties配置文件
 * @author ssk
 * @date 2012-12-26 下午1:39:35
 *
 */
public class ConfUtil {
	
	public static Logger log = Logger.getLogger(ConfUtil.class);
	
	private static Properties p = new Properties();

	/**
	 * 加载properties文件
	 */
	static {
		try {
			p.load(ConfUtil.class.getClassLoader().getResourceAsStream("app.properties"));
		} catch (Exception e) {
			// TODO: handle exception
			log.error("加载properties文件异常", e);
			System.out.println("properties 文件加载失败");
		}
	}
	
	/**
	 * 通过key，获取properties文件的值
	 * @param key 键
	 * @return
	 */
	public static String getValue(String key) {
		return p.getProperty(key);
	}
	
	/**
	 * 设置properties文件的键值对
	 * @param key 键
	 * @param value 值
	 * @param rootPath properties文件跟路径
	 */
	public static void setValue(String key, String value, String rootPath) {
		p.setProperty(key, value);
		try {
			String appPath = rootPath + File.separator + "WEB-INF" + File.separator + "classes" 
						+ File.separator + "app.properties";
			OutputStream out = new FileOutputStream(appPath);
			p.store(out, "update " + key + " value");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("设置properties文件异常", e);
		}
	}
}
