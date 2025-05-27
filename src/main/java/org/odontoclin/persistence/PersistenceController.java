package org.odontoclin.persistence;

import org.odontoclin.logic.User;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenceController {

    DentistJpaController dentistJpa; // = new DentistJpaController();
    LegalGuardJpaController legalGuardJpa; // = new LegalGuardJpaController();
    PatientJpaController patientJpa; // = new PatientJpaController();
    PersonJpaController personJpa; // = new PersonJpaController();
    ScheduleJpaController scheduleJpa; // = new ScheduleJpaController();
    SecretaryJpaController secretaryJpa; // = new SecretaryJpaController();
    ShiftJpaController shiftJpa; // = new ShiftJpaController();
    UserJpaController userJpa; // = new UserJpaController();

    public PersistenceController() {

        dentistJpa = new DentistJpaController();
        legalGuardJpa = new LegalGuardJpaController();
        patientJpa = new PatientJpaController();
        personJpa = new PersonJpaController();
        scheduleJpa = new ScheduleJpaController();
        secretaryJpa = new SecretaryJpaController();
        shiftJpa = new ShiftJpaController();
        userJpa = new UserJpaController();
    }

    public void createUser(User user) {

        userJpa.create(user);
    }

    public List<User> getUsers() {

        return userJpa.findUserEntities();
    }

    public void deleteUser(int id) {
        try {
            userJpa.destroy(id);
        } catch (NonexistentEntityException e) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public User bringUser(int id) {

        return userJpa.findUser(id);
    }

    public void editUser(User user) {

        try {
            userJpa.edit(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}