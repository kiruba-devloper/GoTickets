package com.gotickets.GoTickets.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotickets.GoTickets.Entity.MyBooking;
import com.gotickets.GoTickets.Entity.User;
@Repository
public interface MyBookingRepository extends JpaRepository<MyBooking, Long>  {

	List<MyBooking> findByUser(User user);

}
