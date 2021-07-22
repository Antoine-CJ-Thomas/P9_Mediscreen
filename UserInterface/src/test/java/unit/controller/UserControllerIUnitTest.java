package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import userInterface.controller.UserController;
import userInterface.model.User;
import userInterface.service.UserServiceInterface;

import java.sql.Date;
import java.util.Optional;

public class UserControllerIUnitTest {

    private UserController userController;

    private UserServiceInterface userServiceInterface = Mockito.mock(UserServiceInterface.class);

    private static Integer userId;
    private static String firstName = "testFirstName";
    private static String lastName = "testLastName";
    private static Date birthDate = new Date(System.currentTimeMillis());
    private static String gender = "M";
    private static String address = "testAddress";
    private static String phoneNumber = "0123456789";

    @BeforeEach
    public void beforeEach() {

        userController = new UserController(userServiceInterface);
    }

    @Test
    public void addUser() {

        //GIVEN
        User user = Mockito.mock(User.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        userController.addUser(user, model);

        //THEN
        Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
    }

    @Test
    public void insertUser() {

        //GIVEN
        User user = Mockito.mock(User.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        userController.insertUser(user, bindingResult, model);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).insertUser(user);
    }

    @Test
    public void getUserList() {

        //GIVEN
        Model model = Mockito.mock(Model.class);

        //WHEN
        userController.userList(model);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).selectUserList();
    }

    @Test
    public void editUser() {

        //GIVEN
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN
        userController.editUser(userId, model);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).selectUser(userId);
    }

    @Test
    public void updateUser() {

        //GIVEN
        Integer userId = 1;
        User user = Mockito.mock(User.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        userController.updateUser(userId, user, bindingResult, model);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).updateUser(userId, user);
    }

    @Test
    public void deleteUser() {

        //GIVEN
        Integer userId = 1;
        Model model = Mockito.mock(Model.class);

        //WHEN

        userController.deleteUser(userId, model);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).deleteUser(userId);
    }
}
