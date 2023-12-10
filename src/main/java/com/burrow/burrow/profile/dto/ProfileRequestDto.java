package com.burrow.burrow.profile.dto;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private Long userId;
    private String uid;
    private String nickname;
    private String password;
    private String description;
}
