package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.DTO.UserDetailsDTO;
import com.user.entity.UserDetails;
import com.user.service.UserDetailsService;

import jakarta.validation.Valid;



//@Controller
//@ResponseBody
//@RestController it is a combination of both controller and ResponseBody.
//it is used to handle the request and response
//it is also used to send the response in the form of JSON.
//to avoid the boiler plate code we are using RestController.
@RestController
@Validated
@CrossOrigin("*")
public class UserController 
{
//	@Autowired
//	private  userservice service;
	//it is used to handling insertion operation or put request.
	//it is also used to create end point or URL.
	//it communicate the data in the JSON/XML format.
	//@ResponseBody it used to convert the data java language into JSON format.
	//@RequestBody it is used to convert the data into JSON to java language format.
    //@ResponseBody
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping("registration")
	public ResponseEntity<UserDetails> userregistartion(@Valid @RequestBody UserDetailsDTO userDetailsDTO) 
	{
		return userDetailsService.UserRegistrationdetails(userDetailsDTO);	
	}
	//it is used to handling select  operation or Get request.
	//it is also used to create end point or URL.
	//it communicate the data in the URL format.
    
	//	@ResponseBody
	@GetMapping("login")
	public UserDetails  login(@RequestParam("emailid") String emailid,@RequestParam("password") String password)
    {
		return userDetailsService.selectdetailsusingemailid(password, emailid);	
	}
  	@GetMapping("loginwithgender/{gender}")
	public ResponseEntity loginwithgender(@PathVariable("gender") String gender)
    {
		return userDetailsService.getUserDetailsByUsingGenderOrEmail(gender);
//  		return "User Gender:"+gender;
	}
  	
  	
	
	@GetMapping("/loginwithnameoremailid/{name}/{emailid}")
	public String loginwithnameandemailid(@PathVariable("emailid") String emailid,@PathVariable("name") String name)
    {
//		return userDetailsService.selectdetailsusingemailid(name, name);	
		return "UserName:"+name+"user Emaillid:"+emailid;
	}
	//it is used to handling update operation or Update request.
	//it is also used to create end point or URL.
	//it communicate the data in the JSON/XML format.
//	@ResponseBody
	@PutMapping("update")
	public String updateuserdetails(@RequestBody UserDetails userDetails)
	{
		userDetailsService.updateuserpasswordbyusingemailid(userDetails);
		return "update succesfully";
	}
	//it is used to handling Delete operation or Delete request.
	//it is also used to create end point or URL.
	//it communicate data in the URL format.
//	@ResponseBody
	@DeleteMapping("/delete")
	public String deleteuserdetails(@RequestBody UserDetails userDetails)
	{
		if(userDetailsService.deleteOperation(userDetails.getUseremailid())>0) {
			return "Data deleted Successfully";
		}
		else
			return "Not found";
		
	}
	
	
	
}