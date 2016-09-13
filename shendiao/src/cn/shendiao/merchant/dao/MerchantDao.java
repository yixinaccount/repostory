package cn.shendiao.merchant.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.dao.DaoSupport;
import cn.shendiao.merchant.model.Merchant;
import cn.shendiao.util.SysUtil;

@Repository("merchantDao")
public class MerchantDao extends DaoSupport{
	
	/**
	 * 查询机构列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selMerchantList( Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("MerchantMapper.selMerchantList", map);
		
	}
	
	
	/**
	 * 查询商家总数
	 * @return
	 * @throws Exception
	 */
	public Integer selMerchantCount() throws Exception {
		
		return (Integer) findForObject("MerchantMapper.selMerchantCount", null);
		
	}
	
	/**
	 * 查询商家类型列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selMerTypeList() throws Exception {
		
		return (List<Map<String, Object>>) findForList("MerchantMapper.selMerTypeList", null);
		
	}
	
	
	/**
	 * 添加商家信息
	 * @param merchant
	 * @throws Exception
	 */
	public void addMerchant(Merchant merchant) throws Exception {
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		merchant.setCreate_date(date);
		
		merchant.setLast_mod(date);
		
		merchant.setState(1);
		
		save("MerchantMapper.addMerchant", merchant);
		
		
	}
	
	
	/**
	 * 查询商家门店总数
	 * @param merchantId
	 * @return
	 * @throws Exception
	 */
	public Integer selMerShopCount(Integer merchantId) throws Exception {
		
		return (Integer) findForObject("MerchantMapper.selMerShopCount", merchantId);
		
	}
	
	/**
	 * 查询任务门店列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selMerShopList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("MerchantMapper.selMerShopList", map);
		
	}
	
	

}
