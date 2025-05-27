package org.odontoclin.logic;

import org.odontoclin.persistence.PersistenceController;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;

public class Controller {

    PersistenceController persisController = new PersistenceController();

    public void createUser(String username, String password, String role) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        persisController.createUser(user);
    }

}
