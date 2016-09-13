package cn.shendiao.train.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.dao.DaoSupport;
import cn.shendiao.train.model.UcaTraining;

@Repository("trainDao")
public class TrainDao extends DaoSupport{
	
	/**
	 * 查询试题列表
	 * @param trainId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selQuestionList(Integer trainId) throws Exception {
		
		
		return (List<Map<String, Object>>) findForList("TrainMapper.selQuestionList", trainId);
		
	}
	
	/**
	 * 查询试题答案列表
	 * @param trainId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selQuestionItemList(Integer trainId) throws Exception {
		
		
		return (List<Map<String, Object>>) findForList("TrainMapper.selQuestionItemList", trainId);
		
	}
	
	/**
	 * 查询培训信息通过培训id
	 * @param trainId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selTrainInfo(Integer trainId) throws Exception {
		
		return (Map<String, Object>) findForObject("TrainMapper.selTrainInfo", trainId);
		
	}
	
	/**
	 * 查询培训文件
	 * @param trainId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTrainFile(Integer trainId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TrainMapper.selTrainFile", trainId);
		
	}
	
	
	/**
	 * 添加培训结果
	 * @param ucaTraining
	 * @throws Exception
	 */
	public void insertTrainResult(UcaTraining ucaTraining) throws Exception {
		
		save("TrainMapper.insertTrainResult", ucaTraining);
		
	}
	
	/**
	 * 查询用户获得培训资质总数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer selTrainCount(Integer userId) throws Exception{
		
		return (Integer) findForObject("TrainMapper.selTrainCount", userId);
		
	}
	
	/**
	 * 查询培训的商家列表信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserTrainMer(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TrainMapper.selUserTrainMer", map);
		
	}
	
	/**
	 * 查询用户商家培训列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selUserMerTrain(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TrainMapper.selUserMerTrain", map);
		
	}
	

}
