package com.burrow.burrow.post.service;

import com.burrow.burrow.post.dto.PostRequestDto;
import com.burrow.burrow.post.dto.PostResponseDto;
import com.burrow.burrow.post.entity.Post;
import com.burrow.burrow.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //게시글 전제 조회
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream().map(
                PostResponseDto::new
        ).toList();
    }

    //게시글 단건 조회
    public PostResponseDto getPostDto(Long postId) {
        Post post = getPost(postId);
        return new PostResponseDto(post);
    }

    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = getUserPost(postId);

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setModifiedAt(post.getModifiedAt());

        return new PostResponseDto(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        Post post = getUserPost(postId);
        postRepository.delete(post);
    }

    //게시글 예외처리
    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    //게시글 수정시 예외처리
    public Post getUserPost(Long postId) {
        Post post = getPost(postId);
        return post;
    }
}
