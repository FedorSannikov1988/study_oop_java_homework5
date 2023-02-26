package model;

interface UserMapper {

    //работа с TXT
    public String mapForTXT(User user);

    public User mapForTXT(String line);
    
    //работа с CSV
    public String mapForCSV(User user);

    public User mapForCSV(String line);
}
