package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userInterface.model.User;
import userInterface.proxy.UserProxy;
import userInterface.service.UserService;

import java.util.List;

public class UserServiceUnitTest {

    private UserService userService;

    private UserProxy mockedUserProxy = Mockito.mock(UserProxy.class);

    @BeforeEach
    public void beforeEach() {

        userService = new UserService(mockedUserProxy);
    }

    @Test
    public void insertUser() {

        //GIVEN
        User user = Mockito.mock(User.class);

        //WHEN
        userService.insertUser(user);

        //THEN
        Mockito.verify(mockedUserProxy, Mockito.times(1)).insertUser(user);
    }

    @Test
    public void selectUser() {

        //GIVEN
        int userId = 1;
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(mockedUserProxy.selectUser(userId)).thenReturn(user);

        //THEN
        Assertions.assertThat(userService.selectUser(userId) == user);
    }

    @Test
    public void selectUserList() {

        //GIVEN
        List<User> userList = Mockito.mock(List.class);

        //WHEN
        Mockito.when(mockedUserProxy.selectUserList()).thenReturn(userList);

        //THEN
        Assertions.assertThat(userService.selectUserList() == userList);
    }

    @Test
    public void updateUser() {

        //GIVEN
        int userId = 1;
        User user = Mockito.mock(User.class);

        //WHEN
        userService.updateUser(userId, user);

        //THEN
        Mockito.verify(mockedUserProxy, Mockito.times(1)).updateUser(userId, user);
    }

    @Test
    public void deleteUser() {

        //GIVEN
        int userId = 1;

        //WHEN
        userService.deleteUser(userId);

        //THEN
        Mockito.verify(mockedUserProxy, Mockito.times(1)).deleteUser(userId);
    }
}