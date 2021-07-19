package userInformation.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import userInformation.model.User;
import userInformation.service.UserService;
import userInformation.service.UserServiceInterface;

import java.util.List;

@RestController
public class UserController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private UserServiceInterface userServiceInterface;

    public UserController() {
        logger.info("UserController()");

        userServiceInterface = new UserService();
    }

    public UserController(UserServiceInterface userServiceInterface) {
        logger.info("UserController(" + userServiceInterface + ")");

        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam int id) {
        logger.info("getUser(" + id + ")");

        return userServiceInterface.getUser(id);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        logger.info("getUserList()");

        return userServiceInterface.getUserList();
    }
}
