package com.gotickets.GoTickets.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Table(name="MyBookings")
public class MyBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	private Long consumerbookingid;
//	@ManyToOne
//    @JsonBackReference
//	@JoinColumn(name = "seat_id", nullable = false)
//    private List<Seats> seats;

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	
	public MyBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyBooking( Long consumerbookingid, User user, Theatre theatre) {
		super();
		//this.bookingId = bookingId;
		this.consumerbookingid = consumerbookingid;
		this.user = user;
		this.theatre = theatre;
	}

	public Long getBookingId() {
		return bookingId;
	}

//	public void setBookingId(Long bookingId) {
//		this.bookingId = bookingId;
//	}

	public Long getConsumerbookingid() {
		return consumerbookingid;
	}

	public void setConsumerbookingid(Long consumerbookingid) {
		this.consumerbookingid = consumerbookingid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

//	@Override
//	public String toString() {
//		return "MyBooking [bookingId=" + bookingId + ", consumerbookingid=" + consumerbookingid + ", user=" + user
//				+ ", theatre=" + theatre + "]";
//	}
	
	
	

	
	
	

	
}
