package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userInformation.controller.UserController;
import userInformation.model.User;
import userInformation.service.UserServiceInterface;

public class UserControllerUnitTest {

    private UserController userController;

    private UserServiceInterface userServiceInterface = Mockito.mock(UserServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        userController = new UserController(userServiceInterface);
    }

    @Test
    public void insertUser() {

        //GIVEN
        User user = Mockito.mock(User.class);

        //WHEN
        userController.insertUser(user);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).insertUser(user);
    }

    @Test
    public void selectUser() throws JsonProcessingException {

        //GIVEN
        int userId = 1;

        //WHEN
        userController.selectUser(userId);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).selectUser(userId);
    }

    @Test
    public void selectUserList() throws JsonProcessingException {

        //GIVEN

        //WHEN
        userController.selectUserList();

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).selectUserList();
    }

    @Test
    public void updateUser() {

        //GIVEN
        int userId = 1;
        User user = Mockito.mock(User.class);

        //WHEN
        userController.updateUser(userId, user);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).updateUser(userId, user);
    }

    @Test
    public void deleteUser() {

        //GIVEN
        int userId = 1;

        //WHEN
        userController.deleteUser(userId);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).deleteUser(userId);
    }
}
