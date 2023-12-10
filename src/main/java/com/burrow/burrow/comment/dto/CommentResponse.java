package com.burrow.burrow.comment.dto;

import com.burrow.burrow.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    private String content;
    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
    }
}
