package com.gotickets.GoTickets.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
//@Entity
public class Booking {
	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
	private String status ;
	private String paymentStatus ;
    private String customerName; 
    private String customerPhone;
    private String customerEmail;
    private String theatreName;
    private String theatreLoc;
    //@OneToMany(mappedBy = "booking")  
   // @JsonManagedReference
    private List<Seats> seats;
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(Long bookingId, String status, String paymentStatus, String customerName, String customerPhone,
			String customerEmail, List<Seats> seats) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.seats = seats;
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
	public List<Seats> getSeats() {
		return seats;
	}
	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreLoc() {
		return theatreLoc;
	}
	public void setTheatreLoc(String theatreLoc) {
		this.theatreLoc = theatreLoc;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", status=" + status + ", paymentStatus=" + paymentStatus
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", customerEmail="
				+ customerEmail + ", theatreName=" + theatreName + ", theatreLoc=" + theatreLoc + ", seats=" + seats
				+ "]";
	}
	
	
    
    
}
