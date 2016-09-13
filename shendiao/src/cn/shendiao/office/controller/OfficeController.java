package cn.shendiao.office.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shendiao.office.dao.OfficeDao;
import cn.shendiao.office.model.UcaPaper;
import cn.shendiao.office.service.OfficeService;
import cn.shendiao.task.dao.TaskDao;
import cn.shendiao.user.dao.UserDao;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.StringUtils;
import cn.shendiao.util.SysUtil;
import cn.shendiao.util.ToolUtils;



@Controller
@RequestMapping("office")
public class OfficeController {
	
	protected final transient Logger log = Logger
			.getLogger(OfficeController.class);
	
	
	@Resource
	private OfficeDao officeDao;
	
	@Resource
	private OfficeService officeService;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private TaskDao taskDao;
	
	/**
	 * 用户承接案件
	 * @param request
	 * @param caseId
	 * @param userId
	 * @param response
	 */
	@RequestMapping("receiveTask")
	public void receiveTask(HttpServletRequest request,Integer caseId,Integer taskId,Integer userId,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(caseId)){
			json.put("code", -1);
			json.put("msg", "id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		
		try { 
			
			Map<String, Object> matchCondition = officeDao.ifMatchCondition(userId,taskId);
			
			if(matchCondition.get("code").equals(1)){
				

				 synchronized(this) {  
					 
					 Map<String,Object> map = taskDao.selTaskShopInfo(caseId);
					 
					 Integer state = Integer.parseInt(map.get("state").toString());
					 
					 if(state == 1){
						 
						 	officeService.addReceiveTask(userId,caseId);
						 
					 }else{
						 
						 	json.put("code", -1);
							json.put("msg", "任务已被领取,请重新领取其他任务！");
							JsonUtil.writeJson(json, response);
							return ;
						 
					 }
					 
				    } 
				 
			}else{
				
				json.put("code", -1);
				json.put("msg", matchCondition.get("descs"));
				JsonUtil.writeJson(json, response);
				return ;
				
			}
			
			//json.put("ifMatchCondition", ifMatchCondition);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -2);
			json.put("msg", "领取失败！");
			JsonUtil.writeJson(json, response);
			return ;
			
		}
		
		json.put("code", 0);
		json.put("msg", "领取成功！");
		JsonUtil.writeJson(json, response);
		
		
	}
	
	/**
	 * 查询用户完成案件列表
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserFinishTask")
	public void selUserFinishTask(HttpServletRequest request,Integer page,Integer rows,Integer userId,HttpServletResponse response) {
		
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
				
				List<Map<String, Object>> userFinishTaskList = officeDao.selUserFinishTask(map);
				
				json.put("userFinishTaskList", userFinishTaskList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户办案详情
	 * @param request
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("selUserOfficeDetail")
	public void selUserOfficeDetail(HttpServletRequest request,Integer userCaseId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userCaseId)){
			json.put("code", -1);
			json.put("msg", "userCaseId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> userOfficeDetail = officeDao.selUserOfficeDetail(userCaseId);
			
			List<Map<String, Object>> userOfficeStateList = officeDao.selUserOfficeState(userCaseId);
			
			Map<String, Object> checkInfo = officeDao.selCheckInfo(userCaseId);
			
			json.put("userOfficeDetail", userOfficeDetail);
			
			json.put("userOfficeStateList", userOfficeStateList);
			
			json.put("checkInfo", checkInfo);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 用户未办案请辞
	 * @param request
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("userResigNoOffice")
	public void userResigNoOffice(HttpServletRequest request,Integer userCaseId,Integer targetStateId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userCaseId)){
			json.put("code", -1);
			json.put("msg", "userCaseId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(targetStateId)){
			json.put("code", -1);
			json.put("msg", "目标状态Id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			officeService.addUserResigNoOffice(userCaseId,targetStateId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "取消失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "取消成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 查询任务试题
	 * @param request
	 * @param taskId
	 * @param response
	 */
	@RequestMapping("selTaskQuesList")
	public void selTaskQuesList(HttpServletRequest request,Integer taskId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(taskId)){
			json.put("code", -1);
			json.put("msg", "任务id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			List<Map<String, Object>> taskQuesList = taskDao.selTaskQuesList(taskId);
			
			List<Map<String, Object>> taskQuesItemList = taskDao.selTaskQuesItemList(taskId);
			
			Map<String, Object> taskInfo = taskDao.selTaskInfo(taskId);
			
			json.put("taskInfo", taskInfo);
			
			json.put("taskQuesList", taskQuesList);
			
			json.put("taskQuesItemList", taskQuesItemList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询用户签到信息
	 * @param request
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("selUserSignIn")
	public void selUserSignIn(HttpServletRequest request,Integer userCaseId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userCaseId)){
			json.put("code", -1);
			json.put("msg", "userCaseId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object>  userSignIn = officeDao.selUserSignIn(userCaseId);
			
			json.put("userSignIn", userSignIn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "失败！");
			JsonUtil.writeJson(json, response);
			return ;
			
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 用户签到
	 * @param request
	 * @param userId
	 * @param userCaseId
	 * @param sign_in_longitude
	 * @param sign_in_latitude
	 * @param response
	 */
	@RequestMapping("userSignIn")
	public void userSignIn(HttpServletRequest request,Integer userId,Integer userCaseId,
			
				String sign_in_longitude,String sign_in_latitude,
			
			HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(userCaseId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(sign_in_longitude)){
				json.put("code", -1);
				json.put("msg", "经度不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(sign_in_latitude)){
				json.put("code", -1);
				json.put("msg", "纬度不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				Double distance =Double.parseDouble(officeDao.selDistance(userCaseId,sign_in_longitude,sign_in_latitude));
				
				Map<String,Object> sysParams =	userDao.selSysParams();
				
				if(distance < Integer.parseInt(sysParams.get("sign_valid_dis").toString())){
					
					officeService.addUserSignIn(userId,userCaseId,sign_in_longitude,sign_in_latitude);
					
				}else{
					
					json.put("code", -1);
					json.put("msg", "未进入签到范围，签到失败！");
					JsonUtil.writeJson(json, response);
					return ;
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				json.put("code", -1);
				json.put("msg", "签到失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			json.put("code", 0);
			json.put("msg", "签到成功！");
			JsonUtil.writeJson(json, response);
			
	}
	
	/**
	 * 用户签出
	 * @param request
	 * @param userCaseId
	 * @param sign_out_longitude
	 * @param sign_out_latitude
	 * @param response
	 */
	@RequestMapping("userSignOut")
	public void userSignOut(HttpServletRequest request,Integer userCaseId,Integer taskId,
			
				String sign_out_longitude,String sign_out_latitude,
			
			HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userCaseId)){
			json.put("code", -1);
			json.put("msg", "任务id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(sign_out_longitude)){
			json.put("code", -1);
			json.put("msg", "经度不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(sign_out_latitude)){
			json.put("code", -1);
			json.put("msg", "纬度不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> taskInfo = taskDao.selTaskInfo(taskId);
			
			Integer reqShopTime = Integer.parseInt(taskInfo.get("require_shop_time").toString());
			
			Map<String, Object> userSignIn = officeDao.selUserSignIn(userCaseId);
			
			Date signInTime = SysUtil.toDate(userSignIn.get("sign_in_date").toString(),"yyyy-MM-dd HH:mm:ss");
			
			if(SysUtil.differMinute(signInTime,new Date()) < reqShopTime){
				
				json.put("code", -1);
				json.put("msg", "未到签出时间，不能签出！");
				JsonUtil.writeJson(json, response);
				return ;
				
			}
			
			
			Double distance =Double.parseDouble(officeDao.selDistance(userCaseId,sign_out_longitude,sign_out_latitude));
			
			Map<String,Object> sysParams =	userDao.selSysParams();
			
			if(distance < Integer.parseInt(sysParams.get("sign_valid_dis").toString())){
				
				officeDao.updateUserSignOut(userCaseId,sign_out_longitude,sign_out_latitude);
				
			}else{
				
				json.put("code", -1);
				json.put("msg", "未进入签出范围，签出失败！");
				JsonUtil.writeJson(json, response);
				return ;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			json.put("code", -1);
			json.put("msg", "签出失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "签出成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 保存用户调研答案
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveUserSurveyAnswer")
	public void saveUserSurveyAnswer(HttpServletRequest request,HttpServletResponse response) {
		
		
		String surveyAnswer = request.getParameter("surveyAnswer");

		JSONObject json = new JSONObject();
		
		
		try {
			
			officeService.addUserSurveyAnswer(surveyAnswer);
			
			//json.put("surveyAnswer", surveyAnswer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			json.put("code", -1);
			json.put("msg", "保存失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "保存成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 用户调研提交
	 * @param request
	 * @param surveyAnswer
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("userSurveySubmit")
	public void userSurveySubmit(HttpServletRequest request,HttpServletResponse response) {
		
		String surveyAnswer = request.getParameter("surveyAnswer");
		
		Integer userCaseId = Integer.parseInt(request.getParameter("userCaseId"));

		JSONObject json = new JSONObject();
		
		try {
			
			officeService.addUserSurveySubmit(surveyAnswer,userCaseId);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			json.put("code", -1);
			json.put("msg", "提交失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "提交成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 查询用户所做调研答案
	 * @param request
	 * @param userId
	 * @param userCaseId
	 * @param taskId
	 * @param response
	 */
	@RequestMapping("selUserSurveyAnswer")
	public void selUserSurveyAnswer(HttpServletRequest request,Integer userId,
			
			Integer userCaseId,Integer taskId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userCaseId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(taskId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
		
			try {
				
				List<Map<String,Object>> surveyAnswerList = officeDao.selUserSurveyAnswer(userId,taskId,userCaseId);
				
				json.put("surveyAnswerList", surveyAnswerList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 添加用户领俸
	 * @param request
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("addUserAalary")
	public void addUserAalary(HttpServletRequest request,Integer userCaseId,Integer taskId,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userCaseId)){
				json.put("code", -1);
				json.put("msg", "userCaseId不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(taskId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				officeService.addUserAalary(userCaseId,taskId);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
				json.put("code", -1);
				json.put("msg", "领俸失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			json.put("code", 0);
			json.put("msg", "领俸成功！");
			JsonUtil.writeJson(json, response);
			
		}
	
	
	/**
	 * 用户调研第二次提交
	 * @param request
	 * @param surveyAnswer
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("userSixToSeven")
	public void userSixToSeven(HttpServletRequest request,HttpServletResponse response) {
		
		String surveyAnswer = request.getParameter("surveyAnswer");
		
		Integer userCaseId = Integer.parseInt(request.getParameter("userCaseId"));

		JSONObject json = new JSONObject();
		
		try {
			
			officeService.userSixToSeven(surveyAnswer,userCaseId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			json.put("code", -1);
			json.put("msg", "提交失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "提交成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 用户审核未通过第二次办案
	 * @param request
	 * @param userId
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("userFiveToSix")
	public void userFiveToSix(HttpServletRequest request,Integer userId,Integer userCaseId,
			
			HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(userCaseId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
					officeService.userFiveToSix(userId,userCaseId);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				json.put("code", -1);
				json.put("msg", "失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
			
			json.put("code", 0);
			json.put("msg", "成功！");
			JsonUtil.writeJson(json, response);
			
	}
	
	
	/**
	 * 用户第二次审核未通过点击结束
	 * @param request
	 * @param userCaseId
	 * @param response
	 */
	@RequestMapping("userEightToNine")
	public void userEightToNine(HttpServletRequest request,Integer userCaseId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userCaseId)){
			json.put("code", -1);
			json.put("msg", "userCaseId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		
		try {
			
			officeService.userEightToNine(userCaseId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "获取失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "获取成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 查询系统版本号
	 * @param request
	 * @param response
	 */
	@RequestMapping("selSysParams")
	public void selSysParams(HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		try {
			
			Map<String,Object> sysParams =	userDao.selSysParams();
			
			json.put("sysParams", sysParams);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", " 失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "成功！");
		JsonUtil.writeJson(json, response);
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		//System.out.println(55.5556565<100);
		
		/*Map<String,Object> map=new LinkedHashMap<String,Object>();

		 

			map.put("yi","壹");
	
			map.put("er","贰");
	
			map.put("san","叁");
	
			map.put("si","肆");
			
			Set<String> keySet = map.keySet();
			
			for (String string : keySet) {
				//System.out.println(string);
				System.out.println(map.get(string));
			}*/
		
		String aa = "2016-08-16 14:27:15";
		
		Date date = SysUtil.toDate(aa, "yyyy-MM-dd HH:mm:ss");
		
		System.out.println(date);
		
		Date date2 = new Date();
		
		int aab =  (int) (date2.getTime() - date.getTime())/(1000*60);
		
		System.out.println(aab);
			
		
	}

}
