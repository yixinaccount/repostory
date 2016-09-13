package cn.shendiao.user.model;

import java.io.Serializable;

public class UserAddition implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer user_id;
	
	private String IDCard_front_pic;
	private String IDCard_back_pic;
	
	private String education_front_pic;
	private String education_back_pic;
	
	private String addition_1;
	private String addition_2;
	private String addition_3;
	private String addition_4;
	private String addition_5;
	private String addition_6;
	
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
	public String getIDCard_front_pic() {
		return IDCard_front_pic;
	}
	public void setIDCard_front_pic(String iDCard_front_pic) {
		IDCard_front_pic = iDCard_front_pic;
	}
	public String getIDCard_back_pic() {
		return IDCard_back_pic;
	}
	public void setIDCard_back_pic(String iDCard_back_pic) {
		IDCard_back_pic = iDCard_back_pic;
	}
	public String getEducation_front_pic() {
		return education_front_pic;
	}
	public void setEducation_front_pic(String education_front_pic) {
		this.education_front_pic = education_front_pic;
	}
	public String getEducation_back_pic() {
		return education_back_pic;
	}
	public void setEducation_back_pic(String education_back_pic) {
		this.education_back_pic = education_back_pic;
	}
	public String getAddition_1() {
		return addition_1;
	}
	public void setAddition_1(String addition_1) {
		this.addition_1 = addition_1;
	}
	public String getAddition_2() {
		return addition_2;
	}
	public void setAddition_2(String addition_2) {
		this.addition_2 = addition_2;
	}
	public String getAddition_3() {
		return addition_3;
	}
	public void setAddition_3(String addition_3) {
		this.addition_3 = addition_3;
	}
	public String getAddition_4() {
		return addition_4;
	}
	public void setAddition_4(String addition_4) {
		this.addition_4 = addition_4;
	}
	public String getAddition_5() {
		return addition_5;
	}
	public void setAddition_5(String addition_5) {
		this.addition_5 = addition_5;
	}
	public String getAddition_6() {
		return addition_6;
	}
	public void setAddition_6(String addition_6) {
		this.addition_6 = addition_6;
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
