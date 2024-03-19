package com.user.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.ums.entity.User;
import com.user.ums.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		return new CustomUserDetails(userRepo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("NO USER NAME FOUND")));
	}
	
}
