package com.MainProject.FlightBooking.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_cost")
public class FlightCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cost_id")
    private Integer costId;

    @ManyToOne
    @JoinColumn(name="schedule_id",nullable = false)
    private FlightSchedule flightSchedule;

    @ManyToOne
    @JoinColumn(name="class_id",nullable = false)
    private TravelClass travelClass;

    @Column(name="cost",nullable = false)
    private Integer cost;

    public FlightCost(){

    }

    public FlightCost(Integer costId, FlightSchedule flightSchedule, TravelClass travelClass, Integer cost) {
        this.costId = costId;
        this.flightSchedule = flightSchedule;
        this.travelClass = travelClass;
        this.cost = cost;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
