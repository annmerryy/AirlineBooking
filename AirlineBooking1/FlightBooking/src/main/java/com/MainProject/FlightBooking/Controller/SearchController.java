package com.MainProject.FlightBooking.Controller;

import com.MainProject.FlightBooking.DTO.FlightSearchRequestDTO;
import com.MainProject.FlightBooking.DTO.FlightSearchResultDTO;
import com.MainProject.FlightBooking.SearchService.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class SearchController {

    private final SearchServiceImpl searchService;

    @Autowired
    public SearchController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String token, HttpServletRequest request) {
        if (token != null) {
            // Store token in session for subsequent requests
            request.getSession().setAttribute("jwtToken", token);
        }
        return "redirect:/searchInputs";
    }

    @GetMapping("/searchInputs")
    public String searchInputs(@RequestParam(required = false) String token,
                               HttpServletRequest request,
                               Model model) {

        // Check authentication
        if (!isAuthenticated(token, request)) {
            return "redirect:http://localhost:8081/login-service/users/login?error=Authentication required";
        }

        return "search-inputs";
    }

    // Add new endpoint for login service integration
    @GetMapping("/flights/search")
    public String flightSearch(@RequestParam(required = false) String token,
                               HttpServletRequest request,
                               Model model) {

        // Check authentication
        if (!isAuthenticated(token, request)) {
            return "redirect:http://localhost:8081/login-service/users/login?error=Authentication required";
        }

        // Store token for session
        if (token != null) {
            request.getSession().setAttribute("jwtToken", token);
        }

        return "search-inputs";
    }

    @PostMapping("/listFlights")
    public String listFlights(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam("travelClass") String travelClass,
            @RequestParam("numberOfTravellers") Integer numberOfTravellers,
            HttpServletRequest request,
            Model model) {

        // Check authentication
        if (!isAuthenticated(null, request)) {
            return "redirect:http://localhost:8081/login-service/users/login?error=Session expired";
        }

        FlightSearchRequestDTO requestDTO = new FlightSearchRequestDTO(
                departureCity,
                numberOfTravellers,
                travelClass,
                departureDate,
                arrivalCity
        );

        List<FlightSearchResultDTO> flights = searchService.searchFlights(requestDTO);
        model.addAttribute("flights", flights);
        model.addAttribute("numberOfTravellers", numberOfTravellers);
        model.addAttribute("travelClass", travelClass);

        return "list-flights";
    }

    @GetMapping("/selectFlight")
    public String selectFlight(@RequestParam("scheduleId") Integer scheduleId,
                               @RequestParam("travelClass") String travelClass,
                               @RequestParam("numberOfTravellers") Integer numberOfTravellers,
                               HttpServletRequest request,
                               Model model) {

        // Check authentication
        if (!isAuthenticated(null, request)) {
            return "redirect:http://localhost:8081/login-service/users/login?error=Session expired";
        }

        // Get classId from travelClass name
        Integer classId = getClassIdFromName(travelClass);

        return "redirect:/passenger-details?scheduleId=" + scheduleId +
                "&classId=" + classId + "&numberOfTravellers=" + numberOfTravellers;
    }

    private Integer getClassIdFromName(String className) {
        switch (className.toLowerCase()) {
            case "economy": return 1;
            case "business": return 2;
            case "first class": return 3;
            default: return 1;
        }
    }

    // Helper method to check authentication
    private boolean isAuthenticated(String token, HttpServletRequest request) {
        // Check Spring Security context first
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return true;
        }

        // Check session token
        String sessionToken = (String) request.getSession().getAttribute("jwtToken");
        if (sessionToken != null || token != null) {
            return true;
        }

        return false;
    }
}
