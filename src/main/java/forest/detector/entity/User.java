package forest.detector.entity;

public class User {

    private String firstName;
    private String secondName;
    private String userName;
    private String password;
    private String email;
    private String gender;

    public User() {
    }

    public User(String firstName, String secondName, String userName, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String secondName, String userName, String password, String email, String gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
