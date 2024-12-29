package com.showTimez.ShowTimez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showTimez.ShowTimez.Entity.Booking;
import com.showTimez.ShowTimez.Service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingSer;
	
//	@GetMapping("/booking")
//	public List<Booking> getAllBookings() {
//		return bookingSer.getAllBookings();
//	}
//	@GetMapping("/booking/{id}")
//	public Optional<Booking> getBookingById(@PathVariable Long id) {
//		return bookingSer.getBookingById(id);
//	}
	
	@PostMapping("/crud")
	public Booking addNewBooking(@RequestBody Booking booking){
		return bookingSer.addNewBooking(booking);
	}
	@PutMapping("/crud")
	public Booking updateBookingDetails(@RequestBody Booking booking){
		return bookingSer.updateBookingDetails(booking);
	}
	 
	@DeleteMapping("/crud")
	public ResponseEntity<String> deleteBooking(@RequestBody int id){
		return bookingSer.deleteBooking(id);
	}
}
