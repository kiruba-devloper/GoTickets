package com.showTimez.ShowTimez.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showTimez.ShowTimez.Entity.Booking;
import com.showTimez.ShowTimez.Entity.BookingDTO;
import com.showTimez.ShowTimez.Entity.Seats;
import com.showTimez.ShowTimez.Entity.Showtime;
import com.showTimez.ShowTimez.Repository.SeatsRepository;
import com.showTimez.ShowTimez.others.Theater;

@Service
public class SeatsService {
	@Autowired
	SeatsRepository seatsrep;
	@Autowired
	ShowtimeService showtmeSer;
	@Autowired
	BookingService bookingSer;
	@Autowired
	BookingDTO bookingdto;
	
	
	int[] screenSeats(String screennumber) {
		int screen = Character.getNumericValue((screennumber.charAt(screennumber.length() - 1)));
		switch (screen) {
		case 1:
			int screen1[] = { 10, 10 };
			return screen1;

		case 2:
			int screen2[] = { 8, 8 };
			return screen2;
		case 3:
			int screen3[] = { 7, 8 };
			return screen3;
		case 4:
			int screen4[] = { 9, 9 };
			return screen4;
		default:
			int screen5[] = { 10, 10 };
			return screen5;

		}
	}

	public List<Seats> getAllSeats() {
		return seatsrep.findAll();
	}

	public ResponseEntity<String> getSeatDetailsByShowtime(Long id) {
		StringBuffer sb = new StringBuffer();
		try {
		Optional<Showtime> show = showtmeSer.getShowtimeById(id);
		int scr[] = screenSeats(show.get().getScreenNumber());
		int row = scr[0];
		
		int column = scr[1];
		System.out.println("r: "+row + "  c: "+column);
		long availableSeats = seatsrep.countByShowtimeAndIsAvailable(show, true);
		long bookedSeats = seatsrep.countByShowtimeAndIsAvailable(show, false);
		List<Seats> seats = show.get().getSeats();
		int z = 0;
		sb.append(" 							***"+Theater.Name+" Ticket booking***\n");
		sb.append(" Movie Name : "+show.get().getEvent().getMovieName()+"\n");
		sb.append(" Show Time : "+show.get().getShowTime()+"\n");
		
		for (int i = 0; i < column ; i++) {
			String seatnumber = "";
			String seataval = "";
			for (int j = 0; j < row ; j++) {
				if (z<seats.size()) {
				//System.out.print(" "+z);
				//System.out.print(seats.size());
				seatnumber += "    "+seats.get(z).getSeatNumber() + "     ";
				seataval += seats.get(z).isAvailable() ? "[Available]" : "[  Booked ]";
				z++;
			}
			}
			sb.append(seatnumber).append("\n").append(seataval).append("\n");
		}
		
		
		
		return ResponseEntity.ok(sb + "Total Avaliable Seats : " + availableSeats + " \n Total Booked Seats : " + bookedSeats);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body("Error: Could not view now SeatsService :getSeatDetailsByShowtime. Please check your data.");
					
		}
	 catch (Exception e) {
		 return ResponseEntity
			        .status(HttpStatus.METHOD_FAILURE)
			        .body("Error: Could not view now SeatsService:getSeatDetailsByShowtime. Please check your data.");

	}

	}

