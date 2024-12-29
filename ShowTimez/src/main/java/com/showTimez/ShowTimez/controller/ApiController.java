package com.showTimez.ShowTimez.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.showTimez.ShowTimez.Entity.Booking;
import com.showTimez.ShowTimez.Entity.BookingDTO;
import com.showTimez.ShowTimez.Entity.Event;
import com.showTimez.ShowTimez.Entity.Showtime;
import com.showTimez.ShowTimez.Service.ApiService;
import com.showTimez.ShowTimez.Service.BookingService;
import com.showTimez.ShowTimez.Service.EventService;
import com.showTimez.ShowTimez.Service.SeatsService;
import com.showTimez.ShowTimez.Service.ShowtimeService;

@RestController
@RequestMapping("/showtimez")
public class ApiController {
	@Autowired
	EventService eventSer;
	
	@Autowired
	ShowtimeService showtimeSer;
	
	@Autowired
	SeatsService seatsSer;
	
	@Autowired
	BookingService bookingSer;
	
	@Autowired
	ApiService apiSer;
	
	@PostMapping("/book/{showtimeid}")
	public ResponseEntity<BookingDTO> bookTickets(@RequestBody List<String> seatnumber, @PathVariable Long showtimeid,
												@RequestParam String name,@RequestParam String email,@RequestParam String phone) {
		return apiSer.bookTickets(seatnumber,showtimeid,name,email,phone);
	}
	
	@PutMapping("/cancel/{showtimeid}")
	public ResponseEntity<BookingDTO> cancelTickets( @RequestBody List<String> seatnumber, @PathVariable Long showtimeid,
			@RequestParam String name,@RequestParam String email,@RequestParam String phone) {
												
		return apiSer.cancelBooking(seatnumber,showtimeid,name,email,phone);
	}
	
	@GetMapping("/booking")
	public List<Booking> getAllBookings() {
		return bookingSer.getAllBookings();
	}
	@GetMapping("/booking/{id}")
	public Optional<Booking> getBookingById(@PathVariable Long id) {
		return bookingSer.getBookingById(id);
	}
	
//	@PostMapping("/seats")
//	public ResponseEntity<String> bookSeats(@RequestBody List<Seats> seats) {
//		return seatsSer.bookSeats(seats);
//	}
	@GetMapping("/seat/{id}")
	public ResponseEntity<String> getSeatDetailsByShowtime(@PathVariable Long id) {
		return seatsSer.getSeatDetailsByShowtime(id);
	}
	
	@GetMapping("/showtimes")
	public List<Showtime> getAllShowtime() {
		return showtimeSer.getAllShowtime();
	}
	@GetMapping("/showtime/{id}")
	public Optional<Showtime> getShowtimeById(@PathVariable Long id) {
		return showtimeSer.getShowtimeById(id);
	}
	
	@GetMapping("/movies")
	public List<Event> getMovieList() {
		return eventSer.getMoviesList();
	}
	
	@GetMapping("/movie/{id}")
	public Optional<Event> getMovie(@PathVariable Long id) {
		
		  return eventSer.getMovieById(id);
	}
//	@GetMapping("/movie/{moviename}")
//	public Event getMovieByName(@PathVariable String moviename) {
//		return eventSer.getMovieByName(moviename);
//	}
	@PatchMapping("/patch/hello")
	public ResponseEntity<String> hello() {
		return  new ResponseEntity<String>("hello patch method",HttpStatus.ACCEPTED);
	}
	
}
