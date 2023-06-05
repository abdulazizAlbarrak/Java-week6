package com.example.week6hw27.Repository;

import com.example.week6hw27.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer blogId);
    Blog findBlogByTitle(String title);
    Set<Blog> findBlogsByMyUserId(Integer userId);
}
