package cn.shendiao.account.model;

public class UserAccExchange {
	
	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer source_accbook_id;
	private Integer target_accbook_id;
	
	private Double source_amount;
	private Double target_amount;
	
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
	public Integer getSource_accbook_id() {
		return source_accbook_id;
	}
	public void setSource_accbook_id(Integer source_accbook_id) {
		this.source_accbook_id = source_accbook_id;
	}
	public Integer getTarget_accbook_id() {
		return target_accbook_id;
	}
	public void setTarget_accbook_id(Integer target_accbook_id) {
		this.target_accbook_id = target_accbook_id;
	}
	public Double getSource_amount() {
		return source_amount;
	}
	public void setSource_amount(Double source_amount) {
		this.source_amount = source_amount;
	}
	public Double getTarget_amount() {
		return target_amount;
	}
	public void setTarget_amount(Double target_amount) {
		this.target_amount = target_amount;
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
