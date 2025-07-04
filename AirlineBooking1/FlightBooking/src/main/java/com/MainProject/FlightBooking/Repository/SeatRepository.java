package com.MainProject.FlightBooking.Repository;

import com.MainProject.FlightBooking.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByTravelClass_ClassId(Integer classId);

    @Query("SELECT r.seat.seatId FROM Reservation r WHERE r.flightSchedule.scheduleId = :scheduleId")
    List<Integer> findBookedSeatIdsBySchedule(Integer scheduleId);
}
