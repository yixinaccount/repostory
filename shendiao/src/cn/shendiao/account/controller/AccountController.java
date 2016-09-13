package cn.shendiao.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.shendiao.account.dao.AccountDao;
import cn.shendiao.account.service.AccountService;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.SysUtil;
import cn.shendiao.util.ToolUtils;



@Controller
@RequestMapping("account")
public class AccountController {
	
	@Resource
	private AccountDao accountDao;
	
	@Resource
	private AccountService accountService;
	

	/**
	 * 修改用户银行信息
	 * @param request
	 * @param userId
	 * @param bankCodeNo
	 * @param bank
	 * @param openBank
	 * @param response
	 */
	@RequestMapping("updateBankInfo")
	public void updateBankInfo(HttpServletRequest request,Integer userId,String bankCodeNo,
			
			String bank,String openBank,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
		
				
			if(SysUtil.isNull(userId)){
				json.put("code", -2);
				json.put("msg", "用户userId不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(bank)){
				json.put("code", -2);
				json.put("msg", "银行不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(openBank)){
				json.put("code", -2);
				json.put("msg", "开户行不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(bankCodeNo)){
				json.put("code", -2);
				json.put("msg", "银行卡号不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
		
			try {
				accountService.updateBankInfo(userId,bank,openBank,bankCodeNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "修改失败！");
				JsonUtil.writeJson(json, response);
				return ;
			}
		
			json.put("code", 0);
			json.put("msg", "修改成功！");
			JsonUtil.writeJson(json, response);
		
	}
	
	/**
	 * 更新支付宝账号
	 * @param request
	 * @param userId
	 * @param zhifubaoCode
	 * @param response
	 */
	@RequestMapping("updateZhifubao")
	public void updateZhifubao(HttpServletRequest request,Integer userId,String zhifubaoCode,HttpServletResponse response) {
	
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户userId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(zhifubaoCode)){
			json.put("code", -2);
			json.put("msg", "支付宝账号不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			accountService.updateZhifubao(userId,zhifubaoCode);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", -1);
			json.put("msg", "修改失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		json.put("code", 0);
		json.put("msg", "修改成功！");
		JsonUtil.writeJson(json, response);
	
	}

	/**
	 * 查询用户账户余额
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserAccBal")
	public void selUserAccBal(HttpServletRequest request,Integer userId,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户Id不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> accountBalance = accountDao.selUserBalance(userId, 1);
			
			String drawMoney = accountDao.selUserExtractCash(userId);
			
			String allIncome = accountDao.selUserAllIncome(userId);
		
			json.put("accountBalance", accountBalance);
			
			json.put("drawMoney", drawMoney);
			
			json.put("allIncome", allIncome);
			
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
	 * 查询用户提取现金列表
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("selUserExtCashList")
	public void selUserExtCashList(HttpServletRequest request,Integer userId,Integer page,Integer rows,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户userId不能为空！");
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
			
			List<Map<String, Object>> userExtCashList = accountDao.selUserExtCashList(map);
			
			json.put("userExtCashList", userExtCashList);
			
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
	 * 查询用户总收益列表
	 * @param request
	 * @param userId
	 * @param response
	 */
	@RequestMapping("userEarningsList")
	public void selUserEarningsList(HttpServletRequest request,Integer userId,Integer page,Integer rows,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户userId不能为空！");
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
				
				List<Map<String, Object>> userEarningsList = accountDao.selUserEarningsList(map);
				
				json.put("userEarningsList", userEarningsList);
				
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
	 * 用户提现
	 * @param request
	 * @param userId
	 * @param amount
	 * @param response
	 */
	@RequestMapping("userExtCash")
	public void userExtCash(HttpServletRequest request,Integer userId,Double amount,
			
			Integer paymentType,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(userId)){
				json.put("code", -1);
				json.put("msg", "用户userId不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(amount)){
				json.put("code", -1);
				json.put("msg", "用户提现金额不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			if(SysUtil.isNull(paymentType)){
				json.put("code", -1);
				json.put("msg", "用户提现类型不能为空！");
				JsonUtil.writeJson(json, response);
				return;
			}
			
			
			try {
				
			
			Map<String, Object> userPayInfo = accountDao.selUserPayInfo(userId,paymentType);
			
			String  paymentCode =userPayInfo.get("payment_code").toString();
			String  paymentInfo =userPayInfo.get("payment_info1").toString();
			String  paymentInfo2 =userPayInfo.get("payment_info2").toString();
	
			
			/*	Map<String, Object> virtualAccBook = accountDao.selUserBalance(userId, 1);
			
				if(Double.parseDouble(virtualAccBook.get("balance").toString()) < amount){
					
					json.put("code", -1);
					json.put("msg", "用户提现金额不能超过账户余额！");
					JsonUtil.writeJson(json, response);
					return;
					
				}
					
				accountService.addUserExtCash(userId,amount,paymentType,paymentCode,paymentInfo);*/
				
				
				Map<String, Object> userTixianMap = accountDao.addUserTiXian(userId,amount,paymentType,paymentCode,paymentInfo,paymentInfo2);
				
				if(userTixianMap.get("code").equals(-1)){
					
					json.put("code", -1);
					json.put("msg", userTixianMap.get("descs"));
					JsonUtil.writeJson(json, response);
					return ;
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				json.put("code", -1);
				json.put("msg", "提现失败！");
				JsonUtil.writeJson(json, response);
				return ;
				
			}
			
			json.put("code", 0);
			json.put("msg", "提现成功！");
			JsonUtil.writeJson(json, response);
		
		
	}
	
	/**
	 * 查询用户银行信息
	 * @param request
	 * @param userId
	 * @param paymentType
	 * @param response
	 */
	@RequestMapping("selUserPayInfoDetail")
	public void selUserPayInfoDetail(HttpServletRequest request,Integer userId,Integer paymentType,HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		if(SysUtil.isNull(userId)){
			json.put("code", -1);
			json.put("msg", "用户userId不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		if(SysUtil.isNull(paymentType)){
			json.put("code", -1);
			json.put("msg", "用户提现类型不能为空！");
			JsonUtil.writeJson(json, response);
			return;
		}
		
		try {
			
			Map<String, Object> userPayInfo = accountDao.selUserPayInfo(userId,paymentType);
			
			json.put("userPayInfo", userPayInfo);
			
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
	

}
