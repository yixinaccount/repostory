package cn.shendiao.account.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.account.model.UserAccExchange;
import cn.shendiao.account.model.UserAccount;
import cn.shendiao.account.model.UserAccountPayment;
import cn.shendiao.account.model.UserAccountbook;
import cn.shendiao.account.model.UserAccountbookChange;
import cn.shendiao.account.model.UserExtractCash;
import cn.shendiao.dao.DaoSupport;


@Repository("accountDao")
public class AccountDao extends DaoSupport{
	
	/**
	 * 添加账户
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Integer addUserAccount(UserAccount account) throws Exception{
		
		save("AccountMapper.addUserAccount", account);
		
		return account.getAccount_id();
		
	}
	
	/**
	 * 添加账本
	 * @param userAccbook
	 * @throws Exception
	 */
	public void addUserAccbook(UserAccountbook userAccbook) throws Exception{
		
		save("AccountMapper.addUserAccbook", userAccbook);
		
	}
	
	/**
	 * 通过userId获取银行信息
	 * @param userId
	 * @param paymentType
	 * @return
	 */
	public Map<String,Object> selBankByUserId(Integer userId,Integer paymentType) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("paymentType", paymentType);
		
		return (Map<String, Object>) findForObject("AccountMapper.selBankByUserId", map);
		
	}
	
	
	/**
	 * 查询用户账户通过userId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserAccByUserId(Integer userId) throws Exception{
		
		return (Map<String, Object>) findForObject("AccountMapper.selUserAccByUserId", userId);
		
	}
	
	/**
	 * 插入用户银行信息
	 * @param userAccPayment
	 * @throws Exception
	 */
	public void insertBankInfo(UserAccountPayment userAccPayment) throws Exception{
		
		save("AccountMapper.insertBankInfo", userAccPayment);
		
	}
	
	/**
	 * 更新用户银行信息
	 * @param userAccPayment
	 * @throws Exception
	 */
	public void updateBankInfo(UserAccountPayment userAccPayment) throws Exception{
		
		update("AccountMapper.updateBankInfo", userAccPayment);
		
	}
	
	/**
	 * 插入支付宝信息
	 * @param userAccPayment
	 * @throws Exception
	 */
	public void insertZhifubao(UserAccountPayment userAccPayment) throws Exception{
		
		save("AccountMapper.insertZhifubao", userAccPayment);
		
	}
	
	/**
	 * 更新支付宝信息
	 * @param userAccPayment
	 * @throws Exception
	 */
	public void updateZhifubao(UserAccountPayment userAccPayment) throws Exception{
		
		update("AccountMapper.updateZhifubao", userAccPayment);
		
	}
	
	/**
	 * 查询用户账本育儿
	 * @param userId
	 * @param accBookType
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserBalance(Integer userId,Integer accBookType) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("accBookType", accBookType);
		
		return (Map<String, Object>) findForObject("AccountMapper.selUserBalance", map);
		
	}
	
	/**
	 * 查询用户体现金额
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String selUserExtractCash(Integer userId) throws Exception {
		
		return (String) findForObject("AccountMapper.selUserExtractCash", userId);
		
	}
	
	/**
	 * 查询用户总收益
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String selUserAllIncome(Integer userId) throws Exception {
		
		return (String) findForObject("AccountMapper.selUserAllIncome", userId);
		
	}
	
	/**
	 * 查询用户提现列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserExtCashList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("AccountMapper.selUserExtCashList", map);
		
	}
	
	/**
	 * 查询用户收益列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserEarningsList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("AccountMapper.selUserEarningsList", map);
		
	}
	
	/**
	 * 插入现金变更
	 * @param userAccExch
	 * @throws Exception
	 */
	public void addUserAccExch(UserAccExchange userAccExch) throws Exception {
		
		save("AccountMapper.addUserAccExchh", userAccExch);
		
	}
	
	/**
	 * 更新虚拟账户信息
	 * @param virAccBookId
	 * @param virAccBalan
	 * @throws Exception
	 */
	public void updateVirAccBook(Integer virAccBookId,Double virAccBalan) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("virAccBookId", virAccBookId);
		map.put("virAccBalan", virAccBalan);
		
		update("AccountMapper.updateVirAccBook", map);
		
	}
	
	/**
	 * 更新现金账本信息
	 * @param cashAccBookId
	 * @param cashAccBalan
	 * @throws Exception
	 */
	public void updateCashAccBook(Integer cashAccBookId,Double cashAccBalan) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("cashAccBookId", cashAccBookId);
		map.put("cashAccBalan", cashAccBalan);
		
		update("AccountMapper.updateCashAccBook", map);
		
	}
	
	/**
	 * 添加账户变更
	 * @param userAccBookChan
	 * @throws Exception
	 */
	public void addAccountChange(UserAccountbookChange userAccBookChan) throws Exception {
		
		save("AccountMapper.addAccountChange",userAccBookChan);
		
	}
	
	/**
	 * 添加用户提现记录
	 * @param userExtrCash
	 * @throws Exception
	 */
	public void addUserExtrCash(UserExtractCash userExtrCash) throws Exception {
		
		save("AccountMapper.addUserExtrCash", userExtrCash);
		
	}
	
	/**
	 * 查询用户支付信息
	 * @param userId
	 * @param paymentType
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selUserPayInfo(Integer userId,Integer paymentType) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		
		map.put("paymentType", paymentType);
		
		return (Map<String, Object>) findForObject("AccountMapper.selUserPayInfo", map);
		
	}
	
	/**
	 * 用户提现调存储过程
	 * @param userId
	 * @param amount
	 * @param paymentType
	 * @param paymentCode
	 * @param paymentInfo
	 * @param paymentInfo2
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> addUserTiXian(Integer userId,Double amount,
			
				Integer paymentType,String paymentCode,String paymentInfo,
				
				String paymentInfo2
				
			
			) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
			map.put("userId", userId);
			map.put("amount", amount);
			map.put("paymentType", paymentType);
			map.put("paymentCode", paymentCode);
			map.put("paymentInfo", paymentInfo);
			map.put("paymentInfo2", paymentInfo2);
			
			findForObject("AccountMapper.addUserTiXian", map);
		
		 return map;
		
		
		
	}

}
