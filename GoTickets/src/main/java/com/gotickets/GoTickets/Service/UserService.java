package com.gotickets.GoTickets.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gotickets.GoTickets.Entity.LoginForm;
import com.gotickets.GoTickets.Entity.Role;
import com.gotickets.GoTickets.Entity.User;
import com.gotickets.GoTickets.Repository.UserRepository;
import com.gotickets.GoTickets.Security.MyUserDetailsService;
import com.gotickets.GoTickets.Security.Jwt.JwtService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public ResponseEntity<String> registerUser(User user) {
       
    	 if (userRepository.existsById(user.getName())) {
         	
             return ResponseEntity
                 .badRequest()
                 .body("Error: Username is already taken!");
           }

           if (userRepository.existsByEmail(user.getEmail())) {
           	
             return ResponseEntity
                 .badRequest()
                 .body("Error: Email is already in use!");
           }

        
        try {
        	user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(user.getRole()==null) {
            	user.setRole(Role.CUSTOMER);
            }
           // user.setRole(Role.valueOf(user.getRole()));
            userRepository.save(user);
            return ResponseEntity
                    .ok()
                    .body("User registered successfully!");

        } catch (DataIntegrityViolationException e) {
        	return ResponseEntity
                    .badRequest()
                    .body("Error: Could not register the user. Please check your data.");
        
        }
    }

    public User findByNameAndEmailAndMobile(String name,String email,String mobile) {
    	return userRepository.findByNameAndEmailAndMobile(name, email, mobile);
    }
    public User getUserByName(String name) {
        return userRepository.findById(name).orElse(null);
    }


    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public ResponseEntity<String> updateUser( User updatedUser) {

    	Optional<User> user = userRepository.findById(updatedUser.getName());
    	 if (user.isEmpty()) 
          	
             return ResponseEntity
                 .badRequest()
                 .body("Error: Username is not avaliable in records");
           
    	 if(user.get().getPassword().equals(updatedUser.getPassword())) {
        	 user.get().setPassword(updatedUser.getPassword());
        }else {
        user.get().setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        
        }
    	 
    	 userRepository.save(updatedUser);
    	 return ResponseEntity
                 .ok()
                 .body("User Updated successfully!");
    	 
    }
    
    public ResponseEntity<String> authenticate(LoginForm loginform) {
    	
   	 if (loginform == null ) {
   		 
   	        throw new IllegalArgumentException("Username / password cannot be null or empty");
   	    }
   	   
   	    
   	    System.out.println(loginform.password()+" "+loginform.username());
   	    User userDetails =  userRepository.findById(loginform.username()).orElseThrow(() -> new RuntimeException("User not found")); 
   	    
   	 // Perform authentication
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginform.username(), loginform.password())
       );
       System.out.println("auth varuthu "+userDetails+"  "+authentication);
       if(authentication.isAuthenticated()) {
   		
   		
   		String token = jwtService.generateToken(myUserDetailsService.loadUserByUsername(userDetails.getName()));
   	
      // response.setHeader("Authorization", "Bearer " + token);
       
       // Respond with success
       return ResponseEntity.ok("Authentication successful  : "+token);
       }else {
       	
   		throw new UsernameNotFoundException("invalid Username. Please try again.");
   	}
   }
    
   

    public String deleteUser(String name) {
        if (userRepository.existsById(name)) {
            userRepository.deleteById(name);
            return "User deleted successfully!";
        } else {
            return "User not found.";
        }
    }
}
