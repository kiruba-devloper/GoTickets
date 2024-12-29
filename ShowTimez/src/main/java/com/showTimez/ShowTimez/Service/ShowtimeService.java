package com.showTimez.ShowTimez.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.showTimez.ShowTimez.Entity.Event;
import com.showTimez.ShowTimez.Entity.Showtime;
import com.showTimez.ShowTimez.Repository.EventRepository;
import com.showTimez.ShowTimez.Repository.SeatsRepository;
import com.showTimez.ShowTimez.Repository.ShowtimeRepository;

@Service
public class ShowtimeService {

	@Autowired
	ShowtimeRepository showTimeRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	SeatsService seatsSer;
	
	public List<Showtime> getAllShowtime() {
		return showTimeRepository.findAll();
	}

	public ResponseEntity<String> addNewShowtime(Showtime show) {
		System.out.println(show);
		if(show==null || show.getEvent()==null)
			//return "Error: Empty Data give something and try again...";
			
			return new ResponseEntity<String>("Error: Empty showtime Data give something and try again...", HttpStatus.METHOD_FAILURE);
		try {
			System.out.print(show);
//			showTimeRepository.save(show);
	        //return "showtime added successfully!";
			Event event = eventRepository.findById(show.getEvent().getEventId())
		            .orElseThrow(() -> new RuntimeException("Event not found"));

		    Showtime showtime = new Showtime();
		    showtime.setEvent(event);
		    showtime.setPrice(show.getPrice());
		    showtime.setScreenNumber(show.getScreenNumber());
		    showtime.setSeatCount(show.getSeatCount());
		    showtime.setShowTime(show.getShowTime());
		    Showtime findshow = showTimeRepository.findByScreenNumberAndShowTime(show.getScreenNumber(),show.getShowTime());
		    if(findshow==null) {
		    Showtime showtimez=showTimeRepository.save(showtime);
		    return seatsSer.addSeats(showtimez);
		    }else {
		    	return new ResponseEntity<String>("Showtime already added...! \n", HttpStatus.ALREADY_REPORTED);	
		    }
		    //System.out.println(showtimez);
			//
	    } catch (DataIntegrityViolationException e) {
	       // return "Error: Could not add the Showtime now. Please check your data.";
	    	return new ResponseEntity<String>("Error: Could not add the Showtime now. Please check your data.", HttpStatus.METHOD_FAILURE);
	    }
		
		 
		   
		 
	}

	public Optional<Showtime> getShowtimeById(Long id) {
	    return showTimeRepository.findById(id);
	}

	public ResponseEntity<String> updateShowtimeDetails(Showtime updatedShowtime) {
		long id=updatedShowtime.getShowtimeId();
	    if (showTimeRepository.existsById(id)) {
	    	updatedShowtime.setShowtimeId(id);  
	    	 
	    	showTimeRepository.save(updatedShowtime);
	        //return "Showtime Updated sucessfully..";
	    	return new ResponseEntity<String>("Showtime Updated successfully!", HttpStatus.CREATED);
	    } else {
	        //return "Showtime not found.";
	    	return new ResponseEntity<String>("Error:Showtime not found / Empty Data give something and try again...", HttpStatus.METHOD_FAILURE);
	    }
	}


	public ResponseEntity<String> deleteShowtime(long id) {
	    if (showTimeRepository.existsById(id)) {
	    	Optional<Showtime> show =showTimeRepository.findById(id);
	    	showTimeRepository.deleteById(id);
	    	seatsSer.deleteAllSeatsOnShowtime(show.get());
	        //return "Showtime deleted successfully!";
	    	return new ResponseEntity<String>("Showtime deleted successfully!", HttpStatus.CREATED);
	    } else {
	       // return "Showtime not found.";
	    	return new ResponseEntity<String>("Error: Movie not found. try again...", HttpStatus.METHOD_FAILURE);
	    }
	}
}
