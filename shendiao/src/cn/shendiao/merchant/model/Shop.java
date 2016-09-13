package cn.shendiao.merchant.model;

import java.io.Serializable;

public class Shop implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer shop_id;
	private Integer merchant_id;
	
	
	private String shop_name;
	private String short_name;
	
	private Integer std_addr_id;
	
	private String shop_address;
	private String shop_info;
	private String shop_num;
	private String longitude;
	private String latitude;
	
	
	private Integer state;
	
	private String remark;
	private String create_date;
	private String last_mod;
	
	
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public Integer getStd_addr_id() {
		return std_addr_id;
	}
	public void setStd_addr_id(Integer std_addr_id) {
		this.std_addr_id = std_addr_id;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getShop_info() {
		return shop_info;
	}
	public void setShop_info(String shop_info) {
		this.shop_info = shop_info;
	}
	
	public String getShop_num() {
		return shop_num;
	}
	public void setShop_num(String shop_num) {
		this.shop_num = shop_num;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
