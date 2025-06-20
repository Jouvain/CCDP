package com.ccdp.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccdp.main.repositories.UserRepository;
import com.ccdp.main.entities.User;
import com.ccdp.main.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userRepository.findByUsername(username);
		  if (user == null) {
		   throw new UsernameNotFoundException(username + " n'existe pas!!!");
		  }
		  return new UserDetailsImpl(user);
		 }
	}
	
	
	

