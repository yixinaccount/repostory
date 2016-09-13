package cn.shendiao.user.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

import net.sf.json.JSONObject;
//实名认证
public class IDcard {
	public static void main(String[] args) {
		IDcard demo= new IDcard();
		demo.idcard_verify("名字","身份证号");
	}
	private final static String mall_id="110592";
	private final static String appkey="6f7cdbfeea982bf093b7c93fd2b68142";
	private final static String url="http://121.41.42.121:8080/v2/id-server?";
	
	
	public int idcard_verify(String realname,String idcard){
		idcard=idcard.toLowerCase();
		long tm=System.currentTimeMillis();
		String md5_param=mall_id+realname+idcard+tm+appkey;
		String sign=md5(md5_param);
		String param=new StringBuffer().append("mall_id="+mall_id)
				.append("&realname="+realname)
				.append("&idcard="+idcard)
				
				.append("&tm="+tm)
				.append("&sign="+sign).toString();
		String url_v=url+param;
		//System.out.println(url_v);
		try {
			url_v=url_v.replace(realname,URLEncoder.encode(realname,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString=url2string(url_v);
		//System.out.println(jsonString);
		//JSONObject result=JSONObject.parseObject(jsonString);
		JSONObject result = JSONObject.fromObject(jsonString);
		//System.out.println(result);
		int status=Integer.parseInt(result.getString("status"));
		int code=Integer.parseInt(result.getJSONObject("data").getString("code"));
		String message=result.getJSONObject("data").getString("message");
		System.out.println(message+" "+code+" "+status);	
		/*客户可以根据自己的业务需求进行处理*/
		//1000=一致
		//1001=不一致
		//1002=库中无此号
		//1101=商家ID不合法
		//1102=身份证姓名不合法
		//1103=身份证号码不合法
		//1104=签名不合法
		//1105=第三方服务器异常
		//1106=账户余额不足
		//1107=tm不合法
		//1108=其他异常
		//1109=账号被暂停
		
		return code;
	}
	private String md5(String s){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	private String url2string(String url){
		StringBuffer sb=new StringBuffer();
		try {
			InputStream is=new URL(url).openStream();
			byte[] buf=new byte[1024*10];
			int len=0;
			while((len=is.read(buf, 0, 1024*10))>0){
				sb.append(new String(buf,0,len));
			}
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}



}
