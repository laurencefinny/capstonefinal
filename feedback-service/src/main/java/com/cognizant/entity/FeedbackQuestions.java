package com.cognizant.entity;

import lombok.Data;

@Data
public class FeedbackQuestions {

	private int id;

	private String question;

	private String fb_type;

	private int tot_ans;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFb_type() {
		return fb_type;
	}

	public void setFb_type(String fb_type) {
		this.fb_type = fb_type;
	}

	public int getTot_ans() {
		return tot_ans;
	}

	public void setTot_ans(int tot_ans) {
		this.tot_ans = tot_ans;
	}
	
	

}