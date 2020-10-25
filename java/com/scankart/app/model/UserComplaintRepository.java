package com.scankart.app.model;

import org.springframework.data.jpa.repository.Modifying;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scankart.app.feature.complaint.ComplaintTypePl;
import com.scankart.app.feature.complaint.SupportUsers;
import com.scankart.app.feature.complaint.UserComplaint;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserComplaintRepository extends JpaRepository<UserComplaint, Long>
{
 	

	@Query("select a from ComplaintTypePl a")
	List<ComplaintTypePl> getAllComplaintsType();
	
	@Query("select a from ComplaintTypePl a where a.id=?1")
	ComplaintTypePl getAllComplaintsTypeById(int typeId);
	
	@Query("select COALESCE(max(a.id),0) from UserComplaint a")
	int getComplaintsCount();
	
	@Query("select a from SupportUsers a where a.id=?1")
	SupportUsers getSupportUsers(int id);
	
}