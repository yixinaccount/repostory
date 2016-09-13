package cn.shendiao.account.model;

public class UserAccountbookChange {
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer accountbook_id;
	private Integer account_id;
	private Integer user_id;
	private Integer change_type;
	
	private Double income;
	private Double expend;
	private Double balance;
	
	private String descs;
	
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
	public Integer getChange_type() {
		return change_type;
	}
	public void setChange_type(Integer change_type) {
		this.change_type = change_type;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getExpend() {
		return expend;
	}
	public void setExpend(Double expend) {
		this.expend = expend;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
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
