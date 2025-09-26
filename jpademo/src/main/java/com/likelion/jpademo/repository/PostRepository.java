package com.likelion.jpademo.repository;

import com.likelion.jpademo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { }

