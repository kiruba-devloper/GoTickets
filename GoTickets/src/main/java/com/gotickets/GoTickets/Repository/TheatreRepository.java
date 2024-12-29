package com.gotickets.GoTickets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotickets.GoTickets.Entity.Theatre;
@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
	public Theatre findByTheaterNameAndTheaterLoc(String theaterName, String theaterLoc );
}
