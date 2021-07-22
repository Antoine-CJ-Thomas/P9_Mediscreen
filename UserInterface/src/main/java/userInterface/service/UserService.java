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
    public void insertUser(User user) {
        logger.info("insertUser(" + "," + user + ")");

        userProxy.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        logger.info("selectUser(" + id + ")");

        return userProxy.selectUser(id);
    }

    @Override
    public List<User> selectUserList() {
        logger.info("selectUserList()");

        return userProxy.selectUserList();
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
