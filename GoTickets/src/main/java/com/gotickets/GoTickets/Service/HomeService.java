package com.gotickets.GoTickets.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gotickets.GoTickets.Entity.Booking;
import com.gotickets.GoTickets.Entity.BookingDTO;
import com.gotickets.GoTickets.Entity.Event;
import com.gotickets.GoTickets.Entity.MyBooking;
import com.gotickets.GoTickets.Entity.Theatre;
import com.gotickets.GoTickets.Entity.User;
import com.gotickets.GoTickets.Repository.MyBookingRepository;
import com.gotickets.GoTickets.Repository.TheatreRepository;
import com.gotickets.GoTickets.others.EntityToDtoConverter;

@Service
public class HomeService {
	@Autowired
	EntityToDtoConverter converter;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserService userSer;
	
	@Autowired
	TheatreRepository theareRepo;
	@Autowired
	MyBookingRepository mybookingRepo;
	
	String url = "http://localhost:8081";
	

	public List<Event> movieList(){
		
		final String uri = url+"/showtimez/movies";

	    
		Event[] result = restTemplate.getForObject(uri, Event[].class);
	  
	    System.out.println(result);
		return  Arrays.asList(result) ;
	}
	
	public Event getMovie(int id) {
		final String uri = url+"/showtimez/movies/"+id;

	    RestTemplate restTemplate = new RestTemplate();
	    Event result = restTemplate.getForObject(uri, Event.class);
	   
	    System.out.println(result);
		return result;
	}
	public ResponseEntity<String> getMovieSeats(int id) {
		final String uri = url+"/showtimez/seat/"+id;
		
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//		
//		System.out.println(result);
//		return result;
		RestTemplate restTemplate = new RestTemplate();
	    //String testUrl = "https://jsonplaceholder.typicode.com/posts/1";  // Example URL
	    
	    try {
	        ResponseEntity<String> response = restTemplate.exchange(
	                uri,
	                HttpMethod.GET,
	                null, 
	                String.class
	        );
	        return response;
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Error: " + e.getMessage());
	    }
	}
	
	public ResponseEntity<BookingDTO> bookTickets(List<String> seatnumber, long showid){
		User user = userSer.getUserByName("kiruba");
		System.out.println(user);
		final String uri = url+"/showtimez/book/"+showid+"?name="+user.getName()+"&email="+user.getEmail()+"&phone="+user.getMobile();
		ResponseEntity<BookingDTO> response = restTemplate.postForEntity(uri, seatnumber, BookingDTO.class);
		Booking booking = response.getBody().getBooking();
		System.out.println(booking);
		if(booking==null)
			return response;
		Theatre theatre = theareRepo.findByTheaterNameAndTheaterLoc(booking.getTheatreName(), booking.getTheatreLoc());
		System.out.println(booking.getTheatreName()+ "fdh y== "+booking.getTheatreLoc());
		MyBooking mybooking = new MyBooking(booking.getBookingId(),user,theatre);
		mybookingRepo.save(mybooking);
		return response;
	}
	
	
	public ResponseEntity<BookingDTO> cancelTickets(List<String> seatnumber, long showid){
		User user = userSer.getUserByName("kiruba");
		final String uri = url+"/showtimez/cancel/"+showid+"?name="+user.getName()+"&email="+user.getEmail()+"&phone="+user.getMobile();
		System.out.println(uri);
		//ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.PATCH, new HttpEntity<>(seatnumber), String.class);
		ResponseEntity<BookingDTO> response = restTemplate.exchange(uri,HttpMethod.PUT, new HttpEntity<>(seatnumber), BookingDTO.class);
		// RestTemplate restTemplate = new RestTemplate();

		  return response;
		
	}
	
	
	
//	public String hello() {	
//		final String uri = url+"/showtimez/patch/hello";
//		//ResponseEntity<String> responseEntity = restTemplate.exchange(uri,HttpMethod.PATCH, null, String.class);
//		//String response = restTemplate.(String.class);
//		//String response = responseEntity.getBody();
//		//return response;
//	}
	
	
}
