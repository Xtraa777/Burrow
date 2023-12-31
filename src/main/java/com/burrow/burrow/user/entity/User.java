package com.burrow.burrow.user.entity;

import com.burrow.burrow.profile.dto.ProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String description;
    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String uid, String password, String nickname, String description, UserRoleEnum role) {
        this.nickname = nickname;
        this.uid = uid;
        this.password = password;
        this.description = description;
        this.role = role;
    }

    //프로필 수정
    public void profileUpdate(ProfileRequestDto profileRequestDto) {
        this.nickname = profileRequestDto.getNickname();
        this.description = profileRequestDto.getDescription();
    }

}
