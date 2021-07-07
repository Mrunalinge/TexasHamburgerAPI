package com.texas.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
