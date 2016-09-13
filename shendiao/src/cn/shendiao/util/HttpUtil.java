package cn.shendiao.util;



import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	/**
	 * 要请求的url
	 */
	private String url;
	
	/**
	 * 编码
	 */
	private String encode;
	
	/**
	 * http的响应
	 */
	private HttpResponse response;
	
	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public HttpUtil(String url, String encode) {
		this.url = url;
		this.encode = encode;
	}
	
	public enum METHOD {
		POST, GET;
	}
	

	private String encodeUrl(Map<String , String> param) {
		String val = "";
		Set<String> keySet = param.keySet();
		try {
			for (String key : keySet) {
				val += key + "=" + URLEncoder.encode(param.get(key), "UTF-8") + "&";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return val;
	}
	

	
	/**
	 * 获取请求的状态
	 * @return
	 */
	public int getStatusCode() throws Exception {
		if (response == null) {
			throw new Exception("请现请求http，然后在获取消息状态");
		}
		return response.getStatusLine().getStatusCode();
	}
	
	/**
	 * 获取请求返回的字符串
	 * @return
	 * @throws Exception
	 */
	public String getReturnStr() throws Exception {
		if (response == null) {
			throw new Exception("请现请求http，然后在获取消息状态");
		}
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, encode);
	}
	
	
	
}
