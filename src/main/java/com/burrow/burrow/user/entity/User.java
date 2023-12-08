package com.burrow.burrow.user.entity;

import com.burrow.burrow.profile.dto.PasswordRequestDto;
import com.burrow.burrow.profile.dto.ProfileRequestDto;
import com.burrow.burrow.profile.dto.UpdatePasswordRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
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

    public User(String uid, String password, String nickname, String description) {
        this.nickname = nickname;
        this.uid = uid;
        this.password = password;
        this.description = description;
    }

    //프로필 수정
    public void profileUpdate(ProfileRequestDto profileRequestDto){
        this.nickname=profileRequestDto.getNickname();
        this.uid=profileRequestDto.getUid();
        this.description=profileRequestDto.getDescription();
    }
}
