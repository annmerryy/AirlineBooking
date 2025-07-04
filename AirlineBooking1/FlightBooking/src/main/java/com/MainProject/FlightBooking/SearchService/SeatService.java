package com.MainProject.FlightBooking.SearchService;

import com.MainProject.FlightBooking.Entity.Seat;
import com.MainProject.FlightBooking.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public Map<Seat, Boolean> getSeatsWithAvailability(Integer scheduleId, Integer classId) {
        List<Seat> allSeats = seatRepository.findByTravelClass_ClassId(classId);
        List<Integer> bookedSeatIds = seatRepository.findBookedSeatIdsBySchedule(scheduleId);

        Map<Seat, Boolean> seatStatusMap = new LinkedHashMap<>();
        for (Seat seat : allSeats) {
            seatStatusMap.put(seat, !bookedSeatIds.contains(seat.getSeatId()));
        }
        return seatStatusMap;
    }

    public Seat getSeatById(Integer seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + seatId));
    }
}