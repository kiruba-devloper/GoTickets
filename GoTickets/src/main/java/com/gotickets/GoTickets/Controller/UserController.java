package com.gotickets.GoTickets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotickets.GoTickets.Entity.LoginForm;
import com.gotickets.GoTickets.Entity.User;
import com.gotickets.GoTickets.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gotickets/user")
public class UserController {

    @Autowired
    private UserService userService;

   
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginForm loginform) {
    	return userService.authenticate(loginform);
    }

    
    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

   
    @GetMapping("/allusers")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PutMapping("/{name}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }

    
    @DeleteMapping("/{name}")
    public String deleteUser(@PathVariable String name) {
        return userService.deleteUser(name);
    }
}
