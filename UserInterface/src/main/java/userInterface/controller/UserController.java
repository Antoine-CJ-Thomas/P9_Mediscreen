package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import userInterface.service.UserService;
import userInterface.service.UserServiceInterface;

@Controller
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

    @GetMapping("/getUserList")
    public String getUserList(Model model) {
        logger.info("getUserList(" + model + ")");

        model.addAttribute("userList", userServiceInterface.getUserList());

        return "/user_list.html";
    }
}
