package com.burrow.burrow.profile.controller;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import com.burrow.burrow.profile.dto.ProfileResponseDto;
import com.burrow.burrow.profile.service.ProfileService;
import com.burrow.burrow.user.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    private final ProfileService profileService;

    private ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    private ProfileResponseDto getProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getProfile(userDetails);
    }

    //사용자 정보 변경이 DB에는 반영 되는데 포스트맨에 결과값으로 출력이 안나옴
    @PatchMapping("/profile")
    private ProfileResponseDto updateProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ProfileRequestDto profileRequestDto) {
        return profileService.updateProfile(userDetails, profileRequestDto);
    }

    @PatchMapping("/password")
    private void updatePassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody PasswordRequestDto passwordRequestDto) {
        profileService.updatePassword(userDetails, passwordRequestDto);
    }
}
