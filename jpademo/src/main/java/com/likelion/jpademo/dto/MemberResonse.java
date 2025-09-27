package com.likelion.jpademo.dto;

import com.likelion.jpademo.entity.Member;
import com.likelion.jpademo.entity.Post;

import java.util.List;

public record MemberResonse(Long id, String nickname, List<PostSummary> posts) {

    public record PostSummary(Long id, String title) { }

    public static MemberResonse fromEntity(Member member) {
        return new MemberResonse(
                member.getId(),
                member.getNickname(),
                member.getPosts().stream()
                        .map(p -> new PostSummary(p.getId(), p.getTitle()))
                        .toList()
        );
    }

    public static PostSummary toSummary(Post post) {
        return new PostSummary(post.getId(), post.getTitle());
    }
}
