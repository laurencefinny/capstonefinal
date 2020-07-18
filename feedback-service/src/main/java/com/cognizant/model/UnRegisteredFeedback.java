package com.cognizant.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UnRegisteredFeedback
 */
@Validated
@Table("un_reg")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
public class UnRegisteredFeedback {
	@JsonProperty("id")
	@Id
	private Integer id = null;

	@JsonProperty("comment")
	private String comment = null;

	@JsonProperty("event_event_id")
	private String eventEventId = null;

	public UnRegisteredFeedback comment(String comment) {
		this.comment = comment;
		return this;
	}

	/**
	 * Get comment
	 * 
	 * @return comment
	 **/
	@ApiModelProperty(value = "")

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UnRegisteredFeedback eventEventId(String eventEventId) {
		this.eventEventId = eventEventId;
		return this;
	}

	/**
	 * Get eventEventId
	 * 
	 * @return eventEventId
	 **/
	@ApiModelProperty(value = "")

	public String getEventEventId() {
		return eventEventId;
	}

	public void setEventEventId(String eventEventId) {
		this.eventEventId = eventEventId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UnRegisteredFeedback unRegisteredFeedback = (UnRegisteredFeedback) o;
		return Objects.equals(this.comment, unRegisteredFeedback.comment)
				&& Objects.equals(this.eventEventId, unRegisteredFeedback.eventEventId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, eventEventId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UnRegisteredFeedback {\n");

		sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
		sb.append("    eventEventId: ").append(toIndentedString(eventEventId)).append("\n");
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
