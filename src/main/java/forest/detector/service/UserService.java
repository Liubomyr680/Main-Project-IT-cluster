package forest.detector.service;

import forest.detector.entity.User;
import forest.detector.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserService {

    private final UserRepository repo;

    public UserService(DataSource dataSource) {
        this.repo = new UserRepository(dataSource);
    }

    public List<User> getUsers(){
        return repo.getUsers();
    }

    public boolean checkUser(String username, String passwd){
        return repo.checkUser(username, passwd);
    }

    public String authenticateUser(String mail, String pass){
        return repo.authenticateUser(mail,pass);
    }

    public void setUserInDB(String email, String password, String first_name, String last_name){
        repo.setUserInDB(email, password, first_name, last_name);
    }

    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public void updateUserRoleInDB(String role,String email){
        repo.updateUserRoleInDB(role, email);
    }

    public void deleteUser(String email){
        repo.deleteUser(email);
    }
}
