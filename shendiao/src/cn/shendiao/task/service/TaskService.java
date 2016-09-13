package cn.shendiao.task.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shendiao.office.dao.OfficeDao;
import cn.shendiao.task.dao.TaskDao;
import cn.shendiao.user.dao.UserDao;

@Service("taskService")
public class TaskService {
	
	@Resource
	private TaskDao taskDao;
	
	@Resource
	private OfficeDao officeDao;
	
	@Resource
	private UserDao userDao;
	
	
	/**
	 * 任务审核通过
	 * @param checkCaseId
	 * @param operatorId
	 * @throws Exception
	 */
	public void updateCheckCasePass(Integer checkCaseId,Integer operatorId) throws Exception{
		
		Map<String,Object> checkCaseInfo = taskDao.selCheckCaseInfo(checkCaseId);
		
		//更新uca_check_usercase表
		
		taskDao.updateCheckCaseState(checkCaseId,2,operatorId,"审核通过！");
		
		
		Integer userCaseId = Integer.parseInt(checkCaseInfo.get("usercase_id").toString());
		
		Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
		
		Integer userId = Integer.parseInt(ucaInfo.get("user_id").toString());
		Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
		Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
		

		officeDao.updateUcaInfoState(userCaseId, ucaState, 4);
			
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 4, ucaState);
			
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，恭喜您提交的任务问卷已通过审核，请大人前去领取俸禄。";
		
		String title = "您的任务问卷已通过审核";
	
		userDao.addUserMessage(userId, descs, title);
		
		
	}
	
	
	/**
	 * 任务审核不通过
	 * @param checkCaseId
	 * @param operatorId
	 * @param checkContent
	 * @throws Exception
	 */
	public void updateCheckCaseNoPass(Integer checkCaseId,Integer operatorId,String checkContent) throws Exception {
		
		
		
		Map<String,Object> checkCaseInfo = taskDao.selCheckCaseInfo(checkCaseId);
		
		//更新uca_check_usercase表
		
		taskDao.updateCheckCaseState(checkCaseId,3,operatorId,checkContent);
		
		
		Integer userCaseId = Integer.parseInt(checkCaseInfo.get("usercase_id").toString());
		
		Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
		
		Integer userId = Integer.parseInt(ucaInfo.get("user_id").toString());
		Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
		Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		

		if(ucaState == 3){
			
			officeDao.updateUcaInfoState(userCaseId, ucaState, 5);
			
			officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 5, ucaState);
			
			String descs = user.get("nickname").toString()+"大人，很遗憾您的任务问卷未通过审核，请前往主页面任务记录中处理。";
			
			String title = "您的任务问卷未通过审核";
		
			userDao.addUserMessage(userId, descs, title);
			
		}else if(ucaState == 7){
			
			officeDao.updateUcaInfoState(userCaseId, ucaState, 8);
			
			officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 8, ucaState);
			
			
			String descs = user.get("nickname").toString()+"大人，很遗憾您的任务问卷未通过审核，因您的任务问卷两次没有通过审核，任务失败，请前往主页面任务记录中查看详情。";
			
			String title = "审核未通过，任务失败";
		
			userDao.addUserMessage(userId, descs, title);
			
		}
		
		
	}
	

}
