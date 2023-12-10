package com.burrow.burrow.profile.service;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import com.burrow.burrow.profile.dto.ProfileResponseDto;
import com.burrow.burrow.profile.dto.UpdatePasswordRequestDto;
import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public ProfileResponseDto getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("no user exists")
        );
        return new ProfileResponseDto(user);
    }
    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new IllegalArgumentException("no user exists")
        );
        user.profileUpdate(profileRequestDto);
        return new ProfileResponseDto(user);
    }

    @Transactional
    public void updatePassword(PasswordRequestDto passwordRequestDto, Long userId) {
        String password = passwordRequestDto.getPassword();
        //userId로 user 확인
        User user = userRepository.findById(userId).orElseThrow(
                ()->new IllegalArgumentException("no user exists")
        );
        //비밀번호 확인
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("wrong password");
        }else{
            if(passwordRequestDto.getUpdatePassword().equals(passwordRequestDto.getCheckUpdatePassword())){
                user.setPassword(passwordEncoder.encode(passwordRequestDto.getUpdatePassword()));
            }else{
                throw new IllegalArgumentException("check new password");
            }
        }
    }
}