package com.gotickets.GoTickets.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gotickets.GoTickets.Entity.Theatre;
import com.gotickets.GoTickets.Repository.TheatreRepository;

@Service
public class TheatreService {

	@Autowired
	TheatreRepository theatreRepo;
	public List<Theatre> getTheatreList() {
		// TODO Auto-generated method stub
		return theatreRepo.findAll();
	}

	
	public Optional<Theatre> getTheatreById(Long id) {
		// TODO Auto-generated method stub
		return theatreRepo.findById(id);
	}

	public ResponseEntity<String> addNewTheatre(Theatre theatre) {
		if (theatre == null)
			//return "Error: Empty Data give something and try again...";
			return new ResponseEntity<String>("Error: Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);
		// TODO Auto-generated method stub
		try {
			theatreRepo.save(theatre);
			//return "Movie Added successfully!";
			return new ResponseEntity<String>("Theatre Added successfully!", HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			//return "Error: Could not add the moive this time. Please check your data.";
			return new ResponseEntity<String>("Error: Could not add the Theatre this time. Please check your data.", HttpStatus.METHOD_FAILURE);
		}
		
	}

	public ResponseEntity<String> updateTheatreDetails(Theatre theatre) {
		Long id = theatre.getTheaterid();
		if (theatreRepo.existsById(id)) {
			theatre.setTheaterid(id);

			theatreRepo.save(theatre);
			//return "Movie Updated sucessfully..";
			return new ResponseEntity<String>("Theatre Updated successfully!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error: Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);
		}
	}

	public ResponseEntity<String> deleteTheatre(long id) {
		if (theatreRepo.existsById(id)) {
			theatreRepo.deleteById(id);
			//return "Movie deleted successfully!";
			return new ResponseEntity<String>("Theatre deleted successfully!", HttpStatus.CREATED);
		} else {
			//return "Movie not found.";
			return new ResponseEntity<String>("Error: Theatre not found. try again...", HttpStatus.METHOD_FAILURE);
		}
	}

}
