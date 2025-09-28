package com.likelion.jpademo.service;

import com.likelion.jpademo.dto.PostResponse;
import com.likelion.jpademo.entity.Member;
import com.likelion.jpademo.entity.Post;
import com.likelion.jpademo.exception.NotFoundException;
import com.likelion.jpademo.repository.MemberRepository;
import com.likelion.jpademo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    public PostResponse create(Long memberId, String title, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member not found: " + memberId));
        Post post = Post.builder().title(title).content(content).member(member).build();
        member.addPost(post); //앗 그렇네요 그전에는 post->member로만 되어있네요...
        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        return PostResponse.fromEntity(post);
    }

    @Transactional
    public PostResponse update(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        post.updateTitle(title);
        post.updateContent(content);
        return PostResponse.fromEntity(post);
    }

    @Transactional
    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException("Post not found: " + id);
        }
        postRepository.deleteById(id);
    }
}