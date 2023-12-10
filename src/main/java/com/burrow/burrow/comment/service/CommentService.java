package com.burrow.burrow.comment.service;

import com.burrow.burrow.comment.dto.CommentRequest;
import com.burrow.burrow.comment.dto.CommentResponse;
import com.burrow.burrow.comment.entity.Comment;
import com.burrow.burrow.comment.repository.CommentRepository;
import com.burrow.burrow.post.entity.Post;
import com.burrow.burrow.post.repository.PostRepository;
import com.burrow.burrow.user.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponse createComment(Long postId, CommentRequest request, UserDetailsImpl userDetails) {
        if (request.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        Post post = postRepository.findById(postId).orElseThrow(NullPointerException::new);
        Comment comment = new Comment(request, userDetails.getUser(), post);
        commentRepository.save(comment);
        return new CommentResponse(comment);
    }

    public List<CommentResponse> getComments(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest request, UserDetailsImpl userDetails) {
        if (request.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력해주세요");
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);
        if (userDetails.getUser().getId().equals(comment.getUser().getId())) {
            comment.updateContent(request.getContent());
            CommentResponse res = new CommentResponse(comment);
            return res;
        } else {
            throw new IllegalArgumentException("작성자가 아닙니다!");
        }

    }

    public CommentResponse deleteComment(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);
        if (userDetails.getUser().getId().equals(comment.getUser().getId())) {
            CommentResponse commentResponse = new CommentResponse(comment);
            commentRepository.delete(comment);
            return commentResponse;
        } else {
            throw new IllegalArgumentException("작성자가 아닙니다!");
        }
    }

}
