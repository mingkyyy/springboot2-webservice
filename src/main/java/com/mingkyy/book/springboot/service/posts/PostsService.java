package com.mingkyy.book.springboot.service.posts;

import com.mingkyy.book.springboot.domain.posts.PostRepository;
import com.mingkyy.book.springboot.domain.posts.Posts;
import com.mingkyy.book.springboot.web.dto.PostsResponseDto;
import com.mingkyy.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
}
