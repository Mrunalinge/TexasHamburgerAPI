package com.texas.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.texas.entity.Users;
import com.texas.repository.UsersRespository;

@Service
public class UsersServiceImpl implements  UserDetailsService{
	public static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired(required = false) 
	private UsersRespository usersRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("In user service Impl");
		Optional<Users> user = usersRepository.findUsersByUserName(username);
		System.out.println("!!!!!!!!!!!!!!"+user.get());
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not found "+username));
//		if(user == null)
//			throw new UsernameNotFoundException("Not found "+username);
			MyUserDetails mud = new MyUserDetails(user);
		return mud;
	}

	
}
