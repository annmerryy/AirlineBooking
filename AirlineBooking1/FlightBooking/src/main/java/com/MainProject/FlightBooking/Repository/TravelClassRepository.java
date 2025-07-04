package com.MainProject.FlightBooking.Repository;

import com.MainProject.FlightBooking.Entity.TravelClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelClassRepository extends JpaRepository<TravelClass, Integer> {
}
