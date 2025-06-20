package com.ccdp.main.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ccdp.main.entities.User;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{

	User user;
	
	@Override
	 public String getPassword() {
	  // TODO Auto-generated method stub
	  return user.getPassword();
	 }

	 @Override
	 public String getUsername() {
	  // TODO Auto-generated method stub
	  return user.getUsername();
	 }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
