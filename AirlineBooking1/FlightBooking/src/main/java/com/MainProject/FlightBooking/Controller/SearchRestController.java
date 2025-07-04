package com.MainProject.FlightBooking.Controller;


import com.MainProject.FlightBooking.DTO.FlightSearchRequestDTO;
import com.MainProject.FlightBooking.DTO.FlightSearchResultDTO;
import com.MainProject.FlightBooking.SearchService.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights/api")
public class SearchRestController {

    private SearchServiceImpl searchService;
    @Autowired
    public SearchRestController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightSearchResultDTO>>LsearchFlight(@RequestBody FlightSearchRequestDTO request){
        // Basic validation: ensure essential fields are not null
        if (request.getDepartureCity() == null || request.getArrivalCity() == null ||
                request.getDepartureDate() == null || request.getTravelClass() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<FlightSearchResultDTO>  results = searchService.searchFlights(request);

        return new ResponseEntity<>(results, HttpStatus.OK);

    }


}
