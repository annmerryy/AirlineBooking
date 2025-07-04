-- create database
create database airlineDB;

-- Use database
use airlineDB;

-- Creating Travel_Class table to store the three travel classes
CREATE TABLE travel_class (
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(50) NOT NULL UNIQUE CHECK (class_name IN ('Economy', 'Business', 'First Class'))
);

-- Creating Flight table to store flight details for different routes
CREATE TABLE flight (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_number VARCHAR(10) NOT NULL UNIQUE,
    airline_name VARCHAR(50) NOT NULL,
    INDEX idx_airline_name (airline_name)
);

-- Creating Flight_Schedule table to store specific flight assignments on dates
CREATE TABLE flight_schedule (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_id INT NOT NULL,
    departure_date DATETIME NOT NULL,
    arrival_date DATETIME NOT NULL,
    departure_city VARCHAR(3) NOT NULL,
    arrival_city VARCHAR(3) NOT NULL,
    duration INT NOT NULL,
    FOREIGN KEY (flight_id) REFERENCES flight(flight_id) ON DELETE CASCADE,
    UNIQUE (flight_id, departure_date),
    INDEX idx_departure_city (departure_city),
    INDEX idx_arrival_city (arrival_city),
    INDEX idx_departure_date (departure_date)
);

-- Creating Seat table to store the 20 unique seats applicable to all flights
CREATE TABLE seat (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    seat_number VARCHAR(5) NOT NULL UNIQUE,
    class_id INT NOT NULL,
    FOREIGN KEY (class_id) REFERENCES travel_class(class_id) ON DELETE RESTRICT,
    INDEX idx_class_id (class_id)
);

-- Creating Flight_Cost table to store cost for each route and travel class
CREATE TABLE Flight_Cost (
    cost_id INT PRIMARY KEY AUTO_INCREMENT,
    schedule_id INT NOT NULL,
    class_id INT NOT NULL,
    cost INT NOT NULL CHECK (cost >= 0),
    FOREIGN KEY (schedule_id) REFERENCES flight_schedule(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES travel_class(class_id) ON DELETE RESTRICT,
    UNIQUE (schedule_id, class_id),
    INDEX idx_schedule_id (schedule_id)
);

-- Creating Reservation table to store booking details
CREATE TABLE reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    schedule_id INT NOT NULL,
    seat_id INT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    passenger_email VARCHAR(100) NOT NULL,
    passenger_gender VARCHAR(10) NOT NULL,
    booking_date DATETIME NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES flight_schedule(schedule_id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seat(seat_id) ON DELETE RESTRICT,
    UNIQUE (schedule_id, seat_id),
    INDEX idx_passenger_email (passenger_email),
    INDEX idx_booking_date (booking_date)
);