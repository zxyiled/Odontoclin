package org.odontoclin.logic;

import org.odontoclin.persistence.PersistenceController;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;

public class Controller {

    PersistenceController persisController = new PersistenceController();

    public void createUser(int id, String username, String password, String role) {

        User user = new User(id, username, password, role);
        persisController.userJpa.createUser(user);
    }

}
