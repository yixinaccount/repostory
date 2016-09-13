package cn.shendiao.account.model;

public class UserExtractCash {
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer user_id;
	private Integer account_id;
	private Integer accountbook_id;
	private Integer payment_type;
	
	private String payment_code;
	private String payment_info1;
	private String payment_info2;
	
	private Double amount;
	
	private Integer deal_status;
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
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getAccountbook_id() {
		return accountbook_id;
	}
	public void setAccountbook_id(Integer accountbook_id) {
		this.accountbook_id = accountbook_id;
	}
	public Integer getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(Integer payment_type) {
		this.payment_type = payment_type;
	}
	public String getPayment_code() {
		return payment_code;
	}
	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}
	public String getPayment_info1() {
		return payment_info1;
	}
	public void setPayment_info1(String payment_info1) {
		this.payment_info1 = payment_info1;
	}
	public String getPayment_info2() {
		return payment_info2;
	}
	public void setPayment_info2(String payment_info2) {
		this.payment_info2 = payment_info2;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getDeal_status() {
		return deal_status;
	}
	public void setDeal_status(Integer deal_status) {
		this.deal_status = deal_status;
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
