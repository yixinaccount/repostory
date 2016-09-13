package cn.shendiao.user.model;

import java.io.Serializable;

public class LoginValidCode implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	private Integer id;
	private String tel;
	private String valid_code;
	private Integer state;
	private String create_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getValid_code() {
		return valid_code;
	}
	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	

}
