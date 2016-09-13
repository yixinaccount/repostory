package cn.shendiao.office.model;

import java.io.Serializable;

public class UcaPaper implements Serializable{

	private static final long serialVersionUID = 3603621619954853691L;
	
	private Integer id;
	private Integer user_id;
	private Integer usercase_id;
	private Integer task_id;
	private Integer paper_id;
	private Integer question_id;
	
	private String select_answer;
	private String text_answer;
	private String photo_answer;
	
	
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
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public Integer getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public String getSelect_answer() {
		return select_answer;
	}
	public void setSelect_answer(String select_answer) {
		this.select_answer = select_answer;
	}
	public String getText_answer() {
		return text_answer;
	}
	public void setText_answer(String text_answer) {
		this.text_answer = text_answer;
	}
	public String getPhoto_answer() {
		return photo_answer;
	}
	public void setPhoto_answer(String photo_answer) {
		this.photo_answer = photo_answer;
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
