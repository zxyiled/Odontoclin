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

    public List<User> getUsers() {

        return persisController.getUsers();

    }

    public void deleteUser(int id) {
        persisController.deleteUser(id);
    }

    public User bringUser(int id) {

        return persisController.bringUser(id);
    }


    public void editUser(User user) {

        persisController.editUser(user);
    }

    public boolean checkLogin(String username, String password) {
        List <User> userList = persisController.bringUser();

        boolean isValid = false;
        for (User u : userList) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
