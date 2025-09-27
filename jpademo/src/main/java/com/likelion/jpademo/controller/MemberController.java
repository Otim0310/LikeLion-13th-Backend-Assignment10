package com.likelion.jpademo.controller;

import com.likelion.jpademo.dto.MemberResonse;
import com.likelion.jpademo.dto.MemberRequests;
import com.likelion.jpademo.dto.PostRequests;
import com.likelion.jpademo.dto.PostResponse;
import com.likelion.jpademo.service.MemberService;
import com.likelion.jpademo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;
    private final PostService postService;

    @GetMapping
    public List<MemberResonse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResonse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOneWithPosts(id));
    }

    @PostMapping
    public ResponseEntity<MemberResonse> create(@RequestBody MemberRequests.Create body) {
        var res = service.createMember(body.nickname());
        return ResponseEntity.created(URI.create("/members/" + res.id())).body(res);
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<PostResponse> addPost(@PathVariable Long id, @RequestBody PostRequests.Create body) {
        var res = postService.create(id, body.title(), body.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}