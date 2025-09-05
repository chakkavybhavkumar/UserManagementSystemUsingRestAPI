package com.user.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.DTO.UserDetailsDTO;
import com.user.entity.UserDetails;

@Component
public class userDetailsmapper
{
	@Autowired
	ModelMapper mapper;

	/*It is Used to map the Entity objects into DTO*/
	public UserDetailsDTO mapEntityObjectsToDTO(UserDetails userDetails)
	{
		return mapper.map(userDetails,UserDetailsDTO.class);
	}
	
	/*It is used to map the DTO objects into Entity*/
	public UserDetails maptoobjectstoEntity(UserDetailsDTO userDetailsDTO)
	{
		return mapper.map(userDetailsDTO,UserDetails.class);
	}
	
	/*It is used to map the List of Entity objects into List of DTO Objects*/
	public void mapListofEntityToListOfDTOs(List<UserDetails> userDetailsentity) 
	{
		userDetailsentity.stream()
		.map(entity->mapper
		.map(entity,UserDetailsDTO.class))
		.collect(Collectors.toList());
	}
}
