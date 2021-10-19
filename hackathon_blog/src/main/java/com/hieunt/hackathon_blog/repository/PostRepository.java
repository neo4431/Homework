package com.hieunt.hackathon_blog.repository;

import java.util.Optional;

import com.hieunt.hackathon_blog.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p, User u")
    Page<Post> findAllPostAndUser(Pageable pageable);

    @Query("select p from Post p, Tag t, Comment c where p.id = :id")
    Optional<Post> findPostAndTagByPostId(int id);
    // @Query(value = "select u.full_name, p.title, p.last_update from posts p inner join users u on p.user_id = u.id",nativeQuery = true)
    // List<PostUserDto> findAllDto(Pageable pageable);
}