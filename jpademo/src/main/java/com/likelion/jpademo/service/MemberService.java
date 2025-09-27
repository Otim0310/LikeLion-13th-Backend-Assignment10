package com.likelion.jpademo.service;

import com.likelion.jpademo.dto.MemberResonse;
import com.likelion.jpademo.entity.Member;
import com.likelion.jpademo.exception.NotFoundException;
import com.likelion.jpademo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepo;

    @Transactional(readOnly = true)
    public List<MemberResonse> findAll() {
        return memberRepo.findAll().stream()
                .map(MemberResonse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResonse findOneWithPosts(Long id) {
        Member m = memberRepo.findWithPostsById(id)
                .orElseThrow(() -> new NotFoundException("Member not found: " + id));
        return MemberResonse.fromEntity(m);
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Member not found: " + id));
    }

    @Transactional
    public MemberResonse createMember(String nickname) {
        Member saved = memberRepo.save(Member.builder().nickname(nickname).build());
        return MemberResonse.fromEntity(saved);
    }
}