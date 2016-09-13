package cn.shendiao.office.model;

import java.io.Serializable;

public class UcaCheckUsercase implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer usercase_id;
	private Integer usercase_state;
	private Integer operator_id;
	
	private String content;
	
	
	private Integer state;
	
	
	private String remark;
	private String create_date;
	private String last_mod;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUsercase_id() {
		return usercase_id;
	}
	public void setUsercase_id(Integer usercase_id) {
		this.usercase_id = usercase_id;
	}
	public Integer getUsercase_state() {
		return usercase_state;
	}
	public void setUsercase_state(Integer usercase_state) {
		this.usercase_state = usercase_state;
	}
	public Integer getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getLast_mod() {
		return last_mod;
	}
	public void setLast_mod(String last_mod) {
		this.last_mod = last_mod;
	}
	


}
