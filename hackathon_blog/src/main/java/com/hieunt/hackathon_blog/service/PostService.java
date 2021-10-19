package com.hieunt.hackathon_blog.service;

import javax.persistence.EntityManager;

import com.hieunt.hackathon_blog.entity.Post;
import com.hieunt.hackathon_blog.entity.User;
import com.hieunt.hackathon_blog.exception.ResourceNotFoundException;
import com.hieunt.hackathon_blog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserService userService;

    public Page<Post> findAll(int page) {
        return postRepo.findAll(PageRequest.of(page - 1, 8));
    }

    public Page<Post> findAllPostAndUser(int page, String sort, boolean isAsc) {
        if (sort == null) {
            sort = "id";
        } else if (sort.equals("poster")) {
            sort = "user.fullName";
        } else if (sort.equals("last-update")) {
            sort = "lastUpdate";
        }
        if (!isAsc) {
            return postRepo.findAllPostAndUser(PageRequest.of(page - 1, 10, Sort.by(sort).descending()));
        }
        return postRepo.findAllPostAndUser(PageRequest.of(page - 1, 10, Sort.by(sort)));
    }

    public Post findById(Integer id) {
        return postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found!!!"));
    }

    public void save(Post post, int userId) {
        if (post.getId() == null) {
            User user = userService.findById(userId);
            user.addPost(post);
            postRepo.save(post);
        } else {
            Post postUpdate = this.findById(post.getId());
            postUpdate.setTitle(post.getTitle());
            postUpdate.setContent(post.getContent());
            postUpdate.setTags(post.getTags());
            postRepo.save(postUpdate);
        }
    }

    public void deleteById(Integer id) {
        Post post = this.findById(id);
        postRepo.delete(post);
    }

    public Post findPostAndTagByPostId(int id){
        return postRepo.findPostAndTagByPostId(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found !!!"));
    }
}