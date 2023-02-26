package views;

import model.User;
import controllers.UserController;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private final UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {

        Commands com;

        while (true) {

            String command = prompt("Введите команду: ");

            try {
                com = Commands.valueOf(command.toUpperCase());

                switch (com) {
                    case EXIT:
                        return;
                    case HELP:
                        listCommands();
                        break;
                    case DELETE:
                        deleteRecord();
                        break;
                    case CREATE:
                        createUser();
                        break;
                    case READ:
                        readUser();
                        break;
                    case LIST:
                        listUsers();
                        break;
                    case UPDATE:
                        updateUser();
                        break;
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void deleteRecord() throws Exception {

        String deleteId = prompt("Введите ID удаляемого юзера: ");
        userController.deleteUser(deleteId);
    }

    private void listCommands() {

        Commands[] arrayCommand = Commands.values();

        System.out.print("\n");
        for (Commands item: arrayCommand) {
            System.out.println(item);
        }
        System.out.print("\n");
    }

    private void updateUser() throws Exception {

        String readId = prompt("Введите ID редактируемой записи юзера: ");
        userController.updateUser(readId, inputUser());
    }

    private void listUsers() {

        List<User> listUsers = userController.readAllUsers();

        System.out.print("\n");
        for (User user : listUsers) {
            System.out.println(user + "\n");
        }
    }

    private void readUser() throws Exception {

        String id = prompt("Идентификатор пользователя: ");

        User user = userController.readUser(id);
        System.out.println(user);
    }

    private User inputUser() {

        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    private void createUser() throws Exception {

        userController.saveUser(inputUser());
    }

    private String prompt(String message) {

        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
