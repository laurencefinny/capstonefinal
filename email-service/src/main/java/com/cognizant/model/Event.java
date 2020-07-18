package com.cognizant.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Event
 */
@Validated
@Table("event")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-02-25T09:06:38.313+05:30[Asia/Calcutta]")
public class Event {
	@JsonProperty("id")
	@Id
	private Integer id = null;

	@JsonProperty("event_id")
	private String eventId = null;

	@JsonProperty("month")
	private String month = null;

	@JsonProperty("base_location")
	private String baseLocation = null;

	@JsonProperty("beneficiary_name")
	private String beneficiaryName = null;

	@JsonProperty("venture_address")
	private String ventureAddress = null;

	@JsonProperty("council_name")
	private String councilName = null;

	@JsonProperty("project")
	private String project = null;

	@JsonProperty("category")
	private String category = null;

	@JsonProperty("event_name")
	private String eventName = null;

	@JsonProperty("event_description")
	private String eventDescription = null;

	@JsonProperty("event_date")
	private LocalDate eventDate = null;

	@JsonProperty("total_no_of_volunteers")
	private Integer totalNoOfVolunteers = null;

	@JsonProperty("total_volunteer_hours")
	private Integer totalVolunteerHours = null;

	@JsonProperty("total_travel_hours")
	private Integer totalTravelHours = null;

	@JsonProperty("overall_volunteer_hours")
	private Integer overallVolunteerHours = null;

	@JsonProperty("lives_impacted")
	private Integer livesImpacted = null;

	@JsonProperty("activity_type")
	private Integer activityType = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("poc_id")
	private Integer pocId = null;

	@JsonProperty("poc_name")
	private String pocName = null;

	@JsonProperty("poc_contact")
	private Long pocContact = null;

	public Event eventId(String eventId) {
		this.eventId = eventId;
		return this;
	}

	/**
	 * Get eventId
	 * 
	 * @return eventId
	 **/
	@ApiModelProperty(value = "")

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Event month(String month) {
		this.month = month;
		return this;
	}

