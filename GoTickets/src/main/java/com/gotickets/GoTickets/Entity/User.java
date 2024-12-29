package com.gotickets.GoTickets.Entity;

import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(unique = true)  
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password should have at least 8 characters")
    private String password;

    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, max = 15, message = "Mobile number should be between 10 and 15 characters")
    private String mobile;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<MyBooking> bookings;
    
    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role; 
    public User() {
        super();
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<MyBooking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<MyBooking> bookings) {
        this.bookings = bookings;
    }



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}
    

    @Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", mobile=" + mobile
				+ ", bookings=" + bookings + ", role=" + role + "]";
	}
}
