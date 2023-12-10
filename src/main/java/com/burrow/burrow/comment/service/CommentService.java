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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentRequest request, UserDetailsImpl userDetails) {
        if(request.getContent() == null){
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        Post post = postRepository.findById(postId).orElseThrow(NullPointerException::new);
        Comment comment = new Comment(request, userDetails.getUser(), post);
        post.getCommentList().add(comment);
        commentRepository.save(comment);
        return new CommentResponse(comment);
    }

    public List<CommentResponse> getComments(Long postId) {
        return commentRepository.findAllById(Collections.singleton(postId)).stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public CommentResponse updateComment(Long commentId, CommentRequest request, UserDetailsImpl userDetails) {
        if(request.getContent() == null){
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);
        CommentResponse res = new CommentResponse(comment);
        return res;
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NullPointerException::new);
        commentRepository.delete(comment);
    }

}
