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
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateUser(User user){
        List<User> findUsers= userRepository.findByEmail(user.getEmail());
        if (!findUsers.isEmpty()){
            throw new IllegalStateException("이미 가입하신 이메일입니다.");
        }
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

    // 단일 회원 조회
    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }
}
