package com.showTimez.ShowTimez.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showTimez.ShowTimez.Entity.Seats;
import com.showTimez.ShowTimez.Entity.Showtime;
@Repository
public interface SeatsRepository extends JpaRepository<Seats, Long>{
	 long countByShowtimeAndIsAvailable(Optional<Showtime> showtime, boolean isAvailable);
	    
	   
	    long countByShowtimeAndIsAvailable(Showtime showtime, boolean isAvailable);
	    
	    //Seats findbySeatNumber(String seat_number);
	   
	    Optional<Seats> findById(Long id);
}
