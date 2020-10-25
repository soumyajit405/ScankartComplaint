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
@Table(name="user_complaints")
@NamedQuery(name="UserComplaint.findAll", query="SELECT u FROM UserComplaint u")
public class UserComplaint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="comp_date")
	private Timestamp compDate;

	private String description;

	//bi-directional many-to-one association to ComplaintTypePl
	@ManyToOne
	@JoinColumn(name="complaint_type_id")
	private ComplaintTypePl complaintTypePl;

	//bi-directional many-to-one association to AllUser
	@ManyToOne
	@JoinColumn(name="user_id")
	private AllUser allUser;

	//bi-directional many-to-one association to UserComplaintsStatus
	@OneToMany(mappedBy="userComplaint")
	private List<UserComplaintsStatus> userComplaintsStatuses;

	public UserComplaint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ComplaintTypePl getComplaintTypePl() {
		return this.complaintTypePl;
	}

	public void setComplaintTypePl(ComplaintTypePl complaintTypePl) {
		this.complaintTypePl = complaintTypePl;
	}

	public AllUser getAllUser() {
		return this.allUser;
	}

	public void setAllUser(AllUser allUser) {
		this.allUser = allUser;
	}

	public List<UserComplaintsStatus> getUserComplaintsStatuses() {
		return this.userComplaintsStatuses;
	}

	public void setUserComplaintsStatuses(List<UserComplaintsStatus> userComplaintsStatuses) {
		this.userComplaintsStatuses = userComplaintsStatuses;
	}

	public UserComplaintsStatus addUserComplaintsStatus(UserComplaintsStatus userComplaintsStatus) {
		getUserComplaintsStatuses().add(userComplaintsStatus);
		userComplaintsStatus.setUserComplaint(this);

		return userComplaintsStatus;
	}

	public UserComplaintsStatus removeUserComplaintsStatus(UserComplaintsStatus userComplaintsStatus) {
		getUserComplaintsStatuses().remove(userComplaintsStatus);
		userComplaintsStatus.setUserComplaint(null);

		return userComplaintsStatus;
	}

}