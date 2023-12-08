package com.burrow.burrow.profile.dto;

import com.burrow.burrow.user.entity.User;

import java.util.Date;

public class ProfileResponseDto {
    private Long userId;
    private String uid;
    private String nickname;
    private String password;
    private String discription;
    private Date modifiedAt;

    public ProfileResponseDto(User user) {
        this.uid=user.getUid();
        this.nickname=user.getNickname();
        this.password=user.getPassword();
        this.discription=user.getDescription();
    }
}
