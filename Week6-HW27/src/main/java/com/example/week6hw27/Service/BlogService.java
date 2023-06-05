package com.example.week6hw27.Service;

import com.example.week6hw27.Model.Blog;
import com.example.week6hw27.Model.MyUser;
import com.example.week6hw27.Repository.BlogRepository;
import com.example.week6hw27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MyUserRepository myUserRepository;
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    public void addBlog(Integer userId,Blog blog){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        if (myUser==null)
            throw new RuntimeException("id not found");
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId,Integer blogId, Blog blog){
        Blog old= blogRepository.findBlogById(blogId);
        MyUser myUser = myUserRepository.findMyUserById(userId);
        if (old==null || myUser==null)
            throw new RuntimeException("id not found");
        if (old.getMyUser().getId()!= myUser.getId())
            throw new RuntimeException("not authorized");
        old.setBody(blog.getBody());
        old.setTitle(blog.getTitle());
        blogRepository.save(old);
    }
    public void deleteBlog(Integer userId,Integer blogId){
        Blog blog=blogRepository.findBlogById(blogId);
        MyUser myUser=myUserRepository.findMyUserById(userId);
        if (blog==null||myUser==null)
            throw new RuntimeException("id not found");
        if (blog.getMyUser().getId()!=myUser.getId())
            throw new RuntimeException("not authorized");
        blogRepository.delete(blog);
    }
    public Blog getBlogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog==null)
            throw new RuntimeException("blog not found");
        return blog;
    }
}
