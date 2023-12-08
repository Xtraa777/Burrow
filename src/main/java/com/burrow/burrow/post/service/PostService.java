package com.burrow.burrow.post.service;

import com.burrow.burrow.post.dto.PostRequestDto;
import com.burrow.burrow.post.dto.PostResponseDto;
import com.burrow.burrow.post.entity.Post;
import com.burrow.burrow.post.repository.PostRepository;
import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시물 저장 역할
    public PostResponseDto createPosts(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        // 제목(title)과 내용(content)이 비어 있는 경우
        if (postRequestDto.getTitle() == null || postRequestDto.getTitle().trim().isEmpty()) {
            // 제목이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("제목이 비어있습니다. 제목을 작성해 주세요.");
        } else if (postRequestDto.getContent() == null || postRequestDto.getContent().trim().isEmpty()) {
            // 내용이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("내용이 비어있습니다. 내용을 작성해 주세요.");
        }

        //받아온 정보 객체에 저장
        Post post = new Post(postRequestDto, userDetails);

        //DB에 저장
        Post savePost = postRepository.save(post);

        //DB에 저장된 값 반환
        return new PostResponseDto(savePost);
    }

    //게시글 전제 조회
    public List<PostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();

        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for (Post post : postList) {
            responseDtoList.add(new PostResponseDto(post));
        }

        return responseDtoList;
    }

    //게시글 단건 조회
    public PostResponseDto getPostDto(Long postId) {
        Post post = getPost(postId);
        return new PostResponseDto(post);
    }

    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        // 제목(title)과 내용(content)이 비어 있는 경우
        if (postRequestDto.getTitle() == null || postRequestDto.getTitle().trim().isEmpty()) {
            // 제목이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("제목이 비어있습니다. 제목을 작성해 주세요.");
        } else if (postRequestDto.getContent() == null || postRequestDto.getContent().trim().isEmpty()) {
            // 내용이 비어있을 때의 에러 메시지 처리
            throw new IllegalArgumentException("내용이 비어있습니다. 내용을 작성해 주세요.");
        }

        Post post = getUserPost(postId, userDetails.getUser());

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setModifiedAt(post.getModifiedAt());

        return new PostResponseDto(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails
    ) {
        Post post = getUserPost(postId, userDetails.getUser());

        postRepository.delete(post);
    }

    //게시글 예외처리
    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    //게시글 수정시 예외처리
    public Post getUserPost(Long postId, User user) {
        Post post = getPost(postId);

        if (!user.getUid().equals(post.getUser().getUid())) {
            throw new RejectedExecutionException("수정 권한이 없습니다.");
        }

        return post;
    }
}
