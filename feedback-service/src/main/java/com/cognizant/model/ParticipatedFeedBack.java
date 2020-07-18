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
 * ParticipatedFeedBack
 */
@Validated
@Table("participated")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
public class ParticipatedFeedBack {
	@JsonProperty("id")
	@Id
	private Integer id = null;

	@JsonProperty("likes")
	private String likes = null;

	@JsonProperty("dislike")
	private String dislike = null;

	@JsonProperty("rating")
	private String rating = null;

	@JsonProperty("event_event_id")
	private String eventEventId = null;

	public ParticipatedFeedBack likes(String likes) {
		this.likes = likes;
		return this;
	}

	/**
	 * Get likes
	 * 
	 * @return likes
	 **/
	@ApiModelProperty(value = "")

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public ParticipatedFeedBack dislike(String dislike) {
		this.dislike = dislike;
		return this;
	}

	/**
	 * Get dislike
	 * 
	 * @return dislike
	 **/
	@ApiModelProperty(value = "")

	public String getDislike() {
		return dislike;
	}

	public void setDislike(String dislike) {
		this.dislike = dislike;
	}

	public ParticipatedFeedBack rating(String rating) {
		this.rating = rating;
		return this;
	}

	/**
	 * Get rating
	 * 
	 * @return rating
	 **/
	@ApiModelProperty(value = "")

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public ParticipatedFeedBack eventEventId(String eventEventId) {
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
		ParticipatedFeedBack participatedFeedBack = (ParticipatedFeedBack) o;
		return Objects.equals(this.likes, participatedFeedBack.likes)
				&& Objects.equals(this.dislike, participatedFeedBack.dislike)
				&& Objects.equals(this.rating, participatedFeedBack.rating)
				&& Objects.equals(this.eventEventId, participatedFeedBack.eventEventId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(likes, dislike, rating, eventEventId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ParticipatedFeedBack {\n");

		sb.append("    likes: ").append(toIndentedString(likes)).append("\n");
		sb.append("    dislike: ").append(toIndentedString(dislike)).append("\n");
		sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
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
