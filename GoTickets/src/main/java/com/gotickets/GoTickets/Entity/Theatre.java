package com.gotickets.GoTickets.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Theatre {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Theaterid;
	private String theaterName;
	private String theaterLoc;
	@OneToMany(mappedBy = "theatre")
    @JsonIgnore
    private Set<MyBooking> bookings;
	
	public Theatre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theatre(long theaterid, String theaterName, String theaterLoc) {
		super();
		Theaterid = theaterid;
		this.theaterName = theaterName;
		this.theaterLoc = theaterLoc;
	}
	public long getTheaterid() {
		return Theaterid;
	}
	public void setTheaterid(long theaterid) {
		Theaterid = theaterid;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getTheaterLoc() {
		return theaterLoc;
	}
	public void setTheaterLoc(String theaterLoc) {
		this.theaterLoc = theaterLoc;
	}
	
	public Set<MyBooking> getBookings() {
		return bookings;
	}
	public void setBookings(Set<MyBooking> bookings) {
		this.bookings = bookings;
	}
	@Override
	public String toString() {
		return "Theatre [Theaterid=" + Theaterid + ", theaterName=" + theaterName + ", theaterLoc=" + theaterLoc + "]";
	}
	
	
	
	
}
