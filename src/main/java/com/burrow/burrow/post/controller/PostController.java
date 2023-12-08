package com.burrow.burrow.post.controller;


import com.burrow.burrow.post.dto.CommonResponseDto;
import com.burrow.burrow.post.dto.PostRequestDto;
import com.burrow.burrow.post.dto.PostResponseDto;
import com.burrow.burrow.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPosts(@RequestBody PostRequestDto postRequestDto) {

        PostResponseDto postResponseDto = postService.createPosts(postRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(postResponseDto);
    }


    //전체 게시글 조회
    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> postResponseDtoList = postService.getAllPosts();
        return ResponseEntity.ok().body(postResponseDtoList);
    }

    //단건 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> getPost(@PathVariable Long postId) {
        try {
            PostResponseDto postResponseDto = postService.getPostDto(postId);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(
            @PathVariable Long postId
    ) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok().body(new CommonResponseDto("정상적으로 삭제 되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
