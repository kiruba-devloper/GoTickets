package com.gotickets.GoTickets.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotickets.GoTickets.Entity.MyBooking;
import com.gotickets.GoTickets.Entity.User;
import com.gotickets.GoTickets.Repository.MyBookingRepository;
@Service
public class MyBookingService {
	
	@Autowired
	UserService userSer;

	@Autowired
    private MyBookingRepository bookingRepository;
//
//    @Autowired
//    private ShowtimeRepository showtimeRepository;
    
    public List<MyBooking> mybooking(String username) {
		// TODO Auto-generated method stub
    	User user = userSer.getUserByName(username);
		return bookingRepository.findByUser(user);
	}

//    public MyBooking bookTicket(Long showtimeId, Long userId) {
//        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow();
//        MyBooking booking = new MyBooking();
//        booking.setShowtime(showtime);
//        return bookingRepository.save(booking);
//    }
//
//    public MyBooking cancelBooking(Long bookingId) {
//        MyBooking booking = bookingRepository.findById(bookingId).orElseThrow();
//        booking.setStatus("cancelled");
//        return bookingRepository.save(booking);
//    }

    
}
