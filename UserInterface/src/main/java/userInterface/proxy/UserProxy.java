package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import userInterface.model.User;

import java.util.List;

@Component
@FeignClient(name = "userInformation", url = "http://localhost:8080")
public interface UserProxy {

    @PostMapping(value = "/insertUser", produces = "application/json")
    public void insertUser(@RequestBody User user);

    @GetMapping(value = "/selectUser", produces = "application/json")
    public User selectUser(@RequestParam int id);

    @GetMapping(value = "/selectUserList", produces = "application/json")
    public List<User> selectUserList();

    @PutMapping(value = "/updateUser", produces = "application/json")
    public void updateUser(@RequestParam int id, @RequestBody User user);

    @DeleteMapping(value = "/deleteUser", produces = "application/json")
    public void deleteUser(@RequestParam int id);
}
