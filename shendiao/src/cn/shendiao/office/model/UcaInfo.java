package cn.shendiao.office.model;

import java.io.Serializable;


public class UcaInfo implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer usercase_id;
	
	private Integer case_id;
	
	private Integer user_id;
	
	private Integer uca_state;
	
	private Integer last_uca_state;
	
	private Integer state;
	
	private String remark;
	
	private String create_date;
	
	private String last_mod;

	public Integer getUsercase_id() {
		return usercase_id;
	}

	public void setUsercase_id(Integer usercase_id) {
		this.usercase_id = usercase_id;
	}

	public Integer getCase_id() {
		return case_id;
	}

	public void setCase_id(Integer case_id) {
		this.case_id = case_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getUca_state() {
		return uca_state;
	}

	public void setUca_state(Integer uca_state) {
		this.uca_state = uca_state;
	}

	public Integer getLast_uca_state() {
		return last_uca_state;
	}

	public void setLast_uca_state(Integer last_uca_state) {
		this.last_uca_state = last_uca_state;
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
