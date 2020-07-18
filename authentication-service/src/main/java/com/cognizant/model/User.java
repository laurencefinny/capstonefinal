package com.cognizant.model;

import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-19T15:35:34.885+05:30[Asia/Calcutta]")
public class User {
	@JsonProperty("us_id")
	@Id
	private Integer us_id = null;

	@JsonProperty("us_fs_name")
	private String us_fs_name = null;

	@JsonProperty("us_ls_name")
	private String us_ls_name = null;

	@JsonProperty("us_emp_id")
	private String us_emp_id = null;

	@JsonProperty("us_password")
	private String us_password = null;

	@JsonProperty("role")
	private String role = null;

	public String getRole() {
		return role;
	}

	public User(Integer us_id, String us_fs_name, String us_ls_name, String us_emp_id, String us_password,
			String role) {
		super();
		this.us_id = us_id;
		this.us_fs_name = us_fs_name;
		this.us_ls_name = us_ls_name;
		this.us_emp_id = us_emp_id;
		this.us_password = us_password;
		this.role = role;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Integer getUs_id() {
		return us_id;
	}

	public void setUs_id(Integer us_id) {
		this.us_id = us_id;
	}

	public String getUs_fs_name() {
		return us_fs_name;
	}

	public void setUs_fs_name(String us_fs_name) {
		this.us_fs_name = us_fs_name;
	}

	public String getUs_ls_name() {
		return us_ls_name;
	}

	public void setUs_ls_name(String us_ls_name) {
		this.us_ls_name = us_ls_name;
	}

	public String getUs_emp_id() {
		return us_emp_id;
	}

	public void setUs_emp_id(String us_emp_id) {
		this.us_emp_id = us_emp_id;
	}

	public String getUs_password() {
		return us_password;
	}

	public void setUs_password(String us_password) {
		this.us_password = us_password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((us_emp_id == null) ? 0 : us_emp_id.hashCode());
		result = prime * result + ((us_fs_name == null) ? 0 : us_fs_name.hashCode());
		result = prime * result + ((us_id == null) ? 0 : us_id.hashCode());
		result = prime * result + ((us_ls_name == null) ? 0 : us_ls_name.hashCode());
		result = prime * result + ((us_password == null) ? 0 : us_password.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [us_id=" + us_id + ", us_fs_name=" + us_fs_name + ", us_ls_name=" + us_ls_name + ", us_emp_id="
				+ us_emp_id + ", us_password=" + us_password + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (us_emp_id == null) {
			if (other.us_emp_id != null)
				return false;
		} else if (!us_emp_id.equals(other.us_emp_id))
			return false;
		if (us_fs_name == null) {
			if (other.us_fs_name != null)
				return false;
		} else if (!us_fs_name.equals(other.us_fs_name))
			return false;
		if (us_id == null) {
			if (other.us_id != null)
				return false;
		} else if (!us_id.equals(other.us_id))
			return false;
		if (us_ls_name == null) {
			if (other.us_ls_name != null)
				return false;
		} else if (!us_ls_name.equals(other.us_ls_name))
			return false;
		if (us_password == null) {
			if (other.us_password != null)
				return false;
		} else if (!us_password.equals(other.us_password))
			return false;
		return true;
	}
}
