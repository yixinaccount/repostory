package cn.shendiao.login.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.shendiao.constant.PermissionConstant;
import cn.shendiao.login.model.Operator;
import cn.shendiao.login.service.OperatorService;
import cn.shendiao.util.SysUtil;




@Controller
public class LoginController {
	protected final transient Logger log = Logger
			.getLogger(LoginController.class);
	@Resource
	private OperatorService operatorService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	
	public ModelAndView loginAction(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpSession session, HttpServletResponse resp, Model model,
			HttpServletRequest request) {
		log.info("loginaction");
		session.removeAttribute(PermissionConstant.CURRENT_USER);
		Operator op = null;
		try {
			Operator tmpop=new Operator();
			tmpop.setLogin_name(username);
			password = SysUtil.md5(password);
			tmpop.setLogin_pwd(password);
			op = operatorService.getUserInfo(tmpop).get(0);
			log.info(op.getOperator_id() + " " + op.getOperator_name() + " " + op.getLogin_name()
					+ " " + op.getLogin_pwd());
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(e.getMessage());
		}
		ModelAndView view = null;
		if (op == null) {
			view = new ModelAndView();
			view.setViewName("login");
			view.addObject(PermissionConstant.LOGIN_MESSAGE, "用户密码不正确");
			//model.addAttribute(PermissionConstant.LOGIN_MESSAGE, "用户名不正确");用于return String 路径
			return view;
		} else {
			session.setAttribute(PermissionConstant.CURRENT_USER, op);
		}
	
		view = new ModelAndView("forward:page/index.html");
		view.addObject(PermissionConstant.LOGIN_MESSAGE, "登录成功");
		return view;
	}
	
	@RequestMapping(value = "/first", method = RequestMethod.POST)
	public String first(Model model)throws Exception{
		
		return "page/index";
	}
	
	@RequestMapping("/welcome")
	public String welcome()throws Exception{
		
		return "aa";
	}
	
	@RequestMapping("/aaa")
	public String aa()throws Exception{
		
		return "frameset";
	}
	
	/*//退出系统,退出成功跳转到登录页面
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		
		//清除session
		session.invalidate();
		return View.redirect("login.action");
	}*/
	
	
	/*@RequestMapping(value = "/changepwd.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changepw(ModelMap modelMap, 
			//@RequestParam(value = "oldpw") String oldpw,
			//@RequestParam(value = "newpw") String newpw,
			//@RequestParam(value = "renewpw") String renewpw,
			HttpServletResponse response,HttpServletRequest request,HttpSession session){
		String oldpw=request.getParameter("oldpw");
		String newpw=request.getParameter("newpw");
		log.info(oldpw+" "+newpw);
		try {
			Operator op=(Operator)session.getAttribute(PermissionConstant.CURRENT_USER);
			
			if(op.getPassword().equals(oldpw)){
				OperatorDao.changepwd(op.getId(), newpw);
				modelMap.put("success",true);
				modelMap.put("successMsg","修改成功");
			}else{
				modelMap.put("errorMsg","修改失败!原密码错�?");
			}
			
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
			log.error(e.getMessage());
				
				modelMap.put("errorMsg","修改失败!数据库错�?");
			}
			
			return modelMap;
	}*/
	
	
	
/*	@RequestMapping(params = "method=chkout")
	@ResponseBody
	public  ModelAndView  checkout( HttpServletResponse response,HttpServletRequest request) {
		log.info("checkout");
		//ModelAndView view =new ModelAndView();
		
		ModelAndView view = new ModelAndView("redirect:"+PropertiesUtil.getString("logouturl"));
		request.getSession().invalidate();
		//view.setViewName("login");
		return view;
		

	}*/
	
	public static void main(String[] args) throws Exception {
		String md5 = SysUtil.md5("12");
		System.out.println(md5);
	}
}
