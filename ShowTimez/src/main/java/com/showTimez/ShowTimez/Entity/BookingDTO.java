package com.showTimez.ShowTimez.Entity;

import org.springframework.stereotype.Component;

@Component
public class BookingDTO {
	private String message;
	private Booking booking;
	public BookingDTO(String message, Booking booking) {
		super();
		this.message = message;
		this.booking = booking;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public BookingDTO setBookingDTO(String message,Booking booking) {
		
		return new BookingDTO(message, booking);
	}
	
	@Override
	public String toString() {
		return "BookingDTO [message=" + message + ", booking=" + booking + "]";
	}
	public BookingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
