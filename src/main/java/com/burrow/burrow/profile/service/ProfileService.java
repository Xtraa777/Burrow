package com.burrow.burrow.profile.service;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import com.burrow.burrow.profile.dto.ProfileResponseDto;
import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.repository.UserRepository;
import com.burrow.burrow.user.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileResponseDto getProfile(UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("로그인을 해주세요")
        );
        return new ProfileResponseDto(user);
    }

    @Transactional
    public ProfileResponseDto updateProfile(UserDetailsImpl userDetails, ProfileRequestDto profileRequestDto) {
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("로그인을 해주세요")
        );
        user.profileUpdate(profileRequestDto);
        return new ProfileResponseDto(user);
    }

    @Transactional
    public void updatePassword(UserDetailsImpl userDetails, PasswordRequestDto passwordRequestDto) {
        String password = passwordRequestDto.getPassword();
        //userId로 user 확인
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("로그인을 해주세요")
        );
        //비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("wrong password");
        } else {
            if (passwordRequestDto.getUpdatePassword().equals(passwordRequestDto.getCheckUpdatePassword())) {
                user.setPassword(passwordEncoder.encode(passwordRequestDto.getUpdatePassword()));
            } else {
                throw new IllegalArgumentException("check new password");
            }
        }
    }
}
