package com.scankart.app.feature.complaint;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.scankart.app.dto.RestResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
public interface ComplaintService 
{
    
    @RequestMapping(value ="createComplaint/{userId}" , method =  RequestMethod.POST , headers =  "Accept=application/json" )
    public RestResponse createComplaint( @PathVariable("userId") int userId, @RequestHeader(value="api-key") String apiKey,@RequestBody HashMap<String,Object> complaintDetails);
    
    @RequestMapping(value ="getComplaintsType/{userId}" , method =  RequestMethod.GET , headers =  "Accept=application/json" )
    public RestResponse getComplaintsType( @PathVariable("userId") int userId, @RequestHeader(value="api-key") String apiKey);
     
}
