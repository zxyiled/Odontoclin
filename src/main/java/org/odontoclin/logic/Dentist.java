package org.odontoclin.logic;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Dentist extends Person implements Serializable {

    //private int id_dentist;
    private String specialty;
    @OneToMany(mappedBy = "dentist")
    private List<Shift> shiftList;
    @OneToOne
    private User user;
    @OneToOne
    private Schedule schedule;

    public Dentist() {
        // Void constructor
    }

    public Dentist(int id, String dni, String name, String lastName, String phone, String address, String dateOfBirth, String specialty, List<Shift> shiftList, User user, Schedule schedule) {
        super(id, dni, name, lastName, phone, address, dateOfBirth);
        this.specialty = specialty;
        this.shiftList = shiftList;
        this.user = user;
        this.schedule = schedule;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
