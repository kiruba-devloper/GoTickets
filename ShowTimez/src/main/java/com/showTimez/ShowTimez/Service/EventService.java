package com.showTimez.ShowTimez.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.showTimez.ShowTimez.Entity.Event;
import com.showTimez.ShowTimez.Repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	public List<Event> getMoviesList() {
		return eventRepository.findAll();
	}

	public ResponseEntity<String> addNewMovie(Event event) {
		if (event == null)
			//return "Error: Empty Data give something and try again...";
			return new ResponseEntity<String>("Error: Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);

		try {
			eventRepository.save(event);
			//return "Movie Added successfully!";
			return new ResponseEntity<String>("Movie Added successfully!", HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			//return "Error: Could not add the moive this time. Please check your data.";
			return new ResponseEntity<String>("Error: Could not add the moive this time. Please check your data.", HttpStatus.METHOD_FAILURE);
		}

	}

//	public Event getMovieByName(String name) {
//	    return eventRepository.findbyMovieName(name);
//	}
	public Optional<Event> getMovieById(Long id) {
		return eventRepository.findById(id);
	}

	public ResponseEntity<String> updateMovieDetails(Event updatedMovie) {
		Long id = updatedMovie.getEventId();
		if (eventRepository.existsById(id)) {
			updatedMovie.setEventId(id);

			eventRepository.save(updatedMovie);
			//return "Movie Updated sucessfully..";
			return new ResponseEntity<String>("Movie Updated successfully!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error: Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);
		}
	}

	public ResponseEntity<String> deleteMovie(long id) {
		if (eventRepository.existsById(id)) {
			eventRepository.deleteById(id);
			//return "Movie deleted successfully!";
			return new ResponseEntity<String>("Movie deleted successfully!", HttpStatus.CREATED);
		} else {
			//return "Movie not found.";
			return new ResponseEntity<String>("Error: Movie not found. try again...", HttpStatus.METHOD_FAILURE);
		}
	}
}
