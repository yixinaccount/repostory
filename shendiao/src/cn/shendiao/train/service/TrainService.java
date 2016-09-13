package cn.shendiao.train.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shendiao.train.dao.TrainDao;
import cn.shendiao.train.model.UcaTraining;
import cn.shendiao.util.SysUtil;


@Service("trainService")
public class TrainService {
	
	@Resource
	private TrainDao trainDao;
	
	/**
	 * 插入培训资质
	 * @param trainId
	 * @param userId
	 * @param validateTime
	 * @throws Exception
	 */
	public void insertTrainResult(Integer trainId,Integer userId,Integer validateTime) throws Exception{
		
		UcaTraining ucaTraining = new UcaTraining();
		
		String date = SysUtil.toChar(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		
		Date date1 = SysUtil.nowTimeAddDay(validateTime);
		
		String validate_time = SysUtil.toChar(date1, "yyyy-MM-dd");
		
		ucaTraining.setTraining_id(trainId);
		ucaTraining.setUser_id(userId);
		ucaTraining.setValid_date(validate_time);
		ucaTraining.setState(1);
		ucaTraining.setCreate_date(date);
		ucaTraining.setLast_mod(date);
		
		trainDao.insertTrainResult(ucaTraining);
		
	}
	
		

}
