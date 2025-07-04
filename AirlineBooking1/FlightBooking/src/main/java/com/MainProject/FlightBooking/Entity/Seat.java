package com.MainProject.FlightBooking.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name="class_id",nullable = false)
    private TravelClass travelClass;

    @Column(name="seat_number",nullable = false, unique = true)
    private String seatNumber;

    public Seat(){

    }

    public Seat(Integer seatId, TravelClass travelClass, String seatNumber) {
        this.seatId = seatId;
        this.travelClass = travelClass;
        this.seatNumber = seatNumber;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }
}
