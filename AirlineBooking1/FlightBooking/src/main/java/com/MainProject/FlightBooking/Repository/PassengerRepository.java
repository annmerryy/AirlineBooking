package com.MainProject.FlightBooking.Repository;

import com.MainProject.FlightBooking.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}