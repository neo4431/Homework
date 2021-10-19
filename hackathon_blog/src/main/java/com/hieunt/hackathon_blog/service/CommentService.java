package com.hieunt.hackathon_blog.service;

import java.sql.Timestamp;

import com.hieunt.hackathon_blog.entity.Comment;
import com.hieunt.hackathon_blog.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentRepository commentRepository;

    public void save(String content, int postId, int UserId){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(userService.findById(UserId));
        comment.setPost(postService.findById(postId));
        commentRepository.save(comment);
    }
}
