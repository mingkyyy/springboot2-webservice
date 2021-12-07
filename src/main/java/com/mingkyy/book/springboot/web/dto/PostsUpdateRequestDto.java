package com.mingkyy.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

import javax.swing.plaf.BorderUIResource;

@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title , String content){
        this.title = title;
        this.content = content;
    }
}
