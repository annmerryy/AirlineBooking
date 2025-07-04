package com.MainProject.FlightBooking.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="flight_schedule")
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name="flight_id",nullable = false)
    private Flight flight;

    @Column(name="departure_date",nullable = false)
    private LocalDateTime departureDate;

    @Column(name="arrival_date",nullable = false)
    private LocalDateTime arrivalDate;

    @Column(name="departure_city",nullable = false)
    private String departureCity;

    @Column(name="arrival_city",nullable = false)
    private String arrivalCity;

    @Column(name="duration",nullable = false)
    private Integer duration;

    public FlightSchedule(){

    }

    public FlightSchedule(Integer scheduleId, Integer duration, String arrivalCity, String departureCity, LocalDateTime arrivalDate, LocalDateTime departureDate, Flight flight) {
        this.scheduleId = scheduleId;
        this.duration = duration;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.flight = flight;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
