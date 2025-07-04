package com.MainProject.FlightBooking.Repository;

import com.MainProject.FlightBooking.Entity.FlightCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightCostRepository extends JpaRepository<FlightCost, Integer> {
    List<FlightCost> findByFlightSchedule_ScheduleIdAndTravelClass_ClassId(Integer scheduleId, Integer classId);
}
