package com.MainProject.FlightBooking.Controller;

import com.MainProject.FlightBooking.Entity.Passenger;
import com.MainProject.FlightBooking.Entity.Seat;

import com.MainProject.FlightBooking.SearchService.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PassengerController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private com.MainProject.FlightBooking.Repository.FlightScheduleRepository flightScheduleRepository;
    
    @Autowired
    private com.MainProject.FlightBooking.Repository.FlightCostRepository flightCostRepository;
    
    @Autowired
    private com.MainProject.FlightBooking.Repository.ReservationRepository reservationRepository;
    
    @Autowired
    private com.MainProject.FlightBooking.Repository.SeatRepository seatRepository;

    @GetMapping("/passenger-details")
    public String showPassengerForms(@RequestParam("scheduleId") Integer scheduleId,
                                   @RequestParam("classId") Integer classId,
                                   @RequestParam("numberOfTravellers") Integer numberOfTravellers,
                                   Model model) {

        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < numberOfTravellers; i++) {
            passengers.add(new Passenger());
        }

        try {
            Map<Seat, Boolean> seatStatusMap = seatService.getSeatsWithAvailability(scheduleId, classId);
            model.addAttribute("seatStatusMap", seatStatusMap);
        } catch (Exception e) {
            model.addAttribute("seatStatusMap", new java.util.HashMap<>());
        }
        
        model.addAttribute("passengers", passengers);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("classId", classId);
        model.addAttribute("numberOfTravellers", numberOfTravellers);

        return "passenger";
    }

    @PostMapping("/seat-selection")
    public String showSeatSelection(@RequestParam("scheduleId") Integer scheduleId,
                                  @RequestParam("classId") Integer classId,
                                  @RequestParam Map<String, String> allParams,
                                  Model model) {

        List<Passenger> passengers = new ArrayList<>();
        int passengerCount = 0;
        
        // Extract passenger data from form parameters
        while (allParams.containsKey("firstName" + passengerCount)) {
            Passenger passenger = new Passenger();
            passenger.setFirstName(allParams.get("firstName" + passengerCount));
            passenger.setLastName(allParams.get("lastName" + passengerCount));
            passenger.setEmail(allParams.get("email" + passengerCount));
            passenger.setAge(Integer.parseInt(allParams.get("age" + passengerCount)));
            passenger.setGender(allParams.get("gender" + passengerCount));
            passengers.add(passenger);
            passengerCount++;
        }

        try {
            Map<Seat, Boolean> seatStatusMap = seatService.getSeatsWithAvailability(scheduleId, classId);
            model.addAttribute("seatStatusMap", seatStatusMap);
        } catch (Exception e) {
            model.addAttribute("seatStatusMap", new java.util.HashMap<>());
        }
        
        model.addAttribute("passengers", passengers);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("classId", classId);

        return "seat-grid";
    }

    @PostMapping("/submit-booking")
    public String showPayment(@RequestParam("scheduleId") Integer scheduleId,
                            @RequestParam("classId") Integer classId,
                            @RequestParam Map<String, String> allParams,
                            Model model) {

        // Extract passenger data and seat selections
        List<Passenger> passengers = new ArrayList<>();
        List<String> seatNumbers = new ArrayList<>();
        
        int passengerCount = 0;
        while (allParams.containsKey("firstName" + passengerCount)) {
            Passenger passenger = new Passenger();
            passenger.setFirstName(allParams.get("firstName" + passengerCount));
            passenger.setLastName(allParams.get("lastName" + passengerCount));
            passenger.setEmail(allParams.get("email" + passengerCount));
            passenger.setAge(Integer.parseInt(allParams.get("age" + passengerCount)));
            passenger.setGender(allParams.get("gender" + passengerCount));
            passengers.add(passenger);
            
            String seatNumber = allParams.get("seatNumber" + passengerCount);
            seatNumbers.add(seatNumber);
            passengerCount++;
        }

        // Get cost from database
        int costPerPassenger = 100; // Default fallback
        try {
            // Get cost from FlightCost table based on scheduleId and classId
            var flightCosts = flightCostRepository.findByFlightSchedule_ScheduleIdAndTravelClass_ClassId(scheduleId, classId);
            if (!flightCosts.isEmpty()) {
                costPerPassenger = flightCosts.get(0).getCost();
            }
        } catch (Exception e) {

        }
        
        int numberOfPassengers = passengers.size();
        int totalCost = numberOfPassengers * costPerPassenger;

        model.addAttribute("passengers", passengers);
        model.addAttribute("seatNumbers", seatNumbers);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("classId", classId);
        model.addAttribute("costPerPassenger", costPerPassenger);
        model.addAttribute("numberOfPassengers", numberOfPassengers);
        model.addAttribute("totalCost", totalCost);

        return "payment";
    }

    @PostMapping("/make-payment")
    public String makePayment(@RequestParam("scheduleId") Integer scheduleId,
                            @RequestParam("classId") Integer classId,
                            @RequestParam Map<String, String> allParams,
                            Model model) {

        // Extract all data for ticket
        List<Passenger> passengers = new ArrayList<>();
        List<String> seatNumbers = new ArrayList<>();
        int passengerCount = 0;
        
        while (allParams.containsKey("firstName" + passengerCount)) {
            Passenger passenger = new Passenger();
            passenger.setFirstName(allParams.get("firstName" + passengerCount));
            passenger.setLastName(allParams.get("lastName" + passengerCount));
            passenger.setEmail(allParams.get("email" + passengerCount));
            passenger.setAge(Integer.parseInt(allParams.get("age" + passengerCount)));
            passenger.setGender(allParams.get("gender" + passengerCount));
            passengers.add(passenger);
            
            String seatNumber = allParams.get("seatNumber" + passengerCount);
            seatNumbers.add(seatNumber);
            passengerCount++;
        }

        // Save reservations to database
        List<Integer> reservationIds = new ArrayList<>();
        try {
            var flightSchedule = flightScheduleRepository.findById(scheduleId);
            if (flightSchedule.isPresent()) {
                for (int i = 0; i < passengers.size(); i++) {
                    Passenger passenger = passengers.get(i);
                    String seatNumber = seatNumbers.get(i);
                    
                    // Find seat by seat number and class
                    var seats = seatRepository.findByTravelClass_ClassId(classId);
                    var selectedSeat = seats.stream()
                        .filter(seat -> seat.getSeatNumber().equals(seatNumber))
                        .findFirst();
                    
                    if (selectedSeat.isPresent()) {
                        // Create reservation
                        var reservation = new com.MainProject.FlightBooking.Entity.Reservation();
                        reservation.setFlightSchedule(flightSchedule.get());
                        reservation.setSeat(selectedSeat.get());
                        reservation.setPassengerName(passenger.getFirstName() + " " + passenger.getLastName());
                        reservation.setPassengerEmail(passenger.getEmail());
                        reservation.setPassengerGender(passenger.getGender());
                        reservation.setBookingDate(java.time.LocalDateTime.now());
                        
                        var savedReservation = reservationRepository.save(reservation);
                        reservationIds.add(savedReservation.getReservationId());
                    }
                }
            }
        } catch (Exception e) {

        }

        // Get flight details from database
        String airline = "Air India";
        String flightNumber = "AI-123";
        String departureCity = "New York";
        String arrivalCity = "Los Angeles";
        String departureTime = "10:30 AM";
        String arrivalTime = "2:30 PM";
        String duration = "4 hours 30 minutes";
        String departureDate = "2025-06-20";
        int price = 100;
        
        try {
            var flightSchedule = flightScheduleRepository.findById(scheduleId);
            if (flightSchedule.isPresent()) {
                var schedule = flightSchedule.get();
                airline = schedule.getFlight().getAirlineName();
                flightNumber = schedule.getFlight().getFlightNumber();
                departureCity = schedule.getDepartureCity();
                arrivalCity = schedule.getArrivalCity();
                departureTime = schedule.getDepartureDate().toLocalTime().toString();
                arrivalTime = schedule.getArrivalDate().toLocalTime().toString();
                duration = schedule.getDuration() + " minutes";
                departureDate = schedule.getDepartureDate().toLocalDate().toString();
            }
            
            // Get price from FlightCost table
            var flightCosts = flightCostRepository.findByFlightSchedule_ScheduleIdAndTravelClass_ClassId(scheduleId, classId);
            if (!flightCosts.isEmpty()) {
                price = flightCosts.get(0).getCost();
            }
        } catch (Exception e) {

        }

        model.addAttribute("passengers", passengers);
        model.addAttribute("seatNumbers", seatNumbers);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("totalCost", allParams.get("totalCost"));
        model.addAttribute("airline", airline);
        model.addAttribute("flightNumber", flightNumber);
        model.addAttribute("departureCity", departureCity);
        model.addAttribute("departureTime", departureTime);
        model.addAttribute("arrivalCity", arrivalCity);
        model.addAttribute("arrivalTime", arrivalTime);
        model.addAttribute("duration", duration);
        model.addAttribute("price", String.valueOf(price));
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("reservationIds", reservationIds);
        model.addAttribute("bookingTime", java.time.LocalDateTime.now().toString());

        return "ticket";
    }
}