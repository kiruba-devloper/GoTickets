package com.gotickets.GoTickets.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gotickets.GoTickets.Entity.Booking;
import com.gotickets.GoTickets.Entity.MyBooking;
import com.gotickets.GoTickets.Entity.Theatre;
import com.gotickets.GoTickets.Entity.User;
import com.gotickets.GoTickets.Repository.TheatreRepository;
import com.gotickets.GoTickets.Service.UserService;
@Component
public final class EntityToDtoConverter {
	@Autowired
	UserService userSer;
	@Autowired
	TheatreRepository theatreSer;
	
//	public  Booking toBookingDTO(MyBooking booking) {
//        Booking dto = new Booking();
//        dto.setBookingId(booking.getBookingId());
//        
//        dto.setCustomerName(booking.getUser().getName());
//        dto.setCustomerPhone(booking.getUser().getMobile());
//        dto.setCustomerEmail(booking.getUser().getEmail());
//        //dto.setSeats(booking.getSeats());
//        List<Long> seatIds = new ArrayList<>();
//        for (Seats seat : booking.getSeats()) {
//            seatIds.add(seat.getId());  // Add seat IDs to the DTO
//        }
//        dto.setSeats(seatIds);
//        return dto;
//    }

   
    
    public MyBooking toBookingEntity(Booking dto) {
        MyBooking booking = new MyBooking();
        booking.setConsumerbookingid(dto.getBookingId());
       
        User user = userSer.findByNameAndEmailAndMobile(dto.getCustomerName(), dto.getCustomerEmail(), dto.getCustomerPhone());
        
        booking.setUser(user);
        
        Theatre theatre = theatreSer.findByTheaterNameAndTheaterLoc(dto.getTheatreName(), dto.getTheatreLoc());
       
        booking.setTheatre(theatre);

        return booking;
    }
}
