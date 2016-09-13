package cn.shendiao.account.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.shendiao.account.dao.AccountDao;
import cn.shendiao.account.model.UserAccExchange;
import cn.shendiao.account.model.UserAccountPayment;
import cn.shendiao.account.model.UserAccountbookChange;
import cn.shendiao.account.model.UserExtractCash;
import cn.shendiao.user.dao.UserDao;
import cn.shendiao.util.SysUtil;


@Service("accountService")
public class AccountService {
	
	@Resource
	private AccountDao accountDao;
	
	@Resource
	private UserDao userDao;
	
	
	
	/**
	 * 修改用户银行信息
	 * @param userId
	 * @param bank
	 * @param openBank
	 * @param bankCodeNo
	 * @throws Exception
	 */
	@Transactional
	public void updateBankInfo(Integer userId,String bank,String openBank,String bankCodeNo) throws Exception{
		
		Map<String, Object> bankMap = accountDao.selBankByUserId(userId,1);
		
		UserAccountPayment userAccPayment = new UserAccountPayment();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		userAccPayment.setUser_id(userId);
		userAccPayment.setPayment_code(bankCodeNo);
		userAccPayment.setPayment_info1(bank);
		userAccPayment.setPayment_info2(openBank);
		userAccPayment.setPayment_type(1);
		
		userAccPayment.setState(1);
		userAccPayment.setCreate_date(date);
		userAccPayment.setLast_mod(date);
		
		
		if(SysUtil.isNull(bankMap)){
			
			Map<String, Object> accountMap = accountDao.selUserAccByUserId(userId);
			
			userAccPayment.setAccount_id(Integer.parseInt(accountMap.get("account_id").toString()));
			
			accountDao.insertBankInfo(userAccPayment);
			
		}else{
			
			userAccPayment.setId(Integer.parseInt(bankMap.get("id").toString()));
			accountDao.updateBankInfo(userAccPayment);
			
		}
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，您已成功绑定银行卡，可进行提现操作。";
		
		String title = "您已成功绑定银行卡";
		//int i = 1 / 0; // 抛出运行时异常
		
		userDao.addUserMessage(userId, descs, title);
		
	}
	
	/**
	 * 修改支付宝账号
	 * @param userId
	 * @param zhifubaoCode
	 * @throws Exception
	 */
	public void updateZhifubao(Integer userId,String zhifubaoCode) throws Exception{
		
		Map<String, Object> bankMap = accountDao.selBankByUserId(userId,2);
		
		UserAccountPayment userAccPayment = new UserAccountPayment();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		userAccPayment.setPayment_code(zhifubaoCode);
		
		userAccPayment.setLast_mod(date);
		
		if(SysUtil.isNull(bankMap)){
			
			userAccPayment.setUser_id(userId);
			userAccPayment.setState(1);
			userAccPayment.setPayment_type(2);
			userAccPayment.setCreate_date(date);
			
			Map<String, Object> accountMap = accountDao.selUserAccByUserId(userId);
			userAccPayment.setAccount_id(Integer.parseInt(accountMap.get("account_id").toString()));
			
			accountDao.insertZhifubao(userAccPayment);
			
		}else{
			
			userAccPayment.setId(Integer.parseInt(bankMap.get("id").toString()));
			accountDao.updateZhifubao(userAccPayment);
			
		}
		
		
	}
	
