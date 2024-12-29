package com.showTimez.ShowTimez.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showTimez.ShowTimez.Entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
		//Booking findByBookingNameAndEmailAndPhone(String customerName,String customerEmail,String customerPhone);
	 Booking findByCustomerNameAndCustomerEmailAndCustomerPhone(String customerName, String customerEmail, String customerPhone);
	 //Optional<Booking> findByCustomerNameAndCustomerEmailAndCustomerPhone(String customerName, String customerEmail, String customerPhone);

}
