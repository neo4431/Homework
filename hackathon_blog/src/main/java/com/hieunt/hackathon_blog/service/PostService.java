package com.hieunt.hackathon_blog.service;

import java.util.List;

import com.hieunt.hackathon_blog.entity.Post;
import com.hieunt.hackathon_blog.exception.ResourceNotFoundException;
import com.hieunt.hackathon_blog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepo;

    public List<Post> findAll(){
        return postRepo.findAll();
    }

    public Post findById(Integer id){
        return postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found!!!"));
    }

    public void save(Post post){
        if(post.getId() == null){
            postRepo.save(post);
        } else {
            Post postUpdate = this.findById(post.getId());
            postUpdate.setComments(post.getComments());
            postUpdate.setContent(post.getContent());
            postUpdate.setTags(post.getTags());
            postUpdate.setUser(post.getUser());
            postUpdate.setLastUpdate(post.getLastUpdate());
        }
        // 2021-03-24 16:34:26.666
    }

    public void deleteById(Integer id){
        Post post = this.findById(id);
        postRepo.delete(post);
    }
}