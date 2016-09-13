package cn.shendiao.office.model;

import java.io.Serializable;

public class UcaSign implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer user_id;
	private Integer usercase_id;
	
	private String sign_in_date;
	private String sign_in_longitude;
	private String sign_in_latitude;
	private String sign_out_date;
	private String sign_out_longitude;
	private String sign_out_latitude;
	
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getUsercase_id() {
		return usercase_id;
	}
	public void setUsercase_id(Integer usercase_id) {
		this.usercase_id = usercase_id;
	}
	public String getSign_in_date() {
		return sign_in_date;
	}
	public void setSign_in_date(String sign_in_date) {
		this.sign_in_date = sign_in_date;
	}
	public String getSign_in_longitude() {
		return sign_in_longitude;
	}
	public void setSign_in_longitude(String sign_in_longitude) {
		this.sign_in_longitude = sign_in_longitude;
	}
	public String getSign_in_latitude() {
		return sign_in_latitude;
	}
	public void setSign_in_latitude(String sign_in_latitude) {
		this.sign_in_latitude = sign_in_latitude;
	}
	public String getSign_out_date() {
		return sign_out_date;
	}
	public void setSign_out_date(String sign_out_date) {
		this.sign_out_date = sign_out_date;
	}
	public String getSign_out_longitude() {
		return sign_out_longitude;
	}
	public void setSign_out_longitude(String sign_out_longitude) {
		this.sign_out_longitude = sign_out_longitude;
	}
	public String getSign_out_latitude() {
		return sign_out_latitude;
	}
	public void setSign_out_latitude(String sign_out_latitude) {
		this.sign_out_latitude = sign_out_latitude;
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
