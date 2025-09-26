package com.likelion.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    // 주인 아님 (읽기/탐색용)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    // 양방향 편의 메서드
    public void addPost(Post p) {
        posts.add(p);
        p.setMember(this);
    }
}


//패키지 이름이 다를 때, alt+enter 하면 자동으로 알맞게 설정해줌