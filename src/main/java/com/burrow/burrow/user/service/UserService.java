package com.burrow.burrow.user.service;

import com.burrow.burrow.user.dto.UserRequestDto;
import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String uid = userRequestDto.getUid();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String nickname = userRequestDto.getNickname();
        String description = userRequestDto.getDescription();

        if (userRepository.findByUid(uid).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }

        User user = new User(uid, password, nickname, description);
        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto) {
        String uid = userRequestDto.getUid();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
