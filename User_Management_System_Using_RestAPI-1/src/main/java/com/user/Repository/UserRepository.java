package com.user.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.entity.UserDetails;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

public interface UserRepository extends JpaRepository<UserDetails, Integer>
{
	@Transactional
	@Modifying
	@Query("update UserDetails ud set ud.password=?1 where ud.useremailid=?2")
	int updatepasswordbyusingemailid(String password,String emailid);
	
	@Transactional
	@Modifying
	@Query("Delete from UserDetails details where details.useremailid=?1")
	int	removeUser(String emailid);
	
	@Query("Select ud from UserDetails ud where ud.password=?1 and ud.useremailid=?2 ")
	UserDetails showuserdetails(@PathVariable("password") String password,@PathVariable("emailid") String emailid);

	 @Query("SELECT u FROM UserDetails u WHERE u.gender = ?1 OR u.useremailid = ?2")
	     List<UserDetails> findByGenderOrEmail(String gender, String emailId);
	}

//	Optional<UserDetails> findByUseremailidAndPassword(String email, String password);
//	Optional<UserDetails> findByUseremailidAndPassword(String email, String password);
	
//	@Query("Select details from UserDetails details where details.gender=?1")
//	UserDetails showuserdetailsusinggender(String gender);
//	
//	@Query("Select d from UserDetails d where d.name=?1or d.emailid=?2")
//	UserDetails showuserdetailsusingnameoremailid(String name,String emailid);
	
	
	
	
