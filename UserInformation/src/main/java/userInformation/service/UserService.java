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
        logger.info("UserService(" + userRepository + ")");

        this.userRepository = userRepository;
    }

    @Override
    public void insertUser(User user) {
        logger.info("insertUser(" + user + ")");

        userRepository.save(user);
    }

    @Override
    public User selectUser(int id) {
        logger.info("selectUser(" + id + ")");

        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();

        return user;
    }

    @Override
    public List<User> selectUserList() {
        logger.info("selectUserList()");

        List<User> userList = (List<User>) userRepository.findAll();

        return userList;
    }

    @Override
    public void updateUser(int id, User user) {
        logger.info("updateUser(" + id + "," + user + ")");

        user.setUserId(id);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
}
