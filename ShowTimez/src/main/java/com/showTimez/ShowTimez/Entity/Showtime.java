package com.showTimez.ShowTimez.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Showtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showtimeId;
	private String screenNumber;
	private String showTime;
	private int seatCount;
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	 //@JsonIgnore
	@JsonBackReference
	@JoinColumn(name = "eventId", nullable = false)
	private Event event;
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "showtime")
	private List<Seats> seats;

	public Showtime(Long showtimeId, String screenNumber, String showTime, int seatCount, double price, Event event,
			List<Seats> seats) {
		super();
		this.showtimeId = showtimeId;
		this.screenNumber = screenNumber;
		this.showTime = showTime;
		this.seatCount = seatCount;
		this.price = price;
		this.event = event;
		this.seats = seats;
	}

	public Showtime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(Long showtimeId) {
		this.showtimeId = showtimeId;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Showtime [showtimeId=" + showtimeId + ", screenNumber=" + screenNumber + ", showTime=" + showTime
				+ ", seatCount=" + seatCount + ", price=" + price + ", event=" + event + ", seats=" + seats + "]";
	}

}
