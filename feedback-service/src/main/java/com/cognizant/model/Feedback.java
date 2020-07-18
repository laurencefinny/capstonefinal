package com.cognizant.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Feedback
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
public class Feedback {
	@JsonProperty("ParticipatedFeedBack")
	private List<ParticipatedFeedBack> participatedFeedBack = null;

	@JsonProperty("NotParticipated")
	private List<NotParticipatedFeedBack> notParticipated = null;

	@JsonProperty("Unregistered")
	private List<UnRegisteredFeedback> unregistered = null;

	public Feedback participatedFeedBack(List<ParticipatedFeedBack> participatedFeedBack) {
		this.participatedFeedBack = participatedFeedBack;
		return this;
	}

	/**
	 * Get participatedFeedBack
	 * 
	 * @return participatedFeedBack
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public List<ParticipatedFeedBack> getParticipatedFeedBack() {
		return participatedFeedBack;
	}

	public void setParticipatedFeedBack(List<ParticipatedFeedBack> participatedFeedBack) {
		this.participatedFeedBack = participatedFeedBack;
	}

	public Feedback notParticipated(List<NotParticipatedFeedBack> notParticipated) {
		this.notParticipated = notParticipated;
		return this;
	}

	/**
	 * Get notParticipated
	 * 
	 * @return notParticipated
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public List<NotParticipatedFeedBack> getNotParticipated() {
		return notParticipated;
	}

	public void setNotParticipated(List<NotParticipatedFeedBack> notParticipated) {
		this.notParticipated = notParticipated;
	}

	public Feedback unregistered(List<UnRegisteredFeedback> unregistered) {
		this.unregistered = unregistered;
		return this;
	}

	/**
	 * Get unregistered
	 * 
	 * @return unregistered
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public List<UnRegisteredFeedback> getUnregistered() {
		return unregistered;
	}

	public void setUnregistered(List<UnRegisteredFeedback> unregistered) {
		this.unregistered = unregistered;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Feedback feedback = (Feedback) o;
		return Objects.equals(this.participatedFeedBack, feedback.participatedFeedBack)
				&& Objects.equals(this.notParticipated, feedback.notParticipated)
				&& Objects.equals(this.unregistered, feedback.unregistered);
	}

	@Override
	public int hashCode() {
		return Objects.hash(participatedFeedBack, notParticipated, unregistered);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Feedback {\n");

		sb.append("    participatedFeedBack: ").append(toIndentedString(participatedFeedBack)).append("\n");
		sb.append("    notParticipated: ").append(toIndentedString(notParticipated)).append("\n");
		sb.append("    unregistered: ").append(toIndentedString(unregistered)).append("\n");
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
