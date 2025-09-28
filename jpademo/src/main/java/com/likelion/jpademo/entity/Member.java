package com.likelion.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String nickname) {
        this.nickname = nickname;
        this.posts = new ArrayList<>();
    }

    public void addPost(Post p) {
        posts.add(p);
        p.setMember(this);
    }

    public void changeNickname(String newNickname) {
        this.nickname = newNickname; //사용자의 닉네임 수정은 포스트 수정을 넣으면서 같이 넣어봤습니다.
    }
}