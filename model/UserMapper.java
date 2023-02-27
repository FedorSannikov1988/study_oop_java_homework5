package model;

public class UserMapper {

    public UserMapper() {
    }

    public String mapForTXT(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User mapForTXT(String line) {
        String[] lines = line.split(",");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

    public String mapForCSV(User user) {
        return String.format("%s;%s;%s;%s" + "\n", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User mapForCSV(String line) {
        String[] lines = line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

}
