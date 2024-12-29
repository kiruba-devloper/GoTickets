package com.gotickets.GoTickets.Controller;

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

import com.gotickets.GoTickets.Entity.Theatre;
import com.gotickets.GoTickets.Service.TheatreService;


@RequestMapping("/gotickets/theatre")
@RestController
public class TheatreController {
	
	@Autowired
	TheatreService theatreser;
	
	@GetMapping("/alltheatres")
	public List<Theatre> getTheatreList() {
		return theatreser.getTheatreList();
	}
	
	@GetMapping("/{id}")
	public Optional<Theatre> getTheatre(@PathVariable Long id) {
		return theatreser.getTheatreById(id);

	}
	@PostMapping("/crud")
	public ResponseEntity<String> addTheatre(@RequestBody Theatre theatre){
		return theatreser.addNewTheatre(theatre);
	}
	
	@PutMapping("/crud")
	public ResponseEntity<String> updateTheatre(@RequestBody Theatre theatre){
		return theatreser.updateTheatreDetails(theatre);
	}
	
	 
	@DeleteMapping("/crud")
	public ResponseEntity<String> deleteTheatre(@RequestBody long id){
		return theatreser.deleteTheatre(id);
	}
}
