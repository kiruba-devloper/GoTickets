package com.gotickets.GoTickets.Entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//@Entity
public class Event {


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
	private String movieName;
	private String genre;
	private String duration;

    //@OneToMany(mappedBy = "event")
    private List<Showtime> showtimes;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<Showtime> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(List<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", movieName=" + movieName + ", genre=" + genre + ", duration=" + duration
				+ ", showtimes=" + showtimes + "]";
	}
    
}
