package com.burrow.burrow.user.service;

import com.burrow.burrow.jwt.JwtUtil;
import com.burrow.burrow.user.dto.UserRequestDto;
import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.entity.UserRoleEnum;
import com.burrow.burrow.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final String ADMIN_TOKEN = "ADMINTOKEN";

    public void signup(UserRequestDto userRequestDto) {
        String uid = userRequestDto.getUid();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String nickname = userRequestDto.getNickname();
        String description = userRequestDto.getDescription();
        String adminToken = userRequestDto.getAdminToken();

        if (userRepository.findByUid(uid).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        //관리자 확인
        if (userRequestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(adminToken)) {
                throw new IllegalArgumentException("관리자 암호가 틀렸습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(uid, password, nickname, description, role);

        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto, HttpServletResponse res) {
        String uid = userRequestDto.getUid();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //jwt 생성 및 쿠키에 저장
        String token = jwtUtil.createToken(user.getUid(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);
    }
}
