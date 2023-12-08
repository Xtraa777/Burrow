package com.burrow.burrow.profile.dto;

import com.burrow.burrow.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {
    private Long id;
    private String nickname;
    private String uid;
    private String password;
    private String description;
//    private Date modifiedAt;

    public ProfileResponseDto(User user) {
        this.nickname=user.getNickname();
        this.uid=user.getUid();
        this.password=user.getPassword();
        this.description=user.getDescription();
    }
}
