package com.burrow.burrow.post.controller;


import com.burrow.burrow.post.dto.PostRequestDto;
import com.burrow.burrow.post.dto.PostResponseDto;
import com.burrow.burrow.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
