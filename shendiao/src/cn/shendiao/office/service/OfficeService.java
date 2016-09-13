package cn.shendiao.office.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

























import org.springframework.transaction.annotation.Transactional;

import cn.shendiao.account.dao.AccountDao;
import cn.shendiao.account.model.UserAccountbookChange;
import cn.shendiao.office.dao.OfficeDao;
import cn.shendiao.office.model.UcaCheckUsercase;
import cn.shendiao.office.model.UcaInfo;
import cn.shendiao.office.model.UcaPaper;
import cn.shendiao.office.model.UcaSign;
import cn.shendiao.task.dao.TaskDao;
import cn.shendiao.user.dao.UserDao;
import cn.shendiao.util.SysUtil;


@Service("officeService")
public class OfficeService {
	
	@Resource
	private OfficeDao officeDao;
	
	@Resource
	private TaskDao taskDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private AccountDao accountDao;
	
	/**
	 * 用户接任务
	 * @param userId
	 * @param caseId
	 * @throws Exception
	 */
	public void addReceiveTask(Integer userId,Integer caseId) throws Exception{
		
		taskDao.updateTaskShopState(caseId,2);
		
		UcaInfo ucaInfo = new UcaInfo();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		ucaInfo.setCase_id(caseId);
		ucaInfo.setUser_id(userId);
		ucaInfo.setUca_state(1);
		ucaInfo.setLast_uca_state(0);
		ucaInfo.setState(1);
		ucaInfo.setCreate_date(date);
		ucaInfo.setLast_mod(date);
		
		
		Integer usercaseId = officeDao.insertUserRecTask(ucaInfo);
		
		officeDao.insertUserTaskStateLog(usercaseId,caseId,userId,1,0);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		Map<String, Object> shopTaskDetail = taskDao.selShopTaskDetail(caseId);
		
		Integer taskId = Integer.parseInt(shopTaskDetail.get("task_id").toString());
		
		Map<String, Object> taskInfo = taskDao.selTaskInfo(taskId);
		
		String descs = user.get("nickname").toString()+"大人"
				
				+"，恭喜您已领取任务"+taskInfo.get("task_name").toString()+"！请您留意案件截止日期，尽快至"
				
				+shopTaskDetail.get("shop_address").toString()+"执行任务！";
		
		
		String title = "恭喜您已成功领取任务";
		
		userDao.addUserMessage(userId,descs,title);
		
		
		
	}
	
	/**
	 * 用户未办案请辞
	 * @param userCaseId
	 * @param targetStateId
	 * @throws Exception
	 */
	public void addUserResigNoOffice(Integer userCaseId,Integer targetStateId) throws Exception {
		
		Map<String, Object> officeDetail = officeDao.selUcaInfo(userCaseId);
		
		Integer caseId = Integer.parseInt(officeDetail.get("case_id").toString());
		Integer userId = Integer.parseInt(officeDetail.get("user_id").toString());
		Integer ucaState = Integer.parseInt(officeDetail.get("uca_state").toString());
		
		officeDao.updateUcaInfoState(userCaseId,ucaState,targetStateId);
		
		taskDao.updateTaskShopState(caseId, 1);
		
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, targetStateId, ucaState);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，任务已取消成功。";
		
