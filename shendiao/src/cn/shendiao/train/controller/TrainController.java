package cn.shendiao.train.controller;

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

import cn.shendiao.train.dao.TrainDao;
import cn.shendiao.train.service.TrainService;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.SysUtil;






@Controller
@RequestMapping("train")
public class TrainController {
	
	protected final transient Logger log = Logger
			.getLogger(TrainController.class);
	
	

	
	@Resource
	private TrainDao trainDao;
	
	@Resource
	private TrainService trainService;
	
	/**
	 * 查询培训试题
	 * @param request
	 * @param trainId
	 * @param response
	 */
	@RequestMapping("selQuestionList")
	public void selQuestionList(HttpServletRequest request,Integer trainId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(trainId)){
			json.put("code", -1);
			json.put("msg", "培训id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			
			List<Map<String, Object>> questionList = trainDao.selQuestionList(trainId);
			
			List<Map<String, Object>> questionItemList = trainDao.selQuestionItemList(trainId);
			
			json.put("questionList", questionList);
			json.put("questionItemList", questionItemList);
			
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
	 * 查询培训信息
	 * @param request
	 * @param trainId
	 * @param response
	 */
	@RequestMapping("selTrainInfo")
	public void selTrainInfo(HttpServletRequest request,Integer trainId,HttpServletResponse response) {
		

		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(trainId)){
			json.put("code", -1);
			json.put("msg", "培训id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> trainInfo = trainDao.selTrainInfo(trainId);
			
			
			List<Map<String, Object>> trainFileList = trainDao.selTrainFile(trainId);
			
			json.put("trainInfo", trainInfo);
			
			json.put("trainFileList", trainFileList);
			
			
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
	 * 插入培训资质
	 * @param request
	 * @param userId
	 * @param trainId
	 * @param response
	 */
	@RequestMapping("insertTrainResult")
	public void insertTrainResult(HttpServletRequest request,Integer userId,Integer trainId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(trainId)){
			json.put("code", -1);
			json.put("msg", "培训id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> trainInfo = trainDao.selTrainInfo(trainId);
			
			Integer validTime = Integer.parseInt(trainInfo.get("valid_time").toString());
			
			trainService.insertTrainResult(trainId,userId,validTime);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "获取失败！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		json.put("code", 0);
		json.put("msg", "获取成功！");
		JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 查询培训的商家列表信息
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserTrainMer")
	public void selUserTrainMer(HttpServletRequest request,Integer page,Integer rows,HttpServletResponse response) {
		
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
			
			List<Map<String, Object>> userTrainMerList = trainDao.selUserTrainMer(map);
			
			json.put("userTrainMerList", userTrainMerList);
			
			
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
	 * 查询用户商家培训列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("selUserMerTrain")
	public void selUserMerTrain(HttpServletRequest request,Integer userId,Integer merchantId,
			
			Integer page,Integer rows,
			
			HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(merchantId)){
			json.put("code", -1);
			json.put("msg", "商家id不能为空！");
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
		
		map.put("merchantId", merchantId);
		
		try {
			
			List<Map<String, Object>> userMerTrainList = trainDao.selUserMerTrain(map);
			
			json.put("userMerTrainList", userMerTrainList);
			
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
	

}
