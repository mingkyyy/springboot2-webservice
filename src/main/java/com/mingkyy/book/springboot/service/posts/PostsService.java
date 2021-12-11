package com.mingkyy.book.springboot.service.posts;

import com.mingkyy.book.springboot.domain.posts.PostRepository;
import com.mingkyy.book.springboot.domain.posts.Posts;
import com.mingkyy.book.springboot.web.dto.PostsListResponseDto;
import com.mingkyy.book.springboot.web.dto.PostsResponseDto;
import com.mingkyy.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

   @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
   }

   @Transactional
    public void delete(Long id){
    Posts posts = postRepository.findById(id)
            .orElseThrow(()->new
                    IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
    postRepository.delete(posts);
    }
}