		String title = "任务已取消成功";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 添加用户签到
	 * @param userId
	 * @param userCaseId
	 * @param sign_in_longitude
	 * @param sign_in_latitude
	 * @throws Exception
	 */
	public void addUserSignIn(Integer userId,Integer userCaseId,String sign_in_longitude,String sign_in_latitude) throws Exception {
		
		UcaSign ucaSign = new UcaSign();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		ucaSign.setUser_id(userId);
		ucaSign.setUsercase_id(userCaseId);
		ucaSign.setSign_in_date(date);
		ucaSign.setSign_in_latitude(sign_in_latitude);
		ucaSign.setSign_in_longitude(sign_in_longitude);
		ucaSign.setState(1);
		ucaSign.setCreate_date(date);
		ucaSign.setLast_mod(date);
		
		officeDao.addUserSignIn(ucaSign);
		
		Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
		
		Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
		
		officeDao.updateUcaInfoState(userCaseId,ucaState,2);
		
		Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
		
		
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 2, ucaState);
		
	}
	
	/**
	 * 添加用户保存问卷答案
	 * @param surveyAnswer
	 * @throws Exception
	 */
	public void addUserSurveyAnswer(String surveyAnswer) throws Exception {
		
		JSONArray fromObject = JSONArray.fromObject(surveyAnswer);
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		for (Object object : fromObject) {
			
				UcaPaper ucaPaper = new UcaPaper();
				
				JSONObject fromObject2 = JSONObject.fromObject(object);
				
				Integer user_id = Integer.parseInt(fromObject2.get("user_id").toString());
				Integer usercase_id = Integer.parseInt(fromObject2.get("usercase_id").toString());
				Integer task_id = Integer.parseInt(fromObject2.get("task_id").toString());
				Integer paper_id = Integer.parseInt(fromObject2.get("paper_id").toString());
				Integer question_id = Integer.parseInt(fromObject2.get("question_id").toString());
				
				
				String select_answer = fromObject2.get("select_answer").toString();
				String text_answer = fromObject2.get("text_answer").toString();
				String photo_answer = fromObject2.get("photo_answer").toString();
				
				ucaPaper.setUser_id(user_id);
				ucaPaper.setUsercase_id(usercase_id);
				ucaPaper.setTask_id(task_id);
				ucaPaper.setPaper_id(paper_id);
				ucaPaper.setQuestion_id(question_id);
				
				ucaPaper.setSelect_answer(select_answer);
				ucaPaper.setText_answer(text_answer);
				ucaPaper.setPhoto_answer(photo_answer);
				ucaPaper.setState(1);
				ucaPaper.setCreate_date(date);
				ucaPaper.setLast_mod(date);
				
				officeDao.addUserSurveyAnswer(ucaPaper);
			
		}
		
	}
	
	/**
	 * 添加用户问卷提交
	 * @param surveyAnswer
	 * @param userCaseId
	 * @throws Exception
	 */
	public void addUserSurveySubmit (String surveyAnswer,Integer userCaseId) throws Exception {
		
		addUserSurveyAnswer(surveyAnswer);
		
		Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
		
		Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
		Integer userId = Integer.parseInt(ucaInfo.get("user_id").toString());
		Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
		
		officeDao.updateUcaInfoState(userCaseId, ucaState, 3);
		
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 3, ucaState);
		
		UcaCheckUsercase ucacheck = new UcaCheckUsercase();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		ucacheck.setUsercase_id(userCaseId);
		ucacheck.setUsercase_state(3);
		ucacheck.setContent("调研问卷已经提交,请尽快审核！");
		ucacheck.setState(1);
		ucacheck.setCreate_date(date);
		ucacheck.setLast_mod(date);
		
		officeDao.insertUcaCheck(ucacheck);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，辛苦了！您的任务问卷已上呈审核，3个工作日内出审核结果，请注意消息通知。";
		
		String title = "您的任务问卷已上呈审核";
		
		userDao.addUserMessage(userId, descs, title);
		
		
		
		
	}
	
	/**
	 * 添加用户领取俸禄
	 * @param userCaseId
	 * @param taskId
	 * @throws Exception
	 */
	public void addUserAalary(Integer userCaseId,Integer taskId) throws Exception {
		
		Map<String, Object> officeDetail = officeDao.selUcaInfo(userCaseId);
		
		Integer caseId = Integer.parseInt(officeDetail.get("case_id").toString());
		Integer userId = Integer.parseInt(officeDetail.get("user_id").toString());
		Integer ucaState = Integer.parseInt(officeDetail.get("uca_state").toString());
		
		//修改案件状态
		officeDao.updateUcaInfoState(userCaseId,ucaState,11);
		
		//插入接案案件状态变更
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 11, ucaState);
		
		
		
		Map<String, Object> taskInfo = taskDao.selTaskInfo(taskId);
		
		Double amount = Double.parseDouble(taskInfo.get("task_amount").toString());
		
		
		Map<String, Object> virtualAccBook = accountDao.selUserBalance(userId, 1);
		
		Integer virAccBookId = Integer.parseInt(virtualAccBook.get("accountbook_id").toString());
		Double virAccBalan = Double.parseDouble(virtualAccBook.get("balance").toString()) + amount;
		
		//修改账户余额
		accountDao.updateVirAccBook(virAccBookId, virAccBalan);
		
		
		//插入用户账户变更
		Map<String, Object> account = accountDao.selUserAccByUserId(userId);
		Integer accountId = Integer.parseInt(account.get("account_id").toString());
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		UserAccountbookChange userVirAccBookChan = new UserAccountbookChange();
		
		userVirAccBookChan.setAccountbook_id(virAccBookId);
		userVirAccBookChan.setAccount_id(accountId);
		userVirAccBookChan.setUser_id(userId);
		userVirAccBookChan.setChange_type(1);
		userVirAccBookChan.setIncome(amount);
		userVirAccBookChan.setBalance(virAccBalan);
		userVirAccBookChan.setDescs("用户领俸，虚拟币账本变更记录！");
		userVirAccBookChan.setState(1);
		userVirAccBookChan.setCreate_date(date);
		userVirAccBookChan.setLast_mod(date);
		
		accountDao.addAccountChange(userVirAccBookChan);
		
		
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，恭喜您成功领取俸禄"+amount.toString()+"两，请去金库查看。";
		
		String title = "恭喜您成功领取俸禄";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	
	
	/**
	 * 添加用户问卷第二次提交
	 * @param surveyAnswer
	 * @param userCaseId
	 * @throws Exception
	 */
	public void userSixToSeven(String surveyAnswer,Integer userCaseId) throws Exception {
		
		addUserSurveyAnswer(surveyAnswer);
		
		Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
		
		Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
		Integer userId = Integer.parseInt(ucaInfo.get("user_id").toString());
		Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
		
		officeDao.updateUcaInfoState(userCaseId, ucaState, 7);
		
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 7, ucaState);
		
		UcaCheckUsercase ucacheck = new UcaCheckUsercase();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		ucacheck.setUsercase_id(userCaseId);
		ucacheck.setUsercase_state(7);
		ucacheck.setContent("调研问卷已经提交,请尽快审核！");
		ucacheck.setState(1);
		ucacheck.setCreate_date(date);
		ucacheck.setLast_mod(date);
		
		officeDao.insertUcaCheck(ucacheck);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，辛苦了！您的办案结果已上呈审核，3个工作日内出审核结果，请注意消息通知。";
		
		String title = "您的办案结果已上呈审核";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 添加用户未通过第二次办案
	 * @param userId
	 * @param userCaseId
	 * @throws Exception
	 */
	public void userFiveToSix(Integer userId,Integer userCaseId) throws Exception {
		
			Map<String, Object> ucaInfo = officeDao.selUcaInfo(userCaseId);
			
			Integer ucaState = Integer.parseInt(ucaInfo.get("uca_state").toString());
			
			officeDao.updateUcaInfoState(userCaseId,ucaState,6);
			
			Integer caseId = Integer.parseInt(ucaInfo.get("case_id").toString());
			
			officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 6, ucaState);
			
	}
	
	
	/**
	 * 用户第二次审核未通过点击结束
	 * @param userCaseId
	 * @throws Exception
	 */
	public void userEightToNine(Integer userCaseId) throws Exception {
		
		Map<String, Object> officeDetail = officeDao.selUcaInfo(userCaseId);
		
		Integer caseId = Integer.parseInt(officeDetail.get("case_id").toString());
		Integer userId = Integer.parseInt(officeDetail.get("user_id").toString());
		Integer ucaState = Integer.parseInt(officeDetail.get("uca_state").toString());
		
		officeDao.updateUcaInfoState(userCaseId,ucaState,9);
		
		taskDao.updateTaskShopState(caseId, 1);
		
		officeDao.insertUserTaskStateLog(userCaseId, caseId, userId, 9, ucaState);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，很遗憾您的办案结果未通过审核，因您的问卷两次没有通过审核，您已被取消办案资格，请大人去主页面中查看详情。";
		
		String title = "提交的办案结果终审未通过，并取消办案资格。";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	
	

}
