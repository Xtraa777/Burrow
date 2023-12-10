package com.burrow.burrow.comment.dto;

import com.burrow.burrow.post.entity.Post;
import lombok.Getter;

@Getter
public class CommentRequest {
    private Long id;
    private String content;
    private Post post;
}
