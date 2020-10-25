package com.scankart.app.feature.complaint;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the complaint_type_pl database table.
 * 
 */
@Entity
@Table(name="complaint_type_pl")
@NamedQuery(name="ComplaintTypePl.findAll", query="SELECT c FROM ComplaintTypePl c")
public class ComplaintTypePl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private String name;

	private String status;

	//bi-directional many-to-one association to UserComplaint
	@OneToMany(mappedBy="complaintTypePl")
	private List<UserComplaint> userComplaints;

	public ComplaintTypePl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserComplaint> getUserComplaints() {
		return this.userComplaints;
	}

	public void setUserComplaints(List<UserComplaint> userComplaints) {
		this.userComplaints = userComplaints;
	}

	public UserComplaint addUserComplaint(UserComplaint userComplaint) {
		getUserComplaints().add(userComplaint);
		userComplaint.setComplaintTypePl(this);

		return userComplaint;
	}

	public UserComplaint removeUserComplaint(UserComplaint userComplaint) {
		getUserComplaints().remove(userComplaint);
		userComplaint.setComplaintTypePl(null);

		return userComplaint;
	}

}