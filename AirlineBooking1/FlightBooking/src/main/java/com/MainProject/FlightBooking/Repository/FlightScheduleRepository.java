package com.MainProject.FlightBooking.Repository;

import com.MainProject.FlightBooking.DTO.FlightSearchResultDTO;
import com.MainProject.FlightBooking.Entity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {

    @Query(value = "SELECT " +
            "    new com.MainProject.FlightBooking.DTO.FlightSearchResultDTO(" +
            "        f.airlineName, " +
            "        f.flightNumber, " +
            "        fs.departureCity, " +
            "        fs.departureDate, " +
            "        fs.duration, " +
            "        fs.arrivalCity, " +
            "        fs.arrivalDate, " +
            "        fc.cost, " +
            "        fs.scheduleId" +
            "    ) " +
            "FROM " +
            "    FlightSchedule fs " +
            "JOIN " +
            "    fs.flight f " +
            "JOIN " +
            "    FlightCost fc ON fc.flightSchedule = fs " +
            "JOIN " +
            "    fc.travelClass tc " +
            "WHERE " +
            "    fs.departureCity = :departureCity " +
            "    AND fs.arrivalCity = :arrivalCity " +
            "    AND FUNCTION('DATE', fs.departureDate) = FUNCTION('DATE', :departureDate) " +
            "    AND tc.className = :travelClassName " +
            "    AND (" +
            "        (SELECT COUNT(s) FROM Seat s WHERE s.travelClass.classId = tc.classId) - " +
            "        (SELECT COUNT(r) FROM Reservation r WHERE r.flightSchedule.scheduleId = fs.scheduleId AND r.seat.travelClass.classId = tc.classId)" +
            "    ) >= :numberOfTravellers")

    List<FlightSearchResultDTO> findAvailableFlightsWithDetails( @Param("departureCity") String departureCity,
                                                                 @Param("arrivalCity") String arrivalCity,
                                                                 @Param("departureDate") LocalDateTime departureDate,
                                                                 @Param("travelClassName") String travelClassName,
                                                                 @Param("numberOfTravellers") Integer numberOfTravellers
    );


}
