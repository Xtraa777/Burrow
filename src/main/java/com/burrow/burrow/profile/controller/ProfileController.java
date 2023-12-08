package com.burrow.burrow.profile.controller;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import com.burrow.burrow.profile.dto.ProfileResponseDto;
import com.burrow.burrow.profile.dto.UpdatePasswordRequestDto;
import com.burrow.burrow.profile.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    private final ProfileService profileService;

    private ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile/{id}")
    private ProfileResponseDto getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    //사용자 정보 변경이 DB에는 반영 되는데 포스트맨에 결과값으로 출력이 안나옴
    @PutMapping("/profile/{userId}")
    private ProfileResponseDto updateProfile(@RequestBody ProfileRequestDto profileRequestDto,@PathVariable Long userId) {
        return profileService.updateProfile(profileRequestDto,userId);
    }

    @PutMapping("/password/{userId}")
    private void updatePassword(@RequestBody PasswordRequestDto passwordRequestDto, @PathVariable Long userId) {
        profileService.updatePassword(passwordRequestDto,userId);
    }
}
