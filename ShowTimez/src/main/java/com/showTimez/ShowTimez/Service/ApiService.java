package com.showTimez.ShowTimez.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.showTimez.ShowTimez.Entity.Booking;
import com.showTimez.ShowTimez.Entity.BookingDTO;
import com.showTimez.ShowTimez.Entity.Seats;
import com.showTimez.ShowTimez.Entity.Showtime;
import com.showTimez.ShowTimez.others.DateTimeNow;
import com.showTimez.ShowTimez.others.Theater;

@Service
public class ApiService {
	@Autowired
	SeatsService seatsSer;
	@Autowired
	ShowtimeService showtimeSer;
	@Autowired
	BookingService bookingSer;
	@Autowired
	BookingDTO bookingdto;
//	@Autowired
//	SeatsRepository seatsrep;
	
	public ResponseEntity<BookingDTO> bookTickets(List<String> seatnumber,Long showtimeid,String name,String email,String phone){
		try {
			Booking booking = bookingSer.findByBookingNameAndEmailAndPhone(name, email, phone);
			if(seatnumber.isEmpty() || seatnumber==null)
				return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
						.body(bookingdto.setBookingDTO("Error: Empty Data on Seats, give something and try again...",null));
		
		List<Seats> bookseat = new ArrayList<Seats>();
		Optional<Showtime> showtime = showtimeSer.getShowtimeById(showtimeid);
		
		List<Seats> seats = showtime.get().getSeats();
		if(seats==null || seats.isEmpty())
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Empty Seat data from the show time , give something and try again...",null));

		
		StringBuffer sb = new StringBuffer();
		for(String snum:seatnumber) {
		for(Seats s:seats) {
			if(s.getSeatNumber().equalsIgnoreCase(snum)) {
				if(s.isAvailable()) {
					sb.append(snum);
				bookseat.add(s);
				}else {
					return ResponseEntity.status(HttpStatus.ACCEPTED)
							.body(bookingdto.setBookingDTO("Seat "+s.getSeatNumber()+" were already booked...",null));
					
				}
			}
				
//			}else {
//				return new ResponseEntity<String>("Error: Seat Number not Avaliable. Check your seats - Thankyou...",
//						HttpStatus.BAD_REQUEST);
//			}
		}
		
		}
		if(booking!=null) {
		if(booking.getSeat().containsAll(bookseat)) {
			String responseMessage = String.format(
					"Your Tickets were already booked for movie '%s' on '%s' at '%s' with seats %s"
						+" At "+ Theater.Name+" in "+Theater.Location,
					showtime.get().getEvent().getMovieName(), showtime.get().getScreenNumber(), showtime.get().getShowTime(),
					sb.toString());
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(bookingdto.setBookingDTO(responseMessage,null));
		
	}
		String status = booking.getPaymentStatus();
		
		String currenttime = DateTimeNow.getFormattedDate();
		if (showtime.get().getShowTime().equalsIgnoreCase(currenttime))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bookingdto.setBookingDTO("Error: Movie Show completed. Bookings Not available try other showtime. Thankyou...",null));

		
		booking.setPaymentStatus("paid");
		status = booking.getPaymentStatus();
		if(status.equalsIgnoreCase("paid")) {
			return seatsSer.bookSeats(bookseat,booking);
			
		}
		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(bookingdto.setBookingDTO("Please Make Payment to confirm your Ticket for Movie "+showtime.get().getEvent().getMovieName(),null));
			
		}
		}else {
			Booking book = new Booking();
			
			book.setCustomerName(name);
			book.setCustomerEmail(email);
			book.setCustomerPhone(phone);
			book.setPaymentStatus("paid");
			//book.setSeat(seats);
			String status = book.getPaymentStatus();
			
			if(status.equalsIgnoreCase("paid")) {
				return seatsSer.bookSeats(bookseat,book);
				
			}
			else {
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(bookingdto.setBookingDTO("Please Make Payment to confirm your Ticket for Movie "+showtime.get().getEvent().getMovieName(),null));
				
			}
		}

		}catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Could not view now check the data is entred correct Api service -book ticket. Please check your data."+e,null));
		
		}
	 catch (Exception e) {
		 return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
					.body(bookingdto.setBookingDTO("Error: Could not view now something went wrong . Please check your data."+e,null));

		
	}
		
	}
	
	
		public ResponseEntity<BookingDTO> cancelBooking(List<String> seatnumber,Long showtimeid,String name,String email,String phone) {
			
			try {
				
				Booking booking = bookingSer.findByBookingNameAndEmailAndPhone(name, email, phone);
				
				if(seatnumber.isEmpty() || seatnumber==null)
					return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
							.body(bookingdto.setBookingDTO("Error: Empty Seat data, give steats number for cancelling, give something and try again...",null));
		
			List<Seats> bookseat = new ArrayList<Seats>();
			Showtime showtime = showtimeSer.getShowtimeById(showtimeid).get();
			
			
			
			List<Seats> seats = showtime.getSeats();
			if(seats==null || seats.isEmpty())
				return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
						.body(bookingdto.setBookingDTO("Error: Empty Seat data from the show time , give something and try again...",null));
		
			StringBuffer sb = new StringBuffer();
		
			for(String snum:seatnumber) {
				
			for(Seats s:seats) {
				if(s.getSeatNumber().equalsIgnoreCase(snum) && booking.getSeat().contains(s)) {
					if(!s.isAvailable()  ) {
						sb.append(snum);
//						
						bookseat.add(s);
					}
					
//				}else {
//					
//					return new ResponseEntity<String>("Seat "+snum+" were not booked...",HttpStatus.ACCEPTED);
//				}
//					else {
//					return new ResponseEntity<String>("Error: Seat Number not Avaliable. Check your seats - Thankyou...",
//							HttpStatus.BAD_REQUEST);
			}
			}
			}	
			
				
				
				if (booking == null)
					return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
							.body(bookingdto.setBookingDTO("Error:no seat Booking data not avaliable and try again...",null));

				
				String status = booking.getPaymentStatus();
				String show_time = showtime.getShowTime();
				String currenttime = DateTimeNow.getFormattedDate();
				
				if (show_time.equalsIgnoreCase(currenttime))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(bookingdto.setBookingDTO("Error: Movie Show completed. Cancel Not available . Thankyou...",null));

				
				if(status.equalsIgnoreCase("paid")) {
					return seatsSer.cancelSeats(bookseat,booking);
					
					}
				else {
					return ResponseEntity.status(HttpStatus.ACCEPTED)
							.body(bookingdto.setBookingDTO("Check the paymentstatus.... Thank you",null));

				}
//				}else {
//					return new ResponseEntity<String>("Check your seat numbers that you are booked for cancel ",
//							HttpStatus.ACCEPTED);
//				}
				//}
				
//				else {
//					return new ResponseEntity<String>("No Tickets were booked :( Thank you",
//							HttpStatus.ACCEPTED);
//				}
			}catch (DataIntegrityViolationException e) {
				return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
						.body(bookingdto.setBookingDTO("Error: Could not view now check the data is entred correct . Please check your data."+e,null));

			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
						.body(bookingdto.setBookingDTO("Error: Could not view now something went wrong . Please check your data."+e,null));

			}
		
		
	}
	
//	public List<Seats> bookTickets() {
//		return seatsrep.findAll();
//	}
}
