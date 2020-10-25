package com.scankart.app.feature.complaint;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_complaints_status database table.
 * 
 */
@Entity
@Table(name="user_complaints_status")
@NamedQuery(name="UserComplaintsStatus.findAll", query="SELECT u FROM UserComplaintsStatus u")
public class UserComplaintsStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="assigned_to_id")
	private int assignedToId;

	@Column(name="comp_date")
	private Timestamp compDate;

	private String description;

	private String status;

	//bi-directional many-to-one association to UserComplaint
	@ManyToOne
	@JoinColumn(name="user_complaints_id")
	private UserComplaint userComplaint;

	public UserComplaintsStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssignedToId() {
		return this.assignedToId;
	}

	public void setAssignedToId(int assignedToId) {
		this.assignedToId = assignedToId;
	}

	public Timestamp getCompDate() {
		return this.compDate;
	}

	public void setCompDate(Timestamp compDate) {
		this.compDate = compDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserComplaint getUserComplaint() {
		return this.userComplaint;
	}

	public void setUserComplaint(UserComplaint userComplaint) {
		this.userComplaint = userComplaint;
	}

}