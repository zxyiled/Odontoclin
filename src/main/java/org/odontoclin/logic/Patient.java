package org.odontoclin.logic;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Patient extends Person implements Serializable {

    //private int id_patient;
    private boolean has_SW;
    private String typeOfBlood;
    @OneToOne
    private LegalGuard alegalGuard;
    @OneToMany(mappedBy = "patient")
    private List<Shift> shiftList;

    public Patient() {
        // Void constructor
    }

    public Patient(int id, String dni, String name, String lastName, String phone, String address, String dateOfBirth, boolean has_SW, String typeOfBlood, LegalGuard alegalGuard, List<Shift> shiftList) {
        super(id, dni, name, lastName, phone, address, dateOfBirth);
        this.has_SW = has_SW;
        this.typeOfBlood = typeOfBlood;
        this.alegalGuard = alegalGuard;
        this.shiftList = shiftList;
    }

    public boolean isHas_SW() {
        return has_SW;
    }

    public void setHas_SW(boolean has_SW) {
        this.has_SW = has_SW;
    }

    public String getTypeOfBlood() {
        return typeOfBlood;
    }

    public void setTypeOfBlood(String typeOfBlood) {
        this.typeOfBlood = typeOfBlood;
    }

    public LegalGuard getAlegalGuard() {
        return alegalGuard;
    }

    public void setAlegalGuard(LegalGuard alegalGuard) {
        this.alegalGuard = alegalGuard;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }
}
