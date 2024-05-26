package com.greatlearning.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.Entity.Role;
import com.greatlearning.Entity.User;



public class GlMyUserDecorator implements UserDetails {
	
	
	User user;

	public GlMyUserDecorator(User user) {
		this.user = user;	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role>roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

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
	public boolean isAccountNonExpired() {
		LocalDate expirydate =user.getAccountExpiryDate();//31-05-2024
		LocalDate todaysdate =LocalDate.now();//20-05-2024
        if(expirydate.isAfter(todaysdate))
		return true;
        else 
       return false;
        	
	}

	@Override
	public boolean isAccountNonLocked() {
		if(user.getAccountLockedStatus()==1) 
			return true;
		else 
			return false;
		
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate credExpiryDate = user.getCredentialsExpiryDate();
		LocalDate todaysDate =LocalDate.now();
//		if(credExpiryDate.isAfter(todaysDate)) {
//			return true;
//		}
//		else {
//			return false;
//		}
		//return credExpiryDate.isAfter(todaysDate)?true:false;
		return user.getCredentialsExpiryDate().isAfter(todaysDate.now())? true : false;
	}

	@Override
	public boolean isEnabled() {
		int enabledStatus =user.getEnabledStatus();
		if(enabledStatus ==1) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
