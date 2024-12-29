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

import com.showTimez.ShowTimez.Entity.Event;
import com.showTimez.ShowTimez.Service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventSer;
	
	@GetMapping("/movies")
	public List<Event> getMovieList() {
		return eventSer.getMoviesList();
	}
	
	@GetMapping("/movie/{id}")
	public Optional<Event> getMovie(@PathVariable Long id) {
		return eventSer.getMovieById(id);

	}
	@PostMapping("/crud")
	public ResponseEntity<String> addMovie(@RequestBody Event movie){
		return eventSer.addNewMovie(movie);
	}
	
	@PutMapping("/crud")
	public ResponseEntity<String> updateMovie(@RequestBody Event movie){
		return eventSer.updateMovieDetails(movie);
	}
	
	 
	@DeleteMapping("/crud")
	public ResponseEntity<String> deleteMovie(@RequestBody int id){
		return eventSer.deleteMovie(id);
	}
	
}
