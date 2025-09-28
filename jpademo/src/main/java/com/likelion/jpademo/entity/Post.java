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

    public void setMember(Member member) {
        this.member = member;
    } //이 부분도 삭제했더니 오류가 나서 찾아보니 member와의 연관관계를 위해서 필요하다고 해서 그냥 뒀는데 이게 남아있어도 오류를 야기할 수 있나요?

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }//이걸 생각을 못하고 @Builder만 했더니 테스트로 수정을 해봤을 때 먹히지 않아서 새로 추가했습니다

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}