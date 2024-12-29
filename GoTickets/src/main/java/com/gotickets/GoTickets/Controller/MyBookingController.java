package com.gotickets.GoTickets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gotickets.GoTickets.Entity.MyBooking;
import com.gotickets.GoTickets.Service.MyBookingService;

@RestController
@RequestMapping("/gotickets/mybookings")
public class MyBookingController {
	@Autowired
    private MyBookingService bookingService;
	
	@GetMapping()
	public List<MyBooking> mybooking(@RequestParam String user){
		return bookingService.mybooking(user);
	}

//    @PostMapping("/book")
//    public MyBooking bookTicket(@RequestParam Long showtimeId, @RequestParam Long userId) {
//        return bookingService.bookTicket(showtimeId, userId);
//    }
//
//    @PostMapping("/cancel/{bookingId}")
//    public MyBooking cancelBooking(@PathVariable Long bookingId) {
//        return bookingService.cancelBooking(bookingId);
//    }

}
