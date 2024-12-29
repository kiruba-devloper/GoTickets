package com.showTimez.ShowTimez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showTimez.ShowTimez.Entity.Showtime;
import com.showTimez.ShowTimez.Service.SeatsService;
import com.showTimez.ShowTimez.Service.ShowtimeService;

@RestController
@RequestMapping("/showtime")
public class ShowtimeController {
	@Autowired
	ShowtimeService showtimeSer;
	
//	@GetMapping("/all")
//	public List<Showtime> getAllShowtime() {
//		return showtimeSer.getAllShowtime();
//	}
//	@GetMapping("/showtime/{id}")
//	public Optional<Showtime> getShowtimeById(@PathVariable Long id) {
//		return showtimeSer.getShowtimeById(id);
//	}
	
	@PostMapping("/crud")
	public ResponseEntity<String> addNewShowtime(@RequestBody Showtime show){
		return showtimeSer.addNewShowtime(show);
	}
	@PutMapping("/crud")
	public ResponseEntity<String> updateShowtimeDetails(@RequestBody Showtime show){
		return showtimeSer.updateShowtimeDetails(show);
	}
	 
	@DeleteMapping("/crud")
	public ResponseEntity<String> deleteShowtime(@RequestBody int id){
		
		return showtimeSer.deleteShowtime(id);
	}
}
