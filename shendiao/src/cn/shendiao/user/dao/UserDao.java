package cn.shendiao.user.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.dao.DaoSupport;
import cn.shendiao.user.model.LoginValidCode;
import cn.shendiao.user.model.User;
import cn.shendiao.user.model.UserAddition;
import cn.shendiao.user.model.UserAppComm;
import cn.shendiao.user.model.UserCertification;
import cn.shendiao.user.model.UserMessage;
import cn.shendiao.user.model.UserSysNotice;
import cn.shendiao.util.SysUtil;

@Repository("userDao")
public class UserDao extends DaoSupport{


	/**
	 * 添加短信验证码
	 * @param loginValidCode
	 * @throws Exception
	 */
	public void addValidateCode(LoginValidCode loginValidCode) throws Exception{
		
		save("UserMapper.addValidateCode", loginValidCode);
		
	}
	

	/**
	 * 用户登录
	 * @param phone
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User login(String phone,String password) throws Exception{
		
		password  = SysUtil.md5(password);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("password", password);
		User user = (User) findForObject("UserMapper.selUserByNameAndPwd", param);
		return user;
	}
	
	
	/**
	 * 根据手机查询用户信息
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectUserInfoByPhone(String phone) throws Exception{
		
		return (Map<String, Object>) findForObject("UserMapper.selectUserInfoByPhone", phone);
		
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public Integer addUserInfo(User user) throws Exception {
		
		save("UserMapper.addUserInfo", user);
		return user.getUser_id();
		
	}
	
	/**
	 * 通过userId 查询用户信息
	 * @param userId
	 * @throws Exception
	 */
	public Map<String,Object> selUserByUserId(Integer userId) throws Exception{
		
		return (Map<String, Object>) findForObject("UserMapper.selUserByUserId", userId);
		
	}
	
	/**
	 * 修改邀请码
	 * modify rainbow
	 * @param userId
	 * @param invitationCode
	 * @throws Exception
	 */
	public void updateInvitationCode(Integer userId,String invitationCode) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("invitationCode", invitationCode);
		
