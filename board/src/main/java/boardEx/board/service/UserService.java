package boardEx.board.service;

import boardEx.board.domain.entity.User;
import boardEx.board.domain.repository.UserRepository;
import boardEx.board.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    // 회원가입
    @Transactional
    public String join(User user) {
        userRepository.save(user);
        return "회원가입 성공";
    }

    // 로그인
    public List<User> login(LoginDto loginDto){
        List<User> checking = userRepository.findByEmail(loginDto.getEmail());
        return checking;
    }

    // 전체 회원 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }
}
