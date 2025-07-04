package com.MainProject.FlightBooking.DTO;

import java.time.LocalDateTime;

    public class FlightSearchResultDTO {

        private String airlineName;
        private String flightNumber;
        private String departureCity;
        private LocalDateTime departureDate;
        private Integer duration;
        private String arrivalCity;
        private LocalDateTime arrivalDate;
        private Integer cost;
        private Integer scheduleId;

    public FlightSearchResultDTO(){

    }

    public FlightSearchResultDTO(String airlineName, String flightNumber, String departureCity, LocalDateTime departureDate, Integer duration, String arrivalCity, LocalDateTime arrivalDate, Integer cost, Integer scheduleId) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.duration = duration;
        this.arrivalCity = arrivalCity;
        this.arrivalDate = arrivalDate;
        this.cost = cost;
        this.scheduleId = scheduleId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}
