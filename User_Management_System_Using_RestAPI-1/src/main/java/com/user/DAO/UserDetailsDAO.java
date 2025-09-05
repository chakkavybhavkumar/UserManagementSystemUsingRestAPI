package com.user.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.Repository.UserRepository;
import com.user.entity.UserDetails;

@Repository
public class UserDetailsDAO {
	@Autowired
	UserRepository userRepository;

	public UserDetails insertUserdetails(UserDetails userDetails) {
		return userRepository.save(userDetails);

	}

	public UserDetails selectUserDetails(String password, String emailid) {
		return userRepository.showuserdetails(password, emailid);
	}

	 public List<UserDetails> selectUserDetailsUsingGenderOrEmail1(String genderOrEmail)
	 {
	        return userRepository.findByGenderOrEmail(genderOrEmail, genderOrEmail);
	    }

//	
	public int UpdateUserPasswordusingEmailid(UserDetails userDetails) {
		return userRepository.updatepasswordbyusingemailid(userDetails.getPassword(), userDetails.getUseremailid());
	}

	public int deletedetails(String emailid) {
		return userRepository.removeUser(emailid);
	}

	public List<UserDetails> selectUserDetailsUsingGenderOrEmail(String genderOrEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
