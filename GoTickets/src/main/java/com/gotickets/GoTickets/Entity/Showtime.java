package com.gotickets.GoTickets.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//@Entity
public class Showtime {


   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showtimeId;
    private String screenNumber;
    private String showTime;
    private int seatCount;
    private double price;

    //@ManyToOne
    //@JoinColumn(name = "eventId", nullable = false)
    private Event event;

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

	@Override
	public String toString() {
		return "Showtime [showtimeId=" + showtimeId + ", screenNumber=" + screenNumber + ", showTime=" + showTime
				+ ", seatCount=" + seatCount + ", price=" + price + ", event=" + event + "]";
	}
    
}
