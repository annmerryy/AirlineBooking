package com.MainProject.FlightBooking.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="travel_class")
public class TravelClass {

    @Id
    @Column(name="class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    @Column(name="class_name", unique = true, nullable = false)
    private String className;

    public TravelClass(){

    }

    public TravelClass(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
