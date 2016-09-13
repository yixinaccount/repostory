package cn.shendiao.user.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.shendiao.account.dao.AccountDao;
import cn.shendiao.account.model.UserAccount;
import cn.shendiao.account.model.UserAccountPayment;
import cn.shendiao.account.model.UserAccountbook;
import cn.shendiao.account.service.AccountService;
import cn.shendiao.user.dao.UserDao;
import cn.shendiao.user.model.LoginValidCode;
import cn.shendiao.user.model.User;
import cn.shendiao.user.model.UserAddition;
import cn.shendiao.user.model.UserCertification;
import cn.shendiao.user.model.UserSysNotice;
import cn.shendiao.util.SMSUtil;
import cn.shendiao.util.SysUtil;



@Transactional
@Service("userService")


public class UserService {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private AccountDao accountDao;
	
	@Resource
	private AccountService accountService;
	

	/**
	 * 添加短信验证码
	 * @param phone
	 * @throws Exception
	 */
	public Integer addValidateCode(String phone,String ranText) throws Exception{
		
		LoginValidCode loginValidCode = new LoginValidCode();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		loginValidCode.setTel(phone);
		String sendSMS = SMSUtil.sendSMS(phone, ranText);
		
		if("000000".equals(sendSMS)){
			
			loginValidCode.setValid_code(ranText);
			loginValidCode.setState(1);
			loginValidCode.setCreate_date(date);
			userDao.addValidateCode(loginValidCode);
			return 1;
			
		}else{
			
			return 0;
			
		}
		
	}
	
