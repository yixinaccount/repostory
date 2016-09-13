package cn.shendiao.merchant.controller;

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
import org.springframework.web.multipart.MultipartFile;

import cn.shendiao.constant.PermissionConstant;
import cn.shendiao.login.model.Operator;
import cn.shendiao.merchant.dao.MerchantDao;
import cn.shendiao.merchant.model.Merchant;
import cn.shendiao.merchant.model.Shop;
import cn.shendiao.merchant.service.MerchantService;
import cn.shendiao.util.ImageUtil;
import cn.shendiao.util.JsonUtil;
import cn.shendiao.util.SysUtil;


@Controller
@RequestMapping("merchant")
public class MerchantController {
	
	protected final transient Logger log = Logger
			.getLogger(MerchantController.class);
	
	
	@Resource
	private MerchantDao merchantDao;
	
	@Resource
	private MerchantService merchantService;
	
	
	
	
	
					/********后台*************/
	
	
	
	/**
	 * 商家首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("merchantIndex")
	public String merchantIndex(HttpServletRequest request,HttpServletResponse response) {
		
		return "merchant/merchantList";
		
	}
	
	
	/**
	 * 查询商家列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("selMerchantList")
	public void selMerchantList(HttpServletRequest request,Integer page,Integer rows,HttpServletResponse response) {
		
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
				
				json.put("total", merchantDao.selMerchantCount());
				
				json.put("rows",merchantDao.selMerchantList(map));
				
				
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
	 * 跳转添加商家页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addMergoto")
	public String addMergoto(HttpServletRequest request,HttpServletResponse response) {
		
		
		try {
			
			List<Map<String, Object>> merTypeList = merchantDao.selMerTypeList();
			
			request.setAttribute("merTypeList", merTypeList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "merchant/addMerchant";
		
	}
	
	
	/**
	 * 添加商家
	 * @param request
	 * @param merchant
	 * @param merchantType
	 * @param multipartFile
	 * @param response
	 */
	@RequestMapping("addMerchant")
	public void addMerchant(HttpServletRequest request,Merchant merchant,String merchantType,MultipartFile multipartFile,HttpServletResponse response){
		
		
		Operator op = (Operator) request.getSession().getAttribute(PermissionConstant.CURRENT_USER);
		
		JSONObject json = new JSONObject();
		if(SysUtil.isNull(op)){
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "用户没有登录，请登录后操作！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		if(SysUtil.isNull(multipartFile)){
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "还没有上传比赛图片！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		Map<String, Object> map = ImageUtil.uploadMer(request,"icon",multipartFile);
		Integer state = (Integer) map.get("state");
		
		if(state == 0){
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "上传图片失败！");
			JsonUtil.writeJson(json, response);
			return ;
		}
		
		
		String act_pic =  map.get("picture").toString();
		
		merchant.setIcon(act_pic);
		
		
		try {
			
			merchantDao.addMerchant(merchant);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			json.put("code", SysUtil.FAILURE);
			json.put("msg", "操作失败");
			JsonUtil.writeJson(json, response);
			
		}
		
		
		json.put("code", SysUtil.SUCCESS);
		json.put("msg", "成功");
		JsonUtil.writeJson(json, response);
		
		
	}
	
	
	/**
	 * 跳转添加商家任务页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addMerTaskgoto")
	public String addMerTaskgoto(HttpServletRequest request,Integer merchantId,HttpServletResponse response) {
		
		
		if (SysUtil.isNull(merchantId)) {
			return "forward:404.jsp";
		}
		
		try {
			
			request.setAttribute("merchantId", merchantId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "merchant/addMerTask";
		
	}
	
	
	
	
	/**
	 * 跳转门店列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("shopgoto")
	public String shopgoto(HttpServletRequest request,Integer merchantId,HttpServletResponse response) {
		
		request.setAttribute("merchantId", merchantId);
		
		return "merchant/shopList";
		
	}
	
	
	/**
	 * 查询门店列表
	 * @param request
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping("selShopList")
	public void selShopList(HttpServletRequest request,Integer merchantId,Integer page,Integer rows,HttpServletResponse response) {
		
			JSONObject json = new JSONObject();
			
			if(SysUtil.isNull(merchantId)){
				
				json.put("code", SysUtil.FAILURE);
				json.put("msg", "商家id不能为空！");
				JsonUtil.writeJson(json, response);
				return ;
				
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
		
			map.put("merchantId", merchantId);
			
			try {
				
				json.put("total", merchantDao.selMerShopCount(merchantId));
				
				json.put("rows",merchantDao.selMerShopList(map));
				
				
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
	 * 添加商家门店
	 * @param request
	 * @param shop
	 * @param response
	 */
	@RequestMapping("addMerShop")
	public void addMerShop(HttpServletRequest request,Shop shop,HttpServletResponse response) {
		
		
		
		
	}
	
	
	
	
	
	
}
