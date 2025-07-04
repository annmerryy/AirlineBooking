package com.MainProject.FlightBooking.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private int reservationId;

    @ManyToOne
    @JoinColumn(name="schedule_id",nullable = false)
    private FlightSchedule flightSchedule;

    @ManyToOne
    @JoinColumn(name="seat_id",nullable = false)
    private Seat seat;

    @Column(name="passenger_name", nullable = false)
    private String passengerName;

    @Column(name="passenger_email", nullable = false)
    private String passengerEmail;

    @Column(name="passenger_gender", nullable = false)
    private String passengerGender;

    @Column(name="booking_date",nullable = false)
    private LocalDateTime bookingDate;

    public Reservation(){
        
    }

    public Reservation(int reservationId, FlightSchedule flightSchedule, Seat seat, String passengerName, String passengerEmail, String passengerGender, LocalDateTime bookingDate) {
        this.reservationId = reservationId;
        this.flightSchedule = flightSchedule;
        this.seat = seat;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerGender = passengerGender;
        this.bookingDate = bookingDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
