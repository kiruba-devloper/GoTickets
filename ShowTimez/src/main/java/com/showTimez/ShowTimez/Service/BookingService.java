package com.showTimez.ShowTimez.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.showTimez.ShowTimez.Entity.Booking;
import com.showTimez.ShowTimez.Repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository bookingrep;
	
	public List<Booking> getAllBookings() {
		return bookingrep.findAll();
	}
	
	public Booking findByBookingNameAndEmailAndPhone(String customerName,String customerEmail,String customerPhone) {
		return bookingrep.findByCustomerNameAndCustomerEmailAndCustomerPhone(customerName, customerEmail, customerPhone);
	}
//	public Optional<Booking> findByBookingNameAndEmailAndPhone1(String customerName,String customerEmail,String customerPhone) {
//		return bookingrep.findByCustomerNameAndCustomerEmailAndCustomerPhone1(customerName, customerEmail, customerPhone);
//	}
	
//	public ResponseEntity<String> addNewBooking(Booking booking) {
//		if(booking==null)
//			//return "Error: Empty Data give something and try again...";
//			return new ResponseEntity<String>("Error: Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);
//		try {
//			bookingrep.save(booking);
//	        //return "showtime added successfully!";
//			return new ResponseEntity<String>("Booked successfully!", HttpStatus.CREATED);
//	    } catch (DataIntegrityViolationException e) {
//	       // return "Error: Could not add the Showtime now. Please check your data.";
//	    	return new ResponseEntity<String>("Error: Could not add the Showtime now. Please check your data.", HttpStatus.METHOD_FAILURE);
//	    }
//		 
//	}
	public Booking addNewBooking(Booking booking) {
		
		if(booking!=null) {
			return bookingrep.save(booking);
		}
		else {
			return null;
		}
		
	}

	public Optional<Booking> getBookingById(Long id) {
	    return bookingrep.findById(id);
	}

	public Booking updateBookingDetails(Booking updatedbooking) {
		long id=updatedbooking.getBookingId();
	    if (bookingrep.existsById(id)) {
	    	
	    	 
	    	Booking booking =bookingrep.save(updatedbooking);
	        //return "Showtime Updated sucessfully..";
	    	return booking;
	    }
	    return null;
	}


	public ResponseEntity<String> deleteBooking(long id) {
	    if (bookingrep.existsById(id)) {
	    	bookingrep.deleteById(id);
	        //return "Showtime deleted successfully!";
	    	return new ResponseEntity<String>("booking deleted successfully!", HttpStatus.CREATED);
	    } else {
	       // return "Showtime not found.";
	    	return new ResponseEntity<String>("Error: Movie not found. try again...", HttpStatus.METHOD_FAILURE);
	    }
	}
}
