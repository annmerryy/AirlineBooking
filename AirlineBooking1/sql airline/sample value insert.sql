INSERT INTO travel_class (class_name) VALUES ('Economy');
INSERT INTO travel_class (class_name) VALUES ('Business');
INSERT INTO travel_class (class_name) VALUES ('First Class');


INSERT INTO flight (flight_number, airline_name) VALUES ('AA101', 'American Airlines');
INSERT INTO flight (flight_number, airline_name) VALUES ('BA202', 'British Airways');
INSERT INTO flight (flight_number, airline_name) VALUES ('DL303', 'Delta Airlines');
INSERT INTO flight_schedule (flight_id, departure_date, arrival_date, departure_city, arrival_city, duration) VALUES (1, '2025-07-15 08:00:00', '2025-07-15 11:00:00', 'NYC', 'LAX', 180);
INSERT INTO flight_schedule (flight_id, departure_date, arrival_date, departure_city, arrival_city, duration) VALUES (2, '2025-07-16 10:00:00', '2025-07-16 18:00:00', 'LDN', 'DXB', 480);
INSERT INTO flight_schedule (flight_id, departure_date, arrival_date, departure_city, arrival_city, duration) VALUES (3, '2025-07-17 14:00:00', '2025-07-17 16:30:00', 'CHI', 'MIA', 150);

-- Economy Class Seats (e.g., 10 seats)
INSERT INTO seat (seat_number, class_id) VALUES ('1A', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('1B', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('1C', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('1D', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('2A', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('2B', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('2C', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('2D', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('3A', 1);
INSERT INTO seat (seat_number, class_id) VALUES ('3B', 1);

-- Business Class Seats (e.g., 6 seats)
INSERT INTO seat (seat_number, class_id) VALUES ('4A', 2);
INSERT INTO seat (seat_number, class_id) VALUES ('4B', 2);
INSERT INTO seat (seat_number, class_id) VALUES ('4C', 2);
INSERT INTO seat (seat_number, class_id) VALUES ('5A', 2);
INSERT INTO seat (seat_number, class_id) VALUES ('5B', 2);
INSERT INTO seat (seat_number, class_id) VALUES ('5C', 2);

-- First Class Seats (e.g., 4 seats)
INSERT INTO seat (seat_number, class_id) VALUES ('6A', 3);
INSERT INTO seat (seat_number, class_id) VALUES ('6B', 3);
INSERT INTO seat (seat_number, class_id) VALUES ('7A', 3);
INSERT INTO seat (seat_number, class_id) VALUES ('7B', 3);



-- Costs for schedule_id 1 (NYC to LAX)
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (1, 1, 250); -- Economy
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (1, 2, 500); -- Business
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (1, 3, 1000); -- First Class

-- Costs for schedule_id 2 (LDN to DXB)
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (2, 1, 400); -- Economy
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (2, 2, 800); -- Business
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (2, 3, 1500); -- First Class

-- Costs for schedule_id 3 (CHI to MIA)
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (3, 1, 150); -- Economy
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (3, 2, 300); -- Business
INSERT INTO Flight_Cost (schedule_id, class_id, cost) VALUES (3, 3, 600); -- First Class