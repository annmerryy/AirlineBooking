package com.MainProject.FlightBooking.DTO;


import java.time.LocalDate;

public class FlightSearchRequestDTO {
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private String travelClass;
    private Integer numberOfTravellers;

    public FlightSearchRequestDTO() {
    }

    public FlightSearchRequestDTO(String departureCity, Integer noOfPassengers, String travelClass, LocalDate departureDate, String arrivalCity) {
        this.departureCity = departureCity;
        this.numberOfTravellers = noOfPassengers;
        this.travelClass = travelClass;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public Integer getNumberOfTravellers() {
        return numberOfTravellers;
    }

    public void setNumberOfTravellers(Integer numberOfTravellers) {
        this.numberOfTravellers = numberOfTravellers;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
