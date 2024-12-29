package com.showTimez.ShowTimez.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seats {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 @Column(name = "seat_number") 
    private String seatNumber; 
    
    private boolean isAvailable = true;
    
    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name="showtimeId", nullable = false)
    private Showtime showtime;  
    //@JsonIgnore
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "booking_id", nullable = true) 
    private Booking booking;
     

    public Seats() {}

    public Seats(Long id, String seatNumber, boolean isAvailable, Showtime showtime, Booking booking) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.isAvailable = isAvailable;
		this.showtime = showtime;
		this.booking = booking;
	}

	public Seats(String seatNumber, Showtime showtime) {
        this.seatNumber = seatNumber;
        this.showtime = showtime;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

	public Showtime getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

//	@Override
//	public String toString() {
//		return "Seats [id=" + id + ", seatNumber=" + seatNumber + ", isAvailable=" + isAvailable + ", showtime="
//				+ showtime + ", booking=" + booking + "]";
//	}

	
	
}
