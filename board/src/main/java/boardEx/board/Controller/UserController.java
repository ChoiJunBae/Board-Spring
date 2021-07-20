package boardEx.board.Controller;

import boardEx.board.domain.entity.User;
import boardEx.board.dto.LoginDto;
import boardEx.board.dto.SignUpDto;
import boardEx.board.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        List<User> login = userService.login(loginDto);
        return new LoginResponse(login);
    }


    @PostMapping("/signup")
    public String signup(@RequestBody SignUpDto signUpDto) {
        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        user.setLocation(signUpDto.getLocation());

        String response = userService.join(user);
        return response;
    }

    @Data
    @AllArgsConstructor
    static class LoginResponse{
        private List<User> findUsers;
    }
}
