package com.burrow.burrow.post.dto;

import com.burrow.burrow.comment.dto.CommentResponse;
import com.burrow.burrow.comment.entity.Comment;
import com.burrow.burrow.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponse> commentList;
    private String nickname;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.nickname = post.getUser().getNickname();
    }

    public PostResponseDto(Post post, List<Comment> commentList, String nickname) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = new ArrayList<>();
        for (Comment comment : commentList) {
            this.commentList.add(new CommentResponse(comment));
        }
        this.nickname = nickname;
    }
}
