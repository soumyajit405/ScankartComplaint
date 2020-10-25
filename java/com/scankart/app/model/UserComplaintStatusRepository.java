package com.scankart.app.model;

import org.springframework.data.jpa.repository.Modifying;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scankart.app.feature.complaint.ComplaintTypePl;
import com.scankart.app.feature.complaint.UserComplaint;
import com.scankart.app.feature.complaint.UserComplaintsStatus;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserComplaintStatusRepository extends JpaRepository<UserComplaintsStatus, Long>
{
 		
}