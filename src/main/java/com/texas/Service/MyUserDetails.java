package com.texas.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.texas.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

	
	private String userName;
	
	
	private String password;
	
	private boolean active;
	
	List<GrantedAuthority> authorities;
	
	public MyUserDetails(Optional<Users> user) {
		this.userName = user.get().getUserName();
		this.password = user.get().getPassword();
		this.active = user.get().isActive();
		//this.authorities = getGrantAuthority(user);
		this.authorities= Arrays.stream(user.get().getRoles().split(","))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}
	
//	public List<GrantedAuthority> getGrantAuthority(Optional<Users> user){
//		List<GrantedAuthority> auth = new ArrayList<>();
//		
//		String arr[] = user.get().getRoles().split(",");
//		
//		for(int i=0;i<arr.length;i++){
//			auth.add(new SimpleGrantedAuthority(arr[i]));
//		}
//		
//		
//		return auth;
//		
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

}
