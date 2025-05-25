package org.odontoclin.logic;

import jakarta.persistence.*;

@Entity
public class LegalGuard extends Person {

    private String typeOfGuard;

    public LegalGuard() {
        // Void constructor
    }

    public LegalGuard(int id, String dni, String name, String lastName, String phone, String address, String dateOfBirth, String typeOfGuard) {
        super(id, dni, name, lastName, phone, address, dateOfBirth);
        this.typeOfGuard = typeOfGuard;
    }

    public String getTypeOfGuard() {
        return typeOfGuard;
    }

    public void setTypeOfGuard(String typeOfGuard) {
        this.typeOfGuard = typeOfGuard;
    }
}
