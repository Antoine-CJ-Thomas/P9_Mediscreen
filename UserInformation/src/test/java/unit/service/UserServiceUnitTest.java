package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userInformation.model.User;
import userInformation.repository.UserRepository;
import userInformation.service.UserService;

import java.util.Arrays;
import java.util.Optional;

public class UserServiceUnitTest {

    private UserService userService;

    private UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    public void beforeEach() {

        userService = new UserService(mockedUserRepository);
    }

    @Test
    public void insertUser() {

        //GIVEN
        User user = Mockito.mock(User.class);

        //WHEN
        userService.insertUser(user);

        //THEN
        Mockito.verify(mockedUserRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void selectUser() {

        //GIVEN
        int userId = 1;
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(mockedUserRepository.findById(userId)).thenReturn(Optional.of(user));

        //THEN
        Assertions.assertThat(userService.selectUser(userId) == user);
    }

    @Test
    public void selectUserList() {

        //GIVEN
        User user = Mockito.mock(User.class);
        Iterable<User> userIterable = Arrays.asList(user);

        //WHEN
        Mockito.when(mockedUserRepository.findAll()).thenReturn(userIterable);

        //THEN
        Assertions.assertThat(userService.selectUserList() == userIterable);
    }

    @Test
    public void updateUser() {

        //GIVEN
        int userId = 1;
        User user = Mockito.mock(User.class);

        //WHEN
        userService.updateUser(userId, user);

        //THEN
        Mockito.verify(mockedUserRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void deleteUser() {

        //GIVEN
        int userId = 1;

        //WHEN
        userService.deleteUser(userId);

        //THEN
        Mockito.verify(mockedUserRepository, Mockito.times(1)).deleteById(userId);
    }
}
