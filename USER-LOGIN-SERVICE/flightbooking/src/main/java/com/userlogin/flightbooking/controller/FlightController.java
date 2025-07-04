package com.userlogin.flightbooking.controller;

import com.userlogin.flightbooking.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/flights")
public class FlightController {
    
    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/search")
    public String redirectToFlightSearch(HttpServletRequest request) {
        String token = getJwtToken(request);
        if (token != null) {
            return "redirect:http://localhost:8082/flights/search?token=" + token;
        }
        return "redirect:/users/login?error=Authentication required";
    }
    
    private String getJwtToken(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails) {
            org.springframework.security.core.userdetails.UserDetails userDetails = 
                (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
            return jwtService.generateToken(userDetails, "USER");
        }
        return null;
    }
}