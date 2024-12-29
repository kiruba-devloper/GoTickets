package com.gotickets.GoTickets.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gotickets.GoTickets.Repository.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.gotickets.GoTickets.Entity.User> user = userRepository.findById(username);
		if(user.isPresent()) {
			var userObj = user.get();
			 
			return User.builder()
					.username(userObj.getName())
					.password(userObj.getPassword())
					.roles(userObj.getRole().name())
					.build();
		}
		else {
			throw new UsernameNotFoundException(username);
		}
	}


}

