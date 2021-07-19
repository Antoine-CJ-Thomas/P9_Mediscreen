package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.User;

import java.util.List;

@Component
public interface UserServiceInterface {

    public User getUser(int id);

    public List<User> getUserList();
}
