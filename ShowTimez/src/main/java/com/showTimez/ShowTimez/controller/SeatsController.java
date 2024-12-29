package com.showTimez.ShowTimez.controller;

import java.util.List;
import java.util.Optional;

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

import com.showTimez.ShowTimez.Entity.Seats;
import com.showTimez.ShowTimez.Service.SeatsService;

@RestController
@RequestMapping("/seats")
public class SeatsController {
	
	@Autowired
	SeatsService seatsSer;
	
	@GetMapping("/all")
	public List<Seats> getAllSeats() {
		return seatsSer.getAllSeats();
	}
	@GetMapping("/{id}")
	public Optional<Seats> getSeatsById(@PathVariable Long id) {
		return seatsSer.getSeatsById(id);
	}
	
//	@PostMapping("/bookseat")
//	public ResponseEntity<String> bookSeats(@RequestBody List<Seats> seats){
//		return seatsSer.bookSeats(seats);
//	}
	@PutMapping("/crud")
	public ResponseEntity<String> updateSeatDetails(@RequestBody Seats seat){
		return seatsSer.updateSeatDetails(seat);
	}
	 
	@DeleteMapping("/crud")
	public ResponseEntity<String> deleteSeats(@RequestBody int id){
		return seatsSer.deleteSeats(id);
	}
}