	public ResponseEntity<BookingDTO> bookSeats(List<Seats> seats,Booking booking) {
		StringBuffer sb = new StringBuffer();

		if (seats == null || seats.isEmpty())
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Empty Data on Seats, give something and try again...",null));

		try {

//			Showtime showtime = seats.get(0).getShowtime();
//			Optional<Showtime> showtimeOptional = showtmeSer.getShowtimeById(showtime.getShowtimeId());
			Optional<Seats> Seat = seatsrep.findById(seats.getFirst().getId());
			
			Showtime showtime = Seat.get().getShowtime();

			if (!Seat.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(bookingdto.setBookingDTO("Error: Showtime not found!",null));
				
			}

			long availableSeats = seatsrep.countByShowtimeAndIsAvailable(showtime, true);
			long bookedSeats = seatsrep.countByShowtimeAndIsAvailable(showtime, false);

			//long totalSeats = availableSeats + bookedSeats;
			if (availableSeats == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(bookingdto.setBookingDTO("Error: Tickets are full. No available seats.",null));
				
			}
			
			for (Seats seat : seats) {
				Optional<Seats> existingSeatOpt = seatsrep.findById(seat.getId());
				if (existingSeatOpt.isPresent()) {
					Seats existingSeat = existingSeatOpt.get();
					if (existingSeat.isAvailable()) {
						existingSeat.setAvailable(false);
						existingSeat.setBooking(booking);
						seatsrep.save(existingSeat);
						sb.append(seat.getSeatNumber() + ", ");
					}
				}
			}
			booking.setStatus("Confirmed");
			booking.setSeat(seats);
			bookingSer.addNewBooking(booking);
			Booking booked = bookingSer.addNewBooking(booking);
			//System.out.println(booked);
			if(booked==null)
				return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
						.body(bookingdto.setBookingDTO("Error: Booking failed. recheck the data..",null));
				
			String bookingJson = new ObjectMapper().writeValueAsString(booked);
			String responseMessage = String.format(
					"Ticket booking successful for movie '%s' on '%s' at '%s' with seats %s"
						+" At "+ Theater.Name+" in "+Theater.Location,
					showtime.getEvent().getMovieName(), showtime.getScreenNumber(), showtime.getShowTime(),
					sb.toString());
			System.out.println(booked.getSeat());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(bookingdto.setBookingDTO(responseMessage+"\n"+bookingJson,booked));
			

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Could not add the Showtime now . Please check your data."+e,null));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Please check your data.",null));
			
		}
	}
	
	public ResponseEntity<BookingDTO> cancelSeats(List<Seats> seats,Booking booking) {
		StringBuffer sb = new StringBuffer();
		
		if (seats == null || seats.isEmpty())
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Empty Data on Seats, give something and try again...",null));
		
		try {
			Optional<Seats> Seat = seatsrep.findById(seats.getFirst().getId());
			
			Showtime showtime = Seat.get().getShowtime();
//			Optional<Showtime> showtimeOptional = showtmeSer.getShowtimeById(showtime.getShowtimeId());
//			
			if (!Seat.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(bookingdto.setBookingDTO("Error: Showtime not found!",null));
			}
			
			long availableSeats = seatsrep.countByShowtimeAndIsAvailable(showtime, true);
			long bookedSeats = seatsrep.countByShowtimeAndIsAvailable(showtime, false);
			
			//long totalSeats = availableSeats + bookedSeats;
			if (bookedSeats == 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(bookingdto.setBookingDTO("Error: No seats are Booked...",null));
			
			}
			
			for (Seats seat : seats) {
				Seats existingSeat = seatsrep.findById(seat.getId()).get();					
					if (!existingSeat.isAvailable()) {
						existingSeat.setAvailable(true);
						existingSeat.setBooking(null);
						seatsrep.save(existingSeat);
						sb.append(seat.getSeatNumber() + ", ");
					}
					
				}
			
			booking.setStatus("Confirmed");
			booking.setPaymentStatus("paid");
			//cancel
			Booking cancelled =bookingSer.updateBookingDetails(booking);
			String cancelJson = new ObjectMapper().writeValueAsString(cancelled);
			String responseMessage = String.format(
					"Ticket Cancel successful for movie '%s' on '%s' at '%s' with seats %s"
							+" At "+ Theater.Name+" in "+Theater.Location+". Amount has been refunded.. ",
							showtime.getEvent().getMovieName(), showtime.getScreenNumber(), showtime.getShowTime(),
							sb.toString());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(bookingdto.setBookingDTO(responseMessage+"\n"+cancelJson,cancelled));
			
			
		}  catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Could not add the Showtime now . Please check your data."+e,null));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Please check your data.",null));
			
		}
	}

	public ResponseEntity<String> addSeats(Showtime show) {
		if (show == null)

			return new ResponseEntity<String>("Error: Empty Data on showtime give something and try again...",
					HttpStatus.METHOD_FAILURE);
		try {

			int scr[] = screenSeats(show.getScreenNumber());
			int row = scr[0];
			int column = scr[1];

			if ((row * column) == show.getSeatCount()) {
				char rowLetter = 'A';
				for (int i = 1; i <= row; i++) {
					for (int j = 1; j <= column; j++) {
						String seatNumber = "" + rowLetter + j;
						Seats seat = new Seats(seatNumber, show);
						seatsrep.save(seat);
					}
					rowLetter++;
				}
				// seatsrep.save(seat);
				// return "showtime added successfully!";
				return new ResponseEntity<String>("Show time and Seats Added successfully!", HttpStatus.CREATED);
			} else
				return new ResponseEntity<String>("Seats count not matched..try again....", HttpStatus.NOT_ACCEPTABLE);
		} catch (DataIntegrityViolationException e) {

			return new ResponseEntity<String>("Error: Could not add the Showtime now. Please check your data.",
					HttpStatus.METHOD_FAILURE);
		}

	}

	public Optional<Seats> getSeatsById(Long id) {
		return seatsrep.findById(id);
	}

	public ResponseEntity<String> updateSeatDetails(Seats updatedseat) {
		long id = updatedseat.getId();
		if (seatsrep.existsById(id)) {
			updatedseat.getId();

			seatsrep.save(updatedseat);

			return new ResponseEntity<String>("Showtime Updated successfully!", HttpStatus.CREATED);
		} else {
			// return "Showtime not found.";
			return new ResponseEntity<String>("Error:Showtime not found / Empty Data give something and try again...",
					HttpStatus.METHOD_FAILURE);
		}
	}

	public ResponseEntity<String> deleteSeats(long id) {
		if (seatsrep.existsById(id)) {
			seatsrep.deleteById(id);
			// return "Showtime deleted successfully!";
			return new ResponseEntity<String>("Showtime deleted successfully!", HttpStatus.CREATED);
		} else {
			// return "Showtime not found.";
			return new ResponseEntity<String>("Error: Movie not found. try again...", HttpStatus.METHOD_FAILURE);
		}
	}

	public ResponseEntity<String> deleteAllSeatsOnShowtime(Showtime show) {
		if (show.getSeats() != null && !(show.getSeats().isEmpty())) {
			List<Seats> delseat = show.getSeats();
			for (Seats s : delseat) {
				seatsrep.existsById(s.getId());
			}

			// return "Showtime deleted successfully!";
			return new ResponseEntity<String>("Showtime deleted successfully!", HttpStatus.CREATED);
		} else {
			// return "Showtime not found.";
			return new ResponseEntity<String>("Error: Movie not found. try again...", HttpStatus.METHOD_FAILURE);
		}
	}
}
