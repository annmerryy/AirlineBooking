package com.MainProject.FlightBooking.SearchService;

import com.MainProject.FlightBooking.DTO.FlightSearchRequestDTO;
import com.MainProject.FlightBooking.DTO.FlightSearchResultDTO;
import com.MainProject.FlightBooking.Repository.FlightRepository;
import com.MainProject.FlightBooking.Repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class  SearchServiceImpl {

  //  private final FlightRepository flightRepository;
    private final FlightScheduleRepository flightScheduleRepository;

    @Autowired
    public SearchServiceImpl(FlightScheduleRepository flightScheduleRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
    }

    public List<FlightSearchResultDTO> searchFlights(@RequestBody FlightSearchRequestDTO request){
        // Convert LocalDate to LocalDateTime for the start of the day
        java.time.LocalDateTime departureDateTime = request.getDepartureDate().atStartOfDay();

        System.out.println("Travel class requested: '" + request.getTravelClass() + "'");

        return flightScheduleRepository.findAvailableFlightsWithDetails(
            request.getDepartureCity(),
            request.getArrivalCity(),
            departureDateTime,
            request.getTravelClass(),
            request.getNumberOfTravellers()
        );
    }






}