	/**
	 * Get month
	 * 
	 * @return month
	 **/
	@ApiModelProperty(value = "")

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Event baseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
		return this;
	}

	/**
	 * Get baseLocation
	 * 
	 * @return baseLocation
	 **/
	@ApiModelProperty(value = "")

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public Event beneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
		return this;
	}

	/**
	 * Get beneficiaryName
	 * 
	 * @return beneficiaryName
	 **/
	@ApiModelProperty(value = "")

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Event ventureAddress(String ventureAddress) {
		this.ventureAddress = ventureAddress;
		return this;
	}

	/**
	 * Get ventureAddress
	 * 
	 * @return ventureAddress
	 **/
	@ApiModelProperty(value = "")

	public String getVentureAddress() {
		return ventureAddress;
	}

	public void setVentureAddress(String ventureAddress) {
		this.ventureAddress = ventureAddress;
	}

	public Event councilName(String councilName) {
		this.councilName = councilName;
		return this;
	}

	/**
	 * Get councilName
	 * 
	 * @return councilName
	 **/
	@ApiModelProperty(value = "")

	public String getCouncilName() {
		return councilName;
	}

	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}

	public Event project(String project) {
		this.project = project;
		return this;
	}

	/**
	 * Get project
	 * 
	 * @return project
	 **/
	@ApiModelProperty(value = "")

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Event category(String category) {
		this.category = category;
		return this;
	}

	/**
	 * Get category
	 * 
	 * @return category
	 **/
	@ApiModelProperty(value = "")

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Event eventName(String eventName) {
		this.eventName = eventName;
		return this;
	}

	/**
	 * Get eventName
	 * 
	 * @return eventName
	 **/
	@ApiModelProperty(value = "")

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Event eventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
		return this;
	}

	/**
	 * Get eventDescription
	 * 
	 * @return eventDescription
	 **/
	@ApiModelProperty(value = "")

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Event eventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
		return this;
	}

	/**
	 * Get eventDate
	 * 
	 * @return eventDate
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public Event totalNoOfVolunteers(Integer totalNoOfVolunteers) {
		this.totalNoOfVolunteers = totalNoOfVolunteers;
		return this;
	}

	/**
	 * Get totalNoOfVolunteers
	 * 
	 * @return totalNoOfVolunteers
	 **/
	@ApiModelProperty(value = "")

	public Integer getTotalNoOfVolunteers() {
		return totalNoOfVolunteers;
	}

	public void setTotalNoOfVolunteers(Integer totalNoOfVolunteers) {
		this.totalNoOfVolunteers = totalNoOfVolunteers;
	}

	public Event totalVolunteerHours(Integer totalVolunteerHours) {
		this.totalVolunteerHours = totalVolunteerHours;
		return this;
	}

	/**
	 * Get totalVolunteerHours
	 * 
	 * @return totalVolunteerHours
	 **/
	@ApiModelProperty(value = "")

	public Integer getTotalVolunteerHours() {
		return totalVolunteerHours;
	}

	public void setTotalVolunteerHours(Integer totalVolunteerHours) {
		this.totalVolunteerHours = totalVolunteerHours;
	}

	public Event totalTravelHours(Integer totalTravelHours) {
		this.totalTravelHours = totalTravelHours;
		return this;
	}

	/**
	 * Get totalTravelHours
	 * 
	 * @return totalTravelHours
	 **/
	@ApiModelProperty(value = "")

	public Integer getTotalTravelHours() {
		return totalTravelHours;
	}

	public void setTotalTravelHours(Integer totalTravelHours) {
		this.totalTravelHours = totalTravelHours;
	}

	public Event overallVolunteerHours(Integer overallVolunteerHours) {
		this.overallVolunteerHours = overallVolunteerHours;
		return this;
	}

	/**
	 * Get overallVolunteerHours
	 * 
	 * @return overallVolunteerHours
	 **/
	@ApiModelProperty(value = "")

	public Integer getOverallVolunteerHours() {
		return overallVolunteerHours;
	}

	public void setOverallVolunteerHours(Integer overallVolunteerHours) {
		this.overallVolunteerHours = overallVolunteerHours;
	}

	public Event livesImpacted(Integer livesImpacted) {
		this.livesImpacted = livesImpacted;
		return this;
	}

	/**
	 * Get livesImpacted
	 * 
	 * @return livesImpacted
	 **/
	@ApiModelProperty(value = "")

	public Integer getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(Integer livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public Event activityType(Integer activityType) {
		this.activityType = activityType;
		return this;
	}

	/**
	 * Get activityType
	 * 
	 * @return activityType
	 **/
	@ApiModelProperty(value = "")

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Event status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "")

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Event pocId(Integer pocId) {
		this.pocId = pocId;
		return this;
	}

	/**
	 * Get pocId
	 * 
	 * @return pocId
	 **/
	@ApiModelProperty(value = "")

	public Integer getPocId() {
		return pocId;
	}

	public void setPocId(Integer pocId) {
		this.pocId = pocId;
	}

	public Event pocName(String pocName) {
		this.pocName = pocName;
		return this;
	}

	/**
	 * Get pocName
	 * 
	 * @return pocName
	 **/
	@ApiModelProperty(value = "")

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public Event pocContact(Long pocContact) {
		this.pocContact = pocContact;
		return this;
	}

	/**
	 * Get pocContact
	 * 
	 * @return pocContact
	 **/
	@ApiModelProperty(value = "")

	public Long getPocContact() {
		return pocContact;
	}

	public void setPocContact(Long pocContact) {
		this.pocContact = pocContact;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Event event = (Event) o;
		return Objects.equals(this.eventId, event.eventId) && Objects.equals(this.month, event.month)
				&& Objects.equals(this.baseLocation, event.baseLocation)
				&& Objects.equals(this.beneficiaryName, event.beneficiaryName)
				&& Objects.equals(this.ventureAddress, event.ventureAddress)
				&& Objects.equals(this.councilName, event.councilName) && Objects.equals(this.project, event.project)
				&& Objects.equals(this.category, event.category) && Objects.equals(this.eventName, event.eventName)
				&& Objects.equals(this.eventDescription, event.eventDescription)
				&& Objects.equals(this.eventDate, event.eventDate)
				&& Objects.equals(this.totalNoOfVolunteers, event.totalNoOfVolunteers)
				&& Objects.equals(this.totalVolunteerHours, event.totalVolunteerHours)
				&& Objects.equals(this.totalTravelHours, event.totalTravelHours)
				&& Objects.equals(this.overallVolunteerHours, event.overallVolunteerHours)
				&& Objects.equals(this.livesImpacted, event.livesImpacted)
				&& Objects.equals(this.activityType, event.activityType) && Objects.equals(this.status, event.status)
				&& Objects.equals(this.pocId, event.pocId) && Objects.equals(this.pocName, event.pocName)
				&& Objects.equals(this.pocContact, event.pocContact);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, month, baseLocation, beneficiaryName, ventureAddress, councilName, project,
				category, eventName, eventDescription, eventDate, totalNoOfVolunteers, totalVolunteerHours,
				totalTravelHours, overallVolunteerHours, livesImpacted, activityType, status, pocId, pocName,
				pocContact);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Event {\n");

		sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
		sb.append("    month: ").append(toIndentedString(month)).append("\n");
		sb.append("    baseLocation: ").append(toIndentedString(baseLocation)).append("\n");
		sb.append("    beneficiaryName: ").append(toIndentedString(beneficiaryName)).append("\n");
		sb.append("    ventureAddress: ").append(toIndentedString(ventureAddress)).append("\n");
		sb.append("    councilName: ").append(toIndentedString(councilName)).append("\n");
		sb.append("    project: ").append(toIndentedString(project)).append("\n");
		sb.append("    category: ").append(toIndentedString(category)).append("\n");
		sb.append("    eventName: ").append(toIndentedString(eventName)).append("\n");
		sb.append("    eventDescription: ").append(toIndentedString(eventDescription)).append("\n");
		sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
		sb.append("    totalNoOfVolunteers: ").append(toIndentedString(totalNoOfVolunteers)).append("\n");
		sb.append("    totalVolunteerHours: ").append(toIndentedString(totalVolunteerHours)).append("\n");
		sb.append("    totalTravelHours: ").append(toIndentedString(totalTravelHours)).append("\n");
		sb.append("    overallVolunteerHours: ").append(toIndentedString(overallVolunteerHours)).append("\n");
		sb.append("    livesImpacted: ").append(toIndentedString(livesImpacted)).append("\n");
		sb.append("    activityType: ").append(toIndentedString(activityType)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    pocId: ").append(toIndentedString(pocId)).append("\n");
		sb.append("    pocName: ").append(toIndentedString(pocName)).append("\n");
		sb.append("    pocContact: ").append(toIndentedString(pocContact)).append("\n");
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
