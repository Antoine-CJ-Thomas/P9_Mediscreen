package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.User;
import userInterface.proxy.UserProxy;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private UserProxy userProxy;

    public UserService() {

    }

    public UserService(UserProxy userProxy) {

        this.userProxy = userProxy;
    }

    @Override
    public User getUser(int id) {
        logger.info("getUser(" + id + ")");

        return userProxy.getUser(id);
    }

    @Override
    public List<User> getUserList() {
        logger.info("getUserList()");

        return userProxy.getUserList();
    }

    @Override
    public void updateUser(int id, User user) {
        logger.info("updateUser(" + id + "," + user + ")");

        userProxy.updateUser(id, user);
    }

    @Override
    public void deleteUser(int id) {
        logger.info("deleteUser(" + id + ")");

        userProxy.deleteUser(id);
    }
}