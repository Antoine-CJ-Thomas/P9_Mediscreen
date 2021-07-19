package userInterface.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import userInterface.model.User;

import java.util.List;

@Component
@FeignClient(name = "userInformation", url = "http://localhost:8080")
public interface UserProxy {

    @GetMapping(value = "/getUser", produces = "application/json")
    public User getUser(int id);

    @GetMapping(value = "/getUserList", produces = "application/json")
    public List<User> getUserList();
}
