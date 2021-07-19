package userInformation.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInformation.model.User;
import userInformation.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private UserRepository userRepository;

    public UserService() {

    }

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User getUser(int id) {
        logger.info("getUser(" + id + ")");

        Optional<User> userOptional = userRepository.findById(1);
        User user = userOptional.get();

        return user;
    }

    @Override
    public List<User> getUserList() {
        logger.info("getUserList()");

        Iterable<User> userIterable = userRepository.findAll();

        List<User> userList = (List<User>) userIterable;

        return userList;
    }
}
