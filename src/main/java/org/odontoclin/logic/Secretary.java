package org.odontoclin.logic;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Secretary extends Person {

    private String sector;
    @OneToOne
    private User user;

    public Secretary() {
        // Void constructor
    }

    public Secretary(int id, String dni, String name, String lastName, String phone, String address, String dateOfBirth, String sector, User user) {
        super(id, dni, name, lastName, phone, address, dateOfBirth);
        this.sector = sector;
        this.user = user;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
