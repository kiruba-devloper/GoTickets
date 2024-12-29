package com.showTimez.ShowTimez.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showTimez.ShowTimez.Entity.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

	Showtime findByScreenNumberAndShowTime(String screenNumber, String showTime);
	//Showtime findbyShowtimeByName(String name);
}
