
package cn.shendiao.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shendiao.dao.DaoSupport;
import cn.shendiao.login.model.Operator;



@Service("operatorService")
public class OperatorService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public List<Operator> getUserInfo(Operator op) throws Exception {
		return (List<Operator>) dao.findForList("UserMapper.getUserInfo", op);
		
	}
}
