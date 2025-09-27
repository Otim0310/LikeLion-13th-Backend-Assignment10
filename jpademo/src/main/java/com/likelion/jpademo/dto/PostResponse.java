package com.likelion.jpademo.dto;

import com.likelion.jpademo.entity.Post;

public record PostResponse(Long id, String title, String content, Long memberId) {

    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember() != null ? post.getMember().getId() : null
        );
    }
}