package org.odontoclin.logic;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Shift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_shift;
    @Temporal(TemporalType.DATE)
    private Date shift_date;
    private String shift_time;
    private String affection;
    @ManyToOne
    @JoinColumn(name = "id_dentist")
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    public Shift() {
        // Void constructor
    }

    public Shift(int id_shift, Date shift_date, String shift_time, String affection) {
        this.id_shift = id_shift;
        this.shift_date = shift_date;
        this.shift_time = shift_time;
        this.affection = affection;
    }

    public int getId_shift() {
        return id_shift;
    }

    public void setId_shift(int id_shift) {
        this.id_shift = id_shift;
    }

    public Date getShift_date() {
        return shift_date;
    }

    public void setShift_date(Date shift_date) {
        this.shift_date = shift_date;
    }

    public String getShift_time() {
        return shift_time;
    }

    public void setShift_time(String shift_time) {
        this.shift_time = shift_time;
    }

    public String getAffection() {
        return affection;
    }

    public void setAffection(String affection) {
        this.affection = affection;
    }
}
