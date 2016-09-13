package cn.shendiao.office.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.dao.DaoSupport;
import cn.shendiao.office.model.UcaCheckUsercase;
import cn.shendiao.office.model.UcaInfo;
import cn.shendiao.office.model.UcaPaper;
import cn.shendiao.office.model.UcaSign;
import cn.shendiao.office.model.UcaStateLog;
import cn.shendiao.util.SysUtil;

@Repository("officeDao")
public class OfficeDao extends DaoSupport{
	
	
	/**
	 * 判断用户是否符合接案条件
	 * @param userId
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> ifMatchCondition(Integer userId,Integer taskId) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("taskId", taskId);
		
		findForObject("OfficeMapper.ifMatchCondition", map);
			
		 return map;
	}
	
	/**
	 * 插入用户接案信息
	 * @param ucaInfo
	 * @throws Exception
	 */
	public Integer insertUserRecTask(UcaInfo ucaInfo) throws Exception {
		
		save("OfficeMapper.insertUserRecTask", ucaInfo);
		
		Integer usercaseId = ucaInfo.getUsercase_id();
		
		return usercaseId;
		
	}
	
	/**
	 * 添加用户案件状态日志变更
	 * @param usercaseId
	 * @param caseId
	 * @param userId
	 * @param uca_state
	 * @param last_uca_state
	 * @throws Exception
	 */
	public void insertUserTaskStateLog(Integer usercaseId,Integer caseId,
			
			Integer userId,Integer uca_state,Integer last_uca_state) throws Exception {
		
		UcaStateLog ucaStateLog = new UcaStateLog();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		ucaStateLog.setUsercase_id(usercaseId);
		ucaStateLog.setCase_id(caseId);
		ucaStateLog.setUser_id(userId);
		ucaStateLog.setUca_state(uca_state);
		ucaStateLog.setLast_uca_state(last_uca_state);
		ucaStateLog.setState(1);
		ucaStateLog.setCreate_date(date);
		ucaStateLog.setLast_mod(date);
		
		save("OfficeMapper.insertUserTaskStateLog", ucaStateLog);
		
	}
	
	/**
	 * 查询用户完成案件总数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer selFinishTaskCount(Integer userId) throws Exception{
		
		return (Integer) findForObject("OfficeMapper.selFinishTaskCount", userId);
		
	}
	
	
	/**
	 * 查询用户完成案件列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserFinishTask(Map<String,Object> map ) throws Exception {
		
		return (List<Map<String, Object>>) findForList("OfficeMapper.selUserFinishTask", map);
		
	}
	
	/**
	 * 查询用户未完成案件列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserNoFinTask(Integer userId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("OfficeMapper.selUserNoFinTask", userId);
		
	}
	
	/**
	 * 查询用户办案详情
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserOfficeDetail(Integer userCaseId) throws Exception {
		
		return (Map<String, Object>) findForObject("OfficeMapper.selUserOfficeDetail", userCaseId);
		
	}
	
	
	/**
	 * 查询用户办案状态列表
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserOfficeState(Integer userCaseId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("OfficeMapper.selUserOfficeState", userCaseId);
		
	}
	
	
	
	/**
	 * 查询用户接案信息
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUcaInfo(Integer userCaseId) throws Exception {
		
		return (Map<String, Object>) findForObject("OfficeMapper.selUcaInfo", userCaseId);
		
	}
	
	/**
	 * 修改用户办案状态
	 * @param userCaseId
	 * @param lastUcaState
	 * @param ucaState
	 * @throws Exception
	 */
	public void updateUcaInfoState(Integer userCaseId,Integer lastUcaState,Integer ucaState) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userCaseId", userCaseId);
		map.put("lastUcaState", lastUcaState);
		map.put("ucaState", ucaState);
		
		update("OfficeMapper.updateUcaInfoState", map);
		
	}
	
	/**
	 * 查询用户到店的距离
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String selDistance(Integer userCaseId,String sign_in_longitude,String sign_in_latitude) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
			
			map.put("userCaseId", userCaseId);
			map.put("sign_in_longitude", sign_in_longitude);
			map.put("sign_in_latitude", sign_in_latitude);
		
		return (String) findForObject("OfficeMapper.selDistance", map);
		
	}
	
	/**
	 * 添加用户签到
	 * @param ucaSign
	 * @throws Exception
	 */
	public Integer addUserSignIn(UcaSign ucaSign) throws Exception {
		
		save("OfficeMapper.addUserSignIn", ucaSign);
		
		Integer ucaSignId = ucaSign.getId();
		
		return ucaSignId;
		
	}
	
	/**
	 * 用户签出
	 * @param userCaseId
	 * @param sign_out_longitude
	 * @param sign_out_latitude
	 * @throws Exception
	 */
	public void updateUserSignOut(Integer userCaseId,String sign_out_longitude,String sign_out_latitude) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userCaseId", userCaseId);
		
		map.put("sign_out_longitude", sign_out_longitude);
		
		map.put("sign_out_latitude", sign_out_latitude);
		
		update("OfficeMapper.updateUserSignOut", map);
		
	}
	
	/**
	 * 查询案件审核消息
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selCheckInfo(Integer userCaseId) throws Exception {
		
		return (Map<String, Object>) findForObject("OfficeMapper.selCheckInfo", userCaseId);
		
	}
	
	/**
	 * 添加用户答案
	 * @param ucaPaper
	 * @throws Exception
	 */
	public void addUserSurveyAnswer(UcaPaper ucaPaper) throws Exception {
		
		save("OfficeMapper.addUserSurveyAnswer", ucaPaper);
		
	}
	
	/**
	 * 添加用户案件审核表
	 * @param ucaCheck
	 * @throws Exception
	 */
	public void insertUcaCheck(UcaCheckUsercase ucaCheck) throws Exception {
		
		save("OfficeMapper.insertUcaCheck", ucaCheck);
		
	}
	
	/**
	 * 查询用户调研问卷答案
	 * @param userId
	 * @param taskId
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserSurveyAnswer(Integer userId,Integer taskId,Integer userCaseId) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("taskId", taskId);
		map.put("userCaseId", userCaseId);
		
		return (List<Map<String, Object>>) findForList("OfficeMapper.selUserSurveyAnswer", map);
		
	}

	/**
	 * 查询用户签到信息
	 * @param userCaseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserSignIn(Integer userCaseId) throws Exception {
		
		return (Map<String, Object>) findForObject("OfficeMapper.selUserSignIn", userCaseId);
		
	}

}