		update("UserMapper.updateInvitationCode", map);
		
	}
	
	/**
	 * 查询合法的邀请码
	 * modify rainbow
	 * @param invitationCode
	 * @return
	 * @throws Exception
	 */
	public Integer selUserInvitationBycode(String invitationCode) throws Exception {
		
		return (Integer) findForObject("UserMapper.selUserInvitationBycode", invitationCode);
		
	}
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 * @throws Exception
	 */
	public void updatePassword(Integer userId,String password) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("password", SysUtil.md5(password));
		
		update("UserMapper.updatePassword", map);
		
	}
	
	/**
	 * 通过手机号更新密码
	 * @param phone
	 * @param password
	 * @throws Exception
	 */
	public void updatePassByPhone(String phone,String password) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("password", SysUtil.md5(password));
		
		update("UserMapper.updatePassByPhone", map);
		
	}
	
	/**
	 * 修改昵称
	 * @param userId
	 * @param nickname
	 * @throws Exception
	 */
	public void updateNickname(Integer userId,String nickname) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("nickname", nickname);
		
		update("UserMapper.updateNickname", map);
		
	}
	
	/**
	 * 修改手机号
	 * @param userId
	 * @param phone
	 * @throws Exception
	 */
	public void updatePhone(Integer userId,String phone) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("phone", phone);
		
		update("UserMapper.updatePhone", map);
		
	}
	
	/**
	 * 查询用户所有级别
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserAllLevel() throws Exception{
		
		return (List<Map<String, Object>>) findForList("UserMapper.selUserAllLevel", null);
		
	}
	
	/**
	 * 修改用户头像
	 * @param headPic
	 * @param userId
	 * @throws Exception
	 */
	public void updateHeadPic(String headPic,Integer userId) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("headPic", headPic);
		map.put("userId", userId);
		
		update("UserMapper.updateHeadPic", map);
		
	}
	
	/**
	 * 修改用户婚姻状态
	 * @param userId
	 * @param status
	 * @throws Exception
	 */
	public void updateMarrystatus(Integer userId,Integer status) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("status", status);
		
		update("UserMapper.updateMarrystatus", map);
		
	}
	
	/**
	 * 更新用户真实名和身份证号
	 * @param userId
	 * @param realname
	 * @param IDCard
	 * @throws Exception
	 */
	//public void updateRealAndID(Integer userId,String realname,Integer state,String IDCard) throws Exception{
	public void updateRealAndID(Integer userId,Integer state) throws Exception{	
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		//map.put("realname", realname);
		map.put("state", state);
		//map.put("IDCard", IDCard);
		
		update("UserMapper.updateRealAndID", map);
		
	}
	
	/**
	 * 更新用户真实名和身份证号和状态 add by rainbow
	 * @param userId
	 * @param realname
	 * @param IDCard
	 * @throws Exception
	 */
	public void updateRealAndState(Integer userId,String realname,Integer state,String IDCard) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("realname", realname);
		map.put("state", state);
		map.put("IDCard", IDCard);
		
		update("UserMapper.updateRealAndState", map);
		
	}
	
	/**
	 * 查询用户附加信息
	 * @param userId
	 * @throws Exception
	 */
	public Map<String,Object> selUserAdditionInfo(Integer userId) throws Exception {
		
		return (Map<String, Object>) findForObject("UserMapper.selUserAdditionInfo", userId);
		
	}
	
	/**
	 * 查询用户支付信息
	 * @param userId
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserPaymentInfo(Integer userId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("UserMapper.selUserPaymentInfo", userId);
		
	}
	
	/**
	 * 插入身份信息
	 * @param userAddition
	 * @throws Exception
	 */
	public void insertIdentityInfo(UserAddition userAddition) throws Exception{
		
		save("UserMapper.insertIdentityInfo", userAddition);
		
	}
	
	/**
	 * 修改身份信息
	 * @param userAddition
	 * @throws Exception
	 */
	public void updateIdentityInfo(UserAddition userAddition) throws Exception{
		
		update("UserMapper.updateIdentityInfo", userAddition);
		
	}
	
	/**
	 * 查询用户未读个人通知总数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer selNoMessageCount(Integer userId) throws Exception {
		
		return (Integer) findForObject("UserMapper.selNoMessageCount", userId);
		
	}
	
	/**
	 * 查询用户未读系统通知总数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer selNoSysNotiCount(Integer userId) throws Exception {
		
		return (Integer) findForObject("UserMapper.selNoSysNotiCount", userId);
		
	}
	
	/**
	 * 查询系统给通知列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selSysNotice(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("UserMapper.selSysNotice", map);
		
	}
	
	/**
	 * 查询用户个人通知列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserMessage(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("UserMapper.selUserMessage", map);
		
	}
	
	
	/**
	 * 修改用户查询系统消息时间
	 * @param userSysNotice
	 * @throws Exception
	 */
	public void updateSysNotTime(UserSysNotice userSysNotice) throws Exception {
		
		save("UserMapper.updateSysNotTime", userSysNotice);
		
	}
	
	/**
	 * 修改用户个人消息状态
	 * @param messageId
	 * @param state
	 * @throws Exception
	 */
	public void updateUserMesState(Integer messageId,Integer state) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("messageId", messageId);
		
		map.put("state", state);
		
		update("UserMapper.updateUserMesState", map);
		
		
	}
	
	/**
	 * 查询系统通知详情
	 * @param sysNoticeId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selSysNoticeDetail(Integer sysNoticeId) throws Exception {
		
		return (Map<String, Object>) findForObject("UserMapper.selSysNoticeDetail", sysNoticeId);
		
	}
	
	/**
	 * 查询个人消息详情
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserMesDetail(Integer messageId) throws Exception {
		
		return (Map<String, Object>) findForObject("UserMapper.selUserMesDetail", messageId);
		
	}
	
	/**
	 * 查询系统参数
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selSysParams() throws Exception {
		
		return (Map<String, Object>) findForObject("UserMapper.selSysParams", null);
		
	}
	
	/**
	 * 添加用户个人消息
	 * @param userId
	 * @param descs
	 * @param title
	 * @throws Exception
	 */
	public void addUserMessage(Integer userId,String descs,String title) throws Exception {
		
		UserMessage userMes = new UserMessage();
		
			userMes.setUser_id(userId);
			userMes.setDescs(descs);
			userMes.setTitle(title);
			userMes.setType(1);
			userMes.setState(1);
			String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
			userMes.setCreate_date(date);
			userMes.setLast_mod(date);
			
			save("UserMapper.addUserMessage", userMes);
		
	}
	
	
	/**
	 * 修改用户学历状态
	 * @param userId
	 * @param eduStatus
	 * @throws Exception
	 */
	public void updateEdustatus(Integer userId,Integer eduStatus) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("eduStatus", eduStatus);
		
		update("UserMapper.updateEdustatus", map);
		
	}
	
	/**
	 * 查询配置信息
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selConfigDetail(String type) throws Exception {
		
		return (List<Map<String, Object>>) findForList("UserMapper.selConfigDetail", type);
		
	}
	
	/**
	 * 添加用户反馈
	 * @param userId
	 * @param content
	 * @throws Exception
	 */
	public void addUserFeedback(Integer userId,String content) throws Exception {
		
		UserAppComm userAppComm = new UserAppComm();
		
			String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
			
			userAppComm.setUser_id(userId);
			userAppComm.setContent(content);
			userAppComm.setState(1);
			userAppComm.setCreate_date(date);
			userAppComm.setLast_mod(date);
	
		save("UserMapper.addUserFeedback", userAppComm);
		
	}
	
	/**
	 * 添加用户身份认证表
	 * @param userCertification
	 * @throws Exception
	 */
	public void insertUserCertifi(UserCertification userCertification) throws Exception {
		
		save("UserMapper.insertUserCertifi", userCertification);
		
	}
	
	/**
	 * 查询用户审核总数
	 * @return
	 * @throws Exception
	 */
	public Integer selectUserCheckCount() throws Exception {
		
		return (Integer) findForObject("UserMapper.selectUserCheckCount", null);
		
	}
	
	/**
	 * 查询审核列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectUserCheckList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("UserMapper.selectUserCheckList", map);
		
	}
	
	
	/**
	 * 查询审核详情
	 * @param checkId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selCheckDetail(Integer checkId) throws Exception {
		
		return (Map<String, Object>) findForObject("UserMapper.selCheckDetail", checkId);
		
	}
	
	/**
	 * 更新用户审核
	 * @param userId
	 * @param operatorId
	 * @param checkContent
	 * @throws Exception
	 */
	public void updateCheckState(Integer userId,Integer state,Integer operatorId,String checkContent) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		
		map.put("state", state);
		
		
		map.put("operatorId", operatorId);
		
		map.put("checkContent", checkContent);
		
		
		update("UserMapper.updateCheckState", map);
		
	}
	
	/**
	 * 更新用户状态
	 * @param userId
	 * @param exp
	 * @throws Exception
	 */
	public void updateUserState(Integer userId,Integer state,Integer exp) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		
		
		map.put("state", state);
		
		
		map.put("exp", exp);
		
		update("UserMapper.updateUserState", map);
		
	}
	
	
}
