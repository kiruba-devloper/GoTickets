package com.gotickets.GoTickets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotickets.GoTickets.Entity.BookingDTO;
import com.gotickets.GoTickets.Entity.Event;
import com.gotickets.GoTickets.Service.HomeService;
import com.gotickets.GoTickets.Service.UserService;

@RestController
@RequestMapping("/gotickets")
public class HomeController {
//	@Autowired
//	MyBookingService bookingSer;
	@Autowired
	HomeService homeSer;
	@Autowired
	UserService userSer;
	
	 @PostMapping("/book/{showid}")
	 public ResponseEntity<BookingDTO> bookTickets(@RequestBody List<String> seatnumber,@PathVariable long showid){
		return homeSer.bookTickets(seatnumber, showid);
	 }
	 @PatchMapping("/cancel/{showid}")
	 public ResponseEntity<BookingDTO> cancelTickets(@RequestBody List<String> seatnumber,@PathVariable long showid){
		 return homeSer.cancelTickets(seatnumber, showid);
	 }
	 
	@GetMapping("/movies")
	public List<Event> getMovieList() {
		//String username = (String) session.getAttribute("username");
		return homeSer.movieList();
	}
	@GetMapping("/movie/{id}")
	public Event getMovie(@PathVariable int id) {
		return homeSer.getMovie(id);
	}
	@GetMapping("/movie/{id}/seats")
	public ResponseEntity<String> getMovieSeats(@PathVariable int id) {
		return homeSer.getMovieSeats(id);
	}
	
//	@PatchMapping("/patch/hello")
//	public String hello() {
//		return homeSer.hello();
//	}
	
}
