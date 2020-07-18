package com.cognizant.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Questions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
public class Questions {
	@JsonProperty("id")
	@Id
	private Integer id = null;

	@JsonProperty("question")
	private String question = null;

	@JsonProperty("fb_type")
	private String fbType = null;

	public Questions id(Integer id) {
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

	public Questions question(String question) {
		this.question = question;
		return this;
	}

	/**
	 * Get question
	 * 
	 * @return question
	 **/
	@ApiModelProperty(value = "")

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Questions fbType(String fbType) {
		this.fbType = fbType;
		return this;
	}

	/**
	 * Get fbType
	 * 
	 * @return fbType
	 **/
	@ApiModelProperty(value = "")

	public String getFbType() {
		return fbType;
	}

	public void setFbType(String fbType) {
		this.fbType = fbType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Questions questions = (Questions) o;
		return Objects.equals(this.id, questions.id) && Objects.equals(this.question, questions.question)
				&& Objects.equals(this.fbType, questions.fbType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, question, fbType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Questions {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    question: ").append(toIndentedString(question)).append("\n");
		sb.append("    fbType: ").append(toIndentedString(fbType)).append("\n");
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
