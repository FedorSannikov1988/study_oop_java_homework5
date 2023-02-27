package model;

import java.util.List;
import java.util.ArrayList;

public class RepositoryFile implements Repository {

    private FileOperation fileOperation;

    private UserMapper userMapper;

    public RepositoryFile(FileOperation fileOperation, UserMapper userMapper) {
        this.fileOperation = fileOperation;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {

        List<String> lines = fileOperation.readAllLines();

        List<String> withoutEmptyLines = deleteEmptyLines(lines);

        List<User> users = new ArrayList<>();
        for (String line : withoutEmptyLines) {
            users.add( userMapper.mapForCSV(line) );
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
                lines.add( userMapper.mapForCSV(item) );
            }
            fileOperation.saveAllLines(lines);
    }

}
