package userInformation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userInformation.model.User;
import userInformation.service.UserService;
import userInformation.service.UserServiceInterface;

import java.util.List;

@RestController
public class UserController {

    private ObjectMapper objectMapper = new ObjectMapper();

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
    public String getUser(@RequestParam int id) throws JsonProcessingException {
        logger.info("getUser(" + id + ")");

        return objectMapper.writeValueAsString(userServiceInterface.getUser(id));
    }

    @GetMapping("/getUserList")
    public String getUserList() throws JsonProcessingException {
        logger.info("getUserList()");

        return objectMapper.writeValueAsString(userServiceInterface.getUserList());
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestParam int id, @RequestBody User user) {
        logger.info("updateUser(" + id + "," + user + ")");

        userServiceInterface.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam int id) {
        logger.info("deleteUser(" + id + ")");

        userServiceInterface.deleteUser(id);
    }
}
