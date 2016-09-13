package cn.shendiao.task.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.shendiao.dao.DaoSupport;

@Repository("taskDao")
public class TaskDao extends DaoSupport{
	
	
	/**
	 * 查询案件试题
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskQuesList(Integer taskId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskQuesList", taskId);
		
	}
	
	/**
	 * 查询案件试题选项
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskQuesItemList(Integer taskId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskQuesItemList", taskId);
		
	}

	/**
	 * 查询任务列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskList", map);
		
	}
	
	
	/**
	 * 查询任务详情
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selTaskDetailByTaskId(Integer taskId) throws Exception {
		
		return (Map<String, Object>) findForObject("TaskMapper.selTaskDetailByTaskId", taskId);
		
	}
	
	/**
	 * 查询任务培训列表
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskTrainByTaskId(Integer taskId,Integer userId) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("taskId", taskId);
		map.put("userId", userId);
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskTrainByTaskId",map);
		
	}
	
	/**
	 * 查询任务条件
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskCondition(Integer taskId) throws Exception{
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskCondition", taskId);
		
	}
	
	/**
	 * 查询商家信息
	 * @param merchantId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selMerchantDetail(Integer merchantId) throws Exception{
		
		return (Map<String, Object>) findForObject("TaskMapper.selMerchantDetail", merchantId);
	
	}
	
	/**
	 * 查询任务省列表
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskProvince(Integer taskId) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskProvince", taskId);
		
	}
	
	/**
	 * 查询任务市列表
	 * @param parentAddrCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskCity(Integer parentAddrCode) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskCity", parentAddrCode);
		
	}
	
	/**
	 * 查询任务区列表
	 * @param parentAddrCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskCounty(Integer parentAddrCode) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskCounty", parentAddrCode);
		
	}
	

	/**
	 * 查询门店任务列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selTaskMerList(Map<String,Object> map) throws Exception {
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selTaskMerList", map);
		
	}
	
	/**
	 * 更新案件门店状态
	 * @param caseId
	 * @param state
	 * @throws Exception
	 */
	public void updateTaskShopState(Integer caseId,Integer state) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("caseId", caseId);
		map.put("state", state);
		
		update("TaskMapper.updateTaskShopState", map);
		
	}
	
	/**
	 * 查询门店任务详情
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selShopTaskDetail(Integer caseId) throws Exception {
		
		return (Map<String, Object>) findForObject("TaskMapper.selShopTaskDetail", caseId);
	
	}
	
	/**
	 * 查询任务门店信息
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selTaskShopInfo(Integer caseId) throws Exception {
		
		return (Map<String, Object>) findForObject("TaskMapper.selTaskShopInfo", caseId);
		
	}
	
	/**
	 * 查询任务信息
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selTaskInfo(Integer taskId) throws Exception {
		
		return (Map<String, Object>) findForObject("TaskMapper.selTaskInfo", taskId);
		
	}
	
	
	/**
	 * 查询案件审核总数
	 * @return
	 * @throws Exception
	 */
	public Integer selectCaseCheckCount() throws Exception {
		
		return (Integer) findForObject("TaskMapper.selectCaseCheckCount", null);
		
	}
	
	
	/**
	 * 查询案件审核列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectCaseCheckList(Map<String,Object> map) throws Exception {
		
		
		return (List<Map<String, Object>>) findForList("TaskMapper.selectCaseCheckList", map);
		
	}
	
	
	/**
	 * 查询任务审核信息
	 * @param checkCaseId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selCheckCaseInfo(Integer checkCaseId) throws Exception {
		
		return (Map<String, Object>) findForObject("TaskMapper.selCheckCaseInfo", checkCaseId);
		
	}
	
	/**
	 * 更新任务审核状态
	 * @param checkCaseId
	 * @param state
	 * @param operatorId
	 * @param checkContent
	 * @throws Exception
	 */
	public void updateCheckCaseState(Integer checkCaseId,Integer state,Integer operatorId,String checkContent) throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
			map.put("checkCaseId", checkCaseId);
			map.put("state", state);
			map.put("operatorId", operatorId);
			map.put("checkContent", checkContent);
		
		update("TaskMapper.updateCheckCaseState", map);
		
	}
	
	
	
	
	
}
