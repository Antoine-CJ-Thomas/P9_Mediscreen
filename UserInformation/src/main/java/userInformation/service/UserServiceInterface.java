package userInformation.service;

import org.springframework.stereotype.Component;
import userInformation.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserServiceInterface {

    public User getUser(int id);

    public List<User> getUserList();
}
