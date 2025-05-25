package org.odontoclin.logic;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_schedule;
    private String start_time;
    private String end_time;

    public Schedule() {
        // Void constructor
    }

    public Schedule(int id_schedule, String start_time, String end_time) {
        this.id_schedule = id_schedule;
        this.start_time = start_time;
    }

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }
}
