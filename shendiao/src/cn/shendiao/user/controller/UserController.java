package cn.shendiao.user.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import org.springframework.web.multipart.MultipartFile;















































import cn.shendiao.account.dao.AccountDao;
import cn.shendiao.constant.PermissionConstant;
import cn.shendiao.login.model.Operator;
import cn.shendiao.office.dao.OfficeDao;
import cn.shendiao.train.dao.TrainDao;
import cn.shendiao.user.dao.UserDao;
import cn.shendiao.user.model.User;
import cn.shendiao.user.service.UserService;
import cn.shendiao.util.ImageUtil;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.SysUtil;
import cn.shendiao.util.ToolUtils;


@Controller
@RequestMapping("user")
public class UserController {
	
	protected final transient Logger log = Logger
			.getLogger(UserController.class);
	
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private TrainDao trainDao;
	
	@Resource
	private AccountDao accountDao;
	
	@Resource
	private OfficeDao officeDao;
	
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param phone
	 * @param password
	 * @param username
	 * @param response
	 */
	@RequestMapping("regist")
	public void regist(HttpServletRequest request,String phone, String password,String nickname,String validateCode,String invitation_code, HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		try {
				
			//验证手机号
			if(!ToolUtils.verifyPhone(phone)){
				json.put("code", -1);
				json.put("msg", "手机号不正确！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			Map<String, Object> mapPhone = userDao.selectUserInfoByPhone(phone);
			
			if(!SysUtil.isNull(mapPhone)){
				json.put("code", -2);
				json.put("msg", "手机号已存在！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			
			if(SysUtil.isNull(nickname)){
				json.put("code", -3);
				json.put("msg", "昵称不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
	
	
			//验证密码
			if(SysUtil.isNull(password)){
				
				json.put("code", -4);
				json.put("msg", "密码不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			/*Map<String, Object> validateMap = userDao.selValidateCode(phone);
			if(SysUtil.isNull(validateMap)){
				json.put("code", -5);
				json.put("msg", "还没有点击发送验证码！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(!validateMap.get("valid_code").equals(validateCode)){
				
				json.put("code", -6);
				json.put("msg", "验证码错误！");
				JsonUtil.writeJson(json, response);
				return;
			}*/
			if(userDao.selUserInvitationBycode(invitation_code)==0){
				json.put("code", -9);
				json.put("msg", "邀请码错误！");
				JsonUtil.writeJson(json, response);
				return;
			}
			userService.addUserInfo(phone,password,nickname,1,invitation_code);
			/*User user = userDao.login(phone,password);
			if(SysUtil.isNull(user)){
				json.put("code", -7);
				json.put("msg", "用户名或者密码错误！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			json.put("user", user);*/
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			json.put("code", -8);
			json.put("msg", "注册失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 1);
		json.put("msg", "注册成功！");
		JsonUtil.writeJson(json, response);
	
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param phoneOrname
	 * @param password
	 * @param response
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request,String phone,String password,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		try {
			
			User user = userDao.login(phone,password);
			
			if(SysUtil.isNull(user)){
				json.put("code", -1);
				json.put("msg", "用户名或者密码错误！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			json.put("user", user);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "登录失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 1);
		json.put("msg", "登录成功！");
		JsonUtil.writeJson(json, response);
		
		
	}
	
	
	/**
	 * 获取用户状态
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserStatus")
	public void selUserStatus(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			Map<String, Object> userMap = userDao.selUserByUserId(userId);
			
			Integer userState = Integer.parseInt(userMap.get("state").toString());
			
			json.put("userState", userState);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 添加短信验证码
	 * @param request
	 * @param phone
	 * @param response
	 */
	@RequestMapping("addValidateCode")
	public void addValidateCode(HttpServletRequest request,String phone,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(phone)){
			json.put("code", -1);
			json.put("msg", "手机号不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			String ranText = SysUtil.getRanText(4);
			
			Integer state = userService.addValidateCode(phone,ranText);
			
			if(state==1){
				
				json.put("validateCode", ranText);
				
			}else{
				
				json.put("code", -1);
				json.put("msg", "发送失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "发送失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "发送成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param userId
	 * @param oldPass
	 * @param newPass
	 * @param response
	 */
	@RequestMapping("chaPassByOldPass")
	public void chaPassByOldPass(HttpServletRequest request,Integer userId,String oldPass,String newPass,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		try {
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(oldPass)){
				json.put("code", -1);
				json.put("msg", "原密码不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(newPass)){
				json.put("code", -1);
				json.put("msg", "新密码不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			
			Map<String, Object> userMap = userDao.selUserByUserId(userId);
			
			if(SysUtil.isNull(userMap)){
				json.put("code", -1);
				json.put("msg", "用户不存在！");
				JsonUtil.writeJson(json, response);
				return;
			}
			//password  = SysUtil.md5(password)
			if(!userMap.get("userpwd").equals(SysUtil.md5(oldPass))){
				
				json.put("code", -1);
				json.put("msg", "用户原密码错误！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			userService.updatePassword(userId, newPass);
			
			//userDao.updatePassword(userId,newPass);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				
				userService.updatePassFailMes(userId);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "修改成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 判断用户手机号是否存在
	 * @param request
	 * @param phone
	 * @param response
	 */
	@RequestMapping("selPhoneIfExist")
	public void selPhoneIfExist(HttpServletRequest request,String phone,HttpServletResponse response){
	
		JSONObject json = new JSONObject();

		if(SysUtil.isNull(phone)){
			
			json.put("code", -2);
			json.put("msg", "手机号不能为空！");
			JsonUtil.writeJson(json, response);
			return;
			
		}
		
		try {
			
			Map<String, Object> mapPhone = userDao.selectUserInfoByPhone(phone);
			
			if(SysUtil.isNull(mapPhone)){
				
				json.put("code", -3);
				json.put("msg", "手机号不存在！");
				JsonUtil.writeJson(json, response);
				return;
				
			}else{
				
				Integer state = Integer.parseInt(mapPhone.get("state").toString());
				
				if(state == 0 || state == 99){
					
					json.put("code", -4);
					json.put("msg", "手机号不可用！");
					JsonUtil.writeJson(json, response);
					return;
					
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}

	/**
	 * 忘记密码
	 * @param request
	 * @param phone
	 * @param newPass
	 * @param response
	 */
	@RequestMapping("forgetPassword")
	public void forgetPassword(HttpServletRequest request,String phone,String newPass,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();

			if(SysUtil.isNull(phone)){
				json.put("code", -1);
				json.put("msg", "手机号不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(newPass)){
				json.put("code", -1);
				json.put("msg", "密码不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				userDao.updatePassByPhone(phone,newPass);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "修改失败！");
				JsonUtil.writeJson(json, response);
				return;
			}
		
			json.put("code", 0);
			json.put("msg", "修改成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 修改用户昵称
	 * @param request
	 * @param userId
	 * @param nickname
	 * @param response
	 */
	@RequestMapping("updateNickname")
	public void updateNickname(HttpServletRequest request,Integer userId,String nickname,HttpServletResponse response){
		
		JSONObject json = new JSONObject();

		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(nickname)){
			json.put("code", -1);
			json.put("msg", "昵称不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			userDao.updateNickname(userId,nickname);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "修改成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 修改手机号
	 * @param request
	 * @param userId
	 * @param phone
	 * @param response
	 */
	@RequestMapping("updatePhone")
	public void updatePhone(HttpServletRequest request,Integer userId,String phone,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();

		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(phone)){
			json.put("code", -1);
			json.put("msg", "手机号不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> mapPhone = userDao.selectUserInfoByPhone(phone);
			
			if(!SysUtil.isNull(mapPhone)){
				json.put("code", -2);
				json.put("msg", "手机号已存在！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			userService.updatePhone(userId,phone);
		//	userDao.updatePhone(userId,phone);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				
				userService.updatePhoneFailMes(userId);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "修改成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户所有级别
	 * @param request
	 * @param response
	 */
	@RequestMapping("selUserAllLevel")
	public void selUserAllLevel(HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		try {
			
			List<Map<String, Object>> levelList = userDao.selUserAllLevel();
			json.put("levelList", levelList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("code", -1);
			json.put("msg", "查询失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 通过userid查询用户信息
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserByUserId")
	public void selUserByUserId(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
	
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
		
		try {
			
			Map<String, Object> userMap = userDao.selUserByUserId(userId);
			json.put("userMap", userMap);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 更新用户头像
	 * @param request
	 * @param headPic
	 * @param userId
	 * @param response
	 */
	@RequestMapping("updateHeadPic")
	public void updateHeadPic(HttpServletRequest request,String headPic,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if (SysUtil.isNull(headPic)) {
			
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "图片链接不能为空！");
			JsonUtil.writeJson(json, response);
			return;
			
		}
		
		try {
			
			userDao.updateHeadPic(headPic,userId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
			
		}
		
		json.put("headPic", headPic);
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "成功");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 修改婚姻状态
	 * @param request
	 * @param userId
	 * @param status
	 * @param response
	 */
	@RequestMapping("updateMarrystatus")
	public void updateMarrystatus(HttpServletRequest request,Integer userId,Integer status,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		
		if(SysUtil.isNull(status)){
			json.put("code", -1);
			json.put("msg", "状态不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			userDao.updateMarrystatus(userId,status);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "修改成功");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 身份验证
	 * @param request
	 * @param userId
	 * @param realname
	 * @param IDCard
	 * @param response
	 */
	//add by rainbow
	@RequestMapping("checkIdentityInfo")
	public void checkIdentityInfo(HttpServletRequest request,Integer userId,
			String realname,
			String IDCard,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		
	/*	try {
			realname=new String(realname.getBytes("iso8859_1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("realname:"+realname);
		if (SysUtil.isNull(realname)) {	
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "真实姓名不能为空！");
			JsonUtil.writeJson(json, response);
			return;	
		}

		if (SysUtil.isNull(IDCard)) {
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "身份证号不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		IDcard demo= new IDcard();
		if(demo.idcard_verify(realname,IDCard)==1000){
			try {
				//modify rainbow 
				userDao.updateRealAndState(userId,realname,4,IDCard);
				//userService.updateIdentityInfo(userId,realname,IDCard,IDCardFront,IDCardBack);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -2);
				json.put("msg", "身份证姓名验证成功,修改数据库失败！");
				JsonUtil.writeJson(json, response);
				return;
			}
			json.put("code", SysUtil.SUCCESS);
			json.put("msg", "身份证姓名验证成功");
			JsonUtil.writeJson(json, response);	
		}else{
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "身份证姓名验证失败！");
			JsonUtil.writeJson(json, response);
		}
		
	}
	
	
	/**
	 * 更新用户身份信息
	 * @param request
	 * @param userId
	 * @param realname
	 * @param IDCard
	 * @param IDCardFront
	 * @param IDCardBack
	 * @param response
	 */
	@RequestMapping("updateIdentityInfo")
	public void updateIdentityInfo(HttpServletRequest request,Integer userId,
			//String realname,//modify rainbow 
			//String IDCard,
			String IDHead_pic,
			String IDCardFront,String IDCardBack,
			HttpServletResponse response) {
		
		
			JSONObject json = new JSONObject();
			
			if (SysUtil.isNull(userId)) {
				
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			//modify rainbow
//			if (SysUtil.isNull(realname)) {	
//					json.put("code", SysUtil.FAILURE);
//					json.put("msg", "真实姓名不能为空！");
//					JsonUtil.writeJson(json, response);
//					return;	
//				}
//			
//			if (SysUtil.isNull(IDCard)) {
//				json.put("code", SysUtil.FAILURE);
//				json.put("msg", "身份证号不能为空！");
//				JsonUtil.writeJson(json, response);
//				return;
//			}
			
			if (SysUtil.isNull(IDCardFront)) {
				
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "身份证正面不能为空！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			
			if (SysUtil.isNull(IDCardBack)) {
				
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "身份证反面不能为空！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			
			
			if (SysUtil.isNull(IDHead_pic)) {
				
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "手持身份证照片不能为空！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			
			
			try {
				//modify rainbow 
				userService.updateIdentityInfo(userId,IDCardFront,IDCardBack,IDHead_pic);
				//userService.updateIdentityInfo(userId,realname,IDCard,IDCardFront,IDCardBack);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "修改失败！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			json.put("code", SysUtil.SUCCESS);
			json.put("msg", "修改成功");
			JsonUtil.writeJson(json, response);	
		
	}
	
	
	/**
	 * 上传图片
	 * @param request
	 * @param multipartFile
	 * @param response
	 */
	@RequestMapping("uploadPicture")
	public void uploadPicture(HttpServletRequest request,			
			
			 MultipartFile multipartFile,Integer userId,
			
			HttpServletResponse response){
		
			JSONObject json = new JSONObject();
	
			if (SysUtil.isNull(multipartFile)) {
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "未上传图片");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			Map<String, Object> map = ImageUtil.uploadUser(request,userId.toString(), multipartFile);
			
			Integer state = (Integer) map.get("state");
			
			if(state == 0){
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "上传失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			String  picPath = map.get("picture").toString();
			
			
			json.put("picPath", picPath);
			json.put("code", SysUtil.SUCCESS);
			json.put("msg", "上传成功");
			JsonUtil.writeJson(json, response);
			
	}
	
	/**
	 * 查询用户附加信息即身份信息
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserAdditionInfo")
	public void selUserAdditionInfo(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if (SysUtil.isNull(userId)) {
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> userAdditionInfo = userDao.selUserAdditionInfo(userId);
			
			json.put("userAdditionInfo", userAdditionInfo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
	}
	
	
	/**
	 * 首页
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("index")
	public void index(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if (SysUtil.isNull(userId)) {
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> userInfo = userDao.selUserByUserId(userId);
			
			json.put("userInfo", userInfo);
			
			Integer trainCount = trainDao.selTrainCount(userId);
			
			json.put("trainCount", trainCount);
			
			Map<String, Object> userAccBook = accountDao.selUserBalance(userId,1);
			
			String balance = userAccBook.get("balance").toString();
			
			json.put("balance", balance);
			
			Integer finishTaskCount = officeDao.selFinishTaskCount(userId);
			
			json.put("finishTaskCount", finishTaskCount);
			
			List<Map<String, Object>> userNoFinTaskList = officeDao.selUserNoFinTask(userId);
			
			json.put("userNoFinTaskList", userNoFinTaskList);
			
			Integer noticeState = userService.selUserNoticeState(userId);
			
			json.put("noticeState", noticeState);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户系统通知列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("selSysNotice")
	public void selSysNotice(HttpServletRequest request,Integer page,Integer rows,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			Map<String,Object> map = new HashMap<String, Object>();
			
				if (SysUtil.isNull(page)) {
					
					map.put("page", "1");
					
				}else{
					
					map.put("page", String.valueOf(page));
					
				}
				
			rows = SysUtil.isNull(rows) ? 10 : rows;
			
			map.put("rows", String.valueOf(rows));
			
				if (page == 1) {
					
					map.put("start", "0");
					
				} else {
					
					map.put("start", ((page - 1) * rows) + "");
					
				}
				
			try {
				
				List<Map<String, Object>> sysNoticeList = userDao.selSysNotice(map);
				
				json.put("sysNoticeList", sysNoticeList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
				
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户个人通知列表
	 * @param request
	 * @param userId
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("selUserMessage")
	public void selUserMessage(HttpServletRequest request,Integer userId,Integer page,Integer rows,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		
			if (SysUtil.isNull(page)) {
				
				map.put("page", "1");
				
			}else{
				
				map.put("page", String.valueOf(page));
				
			}
		
		rows = SysUtil.isNull(rows) ? 10 : rows;
		
		map.put("rows", String.valueOf(rows));
		
			if (page == 1) {
				
				map.put("start", "0");
				
			} else {
				
				map.put("start", ((page - 1) * rows) + "");
				
			}
			
			map.put("userId", userId);
			
			try {
				
				List<Map<String, Object>> userMessageList = userDao.selUserMessage(map);
				
				json.put("userMessageList", userMessageList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 修改用户系统消息查看时间
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("updateSysNotTime")
	public void updateSysNotTime(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				userService.updateSysNotTime(userId);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "修改失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
			
			json.put("code", 0);
			json.put("msg", "修改成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 修改用户个人通知状态
	 * @param request
	 * @param messageId
	 * @param response
	 */
	@RequestMapping("updateUserMesState")
	public void updateUserMesState(HttpServletRequest request,Integer messageId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
			if(SysUtil.isNull(messageId)){
				json.put("code", -1);
				json.put("msg", "消息id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				userDao.updateUserMesState(messageId,2);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "修改失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
		
		json.put("code", 0);
		json.put("msg", "修改成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询系统通知详情
	 * @param request
	 * @param sysNoticeId
	 * @param response
	 */
	@RequestMapping("selSysNoticeDetail")
	public void selSysNoticeDetail(HttpServletRequest request,Integer sysNoticeId,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(sysNoticeId)){
				json.put("code", -1);
				json.put("msg", "系统通知id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				Map<String, Object> sysNoticeDetail = userDao.selSysNoticeDetail(sysNoticeId);
				
				json.put("sysNoticeDetail", sysNoticeDetail);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户个人消息详情
	 * @param request
	 * @param messageId
	 * @param response
	 */
	@RequestMapping("selUserMesDetail")
	public void selUserMesDetail(HttpServletRequest request,Integer messageId,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(messageId)){
				json.put("code", -1);
				json.put("msg", "消息id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				Map<String, Object> userMesDetail = userDao.selUserMesDetail(messageId);
				
				json.put("userMesDetail", userMesDetail);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return;
				
			}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户支付信息列表
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserPayInfoList")
	public void selUserPayInfoList(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if (SysUtil.isNull(userId)) {
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			List<Map<String, Object>> userPaymentInfoList = userDao.selUserPaymentInfo(userId);
			
			json.put("userAdditionInfo", userPaymentInfoList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询学历列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("selEduInfoList")
	public void selEduInfoList(HttpServletRequest request,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
				try {
					
					List<Map<String, Object>> eduInfoList = userDao.selConfigDetail("EDUCATION_TYPE");
					
					json.put("eduInfoList", eduInfoList);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					json.put("code", -1);
					json.put("msg", "失败！");
					JsonUtil.writeJson(json, response);
					return;
				}
				
				json.put("code", 0);
				json.put("msg", "成功！");
				JsonUtil.writeJson(json, response);
	}
	
	
	/**
	 * 查询婚姻列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("selMarryInfoList")
	public void selMarryInfoList(HttpServletRequest request,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			try {
				
				List<Map<String, Object>> marryInfoList = userDao.selConfigDetail("MARITAL_STATUS");
				
				json.put("marryInfoList", marryInfoList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 修改学历状态
	 * @param request
	 * @param userId
	 * @param status
	 * @param response
	 */
	@RequestMapping("updateEdustatus")
	public void updateEdustatus(HttpServletRequest request,Integer userId,Integer eduStatus,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(eduStatus)){
			json.put("code", -1);
			json.put("msg", "状态不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			userDao.updateEdustatus(userId,eduStatus);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "修改成功");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 添加用户反馈
	 * @param request
	 * @param userId
	 * @param content
	 * @param response
	 */
	@RequestMapping("addUserFeedback")
	public void addUserFeedback(HttpServletRequest request,Integer userId,
			
			String content,HttpServletResponse response){
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(content)){
				json.put("code", -1);
				json.put("msg", "反馈内容不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				userDao.addUserFeedback(userId,content);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "提交失败！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			json.put("code", SysUtil.SUCCESS);
			json.put("msg", "提交成功");
			JsonUtil.writeJson(json, response);
		
	}
	
	
	
	
	
	
	
	/*************************后台***********************/
	
	
	/**
	 * 审核列表页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkIndex")
	public String checkIndex(HttpServletRequest request,HttpServletResponse response) {
		return "user/checkInfoList";
	}
	
	
	
	/**
	 * 审核列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("checkList")
	public void checkList(HttpServletRequest request,Integer page,
			Integer rows,HttpServletResponse response) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if (SysUtil.isNull(page)) {
			
			map.put("page", "1");
			
		}else{
			
			map.put("page", String.valueOf(page));
			
		}
	
		rows = SysUtil.isNull(rows) ? 10 : rows;
		
		map.put("rows", String.valueOf(rows));
	
		if (page == 1) {
			
			map.put("start", "0");
			
		} else {
			
			map.put("start", ((page - 1) * rows) + "");
			
		}
		
		JSONObject json = new JSONObject();
		try {
		
			json.put("total", userDao.selectUserCheckCount());
			json.put("rows",userDao.selectUserCheckList(map));
		
		} catch (Exception e) {
			//e.printStackTrace();
			log.error(e.getMessage());
		}

		 JsonUtil.writeJson(json, response);
		
	}
	
	
	
	/**
	 * 跳转到审核页面
	 * @param request
	 * @param checkId
	 * @param response
	 * @return
	 */
	@RequestMapping("checkUsergoto")
	public String checkUsergoto(HttpServletRequest request,Integer checkId,HttpServletResponse response){
		
		
		
		if (SysUtil.isNull(checkId)) {
			return "forward:404.jsp";
		}
		
		try {
			
			Map<String,Object> checkDeatil = userDao.selCheckDetail(checkId);
			
		
			request.setAttribute("checkDeatil", checkDeatil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "user/checkInfo";
				
	}
	
	/**
	 * 身份审核通过
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("checkPass")
	public void checkPass(HttpServletRequest request,Integer userId,HttpServletResponse response){
		
		Operator op = (Operator) request.getSession().getAttribute(PermissionConstant.CURRENT_USER);
		
		JSONObject json = new JSONObject();
		
		try {
			userService.updateCheckPass(userId,op.getOperator_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "审核失败");
			JsonUtil.writeJson(json, response);
		}
		
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "审核成功");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 审核不通过
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("checkNoPass")
	public void checkNoPass(HttpServletRequest request,Integer userId,String checkContent,HttpServletResponse response){
		
		Operator op = (Operator) request.getSession().getAttribute(PermissionConstant.CURRENT_USER);
		
		JSONObject json = new JSONObject();
		
		try {
			
			checkContent = java.net.URLDecoder.decode(checkContent, "UTF-8");
			
			userService.updateCheckNoPass(userId,op.getOperator_id(),checkContent);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "审核失败");
			JsonUtil.writeJson(json, response);
		}
		
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "审核成功");
		JsonUtil.writeJson(json, response);
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		boolean verifyPhone = ToolUtils.verifyPhone("1312295941");
		
		System.out.println(verifyPhone);
		
	}
	

}
