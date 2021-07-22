package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.User;

import java.util.List;

@Component
public interface UserServiceInterface {

    public User selectUser(int id);

    public List<User> selectUserList();

    public void updateUser(int id, User user);

    public void deleteUser(int id);

    public void insertUser(User user);
}
