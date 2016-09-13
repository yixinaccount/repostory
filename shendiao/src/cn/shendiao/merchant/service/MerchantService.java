package cn.shendiao.merchant.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shendiao.merchant.dao.MerchantDao;


@Service("merchantService")
public class MerchantService {
	
	@Resource
	private MerchantDao merchantDao;
	

}
