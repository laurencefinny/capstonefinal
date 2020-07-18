package com.cognizant.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Answers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
public class Answers {
	@JsonProperty("id")
	@Id
	private Integer id = null;

	@JsonProperty("answers")
	private String answers = null;

	@JsonProperty("questions_id")
	private Integer questionsId = null;

	public Answers id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Answers answers(String answers) {
		this.answers = answers;
		return this;
	}

	/**
	 * Get answers
	 * 
	 * @return answers
	 **/
	@ApiModelProperty(value = "")

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Answers questionsId(Integer questionsId) {
		this.questionsId = questionsId;
		return this;
	}

	/**
	 * Get questionsId
	 * 
	 * @return questionsId
	 **/
	@ApiModelProperty(value = "")

	public Integer getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(Integer questionsId) {
		this.questionsId = questionsId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Answers answers = (Answers) o;
		return Objects.equals(this.id, answers.id) && Objects.equals(this.answers, answers.answers)
				&& Objects.equals(this.questionsId, answers.questionsId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, answers, questionsId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Answers {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
		sb.append("    questionsId: ").append(toIndentedString(questionsId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
