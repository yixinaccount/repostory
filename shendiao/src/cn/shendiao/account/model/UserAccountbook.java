package cn.shendiao.account.model;

import java.io.Serializable;

public class UserAccountbook implements Serializable{
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer accountbook_id;
	private Integer account_id;
	private Integer user_id;
	private Integer accountitem_type;
	
	private Double balance;
	private Double opening_balance;
	private Integer state;
	
	private String remark;
	private String create_date;
	private String last_mod;
	public Integer getAccountbook_id() {
		return accountbook_id;
	}
	public void setAccountbook_id(Integer accountbook_id) {
		this.accountbook_id = accountbook_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAccountitem_type() {
		return accountitem_type;
	}
	public void setAccountitem_type(Integer accountitem_type) {
		this.accountitem_type = accountitem_type;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getOpening_balance() {
		return opening_balance;
	}
	public void setOpening_balance(Double opening_balance) {
		this.opening_balance = opening_balance;
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
