package forest.detector.repository;

import forest.detector.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

    private static UserRepository userRepository = new UserRepository();

    public UserRepository() {
        users = new ArrayList<>();
    }

    public static UserRepository getInstance(){
        return userRepository;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
