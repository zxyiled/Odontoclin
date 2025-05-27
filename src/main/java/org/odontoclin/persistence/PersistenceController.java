package org.odontoclin.persistence;

import org.odontoclin.logic.User;

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
}