package com.burrow.burrow.post.service;

import com.burrow.burrow.post.dto.PostRequestDto;
import com.burrow.burrow.post.dto.PostResponseDto;
import com.burrow.burrow.post.entity.Post;
import com.burrow.burrow.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    //게시물 저장 역할
    public PostResponseDto createPosts(PostRequestDto postRequestDto) {
        // 제목(title) 또는 내용(content)이 비어 있는 경우
        if (postRequestDto.getTitle() == null || postRequestDto.getTitle().trim().isEmpty()) {
            // 제목이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("제목을 입력해야 합니다.");
        } else if (postRequestDto.getContent() == null || postRequestDto.getContent().trim().isEmpty()) {
            // 내용이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("내용을 입력해야 합니다.");
        }

        //받아온 정보 객체에 저장
        Post post = new Post(postRequestDto);

        //DB에 저장
        Post savePost = postRepository.save(post);

        //DB에 저장된 값 반환
        return new PostResponseDto(savePost);
    }

}
