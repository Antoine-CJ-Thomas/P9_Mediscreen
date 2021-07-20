package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import userInterface.model.User;
import userInterface.service.UserService;
import userInterface.service.UserServiceInterface;

import javax.validation.Valid;

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

    @GetMapping("/userList")
    public String getUserList(Model model) {
        logger.info("getUserList(" + model + ")");

        model.addAttribute("userList", userServiceInterface.getUserList());

        return "/user_list.html";
    }

    @GetMapping("/openUser/{id}")
    public String openUser(@PathVariable("id") int id, Model model) {
        logger.info("openUser(" + id + "," + model + ")");

        model.addAttribute("user", userServiceInterface.getUser(id));

        return "/user_open.html";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        logger.info("editUser(" + id + "," + model + ")");

        model.addAttribute("user", userServiceInterface.getUser(id));

        return "/user_edit.html";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user, BindingResult bindingResult, Model model) {
        logger.info("updateUser(" + id + "," + user + "," + bindingResult + "," + model + ")");

        if (bindingResult.hasErrors() == false) {

            userServiceInterface.updateUser(id, user);

            return ("redirect:/userList");
        }

        else {

            return "/user_edit.html";
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        logger.info("deleteUser(" + id + "," + model + ")");

        userServiceInterface.deleteUser(id);

        return ("redirect:/userList");
    }
}