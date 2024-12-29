package com.showTimez.ShowTimez.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showTimez.ShowTimez.Entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
		//Event findbyMovieName(String movieName);
}
