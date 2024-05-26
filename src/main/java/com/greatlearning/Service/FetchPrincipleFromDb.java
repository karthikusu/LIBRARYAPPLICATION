package com.greatlearning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.Entity.User;
import com.greatlearning.Repository.UserRepository;


@Service
public class FetchPrincipleFromDb implements UserDetailsService {

	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userrepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Principle not found Exception");
		}
		  return new GlMyUserDecorator(user);
	}

}
