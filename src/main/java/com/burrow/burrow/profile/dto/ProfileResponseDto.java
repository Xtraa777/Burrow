package com.burrow.burrow.profile.dto;

import com.burrow.burrow.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileResponseDto {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    private String nickname;
    @Column
    private String uid;
    @Column
    private String password;
    @Column
    private String description;
//    private Date modifiedAt;

    public ProfileResponseDto(User user) {
        this.nickname=user.getNickname();
        this.uid=user.getUid();
        this.password=user.getPassword();
        this.description=user.getDescription();
    }
}
