package com.scankart.app.feature.complaint;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scankart.app.AppStartupRunner;
import com.scankart.app.dto.ErrorResponse;
import com.scankart.app.dto.RestResponse;
import com.scankart.app.model.AllInventoryAttr;
import com.scankart.app.model.AllInventoryAttrRepository;
import com.scankart.app.model.AllMerchant;
import com.scankart.app.model.AllMerchantAttribute;
import com.scankart.app.model.AllMerchantAttributeRepository;
import com.scankart.app.model.AllMerchantRepository;
import com.scankart.app.model.AllOrder;
import com.scankart.app.model.AllOrderRepository;
import com.scankart.app.model.AllUser;
import com.scankart.app.model.AllUserRepository;
import com.scankart.app.model.BusinessTypePl;
import com.scankart.app.model.MerchantOffer;
import com.scankart.app.model.Offer;
import com.scankart.app.model.OfferRepository;
import com.scankart.app.model.UserComplaintRepository;
import com.scankart.app.model.UserComplaintStatusRepository;
import com.scankart.app.model.UserOffer;
import com.scankart.app.model.UserOfferRepository;
import com.scankart.app.util.CustomMessages;
import com.scankart.app.util.DistanceChecker;
import com.scankart.app.util.ValidatorUtility;


@Transactional
@Repository
@Service("DefaultComplaintService")
public class DefaultComplaintService implements ComplaintService{


	@Autowired 
	UserComplaintRepository userComplaintRepo;
	
	@Autowired
	ValidatorUtility validatorUtil ;
	
	@Autowired
	UserComplaintStatusRepository userComplaintStatusRepo;
	
	@Autowired
	AllUserRepository userRepo ;
	
	@Override
	public RestResponse createComplaint(int userId, String apiKey, HashMap<String,Object> complaintDetails) {
		RestResponse response = new RestResponse();
		String records = AppStartupRunner.configValues.get("Support_email"); 
		try {
			int validationStatus = validatorUtil.validateUser(apiKey,userId,1);
			if (validationStatus == 0 || validationStatus == -1) {
				HashMap<String, Object> validationMessage = new HashMap<String, Object>();
				validationMessage.put("message", CustomMessages.getCustomMessages("UAU"));
				validationMessage.put("key", 200);
				response.setResponse(validationMessage);
				return response;
			}
			AllUser user = userRepo.getUserById(userId);
			ComplaintTypePl complaintType = userComplaintRepo.getAllComplaintsTypeById((int)complaintDetails.get("typeId"));
			int complaintsCount = userComplaintRepo.getComplaintsCount();
			Timestamp newDate = new Timestamp(System.currentTimeMillis());
			UserComplaint usercomplaint = new UserComplaint();
			usercomplaint.setId(complaintsCount+1);
			usercomplaint.setAllUser(user);
			usercomplaint.setCompDate(newDate);
			usercomplaint.setComplaintTypePl(complaintType);
			usercomplaint.setDescription((String)complaintDetails.get("description"));
			userComplaintRepo.saveAndFlush(usercomplaint);
			
			SupportUsers supportUsers = userComplaintRepo.getSupportUsers(1);// hard coded 
			UserComplaintsStatus usercomplaintStatus = new UserComplaintsStatus();
			usercomplaintStatus.setAssignedToId(supportUsers.getId());
			usercomplaintStatus.setCompDate(newDate);
			usercomplaintStatus.setDescription((String)complaintDetails.get("description"));
			usercomplaintStatus.setStatus("OPENED");
			usercomplaintStatus.setUserComplaint(usercomplaint);
			
			userComplaintStatusRepo.saveAndFlush(usercomplaintStatus);
			HashMap<String, Object> validationMessage = new HashMap<String, Object>();
			validationMessage.put("message", CustomMessages.getCustomMessages("AS"));
			validationMessage.put("key", 200);
			response.setResponse(validationMessage);
					
			} catch( Exception e) {
			e.printStackTrace();
			ErrorResponse errorResponse = new ErrorResponse(CustomMessages.getCustomMessages("ISE"), 500);
			response.setResponse(errorResponse);
		}
		return response;
	}
	
	@Override
	public RestResponse getComplaintsType(int userId, String apiKey) {
		RestResponse response = new RestResponse();
		String records = AppStartupRunner.configValues.get("Support_email"); 
		DozerBeanMapper dozenBeanMapper = new DozerBeanMapper();
		List<ComplaintTypePlDto> listOfComplaintsDto = new ArrayList<>();
		try {
			int validationStatus = validatorUtil.validateUser(apiKey,userId,1);
			if (validationStatus == 0 || validationStatus == -1) {
				HashMap<String, Object> validationMessage = new HashMap<String, Object>();
				validationMessage.put("message", CustomMessages.getCustomMessages("UAU"));
				validationMessage.put("key", 200);
				response.setResponse(validationMessage);
				return response;
			}
			List<ComplaintTypePl> complaintTypes = userComplaintRepo.getAllComplaintsType();
			for (ComplaintTypePl complaintType: complaintTypes) {
				listOfComplaintsDto.add(dozenBeanMapper.map(complaintType, ComplaintTypePlDto.class));
			}
			HashMap<String, Object> validationMessage = new HashMap<String, Object>();
			validationMessage.put("message", CustomMessages.getCustomMessages("AS"));
			validationMessage.put("key", 200);
			validationMessage.put("data", listOfComplaintsDto);
			response.setResponse(validationMessage);
					
			} catch( Exception e) {
			e.printStackTrace();
			ErrorResponse errorResponse = new ErrorResponse(CustomMessages.getCustomMessages("ISE"), 500);
			response.setResponse(errorResponse);
		}
		return response;
	}
}
