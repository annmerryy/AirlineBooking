SELECT
    f.airline_name,
    f.flight_number,
    fs.departure_city,
    fs.departure_date,
    fs.duration,
    fs.arrival_city,
    fs.arrival_date,
    fc.cost AS price_of_one_ticket
FROM
    flight_schedule fs
JOIN
    flight f ON fs.flight_id = f.flight_id
JOIN
    Flight_Cost fc ON fs.schedule_id = fc.schedule_id
JOIN
    travel_class tc ON fc.class_id = tc.class_id
LEFT JOIN (
    SELECT
        r.schedule_id,
        s.class_id,
        COUNT(r.seat_id) AS booked_seats
    FROM
        reservation r
    JOIN
        seat s ON r.seat_id = s.seat_id
    GROUP BY
        r.schedule_id, s.class_id
) AS booked_seats_Per_class ON fs.schedule_id = booked_seats_per_class.schedule_id AND tc.class_id = booked_seats_per_class.class_id
WHERE
    fs.departure_city = 'NYC' -- User input: departure_city
    AND fs.arrival_city = 'LAX' -- User input: arrival_city
    AND DATE(fs.departure_date) = '2025-07-15' -- User input: departure_date
    AND tc.class_name = 'Economy' -- User input: travel_class
    AND (
        (SELECT COUNT(seat_id) FROM seat WHERE class_id = tc.class_id) -
        COALESCE(booked_seats_Per_class.booked_seats, 0)
    ) >= 2; -- User input: number_of_travellers (e.g., 2)