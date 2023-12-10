package com.burrow.burrow.user.entity;

import com.burrow.burrow.comment.entity.Comment;
import com.burrow.burrow.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
}
