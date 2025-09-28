package com.likelion.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
    protected void setMember(Member member) {
        this.member = member;//protected는 불필요한 외부 접근을 제한해준다고 하네요 처음 알았어요!
    }
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }//이걸 생각을 못하고 @Builder만 했더니 테스트로 수정을 해봤을 때 먹히지 않아서 새로 추가했습니다

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}