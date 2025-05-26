package org.odontoclin.persistence;

public class PersistenceController {

    DentistJpaController dentistJpa = new DentistJpaController();
    LegalGuardJpaController legalGuardJpa = new LegalGuardJpaController();
    PatientJpaController patientJpa = new PatientJpaController();
    PersonJpaController personJpa = new PersonJpaController();
    ScheduleJpaController scheduleJpa = new ScheduleJpaController();
    SecretaryJpaController secretaryJpa = new SecretaryJpaController();
    ShiftJpaController ShiftJpa = new ShiftJpaController();
    UserJpaController userJpa = new UserJpaController();

    public PersistenceController() {}

}
