package com.scankart.app.feature.complaint;

import java.io.Serializable;
import javax.persistence.*;

import com.scankart.app.model.AllUser;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user_complaints database table.
 * 
 */
@Entity
@Table(name="support_users")
@NamedQuery(name="SupportUsers.findAll", query="SELECT u FROM SupportUsers u")
public class SupportUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="date")
	private Timestamp date;

	private String name;
	
	private String role;
	
	private String status;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SupportUsers() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}