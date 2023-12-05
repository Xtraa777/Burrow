package com.burrow.burrow.user.entity;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    @Column
    private String uid;
    @Column
    private String nickname;
    @Column
    private String password;
    @Column
    private String discription;
    @Column
    private Date modified_at;
    @Column
    private Date created_at;


    public void update(ProfileRequestDto profileRequestDto) {
        this.userId = profileRequestDto.getUserId();
        this.uid = profileRequestDto.getUid();
        this.nickname = profileRequestDto.getNickname();
        this.discription = profileRequestDto.getDiscription();
    }

    public void update(PasswordRequestDto passwordRequestDto) {
        this.password=passwordRequestDto.getPassword();
    }
}
