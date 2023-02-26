package model;

import java.util.List;
import java.util.ArrayList;

public class RepositoryFile implements Repository, UserMapper {

    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {

        List<String> lines = fileOperation.readAllLines();

        List<String> withoutEmptyLines = deleteEmptyLines(lines);

        List<User> users = new ArrayList<>();
        for (String line : withoutEmptyLines) {
            users.add( mapForCSV(line) );
        }
        return users;
    }

    private List<String> deleteEmptyLines(List<String> linesFromFile) {

        List<String> withoutEmptyLines = new ArrayList<>();

        for (String line: linesFromFile) {
            if (!line.isEmpty()){
                withoutEmptyLines.add(line);
            }
        }
        return withoutEmptyLines;
    }

    @Override
    public String createUser(User user) {

        List<User> users = getAllUsers();

        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }

        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        saveUsers(users);
        return id;
    }

    @Override
    public void saveUsers(List<User> users) {

        List<String> lines = new ArrayList<>();

            for (User item: users) {
                lines.add( mapForCSV(item) );
            }
            fileOperation.saveAllLines(lines);
    }

    @Override
    public String mapForTXT(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User mapForTXT(String line) {
        String[] lines = line.split(",");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

    @Override
    public String mapForCSV(User user) {
        return String.format("%s;%s;%s;%s" + "\n", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User mapForCSV(String line) {
        String[] lines = line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
}
