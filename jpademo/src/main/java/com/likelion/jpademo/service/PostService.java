package com.likelion.jpademo.service;

import com.likelion.jpademo.dto.PostResponse;
import com.likelion.jpademo.entity.Member;
import com.likelion.jpademo.entity.Post;
import com.likelion.jpademo.exception.NotFoundException;
import com.likelion.jpademo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepo;
    private final MemberService memberService;

    @Transactional
    public PostResponse create(Long memberId, String title, String content) {
        Member member = memberService.findById(memberId);
        Post post = Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
        Post saved = postRepo.save(post);
        return PostResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public PostResponse get(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        return PostResponse.fromEntity(post);
    }

    @Transactional
    public PostResponse update(Long id, String title, String content) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found: " + id));
        post.updateTitle(title);
        post.updateContent(content);
        return PostResponse.fromEntity(post);
    }

    @Transactional
    public void delete(Long id) {
        if (!postRepo.existsById(id)) {
            throw new NotFoundException("Post not found: " + id);
        }
        postRepo.deleteById(id);
    }
}