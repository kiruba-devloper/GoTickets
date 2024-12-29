package com.showTimez.ShowTimez.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.showTimez.ShowTimez.others.Theater;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
	private String status = "pending";
	private String paymentStatus ="unpaid";
    private String customerName; 
    private String customerPhone;
    private String customerEmail;
    private final String theatreName="ShowTimez";
    private final String theatreLoc="Chennai";
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)  
    @JsonManagedReference
    
    private List<Seats> seats;
    public Booking() {}

	public Booking(Long bookingId, String status, String paymentStatus, List<Seats> seat, String customerName,
			String customerPhone, String customerEmail) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.seats = seat;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
	}
	
	
	public Booking(String customerName, String customerPhone, String customerEmail, List<Seats> seat) {
		super();
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.seats = seat;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<Seats> getSeat() {
		return seats;
	}

	public void setSeat(List<Seats> seat) {
		this.seats = seat;
	}

	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String getTheatreName() {
		return theatreName;
	}

	public String getTheatreLoc() {
		return theatreLoc;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", status=" + status + ", paymentStatus=" + paymentStatus
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerEmail="
				+ customerEmail + ", theatreName=" + theatreName + ", theatreLoc=" + theatreLoc + ", seats=" + seats
				+ "]";
	}

    
}
