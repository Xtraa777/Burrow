package com.burrow.burrow.comment.entity;

import com.burrow.burrow.comment.dto.CommentRequest;
import com.burrow.burrow.post.entity.Post;
import com.burrow.burrow.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Comment(CommentRequest request, User user, Post post){
        this.content = request.getContent();
        this.user = user;
        this.post = post;
    }
}
