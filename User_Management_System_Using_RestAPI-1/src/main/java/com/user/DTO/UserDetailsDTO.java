package com.user.DTO;

import jakarta.validation.constraints.Email;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO 
{
	private int userid;
//	@NotNull(message = "Name should not be Entered")//it will allows the double quotes
//	@NotEmpty(message="Name should not be Entered")//it will allows empty spaces
	@NotBlank(message="Name should be Entered")
	@Size(max = 50,min = 3 ,message = "Charecters sholud be 3 to 50")
	private String username;
	@NotNull(message = "Emailid should not be Entered")
	@Email
	private String useremailid;
	@NotNull(message = "MobileNumber Should Be Entered")
	@Min(value = 6000000000l,message = "mobile number should start from 6,7,8,9")
	@Max(value = 9999999999l,message = "mobile number must be 10 digits")
	private long mobilenumber;
	@NotNull(message="Password should not be Entered")
	private String password;
	@NotNull(message="Gender should not be Entered")
	private String gender;

}