	/**
	 *  添加用户
	 * @param phone
	 * @param password
	 * @param nickname
	 * @param userType
	 * @throws Exception
	 */
	public void addUserInfo(String phone,String password,String nickname,Integer userType,String invitationCode) throws Exception{
		
		User user = new User();
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		user.setCellphone(phone);
		user.setUserpwd(SysUtil.md5(password));
		user.setUser_type(userType);
		user.setNickname(nickname);
		user.setState(1);
		user.setCreate_date(date);
		user.setLast_mod(date);
		Integer userId = userDao.addUserInfo(user);
		
		
		
		UserAccount account = new UserAccount();
		account.setUser_id(userId);
		account.setState(1);
		account.setCreate_date(date);
		account.setLast_mod(date);
		
		
		Integer accountId = accountDao.addUserAccount(account);
		
		//Integer aa = 1/0;
		
		UserAccountbook userAccbook = new UserAccountbook();
		
		userAccbook.setAccount_id(accountId);
		userAccbook.setAccountitem_type(1);
		userAccbook.setBalance(0.0);
		userAccbook.setOpening_balance(0.0);
		userAccbook.setState(1);
		userAccbook.setUser_id(userId);
		userAccbook.setCreate_date(date);
		userAccbook.setLast_mod(date);
		
		accountDao.addUserAccbook(userAccbook);
		
		userAccbook.setAccountitem_type(2);
		
		accountDao.addUserAccbook(userAccbook);
		
		//--modifyby rainbow
		userDao.updateInvitationCode(userId, invitationCode);
		
		String descs = nickname+"，恭喜您注册成功！完善个人资料将会获得领取任务的资格哟！";
		
		String title = "恭喜您注册成功！";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 修改用户身份信息
	 * @param userId
	 * @param realname
	 * @param IDCard
	 * @param IDCardFront
	 * @param IDCardBack
	 * @throws Exception
	 */
	//public void updateIdentityInfo(Integer userId,String realname,String IDCard,String IDCardFront,String IDCardBack) throws Exception{
	public void updateIdentityInfo(Integer userId,String IDCardFront,String IDCardBack,String IDHead_pic) throws Exception{	
		//userDao.updateRealAndID(userId,realname,3,IDCard);
		//modify rainbow 
		userDao.updateRealAndID(userId,3);
		
		Map<String,Object> map = userDao.selUserAdditionInfo(userId);
		
		UserAddition userAddition = new UserAddition();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		userAddition.setUser_id(userId);
		userAddition.setIDCard_front_pic(IDCardFront);
		userAddition.setIDCard_back_pic(IDCardBack);
		userAddition.setState(1);
		userAddition.setCreate_date(date);
		userAddition.setLast_mod(date);
		userAddition.setAddition_1(IDHead_pic);//add by rainbow
		
		
		UserCertification userCertification = new UserCertification();
		
		userCertification.setUser_id(userId);
		userCertification.setState(1);
		userCertification.setCreate_date(date);
		userCertification.setLast_mod(date);
			
		if(SysUtil.isNull(map)){
			
			userDao.insertIdentityInfo(userAddition);
			
			userDao.insertUserCertifi(userCertification);
			
		}else{
			
			userAddition.setId(Integer.parseInt(map.get("id").toString()));
			
			userDao.updateIdentityInfo(userAddition);
			
			userDao.insertUserCertifi(userCertification);
			
		}
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"，感谢您完善个人资料。您的审核申请已提交，审核大约需要2-3个工作日，请耐心等待！";
		
		String title = "您已完善个人资料，审核申请已提交";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 查询用户是否有未读通知
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer selUserNoticeState(Integer userId) throws Exception{
		
		Integer noMessageCount = userDao.selNoMessageCount(userId);
		
		Integer noSysNotiCount = userDao.selNoSysNotiCount(userId);
		
		if(noMessageCount+noSysNotiCount>0){
			
			return 1;
			
		}else{
			
			return 0;
		}
		
		
	}
	
	/**
	 * 修改用户最后看系统通知时间
	 * @param userId
	 * @throws Exception
	 */
	public void updateSysNotTime(Integer userId) throws Exception {
		
		UserSysNotice userSysNotice = new UserSysNotice();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		userSysNotice.setUser_id(userId);
		userSysNotice.setLast_reading(date);
		userSysNotice.setState(1);
		userSysNotice.setCreate_date(date);
		userSysNotice.setLast_mod(date);
		
		userDao.updateSysNotTime(userSysNotice);
		
	}
	
	/**
	 * 更新用户手机号
	 * @param userId
	 * @param phone
	 * @throws Exception
	 */
	public void updatePhone(Integer userId,String phone) throws Exception {
		
		userDao.updatePhone(userId, phone);
		
		//Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = "您已成功更换手机号，请注意信息安全。";
		
		String title = "您已成功更换手机号";
		
		userDao.addUserMessage(userId, descs, title);
		
		
	}
	
	/**
	 * 更换手机号失败添加个人消息接口
	 * @param userId
	 * @throws Exception
	 */
	public void updatePhoneFailMes(Integer userId) throws Exception {
		
		//Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = "您申请更换手机号失败，请注意信息安全。";
		
		String title = "您申请更换手机号失败";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 用户修改密码
	 * @param userId
	 * @param newPass
	 * @throws Exception
	 */
	public void updatePassword(Integer userId,String newPass) throws Exception {
		
		userDao.updatePassword(userId, newPass);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，您已成功修改密码，请注意账号安全。";
		
		String title = "您已成功修改密码";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 用户修改密码失败消息
	 * @param userId
	 * @throws Exception
	 */
	public void updatePassFailMes(Integer userId) throws Exception {
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		
		String descs = user.get("nickname").toString()+"大人，您申请修改密码失败，自动退出登录，请注意账号安全。";
		
		String title = "您申请修改密码失败";
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 身份信息审核通过
	 * @param userId
	 * @throws Exception
	 */
	public void updateCheckPass(Integer userId,Integer operatorId) throws Exception {
		
		userDao.updateCheckState(userId,2,operatorId,null);
		
		userDao.updateUserState(userId,2,1000);
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，恭喜您个人资料已通过审核，您已升级为从九品，可以前去领取任务了。";
		
		String title = "恭喜您个人资料已通过审核";
		
		userDao.addUserMessage(userId, descs, title);
		
		
	}
	
	/**
	 * 审核不通过
	 * @param userId
	 * @param operatorId
	 * @param checkContent
	 * @throws Exception
	 */
	public void updateCheckNoPass(Integer userId,Integer operatorId,String checkContent) throws Exception {
		
			userDao.updateCheckState(userId,3,operatorId,checkContent);
			
			userDao.updateUserState(userId,4,0);
			
			Map<String, Object> user = userDao.selUserByUserId(userId);
			
			String descs = user.get("nickname").toString()+"，遗憾通知您个人资料未通过审核，未通过原因："+checkContent+"。请您重新上传您的个人资料。";
		
			String title = "您个人资料未通过审核";
		
			userDao.addUserMessage(userId, descs, title);
		
		
	}
	

}
