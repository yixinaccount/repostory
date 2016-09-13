package cn.shendiao.task.controller;

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

import cn.shendiao.constant.PermissionConstant;
import cn.shendiao.login.model.Operator;
import cn.shendiao.task.dao.TaskDao;
import cn.shendiao.task.service.TaskService;
import cn.shendiao.user.controller.UserController;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.SysUtil;



@Controller
@RequestMapping("task")
public class TaskController {
	
	protected final transient Logger log = Logger
			.getLogger(TaskController.class);
	
	@Resource
	private TaskDao taskDao;
	
	@Resource
	private TaskService taskService;
	
	
	/**
	 * 查询任务列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("selTaskList")
	public void selTaskList(HttpServletRequest request,Integer page,Integer rows,HttpServletResponse response) {
		
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
			
			List<Map<String, Object>> taskList = taskDao.selTaskList(map);
			
			json.put("taskList", taskList);
			
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
	 * 查询任务详情
	 * @param request
	 * @param taskId
	 * @param response
	 */
	@RequestMapping("selTaskDetail")
	public void selTaskDetail(HttpServletRequest request,Integer userId,Integer taskId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(taskId)){
			json.put("code", -1);
			json.put("msg", "任务id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		
		try {
			
			Map<String, Object> taskDetail = taskDao.selTaskDetailByTaskId(taskId);
			
			json.put("taskDetail", taskDetail);
			
			List<Map<String, Object>> taskTrainList = taskDao.selTaskTrainByTaskId(taskId,userId);
			
			json.put("taskTrainList", taskTrainList);
			
			List<Map<String, Object>> taskConditionList = taskDao.selTaskCondition(taskId);
			
			json.put("taskConditionList", taskConditionList);
			
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
	 * 查询商家详情
	 * @param request
	 * @param merchantId
	 * @param response
	 */
	@RequestMapping("selMerchantDetail")
	public void selMerchantDetail(HttpServletRequest request,Integer merchantId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(merchantId)){
			json.put("code", -1);
			json.put("msg", "商户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> merchantDetail = taskDao.selMerchantDetail(merchantId);
			
			json.put("merchantDetail", merchantDetail);
			
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
	 * 查询任务省列表
	 * @param request
	 * @param taskId
	 * @param response
	 */
	@RequestMapping("selTaskProvince")
	public void selTaskProvince(HttpServletRequest request,Integer taskId,HttpServletResponse response){
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(taskId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			try {
				
				List<Map<String, Object>> taskProvinceList = taskDao.selTaskProvince(taskId);
				
				json.put("taskProvinceList", taskProvinceList);
				
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
	 * 查询任务市列表
	 * @param request
	 * @param parentAddrCode
	 * @param response
	 */
	
	@RequestMapping("selTaskCity")
	public void selTaskCity(HttpServletRequest request,Integer parentAddrCode,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(parentAddrCode)){
			json.put("code", -1);
			json.put("msg", "父地址id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			List<Map<String, Object>> taskCityList = taskDao.selTaskCity(parentAddrCode);
			
			json.put("taskCityList", taskCityList);
			
			
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
	 * 查询任务区列表
	 * @param request
	 * @param parentAddrCode
	 * @param response
	 */
	
	@RequestMapping("selTaskCounty")
	public void selTaskCounty(HttpServletRequest request,Integer parentAddrCode,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(parentAddrCode)){
			json.put("code", -1);
			json.put("msg", "父地址id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			List<Map<String, Object>> taskCountyList = taskDao.selTaskCounty(parentAddrCode);
			
			json.put("taskCountyList", taskCountyList);
			
			
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
	 * 查询门店任务列表
	 * @param request
	 * @param taskId
	 * @param siftCode
	 * @param response
	 */
	@RequestMapping("selTaskMerList")
	public void selTaskMerList(HttpServletRequest request,Integer taskId,String siftCode,
			
		Integer page,Integer rows,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(taskId)){
				json.put("code", -1);
				json.put("msg", "任务id不能为空！");
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
			
			map.put("taskId", taskId);
			map.put("siftCode", siftCode);
			
			
			try {
				
				List<Map<String, Object>> taskMerList = taskDao.selTaskMerList(map);
				
				json.put("taskMerList", taskMerList);
				
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
	 * 查询任务门店详情
	 * @param request
	 * @param caseId
	 * @param response
	 */
	@RequestMapping("selShopTaskDetail")
	public void selShopTaskDetail(HttpServletRequest request,Integer caseId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(caseId)){
			json.put("code", -1);
			json.put("msg", "任务门店id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
		 Map<String, Object> shopTaskDetail = taskDao.selShopTaskDetail(caseId);
		 
			json.put("shopTaskDetail", shopTaskDetail);
			
			
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
	 * 查询接案详情
	 * @param request
	 * @param caseId
	 * @param response
	 */
	@RequestMapping("selAcceptCaseDetail")
	public void selAcceptCaseDetail(HttpServletRequest request,Integer caseId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
			if(SysUtil.isNull(caseId)){
				json.put("code", -1);
				json.put("msg", "任务门店id不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
		
		try {
			
			 Map<String, Object> shopTaskDetail = taskDao.selShopTaskDetail(caseId);
			 
			 Integer taskId = Integer.parseInt(shopTaskDetail.get("task_id").toString());
				
			 Map<String, Object> taskDetail = taskDao.selTaskDetailByTaskId(taskId);
		 
			 json.put("shopTaskDetail", shopTaskDetail);
			
			 json.put("taskDetail", taskDetail);
			
			
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
	
	
	
	
	
	
					/***********************后台接口****************/
	
	/**
	 * 任务首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("caseCheckIndex")
	public String caseCheckIndex(HttpServletRequest request,HttpServletResponse response) {
		
		return "case/caseCheckList";
		
		
	}
	
	/**
	 * 查询案件审核列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("caseCheckList")
	public void caseCheckList(HttpServletRequest request,Integer page,
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
		
			json.put("total", taskDao.selectCaseCheckCount());
			json.put("rows",taskDao.selectCaseCheckList(map));
		
		} catch (Exception e) {
			//e.printStackTrace();
			log.error(e.getMessage());
		}

		 JsonUtil.writeJson(json, response);
		
	}
	
	
	/**
	 * 跳转到审核页面
	 * @param request
	 * @param checkCaseId
	 * @param userId
	 * @param userCaseId
	 * @param taskId
	 * @param response
	 * @return
	 */
	@RequestMapping("checkCasegoto")
	public String checkCasegoto(HttpServletRequest request,Integer checkCaseId,Integer userId,Integer userCaseId,Integer taskId,HttpServletResponse response) {
		
		
		if (SysUtil.isNull(checkCaseId)) {
			return "forward:404.jsp";
		}
		
		if (SysUtil.isNull(userId)) {
			return "forward:404.jsp";
		}
		
		if (SysUtil.isNull(userCaseId)) {
			return "forward:404.jsp";
		}
		
		if (SysUtil.isNull(taskId)) {
			return "forward:404.jsp";
		}
		
		try {
			
			request.setAttribute("checkCaseId", checkCaseId);
			request.setAttribute("userId", userId);
			request.setAttribute("userCaseId", userCaseId);
			request.setAttribute("taskId", taskId);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "case/caseCheckInfo";
		
		
	}
	
	
	
	
	/**
	 * 案件审核通过
	 * @param request
	 * @param checkCaseId
	 * @param response
	 */
	@RequestMapping("checkCasePass")
	public void checkCasePass(HttpServletRequest request,Integer checkCaseId,HttpServletResponse response){
		
		Operator op = (Operator) request.getSession().getAttribute(PermissionConstant.CURRENT_USER);
		
		JSONObject json = new JSONObject();
		
		try {
			
			taskService.updateCheckCasePass(checkCaseId,op.getOperator_id());
			
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
	 * 案件审核不通过
	 * @param request
	 * @param checkCaseId
	 * @param checkContent
	 * @param response
	 */
	@RequestMapping("checkCaseNoPass")
	public void checkCaseNoPass(HttpServletRequest request,Integer checkCaseId,String checkContent,HttpServletResponse response){
		
		Operator op = (Operator) request.getSession().getAttribute(PermissionConstant.CURRENT_USER);
		
		JSONObject json = new JSONObject();
		
		
		try {
			
			checkContent = java.net.URLDecoder.decode(checkContent, "UTF-8");
			
			taskService.updateCheckCaseNoPass(checkCaseId,op.getOperator_id(),checkContent);
			
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
	
	
	
	
	
	
	/****************后台************************/
	
	
	/**
	 * 添加商家任务
	 * @param request
	 * @param response
	 */
	@RequestMapping("addMerTask")
	public void addMerTask(HttpServletRequest request,HttpServletResponse response) {
		
		
		
	}
	
	
	
	

}
