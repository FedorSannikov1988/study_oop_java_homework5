package controllers;

import model.Repository;
import model.User;
import views.Validation;

import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validation validator;

    public UserController(Repository repository, Validation validator)
    {
        this.validator = validator;
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {

        validator.validateUser(user);
        repository.createUser(user);
    }

    public User readUser(String userId) throws Exception {

        List<User> users = repository.getAllUsers();
        User user = userSearch(userId, users);
        return user;
    }

    private static User userSearch(String userId, List<User> users) throws Exception {

        int namber = 0;

        try {
            namber = Integer.parseInt(userId);
        }

        catch(Exception ex) {
            throw new Exception("You did not enter a number in integer format");
        }

        if (namber <= 0) {
            throw new Exception("Number cannot be less than or equal to zero");
        }

        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }

    public List<User> readAllUsers() {

        return repository.getAllUsers();
    }

    public void updateUser(String userId, User newUser) throws Exception {

        validator.validateUser(newUser);
        List<User> users = repository.getAllUsers();

        User user = userSearch(userId,users);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhone(newUser.getPhone());

        repository.saveUsers(users);
    }

    public void deleteUser(String deleteUserId) throws Exception {

        List<User> users = repository.getAllUsers();
        User user = userSearch(deleteUserId, users);
        users.remove(Integer.parseInt(user.getId())-1);
        repository.saveUsers(users);
    }
}
