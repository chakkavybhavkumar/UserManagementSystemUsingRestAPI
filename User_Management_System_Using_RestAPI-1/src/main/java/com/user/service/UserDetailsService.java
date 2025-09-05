package com.user.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.user.DAO.UserDetailsDAO;
import com.user.DTO.UserDetailsDTO;
import com.user.Exception.Invalidentryexception;
import com.user.Exception.NoUserDataFound;
import com.user.Exception.UserExceptionHandler;
import com.user.entity.UserDetails;

@Service
public class UserDetailsService
{
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserDetailsDAO userDetailsDAO;

	public ResponseEntity<UserDetails> UserRegistrationdetails(UserDetailsDTO userDetailsDTO) 
	{
		/*  "Map method is used to transfer or swap the One object values in to another Objесt
          ->It is non static method present Instile the ModelRapper-class
          -> It is the two argument method which can take First, argument as the r.f of 
            the object from which class object us need to trasfer the details,second argument as
            the class file to which it has to transfer or Map
            */
		UserDetails userDetails=mapper.map(userDetailsDTO,UserDetails.class);
		UserDetails details=userDetailsDAO.insertUserdetails(userDetails);
		if (userDetails.getId()!=0)
		{
			return new ResponseEntity<UserDetails>(details,HttpStatus.CREATED);		
		} 
		else
		{
			return new ResponseEntity<UserDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	public ResponseEntity<String> updateuserpasswordbyusingemailid(UserDetails userDetails) 
	{
		int result=userDetailsDAO.UpdateUserPasswordusingEmailid(userDetails);
		if (result!=0)
		{
		return new ResponseEntity<>("Updated SucessFully",HttpStatus.OK);
		} 
		else
		{
//           return new ResponseEntity(Invalidentryexception.class,HttpStatus.FORBIDDEN);
			throw new Invalidentryexception("Invalid data Entry");
		}
	}
	
	 public ResponseEntity getUserDetailsByUsingGenderOrEmail(String genderOrEmail)
	 {
        List<UserDetails> list= userDetailsDAO.selectUserDetailsUsingGenderOrEmail(genderOrEmail);
        if (!list.isEmpty()) 
        {
         List<UserDetailsDTO> result=list.stream()
        	.map(entity->mapper
        	.map(entity, UserDetailsDTO.class))
        	.collect(Collectors.toList());
        	return new ResponseEntity(result, HttpStatus.FOUND);		
		} 
        else 
        {
       throw new NoUserDataFound("No user data Found");
		}
    }
			
	
	public UserDetails selectdetailsusingemailid(String password,String emailid)
	{  
	return userDetailsDAO.selectUserDetails(password, emailid);
	}
	
	public int deleteOperation(String emailid) {
		return userDetailsDAO.deletedetails(emailid);

	}
	//public UserDetails selectdetailsusinggender(String gender) 
//	{
//	return userDetailsDAO.selectuserdetailsusinggender(gender);
//   }
//	public UserDetails selectdetailsusingnameoremailid(String name,String emailid)
//	{
//		return userDetailsDAO.selectuserdetailsusingnameoremailid(name,name);
//}
	
}