	/**
	 * 用户提现
	 * @param userId
	 * @param amount
	 * @throws Exception
	 */
	public void addUserExtCash(Integer userId,Double amount,Integer paymentType,String paymentCode,String paymentInfo) throws Exception {
		
		Map<String, Object> account = accountDao.selUserAccByUserId(userId);
		Integer accountId = Integer.parseInt(account.get("account_id").toString());
		
		
		//虚拟币账本
		Map<String, Object> virtualAccBook = accountDao.selUserBalance(userId, 1);
		Integer virAccBookId = Integer.parseInt(virtualAccBook.get("accountbook_id").toString());
		
		//现金账本
		Map<String, Object> cashAccBook = accountDao.selUserBalance(userId, 2);
		Integer cashAccBookId = Integer.parseInt(cashAccBook.get("accountbook_id").toString());
		
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		UserAccExchange userAccExch = new UserAccExchange();
		
		userAccExch.setSource_accbook_id(virAccBookId);
		userAccExch.setTarget_accbook_id(cashAccBookId);
		userAccExch.setSource_amount(amount);
		userAccExch.setTarget_amount(amount);
		userAccExch.setState(1);
		userAccExch.setCreate_date(date);
		userAccExch.setLast_mod(date);
		
		//插入现金变更
		accountDao.addUserAccExch(userAccExch);
		
		Double virAccBalan = Double.parseDouble(virtualAccBook.get("balance").toString()) - amount;
	
		 Double cashBalance = Double.parseDouble(cashAccBook.get("balance").toString());
		
		Double cashAccBalan = cashBalance + amount;
		
		//修改虚拟币账本
		accountDao.updateVirAccBook(virAccBookId,virAccBalan);
		
		
		UserAccountbookChange userVirAccBookChan = new UserAccountbookChange();
		
		userVirAccBookChan.setAccountbook_id(virAccBookId);
		userVirAccBookChan.setAccount_id(accountId);
		userVirAccBookChan.setUser_id(userId);
		userVirAccBookChan.setChange_type(3);
		userVirAccBookChan.setExpend(amount);
		userVirAccBookChan.setBalance(virAccBalan);
		userVirAccBookChan.setDescs("虚拟币兑换现金，虚拟币账本减少记录！");
		userVirAccBookChan.setState(1);
		userVirAccBookChan.setCreate_date(date);
		userVirAccBookChan.setLast_mod(date);
		
		accountDao.addAccountChange(userVirAccBookChan);
		
		
		//修改现金账本
		accountDao.updateCashAccBook(cashAccBookId,cashAccBalan);
		
		UserAccountbookChange userCashAccBookChan = new UserAccountbookChange();
		
		userCashAccBookChan.setAccountbook_id(cashAccBookId);
		userCashAccBookChan.setAccount_id(accountId);
		userCashAccBookChan.setUser_id(userId);
		userCashAccBookChan.setChange_type(2);
		userCashAccBookChan.setIncome(amount);
		userCashAccBookChan.setBalance(cashAccBalan);
		userCashAccBookChan.setDescs("虚拟币兑换现金，现金账本增加记录！");
		userCashAccBookChan.setState(1);
		userCashAccBookChan.setCreate_date(date);
		userCashAccBookChan.setLast_mod(date);
		
		accountDao.addAccountChange(userCashAccBookChan);
		
		//现金账本减少
		accountDao.updateCashAccBook(cashAccBookId,cashBalance);
		
		//提现
		UserExtractCash userExtrCash = new UserExtractCash();
		
		userExtrCash.setUser_id(userId);
		userExtrCash.setAccount_id(accountId);
		userExtrCash.setAccountbook_id(cashAccBookId);
		userExtrCash.setPayment_type(paymentType);
		userExtrCash.setPayment_code(paymentCode);
		userExtrCash.setPayment_info1(paymentInfo);
		userExtrCash.setAmount(amount);
		userExtrCash.setDeal_status(0);
		userExtrCash.setState(1);
		userExtrCash.setCreate_date(date);
		userExtrCash.setLast_mod(date);
		
		accountDao.addUserExtrCash(userExtrCash);
		
		//现金账本变更记录
		UserAccountbookChange extCashAccBookChan = new UserAccountbookChange();
		
		extCashAccBookChan.setAccountbook_id(cashAccBookId);
		extCashAccBookChan.setAccount_id(accountId);
		extCashAccBookChan.setUser_id(userId);
		extCashAccBookChan.setChange_type(6);
		extCashAccBookChan.setExpend(amount);
		extCashAccBookChan.setBalance(cashBalance);
		extCashAccBookChan.setDescs("用户提现，现金账本减少记录！");
		extCashAccBookChan.setState(1);
		extCashAccBookChan.setCreate_date(date);
		extCashAccBookChan.setLast_mod(date);
		
		accountDao.addAccountChange(userCashAccBookChan);
		
		
		Map<String, Object> user = userDao.selUserByUserId(userId);
		
		String descs = user.get("nickname").toString()+"大人，您已申请提现"+amount.toString()+"元，将在3个工作日内到账，请您注意查收！";
		
		
		String title = "您已申请提现"+amount.toString()+"元";
		
		userDao.addUserMessage(userId, descs, title);
		
		
	}
	
	
	

}
