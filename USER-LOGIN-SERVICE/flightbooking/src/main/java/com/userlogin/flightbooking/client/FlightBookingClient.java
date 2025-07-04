package com.userlogin.flightbooking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "flight-booking-service", url = "http://localhost:8082")
public interface FlightBookingClient {
    
    @GetMapping("/flights/search")
    String searchFlights(@RequestParam String from, 
                        @RequestParam String to, 
                        @RequestParam String departureDate,
                        @RequestHeader("Authorization") String token);
    
    @GetMapping("/booking/passenger-details")
    String getPassengerDetailsPage(@RequestParam String flightId,
                                  @RequestHeader("Authorization") String token);
    
    @GetMapping("/booking/seat-selection")
    String getSeatSelectionPage(@RequestParam String bookingId,
                               @RequestHeader("Authorization") String token);
    
    @GetMapping("/booking/payment")
    String getPaymentPage(@RequestParam String bookingId,
                         @RequestHeader("Authorization") String token);
    
    @GetMapping("/booking/boarding-pass")
    String getBoardingPass(@RequestParam String bookingId,
                          @RequestHeader("Authorization") String token);
}