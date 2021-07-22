package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/addUser")
    public String addUser(User user, Model model) {
        logger.info("addUser(" + model + ")");

        model.addAttribute("user", user);

        return "/user_add.html";
    }

    @PostMapping("/insertUser")
    public String insertUser(@Valid User user, BindingResult bindingResult, Model model) {
        logger.info("insertUser(" + "," + user + "," + bindingResult + "," + model + ")");

        if (bindingResult.hasErrors() == false) {

            userServiceInterface.insertUser(user);

            return ("redirect:/userList");
        }

        else {

            model.addAttribute("user", user);

            return "/user_add.html";
        }
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        logger.info("userList(" + model + ")");

        model.addAttribute("userList", userServiceInterface.selectUserList());

        return "/user_list.html";
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam int id, Model model) {
        logger.info("editUser(" + id + "," + model + ")");

        model.addAttribute("user", userServiceInterface.selectUser(id));

        return "/user_edit.html";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam int id, @Valid User user, BindingResult bindingResult, Model model) {
        logger.info("updateUser(" + id + "," + user + "," + bindingResult + "," + model + ")");

        if (bindingResult.hasErrors() == false) {

            userServiceInterface.updateUser(id, user);

            return ("redirect:/userList");
        }

        else {

            return "/user_edit.html";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam int id, Model model) {
        logger.info("deleteUser(" + id + "," + model + ")");

        userServiceInterface.deleteUser(id);

        return ("redirect:/userList");
    }
}