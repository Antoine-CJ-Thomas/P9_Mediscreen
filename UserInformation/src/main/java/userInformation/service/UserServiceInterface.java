package userInformation.service;

import org.springframework.stereotype.Component;
import userInformation.model.User;

import java.util.List;

@Component
public interface UserServiceInterface {

    void insertUser(User user);

    public User getUser(int id);

    public List<User> getUserList();

    public void updateUser(int id, User user);

    public void deleteUser(int id);
}
