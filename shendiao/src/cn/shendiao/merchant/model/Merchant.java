package cn.shendiao.merchant.model;

import java.io.Serializable;

public class Merchant implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer merchant_id;
	private Integer merchant_type;
	
	
	private String merchant_name;
	private String merchant_shortname;
	private String merchar_info;
	private String merchar_desc;
	private String icon;
	private String address;
	private String owner_name;
	private String phone;
	private String post_code;
	
	private Integer corp_type;
	private Integer state;
	
	private String remark;
	private String create_date;
	private String last_mod;
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public Integer getMerchant_type() {
		return merchant_type;
	}
	public void setMerchant_type(Integer merchant_type) {
		this.merchant_type = merchant_type;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_shortname() {
		return merchant_shortname;
	}
	public void setMerchant_shortname(String merchant_shortname) {
		this.merchant_shortname = merchant_shortname;
	}
	public String getMerchar_info() {
		return merchar_info;
	}
	public void setMerchar_info(String merchar_info) {
		this.merchar_info = merchar_info;
	}
	public String getMerchar_desc() {
		return merchar_desc;
	}
	public void setMerchar_desc(String merchar_desc) {
		this.merchar_desc = merchar_desc;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public Integer getCorp_type() {
		return corp_type;
	}
	public void setCorp_type(Integer corp_type) {
		this.corp_type = corp_type;
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
